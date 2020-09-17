/**
 * 
 * @author malik
 *
 */
public class Flooring {
	
	/**
	 * This function is used to calculate the cost of the project
	 * @param num // Number of packages needed for the project
	 * @return The total price of the boards needed
	 */
	public static double Cost(double num ) {
		
		double cost = 24.99* num;
		
		return cost;
		
	}
	
	public static void main(String[] args) {
		
		final double NUMBER_OF_BOARDS_PER_PAC =6.0;
		
		double tileArea= 2.5*0.5; // area of the tiles is 2.5 ft * 0.5 ft;
		
		double numOfPackages, numOfBoards;
				
		
		// Breaking down the diagram into 3 shapes (Work shown in Seperate document)
		
		//First rectangle
		int length1 = 25;
		int width1=10;
		
		int areaSquare1 = length1*width1; // Area of a square is length * width
		
		
		//Second rectangle 
		
		int lenght2=13;
		int width2=10;
		
		int areaSquare2 = lenght2*width2;
		
		//Triangle
		int base=10;
		int height = 12;
		
		double areaTriangle=(0.5) * (base*height); // Area of a triangle is base*height/2
		
		
		double totalArea = areaSquare1+areaSquare2+areaTriangle; // Prints the total area of the room
		
		numOfBoards =  totalArea/ tileArea ; 
		numOfPackages = numOfBoards/NUMBER_OF_BOARDS_PER_PAC ;

		System.out.println("You are going to need " + numOfBoards + " Boards");
		System.out.println("You are going to need: " +numOfPackages + " Packages of boards or about " + (int)(numOfPackages+0.5) + " Packages");
		System.out.println("The project is going to cost you: $" + Cost(numOfPackages));
		
		
	}

}
