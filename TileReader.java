import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Responsible for opening and reading a supported file type the user enters
 * @author malik
 *
 */
public class TileReader {
	/**
	 * Function that creates the file object that contains the file the user wants to open
	 * @param fname name of the file the user wants to open
	 * @return the information stored in the file (if its supported)
	 */
	public ArrayList<Tiles> read(String fname){
		File f = new File(fname); // File object
		return read(f);
	}
	/**
	 * Checks if the file the user wants to open is of a supported file type
	 * Reads the file, and calls on different functions based on the file type
	 * @param f File the user wants to open
	 * @return The data read from the file
	 */
	public ArrayList<Tiles> read(File f) {
		String fname = f.getName().toUpperCase();
		if(fname.endsWith(".TXT")) {
			return readFromText(f);
		}
		if(fname.endsWith(".BIN")) {
			return readFromBinary(f);
		}
		if(fname.endsWith(".XML")) {
			return readFromXML(f);
		}
		return null; // File not found, or there is an unsupported file format
	}
	/**
	 * Reads the file if it is in a .xml format
	 * @param f File the user wants to open
	 * @return the information stored in the file
	 */
	public ArrayList<Tiles> readFromXML(File f) {
		try {
			ArrayList<Tiles> tilesRead;
			XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)));
			tilesRead = (ArrayList<Tiles>) dec.readObject(); // Casts the data read from the xml decoder into an ArrayList of tiles
			dec.close();
			return tilesRead;
		}catch(Exception ex) {
			return null; // File was not read successfully
		}
	}
	/**
	 * Reads the file if it is in a .bin format
	 * @param f File the user wants to open
	 * @return the information stored in the file
	 */
	public ArrayList<Tiles> readFromBinary(File f) {
		try {
			ArrayList<Tiles> tilesRead;
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			tilesRead = (ArrayList<Tiles>) ois.readObject(); // Casts the data read from the object input stream into an ArrayList of tiles
			ois.close();
			return tilesRead;
		}catch(Exception ex){
			return null;
		}
	}
	/**
	 * Reads the file if it is in a .txt format
	 * Goes through each line of the file, splits the information based on the spaces between the numbers, and stores each part in an array
	 * @param f File the user wants to open
	 * @return the information stored in the file
	 */
	private ArrayList<Tiles> readFromText(File f) {
		try {
		String line;
		String[] parts;
		ArrayList<Tiles> tilesRead = new ArrayList<Tiles>();
		Scanner fsc = new Scanner(f);
		int color,shape;
		Tiles tile;
		while(fsc.hasNextLine()) {
			line = fsc.nextLine().trim();
			if(line.length()>0) {
				parts = line.split(" ");
				color= Integer.parseInt(parts[0]);
				shape = Integer.parseInt(parts[1]);
				tile = new Tiles(color,shape);
				tilesRead.add(tile);
			}
		}
		fsc.close();
		return tilesRead;
		}catch(Exception ex) {
			return null;
		}
		
	}
}
