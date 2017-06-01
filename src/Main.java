import javax.swing.*;
import java.awt.*;

public class Main
{

    public static void main(String[] args)
    {
        // make size decisions based on the current screen size
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int xSize = (int) screenSize.getWidth();
        int ySize = (int) screenSize.getHeight();

        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 1));
        Dimension size = new Dimension(xSize / 3, ySize * 2 / 3);
        frame.setSize(size);
        frame.setPreferredSize(size);
        frame.getContentPane().add(new CalculatorGrid(frame));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
