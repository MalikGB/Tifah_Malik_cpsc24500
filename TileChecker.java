import java.util.ArrayList;
/**
 * This class is used to check for winning combinations of tiles, after the player has selected a wager
 * @author malik
 *
 */
public class TileChecker {
	private int numOfColorMatches;
	private int numOfTotalMatches;
	private ArrayList<String>colors;
	/**
	 * Default constructor that instantiates the types of winning combinations, and an arrayList of the Tile colors on screen
	 */
	public TileChecker() {
		numOfTotalMatches = 0;
		numOfColorMatches = 0;
		colors = new ArrayList<String>();
	}
	/**
	 * Checks to see if the user has a winning combination of colors
	 * @return True if the user has a winning combination, false if they don't
	 */
	public boolean getColorMatches() {
		if(numOfColorMatches ==4) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Checks to see if the user has a winning combination of shapes
	 * @return True if the user has a winning combination, false if they don't
	 */
	public boolean getTotalMatches() {
		if(numOfTotalMatches==4) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Function that checks for the number of matches the user has
	 * Goes through each tile object, reads its values, and compares it to the values of the subsequent tiles
	 * Each matching tile value = + 1 more match
	 * @param tiles ArrayList that contains the tiles currently on screen
	 */
	public void numOfMatches(ArrayList<Tiles> tiles) {
		// This section goes through the arrayList, isolates the color value from each tile,
		//and stores them in a separate arrayList for comparison
		String[] parts;
		for(int i =0; i<tiles.size();i++) {
			String line;
			line = tiles.get(i).toString();
			parts= line.split(" ");
			colors.add(parts[0]); // parts[0] corresponds to the color of the tile
		}
		
		// Checks the tile, and color array list to check for matching values
		for(int i =0; i<tiles.size(); i++) {
			for(int j =0; j<3-i; j++) {
				if(numOfTotalMatches<4 && numOfColorMatches<4) { // Once there are four matches in either tile or color, we know the user got them all
					if(tiles.get(i).toString().contains((tiles.get(j+i+1)).toString())) { // Compares each tile, to each subsequent tile for matches
						numOfTotalMatches+=1;
					}
					  if(colors.get(i).equals(colors.get(j+i+1))) { // Compares each color value, and each subsequent color value for matches
						numOfColorMatches+=1;
					 }
				}
			}
		}
	}
}
