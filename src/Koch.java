import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Koch {
	/** Center position: x */
	private double x;
	/** Center position: y */
	private double y;
	/** Angle in radians of the first leg */
	private double angle;
	/** Leg length.  */
	private double scale;
	/** Minimum leg length.  */
	private double minSize;
	/** Color to draw in.  */
	private Color color;
	boolean isFirst = true;
	
	/** Number of radians to change the angle by for each animation step.  */
	private double angleDifference;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param angle
	 * @param scale
	 * @param minSize
	 * @param color
	 */
	public Koch(double x, double y, double angle, double scale, double minSize, Color color) {
		// initialize class variables
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.scale = scale;
		this.minSize = minSize;
		this.color = color;
	}
	
	/**
	 * Draw one Koch line segment.
	 * 
	 * @param g Graphics object
	 * @param x Starting X
	 * @param y Starting Y
	 * @param angle Angle of the line
	 * @param scale Length of the line
	 */
	private void drawLine(Graphics g, double x, double y, double angle, double scale){
		if(scale < minSize){
			g.drawLine((int) x, (int) y, (int) (x + scale * Math.cos(angle)), (int) (y + scale * Math.sin(angle)));
			
			return;
		}
		
		// Recursive case has 4 subcases
		// Segment #1
		drawLine(g, x, y, angle, scale * 1.0/3.0);
		
		// Segment #2
		double newX = x + 1.0/3.0 * scale * Math.cos(angle);
		double newY = y + 1.0/3.0 * scale * Math.sin(angle);
		double newAngle = angle - Math.PI/3.0;
		drawLine(g, newX, newY, newAngle, scale * 1.0/3.0);
		
		// Segment #3
		newX += 1.0/3.0 * scale * Math.cos(newAngle);
		newY += 1.0/3.0 * scale * Math.sin(newAngle);
		newAngle += 2.0 * Math.PI/3.0;
		drawLine(g, newX, newY, newAngle, scale * 1.0/3.0);
		
		// Segment #4
		newX += 1.0/3.0 * scale * Math.cos(newAngle);
		newY += 1.0/3.0 * scale * Math.sin(newAngle);
		newAngle -= Math.PI/3.0;
		drawLine(g, newX, newY, newAngle, scale * 1.0/3.0);
	}
	
	/**
	 * Draw a Koch snowflake
	 * 
	 * @param g Graphics object
	 */
	public void draw(Graphics g){
		double newX;
		double newY;
		double newAngle;
		
		// Set the color of the snowflake
		g.setColor(color);

		// Translate from center
		double dX = -scale / 2.0;
		double dY = scale * Math.tan(Math.PI/6.0) / 2.0;	
		
		// Three sides of the initial triangle
		// Side 1
		newX = x + dX * Math.cos(angle) + dY * Math.sin(angle);
		newY = y + dX * Math.sin(angle) - dY * Math.cos(angle);
		newAngle = angle;
		drawLine(g, newX, newY, newAngle, scale);

		// Side 2
		newX += scale * Math.cos(newAngle);
		newY += scale * Math.sin(newAngle);
		newAngle += 2 * Math.PI / 3.0;
		drawLine(g, newX, newY, newAngle, scale);
		

		// Side 3
		newX += scale * Math.cos(newAngle);
		newY += scale * Math.sin(newAngle);
		newAngle += 2 * Math.PI / 3.0;
		drawLine(g, newX, newY, newAngle, scale);

	}
	
	/**
	 * Set the amount that the angle changes at each animation step 
	 * 
	 * @param angleDifference Change per animation step
	 */
	public void setAngleDifference(double angleDifference) {
		this.angleDifference = angleDifference;
	}
	
	/**
	 * Take one step in the animation sequence.  
	 */
	public void animateStep(){
		this.angle += angleDifference;
	}
	/**
	 * Changes the minSize to allow different stages of snowflake development
	 * 
	 * @param minSize
	 */
	public void setMinSize(double minSize){
		this.minSize = minSize;
	}
	/**
	 * Gets minSize of this object
	 */
	public double getMinSize(){
		return minSize;
	}
	/**
	 * Sets the scale of the object
	 */
	public void setScale(double scale){
		this.scale = scale;
	}
	/**
	 * Gets the scale of the object
	 */
	public double getScale(){
		return scale;
	}
}
