package by.bsu.zakharchenya.lab;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Lagarde on 18.12.2017.
 */
public class Recognizing extends Training {

    private Map<String, Integer> averageScores;
    private Map<Integer, String> values;

    public Recognizing(ArrayList<LClass> classes, Map<String, Integer> averageScores, Map<Integer, String> values) {
        super(classes, averageScores.size());
        this.averageScores = averageScores;
        this.values = values;
    }

    public String recognize(Map<String, Integer> input) {
        int[] vector = vectorGenerator(input);
        double a[][] = a_it;
        double m_k[] = new double[classCount];

        for (int i = 0; i < classCount; i++) {
            double m_x[] = new double[classes.get(i).getAttributes().size()];
            for (int t = 0; t < classes.get(i).getAttributes().size(); t++) {
                m_x[t] = m(vector, classes.get(i).getAttributes().get(t), i, a);
            }
            m_k[i] = max(m_x);
        }
        return classes.get(maxI(m_k)).getName();
    }

    public int[] vectorGenerator(Map<String, Integer> input) {
        int[] vector = new int[attributeCount];
        for(int i = 0; i < attributeCount; i++){
            vector[i] = input.get(values.get(i)) > averageScores.get(values.get(i)) ? 1 : 0;
        }
        return vector;
    }

    private int t(double a, double b) {
        return a == b ? 2 : 1;
    }

    private double m(int[] x, LAttribute attr, int i, double[][] a) {
        double res1 = 0;
        double res2 = 0;
        for (int j = 0; j < attributeCount; j++) {
            res1 += Math.pow(-1, t(x[j], attr.getVector()[j])) * a[i][j];
            res2 += a[i][j];
        }
        return Math.max(0, res1 / res2);
    }

    private double max(double[] a) {
        double max = 0;
        if (a.length != 0) max = a[0];
        else return 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) max = a[i];
        }
        return max;
    }

    private int maxI(double[] a) {
        double max = 0;
        int resI = 0;
        if (a.length != 0) max = a[0];
        else return 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
                resI = i;
            }
        }
        return resI;
    }
}
