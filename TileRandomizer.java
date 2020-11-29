import java.util.ArrayList;
/**
 * This is a controller class  
 * its responsbile for using the tiles class to generate shape and color values
 * @author malik
 *
 */
public class TileRandomizer {
	 private Tiles tl;
	 private TilePanel tp;
	 private ArrayList<Tiles> tls;
	 
	 /**
	  * Constructor function that creates an arrayList of Tiles, generates values for 4 tile objects, and stores them in an array List
	  */
	public TileRandomizer() {
		// Generates a new set if colors and shape types, and stores them in the shapes array
		tls = new ArrayList<Tiles>();
		for (int i=0; i<4; i++) {
			tl= new Tiles();
			tl.setRandomly();
			tls.add(tl);
		}
	}
	/**
	 * Used to get the randomly generated tiles
	 * @return The newly generated arrayList of tiles
	 */
	public ArrayList<Tiles> getRandomTiles(){
		return tls;
	}
	/**
	 * Gets the current color value used by the tl object
	 * @return integer stored in tl
	 */
	public int getRandomColor() {
		return tl.getColors();
	}
	/**
	 * Gets the current shape value used by the tl objecct
	 * @return integer stored in tl
	 */
	public int getRandomShape() {
		return tl.getShapes();
	}
}

