import javax.swing.*;
import java.awt.*;

public class Main {

	public static void main(String[] args) {
		// make size decisions based on the current screen size
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();

		// Define the size of the JFrame as a rectangle that is a percentage of the
		// available screen area and a multiple of 100.
		final int frameHeight = (int) (screenSize.height * 75.00 / 100) / 100 * 100;
		final int frameWidth = ((int) (frameHeight * 3.0 / 4)) / 100 * 100;

		JFrame frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1, 1));

		Dimension size = new Dimension(frameWidth, frameHeight);
		frame.setSize(size);
		frame.setPreferredSize(size);

		frame.getContentPane().add(new CalculatorGrid(frame));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
