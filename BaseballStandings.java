import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.lang.Math.*;
/**
 * 
 * @author malik
 *
 */
public class BaseballStandings {
	/**
	 * 
	 * @param line Contains the information of each team (name, wins, loses)
	 * @return This returns the amount of wins the team that called the function
	 */
	public static double getWins(String line) {
		String parts[] = line.split("\t");
		double wins;
		return wins = Double.parseDouble(parts[1]);
	}
	/**
	 * 
	 * @param prevW This is the number of wins of the top team already in the array
	 * @param currentW The number of wins of the team that we want to compare to the top team to calculate PCT behind
	 * @param currentL The number of wins of the team that we want to calculate their PCT behind
	 * @param prevL The number of loses of the top team already in the array
	 * @return Returns the points behind the overall winning team for each of the other teams
	 */
	public static double calculatePCTBehind(double prevW, double currentW, double currentL, double prevL) {
	
		double behind; 
		
		behind = (Math.abs(prevW- currentW) + Math.abs(prevL- currentL))/2.0; // To find the points behind = ((wins of 1 team - wins of 2nd team) + (loses of 1 team - loses of the second team)/2 
		
		return behind; 
	}
	/**
	 * This function prints the menu and prompts the user to choose which data they want to see
	 * @return The choice of what information the viewer wants to see
	 */
	public static int menuSelect() {
		
		Scanner ch = new Scanner(System.in);
		boolean check = true; // Checks if the user has inputed a valid value
		
		int choice = 0; // Variable that will contain user choice
		System.out.println("Which standings do you want to see?"
				+ "\n1. AL East "
				+ "\n2. AL Central"
				+ "\n3. AL West" 
				+ "\n4. NL East"  
				+ "\n5. NL Central"  
				+ "\n6. NL West" 
				+ "\n7. Overall" 
				+ "\n8. Exit");
		while (check == true) {
		System.out.print("Enter the number of your choice: ");
			// Note the exception doesn't entirely work. If an letter is put in it will trigger an infinite loop
			try {
			choice = ch.nextInt(); // Checks if the user puts in a valid value
			}catch(Exception ex) {
				System.out.println("Invalid input");
				menuSelect();
			}
		
			// If they put in a valid value, the loop break
			if(choice>=1 && choice<=8) {
				check =false;
			}
			else {
				System.out.println("Not a valid number");
			}
		}
		return choice;
	}
	/**
	 * This function prints out the team's information (name, wins, and loses) for the section the user desires
	 * @param chosen This is the array that stores all of the teams for the desired section
	 */
	public static void printStats (ArrayList<String> chosen) {
		
		String parts[]; // Contains the different parts of the team's information
		System.out.println("Team            Wins    Losses  Pct.  Behind");
		System.out.println("--------------------------------------------");
		
		//Variables that contain winning percentage and points behind
		double pct; 
		double pctBehind = 0;
		
		double prevWins=0, newWins; // Scores used to calculate the PCT behind
		double prevLose=0, newLose; 
		
		for(String stats: chosen)  {
			parts = stats.split ("\t");
			
			// Calculating PCT for each team. ( PCT= (wins/ wins + losses))
			pct = Double.parseDouble(parts[1]) / (Double.parseDouble(parts[1]) + Double.parseDouble(parts[2]));
			
			//Calculating PCT behind
			newWins= Double.parseDouble(parts[1]);
			newLose= Double.parseDouble(parts[2]);
			
			//Checks if the team we are calculating PCT for is the team on top 
			if(prevWins>0) {
				pctBehind = calculatePCTBehind(prevWins, newWins,  newLose, prevLose );
			}else {
				prevWins = newWins;
				prevLose= newLose;
			}
			System.out.printf("%-10s	%.0f	%.0f	%.2f	%.2f\n", parts[0],  newWins, newLose,  pct,  pctBehind ); // Printing out all information		
		}
	}
	/**
	 * 
	 * @param chosen This array will contain information for all team (regardless of section)
	 * @param line This variable contains the team information on each line (name, wins, and loses)
	 */
	public static void sortByWins(ArrayList<String> chosen, String line) {
		
		String[] parts; // Contains the parts of the team on each line
		double winsC, winsP; // winsC= current # of wins of the team we are looking at and winsP is the wins of the previous team 
		
		winsC= getWins(line);
		int counter =-1; // Counter to keep track of where in the array we are
			//This for loop goes through the entire arraylist and compares the wins for the current team and the teams already in the list to organize the array by wins
		for(int i =0; i<chosen.size(); i++) {
			winsP = getWins(chosen.get(i));
			if(winsC>winsP) {
				counter =i;
				break;
			}
		}
			// If there is no other team in the list, the current team is added to the front
			if(counter<0) {
				chosen.add(line); 
			}
			// Organizes the current team into the list
			else {
				chosen.add(counter,line);
			}
	}
		
	/**
	 * This function is mainly used to read the file, and begin storing the team information into different array lists
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("**********************************\n"
						  + "* BASEBALL STANDINGS ANALYZER \n*"
						  + "*********************************");
		
		ArrayList<String> ALEast = new ArrayList<String>();
		ArrayList<String> ALCentral = new ArrayList<String>();
		ArrayList<String> ALWest = new ArrayList<String>();
		ArrayList<String> NLEast = new ArrayList<String>();
		ArrayList<String> NLCentral = new ArrayList<String>();
		ArrayList<String> NLWest = new ArrayList<String>();
		ArrayList<String> overall = new ArrayList<String>();
		ArrayList<String> pointer = null; // This is used to identify which array list to store team information into
	
		String[] parts; // array that contains all the parts of each team
	
		Scanner sc = new Scanner(System.in);
		
		String line, fileName; // The current line the fileReader is reading, and the name of the  file
		String division; // The division each team is in
		
		System.out.print("Enter the directory of the standings file: ");
	
		fileName = sc.nextLine();
		// Begins reading the current file
		try {
			Scanner fsc = new Scanner(new File(fileName)); 
			while(fsc.hasNextLine()) {
				line = fsc.nextLine();
				parts = line.split("\t");
				
				// Checks if the file Reader reads certain divisions, and when stores the information into the correct array
				if(parts[0].equalsIgnoreCase("League")) {
					division = parts[1].toUpperCase();
				
				if(division.equalsIgnoreCase("AL East")) {
					pointer = ALEast;
					
				} else if(division.equalsIgnoreCase("AL West")) {
					pointer = ALWest;
					
				} else if(division.equalsIgnoreCase("AL Central")) {
					pointer = ALCentral;
				} else if(division.equalsIgnoreCase("NL East")) {
					pointer = NLEast;
				} else if(division.equalsIgnoreCase("NL West")) {
					pointer = NLWest;
					
				} else if(division.equalsIgnoreCase("NL Central")) {
					pointer = NLCentral;
				}
				}
				// Once the pointer is on the correct array, information begins to be added
				else {
					pointer.add(line);
					sortByWins(overall, line);
				}
			}
			// If the file the user inputted is not found
		}catch(Exception ex) {
			System.out.println("File not found");
		}
		// Switch statement that reads the user input and calls upon functions depending on what they user wants
		switch(menuSelect()) {
		case(1):
			printStats(ALEast);
			break;
		case(2):
			printStats(ALCentral);
			break;
		case(3):
			printStats(ALWest);
			break;
		case(4):
			printStats(NLEast);
			break;
		case(5):
			printStats(NLCentral);
			break;
		case(6):
			printStats(NLWest);
			break;
		case(7):
			for(String stats: overall) {
				System.out.printf("%-15s\n",stats);
			}
			break;
		case(8):
			break;
		}
		}
	}
