
/**
 * @author Yaxuan Zhang
 * @version 12/07/2020
 * 
 *
 */
public class Pair {
    private int distance;
    private int preID;

    /**
     * Constructor
     * 
     * @param x
     *            distance
     * @param y
     *            previous node ID
     */
    public Pair(int x, int y) {
        distance = x;
        preID = y;
    }


    /**
     * getter method of the distance
     * 
     * @return distance
     */
    public int getDistance() {
        return distance;
    }


    /**
     * setter method of the distance
     * 
     * @param distance
     *                      distance 
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }


    /**
     * getter method of the ID
     * 
     * @return ID
     */
    public int getPreID() {
        return preID;
    }


    /**
     * setter method of the ID
     * 
     * @param preID
     *            ID value
     */
    public void setPreID(int preID) {
        this.preID = preID;
    }

}
