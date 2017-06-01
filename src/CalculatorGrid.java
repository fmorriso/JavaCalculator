import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by fpmor on 5/31/2017.
 */
public class CalculatorGrid extends JPanel
{
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
    private static final int BTN_HEIGHT = 30;
    private static final Dimension BTN_SIZE = new Dimension(BTN_WIDTH, BTN_HEIGHT);

    public CalculatorGrid()
    {
        this.setLayout(new GridLayout(0, 1));
        this.setPreferredSize(new Dimension(500, 350));

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
        GridLayout controlGridLayout = new GridLayout(0, 4);
        JPanel panel = new JPanel();
        panel.setLayout(controlGridLayout);
        //Dimension bottomSize = new Dimension(BTN_WIDTH * 4, BTN_HEIGHT / 10);
        //panel.setPreferredSize(bottomSize);
        //panel.setMaximumSize(bottomSize);

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(BTN_FONT);
        btnClear.setPreferredSize(BTN_SIZE);
        //btnClear.setMaximumSize(BTN_SIZE);
        panel.add(btnClear);

        JButton btnClearEntry = new JButton("CE");
        btnClearEntry.setFont(BTN_FONT);
        btnClearEntry.setPreferredSize(BTN_SIZE);
        //btnClearEntry.setMaximumSize(BTN_SIZE);
        panel.add(btnClearEntry);

        JButton btnSave = new JButton("Save");
        btnSave.setFont(BTN_FONT);
        btnSave.setPreferredSize(BTN_SIZE);
        //btnSave.setMaximumSize(BTN_SIZE);
        panel.add(btnSave );

        JButton btnExit = new JButton("Quit");
        btnExit.setFont(BTN_FONT);
        btnExit.setPreferredSize(BTN_SIZE);
        //btnExit.setMaximumSize(BTN_SIZE);
        btnExit.addActionListener(
                (ActionEvent e) -> System.exit(0)
        );
        panel.add(btnExit);

        return panel;
    }


}
