import java.util.Scanner;
import java.util.Random;
/**
 * 
 * @author malik
 *
 */
public class GameZone {

	/**
	 * This function is used for the Rock paper scissors game mode
	 * The function randomly generates a number, and uses that number to select a cell in an array
	 * It also compares the selections to determine a winner 
	 */
	public static void RPS() {
		Random rnd = new Random();
		String[] choices = {"Rock", "Paper", "Scissors"}; // The different options that either the player or cpu can select
		
		 // Randomly generates a number for the player and computer
		int playerRnd = rnd.nextInt(3);
		int cpuRnd= rnd.nextInt(3); 
		
		// Selects a choice from the array
		String playerCh=choices[playerRnd];
		String cpuCh = choices[cpuRnd];
		
		System.out.println("You played "+ playerCh + " and the computer played " + cpuCh);
		
		//If the player and Cpu use the same move
		if(playerRnd ==cpuRnd) {
			System.out.println("It was a tie");
		}else if((playerCh.equals("Paper") && cpuCh.equals("Rock")) //All of the situations where the player beats the cpu
			|| playerCh.equals("Scissors") && cpuCh.equals("Paper")
			||  playerCh.equals("Rock") && cpuCh.equals("Scissors"))   {
					System.out.println("You Won");
				}else {
			System.out.println("You lost");// Every other situation involves the player losing
			}	
	}
	/**
	 * This function is used for the 21 game mode
	 * The function prompts the user to draw a card, and asks if they want to continue drawing
	 * The function ends with either the player winning, losing, or tying with the CPU
	 */
	public static void TwentyOne() {	
		Scanner sc = new Scanner(System.in);
		Random rnd = new Random();
		
		int playerTotal = 0,cpuTotal = 0; // The total number of cards in each players' hands
		int cpuDraw, playerDraw;// How much they draw per turn
		String choice = "n"; // contains the player's choice of whether or not to continue drawing
		
		do {
			playerDraw = rnd.nextInt(11)+1; // Generates a random number (card) from 1-11 for the player
			playerTotal += playerDraw;
			
			// If the player either reaches or exceeds 21, the loop breaks and the results of the game are shown
			if(playerTotal>=21) {
				System.out.printf("You drew %d\n", playerDraw);
				break;
			}
			
			System.out.printf("You drew %d", playerDraw);
			System.out.printf("\nYour current total is %d\n", playerTotal);
			
			// This while loop is here to make sure that the user enters a valid value when prompted.
			// If they don't the loop will repeat
			while(true) {
				System.out.print("Do you want to draw another card? ");
				
				//Trying to catch a mismatch exception
				try {
					choice =sc.nextLine();
					}catch(java.util.InputMismatchException ex)  {
						System.out.println("Invalid input");
					}
				
				if(choice.equalsIgnoreCase("y")|| choice.equalsIgnoreCase("n")) {
					break;
				}
				
				else {
					System.out.println("Invalid Input. Try again");
				}
			}
			
		}while(choice.equalsIgnoreCase("y"));
		
		//Prints the final total of the Player and the CPU
		System.out.printf("Your total was %d\n", playerTotal);
		
		cpuDraw=rnd.nextInt(7)+13;
		cpuTotal += cpuDraw;
		
		System.out.println("The computer drew "+ cpuDraw);
		
		//All outcomes of the game
		
		//If the cpu has a larger total than the player (resulting in a negative number) they lost
		if(playerTotal-cpuTotal<0 ) {
			System.out.println("You lost");
		}else if(playerTotal ==cpuTotal) {
			System.out.println("Its a tie");
		}
		
		if(playerTotal>21) {
			System.out.println("You lost. You really shouldn't have overextended. ");
		}
		
		if(playerTotal==21){
			System.out.println("You lucky goose, you won");
		}
		//If the player has a higher total (resulting in a positive number), and has a total smaller than 21 they won
		if(playerTotal-cpuTotal>0 && playerTotal<21) {
			System.out.println("You won");
		}
	}
	 /**
	  * This is the main function, which is used to print the menu of options available to the user
	  * @param args
	  */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int choice = 0; // Player choice on the menu
		
		System.out.println("********************************\n"
					     + "*   Welcome to the Game Zone   *\n"
					     + "********************************");
		
		//Do loop is in order to keep printing the menu until the user manually exits the program
		do {
		System.out.println("What would you like to play?");
		
		System.out.println("1. Twenty-one");
		System.out.println("2. Rock Paper Scissors");
		System.out.println("3. Neither- I'm done");
		System.out.print("Please enter the number of your choice: ");
		//In case they don't enter an integer
		try {
			choice = sc.nextInt();
		}catch(java.util.InputMismatchException ex) {
			System.out.println("Invalid Input: Please enter a number.\n");
			sc.next();
			continue;
		}
		
		//All of the different options
		if(choice == 1) {
			TwentyOne();
		}else if(choice ==2) {
			RPS();
		}else if (choice ==3) {
			break;
		}
		 if(choice<=0 || choice>3) {
			System.out.println("Invalid Input.\n");
		}
	}while(choice!=3);
		sc.close();
		System.out.println("Thank you for playing. Please come again");
	}

}
