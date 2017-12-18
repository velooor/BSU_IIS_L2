package by.bsu.zakharchenya.lab;

/**
 * Created by Lagarde on 18.12.2017.
 */
public class Recognizing extends Training {

    public void recognize(int[] vector) {
        double a[][] = a_it;
        double m_k[] = new double[classCount];

        for (int i = 0; i < classCount; i++) {
            double m_x[] = new double[classes.get(i).getAttributes().size()];
            for (int t = 0; t < classes.get(i).getAttributes().size(); t++) {
                m_x[t] = m(vector, classes.get(i).getAttributes().get(t), i, a);
            }
            m_k[i] = max(m_x);
        }
        determine_position(m_k);
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
        return Math.max(0, res1/res2);
    }

    private double max(double [] a){
        double max = 0;
        if(a.length != 0) max = a[0];
        else return 0;
        for(int i = 1; i < a.length; i++){
            if(a[i] > max) max = a[i];
        }
        return max;
    }

    private double determine_position(double []m_k){
        return 0;
    }
}
