import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PumpkinMaker {

	/**
	 * This function is used to create a new frame, and make it visible
	 * @param args argument of main function
	 */
	public static void main(String[] args) {
		
		FrameMaker frm = new FrameMaker();
		frm.setVisible(true);
	}
}
/**
 * This class contains the functions and variables used to manipulate the different parts of the pumpkin
 * @author malik
 *
 */
class PumpkinPanel extends JPanel{
	private int width;
	private int height;
	private int left;
	private int top;
	private int eyeType;
	private int noseType;
	private int mouthType;
	//Ratio that determines how much smaller a face part is in comparison to the head
	private int faceRatioH; 
	private int faceRatioL;
	private double totalHeight; // Edge of the top of the frame to pumpkin + pumpkin's height
	private double totalWidth; // Edge of the left of the frame to pumpkin + pumpkin's height
	// Multipliers that help determine the position of the components of the face relative to the pumpkin
	private double multiplierW;
	private double multiplierH;
	
	public PumpkinPanel(){
		width =100;
		height = 100;
		left=200;
		top = 100;
		faceRatioH= height/8;
		faceRatioL = width/8;
		totalHeight = height+top;
		totalWidth=width+left;
		eyeType=1;
		mouthType=1;
		noseType=2;
	}
	/**
	 * This function is used to change the width of the pumpkin
	 * @param len The length that the user wants the pumpkin to be
	 */
	public void setWidth(int len) {
		// Put in place in case the user enters a negative length
		if(len<=0) {
			len=1;
		}
		//Updates the width
		else {
			width = len;
			faceRatioL = len/8;
		}
	}
	/**
	 * Used to retrieve the current width of the pumpkin
	 * @return current width
	 */
	public int getWidths() {
		return width;
	}
	/**
	 * This function is used to change the height of the pumpkin
	 * @param tall Height the user wants the pumpkin to be
	 */
	public void setHeight(int tall) {
		// Put in place the user enters a negative integer for the height
		if(tall<=0 ||tall>=800) {
			tall=1;
		}
		// Updates the height
		else {
			height = tall;
			faceRatioH=tall/8;
		}	
	}
	/**
	 * Retrieves the current height
	 * @return current height
	 */
	public int getHeights() {
		return height;
	}
	
	/**
	 * Sets the pumpkins position relative to the left edge of the frame
	 * @param lPos Horizontal position of the pumpkin
	 */
	public void setLeft(int lPos) {
		// In case the user enters a negative number
		if(lPos<=0|| lPos>=800) {
			left =1;
		}
		// Updates left position
		else {
			left = lPos;
		}
	}
	
	/**
	 * Returns the horizontal position of the function
	 * @return horizontal position
	 */
	public int getLeft() {
		return left;
	}
	/**
	 * Sets the pumpkins vertical position relative to the top edge of the frame
	 * @param tPos Vertical position of the pumpkin
	 */
	public void setTop(int tPos) {
		// In case the user enters a value that would put the pumpkin outside of the frame
		if(tPos<=0 || tPos>1000) {
			tPos =1;
		}
		// Updates the vertical position
		top = tPos;
	}
	/**
	 * This function returns the top position of the function
	 * @return top position
	 */
	public int getTop() {
		return top;
	}
	/**
	 * This function is used to set up the type of eyes displayed on the pumpkin
	 * Matches the different eye types with integers that are used by other functions
	 * @param eye Type of eye the user wants
	 */
	public void setEyeType(String eye) {
		if(eye.equalsIgnoreCase("C")) {
			eyeType = 1;
		}else if(eye.equalsIgnoreCase("S")) {
			eyeType=2;
		}else if(eye.equalsIgnoreCase("T")) {
			eyeType = 3;
		}
		// Default case where the user enters an invalid input
		else {
			eyeType =1;
		}
			
	}
	/**
	 * Returns the current eye type
	 * @return current eye type
	 */
	public int getEyeType() {
	 return eyeType;
	}
	/**
	 * This function sets the type of nose that is displayed on the pumpkin
	 * Matches the different nose types of integers that are used by other functions
	 * @param nose User's desired nose type
	 */
	public void setNoseType(String nose) {
		if(nose.equalsIgnoreCase("C")) {
			noseType = 1;
		}else if(nose.equalsIgnoreCase("S")) {
			noseType=2;
		}else if(nose.equalsIgnoreCase("T")) {
			noseType = 3;
		}
		// In case the user inputs an invalid nose type
		else {
			noseType =1;
		}	
	}
	/**
	 * Returns the current nose type
	 * @return current nose type
	 */
	public int getNoseType() {
		return noseType;
	}
	/**
	 * This function sets the type of mouth that will be displayed on the pumpkin
	 * @param mouth The mouth type the user enters
	 */
	public void setMouthType(String mouth) {
		if(mouth.equalsIgnoreCase("O")) {
			mouthType = 1;
		}else if(mouth.equalsIgnoreCase("R")) {
			mouthType=2;
		}
		// Default case where the user inputs an invalid mouth type
		else {
			mouthType =1;
		}
	}
	
