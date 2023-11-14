import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
//
import javax.swing.JFrame;

public class Main
{

	private static final double SCREEN_PERCENTAGE = 0.85;

	public static void main(String[] args)
	{
		System.out.format("java version: %s%n", System.getProperty("java.version"));

		// examine the current graphics environment
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gds = ge.getScreenDevices();
		System.out.format("gds.length = %d%n", gds.length);
		
		GraphicsDevice smallScreen = gds[0];
		GraphicsDevice bigScreen = null;
		if(gds.length > 1)
		{
			 bigScreen = gds[1];
		}
		bigScreen = null;
		
		Rectangle virtualBounds = new Rectangle();
		int n = 0;
		for (GraphicsDevice gd : gds)
		{
			System.out.format("device: %s%n", gd);
			GraphicsConfiguration[] gcs = gd.getConfigurations();

			for (GraphicsConfiguration gc : gcs)
			{
				Rectangle rect = gc.getBounds();
				int area = rect.height * rect.width;				
				virtualBounds = virtualBounds.union(gc.getBounds());
				System.out.format("configuration: %d, bounds: %s%n", n++, virtualBounds);				
			}
		}

		JFrame frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1, 1));
		
		Dimension size = SwingScreenUtilities.getScaledSize( SCREEN_PERCENTAGE, 100, true);
		if(bigScreen != null)
		{
			size = SwingScreenUtilities.getScaledSize(SCREEN_PERCENTAGE, 100, true);	
		}
		
		frame.setSize(size);
		frame.setPreferredSize(size);
		
		frame.getContentPane().add( new CalculatorGrid(frame) );
		frame.pack();
		frame.setLocationRelativeTo(null);
				
		if(bigScreen == null)
		{
			showOnScreen(smallScreen, frame);
		}
		else
		{
			showOnScreen(bigScreen, frame);	
		}
		
	
		frame.setVisible(true);
	}
	


	public static void showOnScreen(GraphicsDevice gd, JFrame frame)
	{
		// get screen size
		GraphicsConfiguration gc = gd.getDefaultConfiguration();		
		Rectangle bounds = gc.getBounds();		
		int width = bounds.width;
		int height = bounds.height;		
		System.out.format("Screen: %d width x %d height%n", width, height);
		
		// center JFrame
		Dimension size = frame.getSize();
		int x = ((width / 2) - (size.width / 2)) + bounds.x;
		int y = ((height / 2) - (size.height / 2)) + bounds.y;
		frame.setLocation(x, y);
	}
	
	public static void showOnScreen(int screen, JFrame frame ) 
	{
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gd = ge.getScreenDevices();
	    int width = 0, height = 0;
	    if( screen > -1 && screen < gd.length ) {
	        width = gd[screen].getDefaultConfiguration().getBounds().width;
	        height = gd[screen].getDefaultConfiguration().getBounds().height;
	        frame.setLocation(
	            ((width / 2) - (frame.getSize().width / 2)) + gd[screen].getDefaultConfiguration().getBounds().x, 
	            ((height / 2) - (frame.getSize().height / 2)) + gd[screen].getDefaultConfiguration().getBounds().y
	        );
	        // frame.setVisible(true);
	    } else {
	        throw new RuntimeException( "No Screens Found" );
	    }
	}
}
