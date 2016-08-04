import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;


public class Driver extends JFrame {

	/** Panel containing the Koch snowflake */
	private KochPanel panel;
	/** Timer used to control animation */
	private Timer timer;
	
	/**
	 * Driver constructor
	 * 
	 * Acts as a frame which contains the KochPanel
	 * This also handles the animation using a timer
	 */
	public Driver(){
		// Set title of the frame
		super("Snowflake");
		// Set the size of the frame
		this.setSize(1000, 600);
		// Exit on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// Initialize and add panel
		panel = new KochPanel();
		add(panel);
		
		// Set visible to true
		this.setVisible(true);
		
		// Create ActionListener to animate panel according to timer
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.animateStep();
			}
		};
		// Initialize and start the timer
		int interval = 200;
		timer = new Timer(interval, listener);
		timer.start();
	}
	
	
	/**
	 * Main method: creating the Driver frame
	 * 
	 * @param args Not used
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Driver frame = new Driver();
	}
}
