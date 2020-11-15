import java.io.Serializable;
import java.util.Random;

/**
 * Class contains:
 * Data format for each tile object
 * Get and set functions for the colors and shapes of each tile
 * Function that generates a random color and shape value
 * @author malik
 *
 */
public class Tiles implements Serializable{
	private int  color;
	private int shape;
	
	/**
	 * Constructor function that instantiates the color and shape values
	 */
	public Tiles() {
		color =1;
		shape =1;
	}
	/**
	 * Constructor function that sets different color and shape values
	 * @param c Color
	 * @param s Shape
	 */
	public Tiles(int c, int s) {
		setColors(c);
		setShapes(s);
	}
	
	/**
	 * Generates a random color and shape value in a specified range
	 */
	public void setRandomly() {
		Random rnd = new Random();
		setColors(rnd.nextInt(5)+1); // Generates a number from 1 - 5, which corresponds to a specific color
		setShapes(rnd.nextInt(2)+1); // Generates a number from 1 - 2, which corresponds to a specific shape
	}
	/**
	 * Gets the shape value
	 * @return Current shape value
	 */
	public int getShapes() {
		return shape;
	}
	/**
	 * Sets the shape value
	 * @param sh Shape value passed into the function call
	 */
	public void setShapes(int sh) {
		shape = sh;
	}
	/**
	 * Gets the color value
	 * @return current color value
	 */
	public int getColors() {
		return color;
	}
	/**
	 * Sets the color value
	 * @param Color value passed into the function call
	 */
	public void setColors(int col) {
		color = col;
		
	}
	/**
	 * Override tostring function that formats the class objects to be printed in a specific way
	 */
	@Override
	public String toString() {
		return String.format("%d %d",color,shape);
	}
}
