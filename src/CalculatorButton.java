import java.awt.Dimension;
import java.awt.Font;
//
import javax.swing.JButton;

// extended JButton so that each one remembers which calculator operation it performs
public class CalculatorButton extends JButton
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtonOperation operation;

    public CalculatorButton(String text)
    {
        super(text);
        operation = ButtonOperation.Unknown;
    }

    public CalculatorButton(String text, Font font, Dimension size) 
    {
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
