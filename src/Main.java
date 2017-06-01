import javax.swing.*;
import java.awt.*;

public class Main
{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 1));
        Dimension size = new Dimension(700, 900);
        frame.setSize(size);
        frame.setPreferredSize(size);
        frame.getContentPane().add(new CalculatorGrid(frame));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
