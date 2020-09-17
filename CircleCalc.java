
/*Malik Tifah
 * Object Oriented Programming Section 1
 */
import java.util.Random;
import java.lang.Math;

/**
 * 
 * @author malik
 *
 */
public class CircleCalc {
	
	/**
	 * This function calculates the area of a circle 
	 * @param r This is the radius of the randomly generated circle
	 * @return Returns the area of the circle
	 */
	public static double CalcArea( int r){
		
		double area; //Declaring area variable
		
		// Area of a circle is pi*r^2 (Math.Pow format: Math.pow (base, exponent)
		area =  Math.PI*Math.pow(r, 2);
		
		return area;
		
	}
	/**
	 * This function calculates the circumference of a circle
	 * @param r This is the radius of the randomly generated circle 
	 * @return returns the circumference of the circle
	 */
	public static double CalcCircumfe( int r) {
		
		double circumfe; // Declaring the circumference variable
		
		circumfe =  (2*r)*(Math.PI); // Circumference of a circle is 2*pi*r
		
		return circumfe; // Returning the circumference
	}

	public static void main(String[] args) {
		
		
		Random rnd = new Random(); // creating an new object of the random class
		
		int radius = rnd.nextInt(10)+1; // Generates a number between 1-10
		
		System.out.println(radius); 
		
		System.out.println("The radius of the radomly generated circle is: " + CalcArea(radius));
		System.out.println("The circumference of the radomly generated circle is: " + CalcCircumfe(radius));
		
		

	}

}
