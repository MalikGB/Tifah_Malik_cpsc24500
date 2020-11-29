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

// Things you need to work on:
// Printing the current arrangement of tiles when the user chooses the print button
// Javadoc comments
/**
 * This class is used to contain the frame, the menu, the shapes, and the player options.
 * @author malik Tifah
 *
 */
public class SlotMachineFrame extends JFrame{
	private double playerBalance =5.00;
	TilePanel panCenter;
	private double playerBet; 
	
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
		setTitle("Vegas Baby Slot Machine");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int screenWidth = (int)dim.getWidth();
		int screenHeight = (int) dim.getHeight();
		int frameWidth= 800;
		int frameHeight= 480;
		// The frame is centered in the middle of the screen
		int left = (screenWidth-frameWidth)/2;
		int top = (screenHeight-frameHeight)/2;
		setBounds(left,top,frameWidth,frameHeight);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Center of the frame that contains the shapes
		panCenter = new TilePanel();
		c.add(panCenter,BorderLayout.CENTER);
		
		// Initializing the bottom of the frame which contains text fields and buttons
		JPanel panSouth = new JPanel();
		
		JTextField mny = new JTextField(5);
		JButton max = new JButton("Max");
		max.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playerBalance>0.009) { // This is used to prevent the player from betting if they have a balance less than 0.01
					TileRandomizer tr = new TileRandomizer();
					panCenter.setTiles(tr.getRandomTiles()); //Pushes the generated tile objects, into the tile Panel class
					repaint();
					TileChecker tc= new TileChecker(); // Checks for winning combinations
					tc.numOfMatches(panCenter.getTiles());
					 if(tc.getTotalMatches()) {
						playerBalance*=100;
					}else if(tc.getColorMatches()) {
						playerBalance*=25;
					}
					else {
						playerBalance = 0;
					}
					mny.setText(String.format("%.2f",playerBalance));
					if(playerBalance <=0.009) {
						 JOptionPane.showMessageDialog(null, "You have gone bankrupt :(");
					 }
				}
			}
		});
		panSouth.add(max);
		JButton mid = new JButton("Mid");
		mid.addActionListener(new ActionListener(){ // Activated once the player hits the mid button
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playerBalance>0.009) {
					playerBet = playerBalance/2; 
					playerBalance-= playerBet;
					TileRandomizer tr = new TileRandomizer();
					panCenter.setTiles(tr.getRandomTiles());
					repaint();
					TileChecker tc= new TileChecker();
					tc.numOfMatches(panCenter.getTiles());
					 if(tc.getTotalMatches()) {
						playerBalance+=playerBet*50;
					}else if(tc.getColorMatches()) {
						playerBalance+=playerBet*10;
					}
					mny.setText(String.format("%.2f",playerBalance));
					if(playerBalance <=0.009) {
						playerBalance=0;
						mny.setText(String.format("%.2f",playerBalance));
						JOptionPane.showMessageDialog(null, "You have gone bankrupt :(");
					 }
				}
			}
		});
		panSouth.add(mid);
		JButton min = new JButton("Min");
		min.addActionListener(new ActionListener() { // Activated once the player hits the min button
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playerBalance>0.009) {
					playerBet = playerBalance/10.00; 
					playerBalance-= playerBet;
					TileRandomizer tr = new TileRandomizer();
					panCenter.setTiles(tr.getRandomTiles());
					repaint();
					TileChecker tc= new TileChecker();
					tc.numOfMatches(panCenter.getTiles());
					 if(tc.getTotalMatches()) {
						playerBalance += playerBet*10;
					}else if(tc.getColorMatches()) {
						playerBalance += playerBet*5;
					}
					 mny.setText(String.format("%.2f",playerBalance));
					 if(playerBalance <=0.009) {
						 playerBalance = 0;
						 mny.setText(String.format("%.2f",playerBalance));
						 JOptionPane.showMessageDialog(null, "You have gone bankrupt :(");
					 }
				}
			}
		});
		panSouth.add(min);
		JLabel dl = new JLabel("$");
		
		panSouth.add(dl);
		panSouth.add(mny);
		c.add(panSouth, BorderLayout.SOUTH);
		
		// Player balance
		mny.setText(String.format("%.2f",playerBalance));
		
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
		JMenuItem print = new JMenuItem("Print");
		print.addActionListener(new ActionListener() { // Activates once the user selets the print option. It reads the tiles on screen and prints their color and shape
			public void actionPerformed(ActionEvent e) {
				String[] colorTypes = {"Blue","Yellow","Red","Green","Orange"};
				String[] shapeTypes = {"Rectangle","Circle"};
				System.out.print("Current tiles: ");
				for(int i=0; i<4; i++) {
					System.out.print(colorTypes[panCenter.getColorValues(i)-1]+ " ");
					System.out.print(shapeTypes[panCenter.getShapeValues(i)-1]);
					if(i!=3) {
						System.out.print(", ");
					}
				}
				System.out.println();
			}
		});
		JMenuItem rst = new JMenuItem("Restart");
		rst.addActionListener(new ActionListener() {
			// Resets the player's balance, and allows them to play the game again
			public void actionPerformed(ActionEvent e) { 
				JOptionPane.showMessageDialog(null, "The game has been reset");
				playerBalance = 5.00;
				mny.setText(String.format("%.2f",playerBalance));
			}
		});
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