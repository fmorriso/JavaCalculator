import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
//
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

// @SuppressWarnings("serial")
/**
 * 
 * @author Fred Morrison
 * @implNote extends JPanel for the purposes of using a Grid Layout
 */
public class CalculatorGrid extends JPanel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Rows of buttons, arranged left-to-right in each row
    // as a two-dimensional array
    private static final String[][] BUTTON_TEXTS = {

            {"7", "8", "9", "+"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "*"},
            {"0", ".", "/", "="},

    };
    private static Font BTN_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 32);
    private static int BTN_WIDTH = 20;
    private static int BTN_HEIGHT = 40;
    private static Dimension BTN_SIZE;

    private JLabel resultsLabel;
    private JLabel inputLabel;
    private Dimension panelSize;

    private double currentInput = 0;
    //private double result = 0;
    private ButtonOperation lastOperation = ButtonOperation.Unknown;
    private Calculator calculator;

    public CalculatorGrid(JFrame frame)
    {
        this.setLayout(new GridLayout(0, 1));
        panelSize = frame.getSize();
        this.setPreferredSize(panelSize);

        BTN_HEIGHT = panelSize.height / 10;
        BTN_WIDTH = BTN_HEIGHT / 2;
        BTN_SIZE = new Dimension(BTN_WIDTH, BTN_HEIGHT);
        System.out.format("Button size=%s%n", BTN_SIZE);

        // scale button font to fit the button size
        int buttonFontSize = (int) ( BTN_HEIGHT / 3 );
        BTN_FONT = new Font(Font.SANS_SERIF, Font.BOLD, buttonFontSize);

        this.add(generateInputDisplayPanel());
        this.add(generateResultsPanel());
        this.add(generateCalculatorButtonPanel());
        this.add(generateControlPanel());

        calculator = new Calculator();
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

    // determine if the specified string is an integer number
    private static boolean isInteger(String text)
    {
        try
        {
            @SuppressWarnings("unused")
            int i = Integer.parseInt(text.trim());
        } catch (NumberFormatException ex)
        {
            return false;
        }
        return true;
    }

    // generate the calculator buttons sub-panel
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

                CalculatorButton button = new CalculatorButton(text, BTN_FONT, BTN_SIZE);

                // If the button is a number, they can share a common click handler
                if (isInteger(text))
                {
                    button.addActionListener((ActionEvent e) -> numberButtonClick(button));
                } else
                {
                    // for non-number buttons, generate an appropriate click handler
                    generateMathOperationButtonClickHandler(button);
                }

                panel.add(button);
            }
        }
        return panel;
    }

    private void generateMathOperationButtonClickHandler(CalculatorButton button)
    {
        String text = button.getText();
        switch (text)
        {
            case "+":
                button.addActionListener((ActionEvent e) -> mathOperationClick(button));
                button.setOperation(ButtonOperation.Add);
                break;

            case "-":
                button.addActionListener((ActionEvent e) -> mathOperationClick(button));
                button.setOperation(ButtonOperation.Subtract);
                break;

            case "*":
                button.addActionListener((ActionEvent e) -> mathOperationClick(button));
                button.setOperation(ButtonOperation.Multiply);
                break;

            case "/":
                button.addActionListener((ActionEvent e) -> mathOperationClick(button));
                button.setOperation(ButtonOperation.Divide);
                break;

            case ".":
                button.addActionListener((ActionEvent e) -> decimalPointButtonClick(button));
                button.setOperation(ButtonOperation.DecimalPoint);
                break;

            case "=":
                button.addActionListener((ActionEvent e) -> equalButtonClick(button));
                button.setOperation(ButtonOperation.Equal);
                break;
        }
    }

    // convert a double to a string with two decimal places (like money)
    private String convertDoubleToMoneyString(double value)
    {
        return String.format("%.2f", value);
    }

    private void equalButtonClick(CalculatorButton button)
    {
        // get the last input
        getLastInput();

        appendToResults(" " + convertDoubleToMoneyString(currentInput));

        // capture a copy of the "formula" before we replace it with the results
        String formula = resultsLabel.getText().trim() + " =";

        // handle the last input based on the last operation performed
        performLastOperation();

        // display the results instead of the formula
        resultsLabel.setText(convertDoubleToMoneyString(calculator.getResult()));

        // show the formula where the input used to be
        inputLabel.setText(formula);
    }

    private void getLastInput()
    {
        // get the input area as a number
        String input = inputLabel.getText(); // ERROR: this may have a mix of numbers and operations at this point
        System.out.println("input=" + input + ", result=" + calculator.getResult());
        currentInput = Double.parseDouble(input);
        System.out.println("currentInput=" + currentInput + ", result=" + calculator.getResult());
    }

    // If there is a pending last operation, perform it.
    // For the special case of first-time, we simply capture the input as the current result
    private void performLastOperation()
    {
        switch (lastOperation)
        {
            case Add:
                // result += currentInput;
                calculator.setResult(calculator.add( calculator.getResult(), currentInput ));
                break;

            case Subtract:
                // result -= currentInput;
                calculator.setResult(calculator.subtract(calculator.getResult(), currentInput));
                break;

            case Multiply:
                // result *= currentInput;
                calculator.setResult(calculator.multiply(calculator.getResult(), currentInput));
                break;

            case Divide:
                // result /= currentInput;
                calculator.setResult(calculator.divide(calculator.getResult(), currentInput));
                break;

            case DecimalPoint:
            case Equal:
            case Clear:
                break;

            case Unknown:
                //result = currentInput;
                calculator.setResult(currentInput);
                break;
        }
    }

    private void decimalPointButtonClick(CalculatorButton button)
    {
        appendToInput(button.getText());
    }

    // generic method that works for +, -, * and /
    private void mathOperationClick(CalculatorButton button)
    {
        // Get the last input value
        getLastInput();

        // perform any previous operation that might be pending
        performLastOperation();

        // display as a formula in the results area
        appendToResults(" " + convertDoubleToMoneyString(currentInput) + " " + button.getText());

        // clear the input area
        clearInput();

        // remember what was the last operation so that when the = button is clicked, we can properly handle the last input
        lastOperation = button.getOperation();
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
        GridLayout gridLayout = new GridLayout(0, 2);
        JPanel panel = new JPanel();
        panel.setLayout(gridLayout);

        CalculatorButton btnClear = new CalculatorButton("Clear", BTN_FONT, BTN_SIZE);
        btnClear.addActionListener((ActionEvent e) -> clearButtonClick(e));
        panel.add(btnClear);

        CalculatorButton btnExit = new CalculatorButton("Quit", BTN_FONT, BTN_SIZE);
        btnExit.addActionListener(
                (ActionEvent e) -> System.exit(0)
        );
        panel.add(btnExit);

        return panel;
    }

    private void clearButtonClick(ActionEvent e)
    {
        lastOperation = ButtonOperation.Unknown;
        // result = 0;
        calculator.setResult(0);
        currentInput = 0;
        inputLabel.setText("");
        resultsLabel.setText("");
    }

    private void numberButtonClick(CalculatorButton button)
    {
        appendToInput(button.getText());
    }

    private void appendToInput(String text)
    {
        String labelText = inputLabel.getText();
        labelText += text;
        inputLabel.setText(labelText);
    }

}
