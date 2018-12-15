import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Holds onto a main panel and controls application sizing and exiting
 * 
 * @author William
 *
 */
public class MainFrame extends JFrame {

	public static Dimension FRAMEDIM = new Dimension(800, 800);

	private MainPanel mp;

	public MainFrame() {
		// application settings
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setPreferredSize(FRAMEDIM);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// adding the mainpanel for display
		mp = new MainPanel();
		this.add(mp);
		this.pack();
	}

}
