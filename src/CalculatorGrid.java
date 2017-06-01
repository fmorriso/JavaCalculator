import javax.swing.*;
import javax.swing.border.Border;
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

    private JLabel resultsLabel;
    private JLabel inputLabel;

    private double currentInput = 0;
    private double result = 0;


    public CalculatorGrid()
    {
        this.setLayout(new GridLayout(0, 1));
        this.setPreferredSize(new Dimension(500, 350));

        this.add(generateInputDisplayPanel());
        this.add(generateResultsPanel());
        this.add(generateCalculatorButtonPanel());
        this.add(generateControlPanel());
    }

    private JPanel generateInputDisplayPanel()
    {
        GridLayout gridLayout = new GridLayout(0, 1);
        JPanel panel = new JPanel(gridLayout);
        inputLabel = new JLabel("");
        inputLabel.setFont(BTN_FONT);
        inputLabel.setPreferredSize(new Dimension(BTN_WIDTH * 4, BTN_HEIGHT));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        inputLabel.setBorder(border);
        panel.add(inputLabel);
        return panel;
    }

    private JPanel generateResultsPanel()
    {
        GridLayout gridLayout = new GridLayout(0, 1);
        JPanel panel = new JPanel(gridLayout);
        resultsLabel = new JLabel("");
        resultsLabel.setFont(BTN_FONT);
        resultsLabel.setPreferredSize(new Dimension(BTN_WIDTH * 4, BTN_HEIGHT));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
        resultsLabel.setBorder(border);
        panel.add(resultsLabel);
        return panel;
    }

    private static boolean isInteger(String text)
    {
        try
        {
            int i = Integer.parseInt(text);
        } catch (NumberFormatException ex)
        {
            return false;
        }
        return true;
    }

    private JPanel generateCalculatorButtonPanel()
    {
        final int numRows = BUTTON_TEXTS.length;
        final int numCols = BUTTON_TEXTS[0].length;

        GridLayout gridLayout = new GridLayout(numRows, numCols);
        JPanel panel = new JPanel();
        panel.setLayout(gridLayout);

        for (int row = 0; row < numRows; row++)
        {
            for (int col = 0; col < numCols; col++)
            {
                String text = BUTTON_TEXTS[row][col];

                JButton button = new JButton(text);
                button.setFont(BTN_FONT);
                button.setPreferredSize(BTN_SIZE);
                // If the button is a number, they can share a common click handler
                if (isInteger(text))
                {
                    button.addActionListener((ActionEvent e) -> numberButtonClick(button));
                } else
                {
                    // for non-number buttons, generate an appropriate click handler
                    generateControlButtonClickHandler(button);
                }

                panel.add(button);
            }
        }
        return panel;
    }

    private void generateControlButtonClickHandler(JButton button)
    {
        String text = button.getText();
        switch (text)
        {
            case "+":
                button.addActionListener((ActionEvent e) -> addButtonClick(button));
                break;

            case "-":
                button.addActionListener((ActionEvent e) -> subtractButtonClick(button));
                break;

            case "*":
                button.addActionListener((ActionEvent e) -> multiplyButtonClick(button));
                break;

            case "/":
                button.addActionListener((ActionEvent e) -> divideButtonClick(button));
                break;

            case ".":
                button.addActionListener((ActionEvent e) -> decimalPointButtonClick(button));
                break;

            case "=":
                button.addActionListener((ActionEvent e) -> equalButtonClick(button));
                break;
        }
    }

    private void equalButtonClick(JButton button)
    {
    }

    private void decimalPointButtonClick(JButton button)
    {
        appendToInput(button.getText());
    }

    private void divideButtonClick(JButton button)
    {
        //appendToInput(button.getText());
    }

    private void multiplyButtonClick(JButton button)
    {
        //appendToInput(button.getText());
    }

    private void subtractButtonClick(JButton button)
    {
        //appendToInput(button.getText());
    }

    private void addButtonClick(JButton button)
    {
        // get the input area as a number
        String input = inputLabel.getText();
        currentInput = Double.parseDouble(input);

        // add it to the result
        result += currentInput;

        // display as a formula in the results area
        appendToResults(" " + input + " " + button.getText());

        // clear the input area
        clearInput();
    }

    private void appendToResults(String text)
    {
        String labelText = resultsLabel.getText();
        labelText += text;
        resultsLabel.setText(labelText.trim());
    }

    private void clearInput()
    {
        inputLabel.setText("");
    }

    private JPanel generateControlPanel()
    {
        GridLayout gridLayout = new GridLayout(0, 4);
        JPanel panel = new JPanel();
        panel.setLayout(gridLayout);
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
        panel.add(btnSave);

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

    private void numberButtonClick(JButton button)
    {
/*
        String text = inputLabel.getText();
        text += Integer.parseInt(button.getText());
        inputLabel.setText(text);
*/
        appendToInput(button.getText());
    }

    private void appendToInput(String text)
    {
        String labelText = inputLabel.getText();
        labelText += text;
        inputLabel.setText(labelText);
    }

}
