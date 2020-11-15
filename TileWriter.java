import java.io.File;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * This class is responsible for writing the information about the current shapes into a external file
 * @author malik
 *
 */
public class TileWriter {
	
	/**
	 * Creates the file the user saves the tiles to
	 * @param fname Name of the file
	 * @param shapes ArrayList of the shapes the user will save
	 * @return True if the is a supported file type and was written successfully, false if an exception or problem occured
	 */
	public boolean write (String fname, ArrayList<Tiles> shapes) {
		File f = new File(fname); // File object
		return write(f,shapes);
	}
	/**
	 * Checks if the user entered a valid file type when saving the file
	 * @param f File the information is stored in
	 * @param shapes Arraylist of the shapes the user will save
	 * @return True if the information was written successfully to the desired file type, false if they entered an invalid file type or an exception occured
	 */
	public boolean write(File f, ArrayList<Tiles> shapes) {
		String fname = f.getName().toUpperCase(); // Converts the file's name to uppercase in order to compare its file type
		if(fname.endsWith(".TXT")) {
			return writeToText(f,shapes);
		}
		if(fname.endsWith(".XML")) {
			return writeToXML(f,shapes);
		}
		if(fname.endsWith(".BIN")) {
			return writeToBinary(f,shapes);
		}
		return false;
	}
	/**
	 * Writes the information into a .txt file
	 * @param f File being created
	 * @param shapes ArrayList of the shapes the user wants to save
	 * @return true if the information is written sucessfully, false if an exception occurs
	 */
	public boolean writeToText(File f, ArrayList<Tiles>shapes) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f))); // pw allows data to be written to a text file
			for(int i =0; i<shapes.size(); i++) {
				pw.println(shapes.get(i));	
			}
			pw.close();
			return true;
		}catch(Exception ex) {
			return false; // Data was not written successfully 
		}
	}
	/**
	 * Writes the shapes to a binary (.bin) file
	 * @param f File the information will be saved to
	 * @param shapes ArrayList of the shapes the user will save
	 * @return True if the information was written to the .bin file successfully, false if an exception occurred
	 */
	public boolean writeToBinary(File f, ArrayList<Tiles> shapes) {
		try {
			ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(f)); //oos is allows data to be written to a binary file 
			oos.writeObject(shapes);
			oos.close();
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	/**
	 * Writes the shapes to a XML (.xml) file
	 * @param f File the information will be saved to
	 * @param shapes ArrayList of the shapes the user will save
	 * @return True if the information was written to the .xml file successfully, false if an exception occurred
	 */
	public boolean writeToXML(File f, ArrayList<Tiles> shapes) {
		try {
			XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f))); // enc allows the data to be written to a xml file
			enc.writeObject(shapes);
			enc.close();
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
}
