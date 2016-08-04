import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class KochPanel extends JPanel {
	/** One Koch snow flake  */
	private Koch flake;
	private Koch coolFlake;
	private Koch smallflake;
	
	/**
	 * Constructor
	 * Sets the background color of the panel, creates a new Koch snowflake,
	 * and sets the change in the angle, which in part controls the animation speed
	 */
	public KochPanel(){
		this.setBackground(new Color(0,0,30));
		this.setDoubleBuffered(true);
		// Initialize flakes
		flake = new Koch(500.0, 300.0, 0.0, 75.0, 5.0, new Color(155,205,255));
		// Set the change in angle
		flake.setAngleDifference(0.1);
		
		coolFlake = new Koch(500.0, 300.0, 0.0, 100.0, 100.0, new Color(155,205,255));
		coolFlake.setAngleDifference(.2);
		
		smallflake = new Koch(500.0, 300.0, 0.0, 25.0, 2, new Color(155,205,255));
		smallflake.setAngleDifference(-.1);
	}
	
	/**
	 * Take one step in the animation sequence and repaint
	 * 
	 */
	public void animateStep(){
		flake.animateStep();
		smallflake.animateStep();
		
		// resets the size and scale of the snowflake 
		if(coolFlake.getMinSize() < 10.0){
			coolFlake.setMinSize(100);
			coolFlake.setScale(100);
		}else
			// each step resets the size of flake and makes it rotate
			coolFlake.setMinSize(coolFlake.getMinSize() * 1.0/3.0);
			coolFlake.setScale(coolFlake.getScale() + coolFlake.getScale() * .5);
			coolFlake.animateStep();
		
		repaint();
	}
	
	/**
	 * Render the snowflake
	 */
	protected void paintComponent(Graphics g){
		// Call the parent's paintComponent
		super.paintComponent(g);
		// Draw the snowflake
		flake.draw(g);
		coolFlake.draw(g);
		smallflake.draw(g);
		
	}
}
