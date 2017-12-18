package by.bsu.zakharchenya;

import by.bsu.zakharchenya.lab.LClass;
import by.bsu.zakharchenya.lab.Recognizing;
import org.junit.*;

import java.util.*;

public class LRecTest extends Assert {
    private static Map<String, Integer> averageScores = new HashMap<String, Integer>();
    private static ArrayList<LClass> classes = new ArrayList<LClass>();
    private static Map<Integer, String> values = new HashMap<Integer, String>();

    @BeforeClass
    public static void init() {
        averageScores.put("pac", 62);
        averageScores.put("sho", 71);
        averageScores.put("pas", 75);
        averageScores.put("dri", 79);
        averageScores.put("def", 56);
        averageScores.put("phy", 70);

        int[][] attrs = {   {1, 1, 0, 1, 0, 1},
                            {1, 1, 1, 1, 0, 1},
                            {1, 1, 0, 0, 0, 1},
                            {1, 1, 1, 0, 0, 1}};
        classes.add(new LClass("forward", attrs));
        int[][] attrss = {  {1, 1, 1, 1, 1, 0},
                            {0, 1, 1, 0, 1, 0},
                            {0, 0, 1, 0, 1, 1},
                            {1, 1, 1, 1, 1, 1},
                            {1, 0, 1, 0, 1, 1}};
        classes.add(new LClass("midfielder", attrss));
        int[][] attrsss = { {1, 0, 0, 0, 1, 1},
                            {0, 0, 0, 0, 1, 1},
                            {1, 0, 1, 1, 1, 1},
                            {1, 0, 1, 1, 1, 0}};
        classes.add(new LClass("defender", attrsss));

        values.put(0, "pac");
        values.put(1, "sho");
        values.put(2, "pas");
        values.put(3, "dri");
        values.put(4, "def");
        values.put(5, "phy");
    }

    @Test
    public void CaseTest1() {
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put("pac", 62);
        input.put("sho", 71);
        input.put("pas", 75);
        input.put("dri", 79);
        input.put("def", 57);
        input.put("phy", 71);

        Recognizing test = new Recognizing(classes, averageScores, values);


        assertEquals("defender",test.recognize(input));
    }

    @Test
    public void CaseTest2() {
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put("pac", 62);
        input.put("sho", 71);
        input.put("pas", 75);
        input.put("dri", 79);
        input.put("def", 56);
        input.put("phy", 70);

        Recognizing test = new Recognizing(classes, averageScores, values);

        test.recognize(input);
        assertEquals("defender",test.recognize(input));
    }

    @Test
    public void CaseTest3() {
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put("pac", 63);
        input.put("sho", 73);
        input.put("pas", 79);
        input.put("dri", 89);
        input.put("def", 59);
        input.put("phy", 79);

        Recognizing test = new Recognizing(classes, averageScores, values);

        test.recognize(input);
        assertEquals("midfielder",test.recognize(input));
    }

    @Test
    public void CaseTest4() {
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put("pac", 63);
        input.put("sho", 73);
        input.put("pas", 79);
        input.put("dri", 89);
        input.put("def", 39);
        input.put("phy", 79);

        Recognizing test = new Recognizing(classes, averageScores, values);

        test.recognize(input);
        assertEquals("forward",test.recognize(input));
    }
}
