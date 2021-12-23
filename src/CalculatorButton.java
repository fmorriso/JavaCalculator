import javax.swing.*;
import java.awt.*;

// extended JButton  so that each one remembers which calculator operation it performs
public class CalculatorButton extends JButton
{
    private ButtonOperation operation;

    public CalculatorButton(String text)
    {
        super(text);
        operation = ButtonOperation.Unknown;
    }

    public CalculatorButton(String text, Font font, Dimension size) {
        super(text);
        this.setFont(font);
        this.setSize(size);
        this.setPreferredSize(size);
    }

    public void setOperation(ButtonOperation operation)
    {
        this.operation = operation;
    }

    public ButtonOperation getOperation()
    {
        return this.operation;
    }

}