	/**
	 * Returns the current mouth type
	 * @return current mouth type
	 */
	public int getMouthType() {
		return mouthType;
	}
	
	/**
	 * This function helps draw the various components of the pumpkin
	 * Overridden from the JFrame class
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		// Recalculating these variables whenever a new frame needs to be drawn
		multiplierH = (double)height/100;
		multiplierW = (double)width/100;
		totalHeight = height+top;
		totalWidth=width+left;
		
		// Arrays that stores the coordinates of each component of the pumpkin's face (used when calculating where to draw the different shapes)
		// This set applies when drawing face components that are triangles (contains either the X or Y coordinates used to draw the triangle)
		int triangleNX[] = {(int)(Math.abs(totalWidth)-58*(multiplierW)),(int) Math.abs((totalWidth)-38*(multiplierW)), (int)Math.abs((totalWidth)-48*(multiplierW))}; // Nose X position 
		int triangleNY[] = {(int)(Math.abs(totalHeight)-40*(multiplierH)),(int)Math.abs((totalHeight)-40*(multiplierH)), (int)Math.abs((totalHeight)-60*(multiplierH))};// Nose Y position
		int triangleREX[] = {(int)Math.abs(totalWidth-(10*(multiplierW))), (int)Math.abs(totalWidth-25*(multiplierW)), (int)Math.abs(totalWidth-40*(multiplierW))}; // Right Eye X position
		int triangleREY[] = {(int)Math.abs(totalHeight-65*(multiplierH)),(int)Math.abs(totalHeight-80*(multiplierH)),(int)Math.abs(totalHeight-65*(multiplierH))}; // Right Eye Y position
		int triangleLEX[] = {(int)Math.abs(totalWidth-55*(multiplierW)), (int)Math.abs(totalWidth-70*(multiplierW)),(int) Math.abs(totalWidth-85*(multiplierW))}; // Left Eye X position 
		int triangleLEY[] = {(int)Math.abs(totalHeight-65*(multiplierH)),(int)Math.abs(totalHeight-80*(multiplierH)),(int)Math.abs(totalHeight-65*(multiplierH))}; // Left Eye Y position
		
		// This set applies when drawing other types of face components (contains the X, Y coordinates of each component)
		double eyeL[] = {(totalWidth)-(75*(multiplierW)), (totalHeight)-(75*(multiplierH))}; // X, Y of the left eye
		double eyeR[] = {(totalWidth)-(35*(multiplierW)), (totalHeight)-(75*(multiplierH))}; // X, Y of the right eye
		double noseCS[] = {(totalWidth)-(55*(multiplierW)),(totalHeight)-(55*(multiplierH))}; // X,Y of nose 
		double mouth[] = {(totalWidth)- (75*(multiplierW)), (totalHeight)-(30*(multiplierH))}; // X,Y of the mouth
		double stalk[] = {(totalWidth)-(55*multiplierW),(totalHeight)-(110*multiplierH)}; // X,Y of the stem / stalk
		
		// Drawing the main circle of the pumpkin
		g.setColor(Color.orange);
		g.fillOval(left, top, width, height);
		g.setColor(Color.white);
	
		// Drawing the eyes
		if(eyeType==1) { // Circle type
			g.fillOval((int)Math.abs(eyeL[0]),(int)Math.abs(eyeL[1]), faceRatioL,faceRatioH) ; // Left eye
			g.fillOval((int)Math.abs(eyeR[0]),(int)Math.abs(eyeR[1]) , faceRatioL, faceRatioH);// Right eye
		}else if (eyeType ==2) {  // Square type
			g.fillRect((int)eyeL[0],(int)eyeL[1], faceRatioL,faceRatioH) ; // Left eye
			g.fillRect((int)eyeR[0], (int)eyeR[1] , faceRatioL, faceRatioH);// Right eye
		}else if (eyeType ==3) { // Triangle type
			g.fillPolygon(triangleREX, triangleREY, 3);
			g.fillPolygon(triangleLEX, triangleLEY, 3);
		}
		//Drawing the nose
		// Nose
		if(noseType==1) { // Circle type
			g.fillOval((int)Math.abs(noseCS[0]),(int) Math.abs(noseCS[1]), faceRatioL, faceRatioH);	 //circle
		} else if (noseType==2) { //Square type
			g.fillRect((int)Math.abs(noseCS[0]), (int)Math.abs(noseCS[1]), faceRatioL, faceRatioH);	 // square
		} else if(noseType ==3) { // Triangle type
			g.fillPolygon(triangleNX, triangleNY, 3); // Triangle nose
		}
		//Drawing the mouth
		if(mouthType==1) { // Oval type
			g.fillOval((int)Math.abs(mouth[0]), (int)Math.abs(mouth[1]), width/2, faceRatioH);
		} else if(mouthType ==2) { // Rectangle type
			g.fillRect((int)(Math.abs(mouth[0])), (int)Math.abs(mouth[1]), width/2, faceRatioH);
		}
		// Drawing the stem / stalk
		g.fillRect((int)Math.abs(stalk[0]), (int)Math.abs(stalk[1]), faceRatioL,faceRatioH);
	}
}
/**
 * This class is used to create the frame that the pumpkin will be stored in
 * @author malik
 *
 */
class FrameMaker extends JFrame{
	// Checks if the user has inputed in correct values for all components of the pumpkin
	
	
	
