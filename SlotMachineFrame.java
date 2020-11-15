import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class is used to contain the frame, the menu, the shapes, and the player options.
 * @author malik Tifah
 *
 */
public class SlotMachineFrame extends JFrame{
	/**
	 * Constructor function that calls on frmstats which creates the frame
	 */
	public SlotMachineFrame() {
		frmStats();
	}
	/**
	 * Instantiates and creates the main frame and menu
	 * Implements actionListeners for the menu options
	 * Calls functions that save and load files 
	 */
	public void frmStats() {
		
		//Adjusting the frame dimensions, name, default close operation, and the container
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int screenWidth = (int)dim.getWidth();
		int screenHeight = (int) dim.getHeight();
		Container c = getContentPane();
		setTitle("Vegas Baby Slot Machine");
		int frameWidth= 800;
		int frameHeight= 480;
		// The frame is centered in the middle of the screen
		int left = (screenWidth-frameWidth)/2;
		int top = (screenHeight-frameHeight)/2;
		setBounds(left,top,frameWidth,frameHeight);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		c.setLayout(new BorderLayout());
		
		//Center of the frame that contains the shapes
		TilePanel panCenter = new TilePanel();
		c.add(panCenter,BorderLayout.CENTER);
		
		// Initializing the bottom of the frame which contains text fields and buttons
		JPanel panSouth = new JPanel();
		JButton max = new JButton("Max");
		panSouth.add(max);
		JButton mid = new JButton("Mid");
		panSouth.add(mid);
		JButton min = new JButton("Mid");
		panSouth.add(min);
		JLabel dl = new JLabel("$");
		JTextField mny = new JTextField(10);
		panSouth.add(dl);
		panSouth.add(mny);
		c.add(panSouth, BorderLayout.SOUTH);
		
		// Allows the user to choose which file to open or close
		JFileChooser jfc = new JFileChooser();
		//Menu
		JMenuBar mbar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem loadT = new JMenuItem("Load Tiles");
		TileReader tr = new TileReader(); // Responsible for reading the information in the file the user opens
		//Activates once the user clicks the "load" option
		loadT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jfc.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
					ArrayList<Tiles> tilesRead = tr.read(jfc.getSelectedFile()); // Creates a new array containing the information read from the file
					if(tilesRead == null) { // If the user selects an invalid file type, or the file is not found in the specified directory
						JOptionPane.showMessageDialog(null, "Could not read the file" );
					}else { //Reads the file and redraws the center portion with the new shapes loaded from the file
						panCenter.setTiles(tilesRead);
						repaint();
					}
				}
			}
		});
		JMenuItem saveT = new JMenuItem("Save Tiles");
		saveT.addActionListener(new ActionListener() { // Activated once the User selects "save tiles"
		
			TileWriter tw = new TileWriter(); // Responsible for writing the current tiles into an external file
			public void actionPerformed(ActionEvent e) {
				if(jfc.showSaveDialog(null)== JFileChooser.APPROVE_OPTION){ 
					if(tw.write(jfc.getSelectedFile(),panCenter.getTiles() )) { // Opens the prompt for the user to save their file
						JOptionPane.showMessageDialog(null, "Tiles have been Saved"); // Save is successful
					}
					else {
						JOptionPane.showMessageDialog(null, "An exception has occured");
					}
				}
			}

		});
		JMenuItem print = new JMenuItem("Print"); //Not working at the moment
		JMenuItem rst = new JMenuItem("Restart"); // Not working at the moment
		JMenuItem ext = new JMenuItem("Exit"); // Allows the user to exit the program
		ext.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// Adding all of the menu options under file
		file.add(loadT);
		file.add(saveT);
		file.add(print);
		file.add(rst);
		file.add(ext);
		mbar.add(file);
		
		JMenu help = new JMenu("Help");
		JMenuItem abt = new JMenuItem("About");
		abt.addActionListener(new ActionListener(){ // Activated once the user selects help
			public void actionPerformed(ActionEvent e) {
				// Opens my github link
				JOptionPane.showMessageDialog(null, "Made by Malik Tifah \nGithub Link: https://github.com/MalikGB/Tifah_Malik_cpsc24500");
			}
		});
		help.add(abt);
		mbar.add(help);
		setJMenuBar(mbar);
	}
}

