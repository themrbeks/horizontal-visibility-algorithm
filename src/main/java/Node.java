/**
 * Created by Stjepan on 21/12/14.
 */
public class Node {

    public static int IDcounter = 0;

    public double value;
    public int timeSignature;
    public int ID;

    public Node (double value, int timeSignature) {
        this.value = value;
        this.timeSignature = timeSignature;
        this.ID = IDcounter++;
    }

}
