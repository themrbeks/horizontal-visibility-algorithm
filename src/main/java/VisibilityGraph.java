import edu.uci.ics.jung.graph.UndirectedSparseGraph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stjepan on 24/12/14.
 */
public class VisibilityGraph extends UndirectedSparseGraph {

    public ArrayList<Node> listOfNodes = new ArrayList<Node>();

    public VisibilityGraph (double[] timeSeries) {
        this.addAllNodes(timeSeries);
        this.connectLineOfSightNodes(timeSeries);
    }

    public void exportToCSV (String fileName) throws IOException {

        String stringToWrite = "Source,Destination \n";

        FileWriter fStream = new FileWriter(fileName);
        BufferedWriter out = new BufferedWriter(fStream);

        List<Edge> listOfEdges = new ArrayList<Edge>(this.getEdges());

        for (Edge edgeItem : listOfEdges) {
            List<Node> incidentNodes = new ArrayList<Node>(this.getIncidentVertices(edgeItem));
            stringToWrite = stringToWrite + "Node" + incidentNodes.get(0).ID + "," + "Node" + incidentNodes.get(1).ID + "\n";
        }

        out.write(stringToWrite);
        out.close();
        fStream.close();
    }

    public void addNode (Node node) {
        this.addVertex(node);
        this.listOfNodes.add(node);
    }

    private void addAllNodes(double [] timeSeries) {
        for (int timeSignature = 0; timeSignature < timeSeries.length; timeSignature++) {
            this.addNode(new Node(timeSeries[timeSignature], timeSignature));
        }
    }

    private void connectLineOfSightNodes(double[] timeSeries) {

        for (int ta = 0; ta < listOfNodes.size(); ta++) {
            for (int tb = ta+1; tb < listOfNodes.size(); tb++) {
                boolean isVisible = true;
                for (int tc = ta+1; tc < tb; tc++) {
                    if (isInTheWay(ta, timeSeries[ta], tb, timeSeries[tb], tc, timeSeries[tc])) {
                        isVisible = false;
                        break;
                    }
                }
                if (isVisible){
                    this.addEdge(new Edge(), this.getNodeWithTimeSignature(ta), this.getNodeWithTimeSignature(tb));
                }
            }
        }
    }

    private Node getNodeWithTimeSignature (int timeSignature) {
        for (Node nodeItem : this.listOfNodes) {
            if (nodeItem.timeSignature == timeSignature) {
                return nodeItem;
            }
        }
        return null;
    }
    public static boolean isInTheWay (int timeSignatureOfPointA, double valueOfPointA, int timeSignatureOfPointB, double valueOfPointB, int timeSignatureOfPointC, double valueOfPointC) {
        return valueOfPointC>valueOfPointB + (valueOfPointA - valueOfPointB)*(timeSignatureOfPointB-timeSignatureOfPointC)/(timeSignatureOfPointB-timeSignatureOfPointA);
    }
}
