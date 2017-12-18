package by.bsu.zakharchenya.lab;

import by.bsu.zakharchenya.lab.enity.LAttribute;
import by.bsu.zakharchenya.lab.enity.LClass;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class LStart implements ActionListener {

    private JScrollPane masterComponent = null;
    private JTextPane textArea = new JTextPane();
    private ArrayList<LClass> classes;
    private int attributeCount;
    private HashMap<String, Integer> averageScores;
    private HashMap<Integer, String> values;


    public LStart() {
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyledDocument doc = textArea.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        textArea.setEditable(false);
        masterComponent = new JScrollPane(textArea);

        FileInputStream fisR = null;
        FileInputStream fisA = null;
        try {
            fisR = new FileInputStream(Run.pathToRules);
            fisA = new FileInputStream("data/aa.txt");
            initData(fisR);
            initA(fisA);
        } catch (IOException e) {
            writeLine(e.getMessage());
        } finally {
            try {
                if (fisR != null) {
                    fisR.close();
                }
            } catch (IOException e) {
                writeLine(e.getMessage());
            }
            try {
                if (fisA != null) {
                    fisA.close();
                }
            } catch (IOException e) {
                writeLine(e.getMessage());
            }
        }
    }

    public ArrayList<LClass> initData(FileInputStream fis) throws IOException {
        classes = new ArrayList<>();
        Scanner sc = new Scanner(fis, StandardCharsets.UTF_8.name());
        attributeCount = Integer.parseInt(sc.nextLine());
        while (sc.hasNext()) {
            String header = sc.nextLine();
            String[] headers = header.split(" ");
            String idString = headers[0];
            int id = Integer.parseInt(idString.trim());
            String name = headers[1];
            int n = Integer.parseInt(headers[2].trim());
            ArrayList<LAttribute> attributes = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int v[] = new int[attributeCount];
                String[] vv = sc.nextLine().split(" ");
                for (int j = 0; j < attributeCount; j++) {
                    v[j] = Integer.parseInt(vv[j]);
                }
                attributes.add(new LAttribute(v));
            }
            classes.add(new LClass(name, id, attributes));
        }
        return classes;
    }

    public void initA(FileInputStream fis) throws IOException {
        values = new HashMap<>();
        averageScores = new HashMap<>();
        Scanner sc = new Scanner(fis, StandardCharsets.UTF_8.name());
        int i = 0;
        while (sc.hasNext()) {
            String[] headers = sc.nextLine().split(" ");
            String name = headers[0];
            int val = Integer.parseInt(headers[1].trim());

            values.put(i, name);
            averageScores.put(name, val);
            i++;
        }
    }

    JComponent getMasterComponent() {
        return masterComponent;
    }

    void writeLine(final String line) {
        SwingUtilities.invokeLater(() -> textArea.setText(textArea.getText() + line + "\n"));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        textArea.setText("");
        Map<String, Integer> input = new HashMap<>();
        for (int i = 0; i < attributeCount; i++) {
            Integer val = Integer.parseInt((String) JOptionPane.showInputDialog(getMasterComponent(), "Choose " + values.get(i) + " (" + averageScores.get(values.get(i)) + "):",
                    "Choose value for " + values.get(i) + ":", JOptionPane.QUESTION_MESSAGE, null, null, null));
            if (val == null) {
                writeLine("You cancelled input!");
                return;
            }
            input.put(values.get(i), val);
            writeLine("You entered " + val + " for '" + values.get(i) + "'. Average is: " + averageScores.get(values.get(i)));
        }
        writeLine("-----------------------------------------------------");
        writeLine("       Analyzing is in progress...");
        Recognizing recognizing = new Recognizing(classes, averageScores, values, this);
        String answer = recognizing.recognize(input);
        writeLine("-----------------------------------------------------");
        writeLine("        FINISHED");
        writeLine("");
        writeLine("Answer is: " + answer);


    }
}
