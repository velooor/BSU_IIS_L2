package by.bsu.zakharchenya.lab;

import by.bsu.zakharchenya.lab.enity.LClass;

import java.util.ArrayList;

/**
 * Created by Lagarde on 17.12.2017.
 */
public class Training {
    protected int classCount;
    protected int attributeCount;

    protected ArrayList<LClass> classes;

    protected double b_it[][];
    protected double b_t[];
    protected double a_it[][];

    public Training(){
        a_it = new double[classCount][attributeCount];
        b_it = new double[classCount][attributeCount];
        b_t = new double[attributeCount];
    }

    public Training(ArrayList<LClass> classes, int attributeCount) {
        this.classes = classes;
        this.classCount = classes.size();
        this.attributeCount = attributeCount;
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

    public double[] getB_t() {
        return b_t;
    }

    public double[][] getA_it() {
        return a_it;
    }

    public ArrayList<LClass> getClasses() {
        return classes;
    }
}
