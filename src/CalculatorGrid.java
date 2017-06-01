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
    private static final Font BTN_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 24);

    public CalculatorGrid()
    {
        this.setLayout(new GridLayout(0, 1));

        final int numRows = BUTTON_TEXTS.length;
        final int numCols = BUTTON_TEXTS[0].length;

        GridLayout btnGrid = new GridLayout(numRows, numCols);
        JPanel btnPanel = new JPanel(btnGrid);

        for (int row = 0; row < numRows; row++)
        {
            for(int col = 0; col < numCols; col++)
            {
                JButton btn = new JButton(BUTTON_TEXTS[row][col]);
                btn.setFont(BTN_FONT);
                btnPanel.add(btn);
            }
        }


        this.add(btnPanel);

        GridLayout bottomGrid = new GridLayout(1, 2);
        JPanel bottomPanel = new JPanel(bottomGrid);

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(BTN_FONT);
        bottomPanel.add(btnClear);

        JButton btnExit = new JButton("Quit");
        btnExit.setFont(BTN_FONT);
        btnExit.addActionListener(
                (ActionEvent e) -> System.exit(0)
        );
        bottomPanel.add(btnExit);

        this.add(bottomPanel);


    }


}
