import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	public static Dimension FRAMEDIM = new Dimension(800, 800);
	
	private MainPanel mp;
	
	public MainFrame() {
		
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setPreferredSize(FRAMEDIM);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		mp = new MainPanel();
		this.add(mp);
		this.pack();
	}

}