	/**
	 * Default constructor function (Sets the default close operation and creates the frame)
	 */
	public FrameMaker() {
		frmStats();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	/**
	 * Sets up the Frame and the UI elements that the user will be able to manipulate
	 */
	public void frmStats() {
		setTitle("Pumpkin Maker");
		
		// Centering the frame
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int screenWidth = (int)dim.getWidth();
		int screenHieght = (int) dim.getHeight();
		int frameWidth= 800;
		int frameHeight= 480;
		int left = (screenWidth-frameWidth)/2;
		int top = (screenHieght-frameHeight)/2;
		setBounds(left,top,frameWidth,frameHeight);
		
		//Creating the layout of the frame
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		PumpkinPanel panCenter = new PumpkinPanel(); 
		JPanel panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout());
		c.add(panSouth, BorderLayout.SOUTH);
		c.add(panCenter, BorderLayout.CENTER);
		
		// All of the different statistics the user can affect
		JLabel leftTxt = new JLabel("Left:");
		panSouth.add(leftTxt);
		JTextField leftPos = new JTextField(3);
		panSouth.add(leftPos);
		JLabel topTxt = new JLabel("Top");
		panSouth.add(topTxt);
		JTextField topPos = new JTextField(3);
		panSouth.add(topPos);
		JLabel widthPos = new JLabel("Width:");
		panSouth.add(widthPos);
		JTextField width = new JTextField(3);
		panSouth.add(width);
		JLabel heightPos = new JLabel("Height:");
		panSouth.add(heightPos);
		JTextField height  = new JTextField(3);
		panSouth.add(height);
		JLabel eye = new JLabel("Eyes (C,S,T):");
		panSouth.add(eye);
		JTextField eyePos  = new JTextField(1);
		panSouth.add(eyePos);
		JLabel nose = new JLabel("Nose (C,S,T):");
		panSouth.add(nose);
		JTextField nosePos  = new JTextField(1);
		panSouth.add(nosePos);
		JLabel mouth = new JLabel("Mouth(O,R):");
		panSouth.add(mouth);
		JTextField mouthPos  = new JTextField(1);
		panSouth.add(mouthPos);
		
		// Button to put the changes into affect
		JButton btn = new JButton("Draw");
		panSouth.add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// Checks to make sure the user has inputed data, and checks that they inputed the correct data types when prompted
				boolean goAheadC = false; 
				boolean goAheadP = true;
			try {
				try {
					panCenter.setLeft((Integer.parseInt(leftPos.getText())));
				}catch(NumberFormatException ex) {
					if(leftPos.getText().length()==0) {
						JOptionPane.showMessageDialog(null, "Please enter a Left position value.");
						goAheadP = false;
					}
					else {
						JOptionPane.showMessageDialog(null, "The left position must be an integer");
						goAheadP = false;
					}
				}
				try {
					panCenter.setTop(Integer.parseInt(topPos.getText()));
				}catch(NumberFormatException ex) {
					if(topPos.getText().length()== 0) {
						JOptionPane.showMessageDialog(null, "Please enter a Top position value.");
						goAheadP = false;
					}
					else {
						JOptionPane.showMessageDialog(null, "The top position must be an Integer");
						goAheadP = false;
					}
				}
				
				try {
					panCenter.setWidth(Integer.parseInt(width.getText()));
				}catch(NumberFormatException ex) {
					if(width.getText().length()==0) {
						JOptionPane.showMessageDialog(null, "Please enter a Width.");
						goAheadP = false;
					} 
					else {
						JOptionPane.showMessageDialog(null, "The Width must be an Integer");
						goAheadP = false;
					}
				}
				try {
					panCenter.setHeight(Integer.parseInt(height.getText()));
				}catch(NumberFormatException ex) {
					if(height.getText().length()==0) {
						JOptionPane.showMessageDialog(null, "Please enter a Height.");
						goAheadP = false;
					}
					else {
						JOptionPane.showMessageDialog(null, "The Height must be an Integer");
						goAheadP = false;
					}
				}
				if(eyePos.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "Please enter a Eye type.");
				}  else if(nosePos.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "Please enter a Nose type.");
				}else if(mouthPos.getText().length()== 0) {
					JOptionPane.showMessageDialog(null, "Please enter a Mouth type .");
				}
				
