package by.bsu.zakharchenya.lab.enity;

/**
 * Created by Lagarde on 18.12.2017.
 */
public class LAttribute {
    private int[] vector;

    public LAttribute(int[] vectors) {
        this.vector = vectors;
    }

    public int[] getVector() {
        return vector;
    }

    public void setVector(int[] vector) {
        this.vector = vector;
    }
}
