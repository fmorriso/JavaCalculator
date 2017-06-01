import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by fpmor on 5/31/2017.
 */
public class CalculatorGrid extends JPanel
{
    //private final int numRows = 0; // allow unlimited number of rows
    //private final int numCols = 4;
    // Rows of buttons, arranged left-to-right in each row
    // as a two-dimensional array
    private static final String[][] BUTTON_TEXTS = {
            {"7", "8", "9", "+"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "*"},
            {"0", ".", "/", "="}
    };
    private static final Font BTN_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 32);
    private static final int BTN_WIDTH = 20;
    private static final int BTN_HEIGHT = 40;
    private static final Dimension BTN_SIZE = new Dimension(BTN_WIDTH, BTN_HEIGHT);

    public CalculatorGrid()
    {
        this.setLayout(new GridLayout(0, 1));


        this.add(generateCalculatorButtonPanel());
        this.add(generateControlPanel());
    }

    private JPanel generateCalculatorButtonPanel()
    {
        final int numRows = BUTTON_TEXTS.length;
        final int numCols = BUTTON_TEXTS[0].length;

        GridLayout btnGrid = new GridLayout(numRows, numCols);
        JPanel panel = new JPanel();
        panel.setLayout(btnGrid);

        for (int row = 0; row < numRows; row++)
        {
            for(int col = 0; col < numCols; col++)
            {
                JButton btn = new JButton(BUTTON_TEXTS[row][col]);
                btn.setFont(BTN_FONT);
                btn.setPreferredSize(BTN_SIZE);
                panel.add(btn);
            }
        }
        return panel;
    }

    private JPanel generateControlPanel()
    {
        GridLayout controlGridLayout = new GridLayout(1, 4, 0, 10);
        JPanel panel = new JPanel();
        panel.setLayout(controlGridLayout);
        Dimension bottomSize = new Dimension(BTN_WIDTH * 2, BTN_HEIGHT / 4);
        panel.setPreferredSize(bottomSize);
        panel.setMaximumSize(bottomSize);

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(BTN_FONT);
        btnClear.setPreferredSize(bottomSize);
        btnClear.setMaximumSize(bottomSize);
        panel.add(btnClear);

        JButton btnExit = new JButton("Quit");
        btnExit.setFont(BTN_FONT);
        btnExit.setPreferredSize(bottomSize);
        btnExit.setMaximumSize(bottomSize);
        btnExit.addActionListener(
                (ActionEvent e) -> System.exit(0)
        );
        panel.add(btnExit);

        return panel;
    }


}
