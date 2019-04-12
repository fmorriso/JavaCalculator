import javax.swing.*;

// extended JButton  so that each one remembers which calculator operation it performs
@SuppressWarnings("serial")
public class CalculatorButton extends JButton
{
    private ButtonOperation operation;

    public CalculatorButton()
    {
        super();
        operation = ButtonOperation.Unknown;
    }

    public CalculatorButton(Icon icon)
    {
        super(icon);
        operation = ButtonOperation.Unknown;
    }

    public CalculatorButton(String text)
    {
        super(text);
        operation = ButtonOperation.Unknown;
    }

    public CalculatorButton(Action a)
    {
        super(a);
        operation = ButtonOperation.Unknown;
    }

    public CalculatorButton(String text, Icon icon)
    {
        super(text, icon);
        operation = ButtonOperation.Unknown;
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