				// Checks if the user inputed a valid component type
				else if(!eyePos.getText().equalsIgnoreCase("C") && !eyePos.getText().equalsIgnoreCase("S") && !eyePos.getText().equalsIgnoreCase("T") ) {
					System.out.println(eyePos.getText());
					JOptionPane.showMessageDialog(null, "Please enter a valid eye type");
				}else if (!nosePos.getText().equalsIgnoreCase("C") && !nosePos.getText().equalsIgnoreCase("S") && !nosePos.getText().equalsIgnoreCase("T")) {
					JOptionPane.showMessageDialog(null, "Please enter a valid nose type");
				}else if (!mouthPos.getText().equalsIgnoreCase("O") && !mouthPos.getText().equalsIgnoreCase("R")) {
					JOptionPane.showMessageDialog(null, "Please enter a valid mouth type");
				}
				// The frame can now be updated
				else {
				goAheadC=true;
				}
				// Backup in case an unexpected exception happens during run time
			}catch(Exception ex) {
				JOptionPane.showConfirmDialog(null, "An unknown Exception has occured. Please try again");
			}
			if(goAheadC==true && goAheadP ==true) {
				panCenter.setNoseType(nosePos.getText());
				panCenter.setMouthType(mouthPos.getText());
				panCenter.setEyeType(eyePos.getText());
				System.out.println(eyePos.getText());
				repaint();
			}
			
		}
	});
	}
}