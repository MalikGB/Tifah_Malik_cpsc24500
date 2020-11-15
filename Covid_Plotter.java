import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.util.Scanner;
import java.io.File;
import java.util.LinkedHashMap;
import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;
public class Covid_Plotter {
	/**
	 * This function is mainly used for opening and reading the contents of the file
	 * @param args A
	 */
	public static void main(String[] args) {
		// Reading the tab delimited file and storing its contents into a linked hash map
		Scanner sc = new Scanner(System.in);
		LinkedHashMap<String, double[]>data = new LinkedHashMap<>(); // Linked hash map that contains the name of the country, and the number of deaths
		String parts[]; // Separating the name of the countries, and different death values into separate parts
		String line; // Current line the file reader is on
		String name; //Names of the different countries in the file
		double values[]; // The total number of deaths each country has per day
		System.out.println("******************************************\n"
						   + "* International COVID-19 Mortality Rates *\n"
						 + "******************************************");
		while(true) {
		// Opening the file
			
			System.out.print("Enter the name of the file cotaining the data: ");
			String fileName = sc.nextLine();
			
			try {
				Scanner fsc = new Scanner(new File (fileName));
				while(fsc.hasNextLine()) {
					line = fsc.nextLine();
					parts = line.split("\t");
					values = new double[parts.length-1];
					name = parts[0];
					for(int i =1; i<parts.length; i++) {
						values[i-1] = Double.parseDouble(parts[i]);
						data.put(name, values);
					}
				}
			}catch(Exception ex) {
				System.out.println("An exception has occured. Check that the file name is correct, and please try again");
				continue;
			}
			break;
		}
		userInput(data);	
	}
	/**
	 * This function is used to take in user input for what countries they want to plot, and what type of data they want to see (Cumulative or Daily)
	 * @param data This Linked HashMap contains all of the data read from the file
	 */
	public static void userInput(LinkedHashMap<String, double[]>data) {
		String input; 
		Scanner sc= new Scanner(System.in);
		// This portion of code can be broken up into two "Phases". First phase = ask user for countries they want to see and check the information, second phase = ask user if they want to see cumulative or daily data
		boolean check = true; // Checks if the user inputs valid countries from the data set
		while(check) {
			// Prompting the user to enter the countries data they want to see
			System.out.print("Enter countries to be plotted seperated by a comma (,) or enter quit to exit: ");
			input = sc.nextLine();
			String countriesIndv[] = input.split(","); // Splits up the string input by the commas entered
						
			//Exits the program
			if(input.equalsIgnoreCase("quit")) { 
				System.out.println("\nLet this be a reminder to wear your mask");
				break;
			}
			// Checks if the user has inputed valid names for the countries in the data set
			for(String places: countriesIndv) {
				if(data.containsKey(places.trim())) {
					check = false; // Allows the program to move into the next phase of printing plots
				}
				else {
					System.out.printf("'%s' is not a valid entry. Please try again\n", places);
					check = true;
				}
			}
			// Input that asks the user what kind of plot they want to see 
			if(check==false) {
				while(true) {
					System.out.print("Would you like to see daily (Enter 'D') or Cumulative deaths (Enter 'C')?: "); 
					input= sc.nextLine();
					if(!input.equalsIgnoreCase("D") && !input.equalsIgnoreCase("C")){
						System.out.println("Invalid input try again");
						continue;
					}
					// Prints the frame and the plot according to the desired data
					else {
						printScreen(createPlot(data,countriesIndv,input));
						check=true; // Forces the program back into the first phase of asking the user what countries they want to see plotted
						break; 
					}
				}
			}
		}
	}
	/**
	 * This function creates the plot, and all of its attributes
	 * @param info The data read from the file
	 * @param countries An array of the valid countries that the user inputed, and wants to see plotted
	 * @param input String variable that represents whether the user wants to see daily or cumulative deaths
	 * @return Plot that will be printed onto the frame
	 */
	public static Plot2DPanel createPlot(LinkedHashMap<String, double[]> info, String[] countries, String input) {
			
		Plot2DPanel plot = new Plot2DPanel();
		plot.setAxisLabels("Days", "Deaths"); // Naming the x and y axis of the plot
		plot.addLegend("South"); // Adding a legend onto the south region
			
		for(String key: countries) {
			double num[]; //Array that stores the number of deaths per country
			num = info.get(key.trim());
			double prevDeaths =0; // The deaths from the previous day
			double[] dailyDeaths = new double[info.size()-1]; // Array that stores the number of daily deaths
			
			// This prints out the cumulative deaths
			if(input.equalsIgnoreCase("C")) {
				BaseLabel bl = new BaseLabel("Cumulative Deaths", Color.RED,0.5,1.1);
				plot.addPlotable(bl);
				plot.addLinePlot(key, num);
			}
				// This prints out the daily deaths
			else {
				for (int i=0; i<num.length-1; i++) {
					dailyDeaths[i]= num[i] - Math.abs(prevDeaths); // Daily deaths = total deaths - total deaths of previous days
					prevDeaths+=dailyDeaths[i];
				}
				BaseLabel bl = new BaseLabel("Daily Deaths", Color.RED,0.5,1.1);
				plot.addPlotable(bl);
				plot.addLinePlot(key, dailyDeaths);
				plot.setFixedBounds(1, -1000, 3000.0); // I think it might be a scaling problem, but this function doesn't seem to work
			}
		}
		// Returns the plot
		return plot;
	}
	/**
	 * This function creates and prints the frame that the plot will be displayed on
	 * @param plot Passes the plot in order to print it to the new frame
	 */
	public static void printScreen(Plot2DPanel plot) {
		JFrame frm = new JFrame();
		frm.setTitle("Covid-19 Data plot"); // Title of window
		frm.setBounds(0,0, 1366, 768); // X, y position  (top left of screen is 0,0)
		//frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		Container c = frm.getContentPane(); // Creates the window that the frame will be displayed in
		c.setBackground(Color.WHITE);
		c.setLayout(new BorderLayout()); // N, S,E,W, Center quadrants 
		c.add(plot,BorderLayout.CENTER);
		frm.setVisible(true);
	}
}

