package by.bsu.zakharchenya.lab;

import javax.swing.*;
import java.awt.*;

public class Run {

    public static String pathToRules = "data/rr.txt";
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { e.printStackTrace(); }
            initFrameDim(frame);
            LStart LStartAction = new LStart();
            initApplication(frame, LStartAction, WindowConstants.EXIT_ON_CLOSE);
        });
    }

    private static void initFrameDim(JFrame frame) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(400, (int) (screen.getHeight() * .5));
        frame.setLocation(screen.width/2 - frame.getSize().width/2, screen.height/2 - frame.getSize().height/2);
        frame.setResizable(true);
    }

    private static void initApplication(JFrame frame, LStart LStartAction, final int onCloseOperation) {
        frame.setTitle("BSU IIS, L2");
        frame.setDefaultCloseOperation(onCloseOperation);
        frame.getContentPane().add(LStartAction.getMasterComponent(), BorderLayout.CENTER);
        JPanel panel = new JPanel();
        JButton start = new JButton("Start");

        start.addActionListener(LStartAction);
        panel.add(start);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}