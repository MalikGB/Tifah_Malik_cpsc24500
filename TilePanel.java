import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This class contains:
 * The shapes types and positions that are printed to the center of the screen
 * The paint component function, that prints the shapes to the screen
 * The mouse listener that checks if the user has clicked one of the shapes
 * @author malik
 *
 */
public class TilePanel extends JPanel implements MouseListener{
	private int x1;
	private int y=150;
	private int x2 =250;
	private int x3=450;
	private int x4=650;
	private int radX= 100;
	private int radY = 100;
	private ArrayList<Tiles> shapes; // Contains an arraylist of Tile objects, which contains the shape and color of each tile
	Tiles ti; // Default constructor object with no parameters
	Tiles to; //Default constructor object with the parameters
	private int tilegenMode; // Checks if a new set of tiles can be drawn (prevents new tiles being drawn when the frame is resized, or changed)
	
	/**
	 * Default constructor that:
	 * Activates the mouse listener that listens to the TilePanel class
	 * Instantiates the arrayList called shapes which contains an array of Tile objects
	 * Sets tilegenMode to 0 (draw a new set of tiles)
	 */
	public TilePanel() {
		addMouseListener(this);
		tilegenMode = 0;
		shapes = new ArrayList<Tiles>(); // Format: color shapeType
		x1 =50; 
		y=150;
		x2 =250;
		x3=450;
		x4=650;
		radX= 100;
		radY = 100;
	}
	/**
	 * Get function for the ArrayList of tiles
	 * @return the array of tile objects
	 */
	public ArrayList<Tiles> getTiles() {
		return shapes;
	}
	/**
	 * Set function for the arrayList of tiles
	 * @param Tile The array list of tiles passed into the function call
	 */
	public void setTiles(ArrayList<Tiles> Tile) {
		shapes = Tile;
	}
	/**
	 * Paint component function, which includes:
	 * The position, size, and shape type for each tile
	 * Calls upon the setRandomly function in the Tiles class, which generates new color and shape values
	 * Stores the color and shape information into the shapes arrayList
	 * Draws the shapes onto the center section of the frame
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ti = new Tiles(); // Calling on the Tiles constructor function
		int index =0; // Used when reading data from the shapes arrayList
		
		// Shapes position and size
		int[][]shapePos = {{x1,y,radX,radY},{x2,y,radX,radY},{x3,y,radX,radY},{x4,y,radX,radY}};
		
		// Generates a new set if colors and shape types, and stores them in the shapes array
		if(tilegenMode ==0) {
			for(int i=0; i<4;i++) {
				ti.setRandomly();
				to = new Tiles(ti.getColors(),ti.getShapes());
				shapes.add(to);
			}
		}	
			// Goes through all of the objects stored in shapes, and generates shapes based on the information
			for(Tiles tiles: shapes) {
				String parts[];
				String line;
				line = shapes.get(index).toString();
				parts = line.split(" ");
				//Reads the color portion of the tile object, and sets the paint component to a specific color
				switch(Integer.parseInt(parts[0])){
					case(1):
						g.setColor(Color.BLUE);
						break;
					case(2):
						g.setColor(Color.YELLOW);
						break;
					case(3):
						g.setColor(Color.RED);
						break;
					case(4):
						g.setColor(Color.GREEN);
						break;
					case(5):
						g.setColor(Color.ORANGE);
						break;
				}
				//Reads the shape portion of the tile object, and sets draws the shape that corresponds to the integer
				switch(Integer.parseInt(parts[1])){
					case(1):
						g.fillRect(shapePos[index][0], shapePos[index][1], shapePos[index][2], shapePos[index][3]);
						break;
					case(2):
						g.fillOval(shapePos[index][0], shapePos[index][1], shapePos[index][2], shapePos[index][3]);	
				}
				index++;
			}
		tilegenMode=1; // Makes it so a new set of shapes cannot be drawn
	}
	/**
	 * This function is responsible for checking if the user has clicked on one of the shapes
	 * If they do, it replaces the current shape on screen with a new shape
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		ti.setRandomly();
		to = new Tiles(ti.getColors(),ti.getShapes());
		if(e.getX()>=x1 && e.getX()<=x1+radX && e.getY()>=y && e.getY()<=y+radY) {
			shapes.set(0,to); // Replaces the Tile object's information at the 0th index
			repaint();
		}else if(e.getX()>=x2 && e.getX()<=x2+radX && e.getY()>=y && e.getY()<=y+radY) {
			shapes.set(1,to);
			repaint();
		}else if(e.getX()>=x3 && e.getX()<=x3+radX && e.getY()>=y && e.getY()<=y+radY) {
			shapes.set(2,to);
			repaint();
		}else if(e.getX()>=x4 && e.getX()<=x4+radX && e.getY()>=y && e.getY()<=y+radY) {
			shapes.set(3,to);
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	

}
