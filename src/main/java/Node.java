/**
 * Created by Stjepan on 21/12/14.
 */
public class Node {

    public static int IDcounter = 0;

    public double value;
    public int ID;

    public Node (double value) {
        this.value = value;
        this.ID = IDcounter++;
    }

}
