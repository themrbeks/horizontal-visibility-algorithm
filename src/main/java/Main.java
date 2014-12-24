import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Stjepan on 24/12/14.
 */
public class Main {

    public static void main (String... args) throws IOException {
        int sizeOfTimeSeries = 1000;
        double[] timeSeries = new double[sizeOfTimeSeries];
        Random randomGenerator = new Random();

        for (int i = 0; i < sizeOfTimeSeries; i++) {
            timeSeries[i] = randomGenerator.nextDouble();
        }

        VisibilityGraph graph = new VisibilityGraph(timeSeries);
        graph.exportToCSV("randomVisibilityGraph.csv");

    }
}
