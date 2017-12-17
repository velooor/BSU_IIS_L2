package by.bsu.zakharchenya.lab;

import java.util.ArrayList;

/**
 * Created by Lagarde on 17.12.2017.
 */
public class Training {
    private static int classCount = 3;
    private static int attributeCount = 6;

    private double b_it[][];
    private double b_t[];
    private double a_it[][];

    public Training(ArrayList<LClass> classes) {
        this.classes = classes;
        a_it = new double[classCount][attributeCount];
        b_it = new double[classCount][attributeCount];
        b_t = new double[attributeCount];

        for (int i = 0; i < classCount; i++) {
            int m = classes.get(i).getAttributes().size();
            double res = 0;

            for (int t = 0; t < attributeCount; t++) {
                for (int j = 0; j < m; j++) {
                    res += classes.get(i).getAttributes().get(j).getVector()[t];
                }
                b_it[i][t] = res / m;
            }
        }
        for (int t = 0; t < attributeCount; t++) {
            double res = 0;
            for (int i = 0; i < classCount; i++) {
                res += b_it[i][t];
            }
            b_t[t] = res / classCount;
        }
        for (int i = 0; i < classCount; i++) {
            for (int t = 0; t < attributeCount; t++) {
                a_it[i][t] = Math.abs(b_it[i][t] - b_t[t]);
            }
        }
    }

    public double[][] getB_it() {
        return b_it;
    }

    public void setB_it(double[][] b_it) {
        b_it = b_it;
    }

    public double[] getB_t() {
        return b_t;
    }

    public void setB_t(double[] b_t) {
        b_t = b_t;
    }

    public double[][] getA_it() {
        return a_it;
    }

    public void setA_it(double[][] a_it) {
        a_it = a_it;
    }

    private ArrayList<LClass> classes;


    public ArrayList<LClass> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<LClass> classes) {
        this.classes = classes;
    }

}
