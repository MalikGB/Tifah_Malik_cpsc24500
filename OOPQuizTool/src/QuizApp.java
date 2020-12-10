import java.util.Scanner;

/**
 * This is the main application of the program
 * It prints the header of the program and a menu
 * Calls on function that opens and reads the file containing the questions and answers
 * Prompts the user to make a selection from 3 options (start quiz, see Questions and answers, and exit the program
 *  
 * @author malik
 *
 */
public class QuizApp {
	private static Quizzer quiz;
	/**
	 * Main function of the program
	 * Creates a quizzer object, and uses its related functions (get random questions, and printing all the questions and answers)
	 * Creates a questionReader object, which is responsible for reading the file the questions are stored in
	 * @param args arguments passed into the function
	 */
	public static void main(String[] args) {
		quiz = new Quizzer();
		boolean fileCheck=true;
		System.out.println("**************************************"
						  +"\n*  OOP Theory and Concept Questions  *" 
						  +"\n**************************************");
		Scanner sc = new Scanner(System.in); // used for user input
		Scanner ch = new Scanner(System.in);
		String file; // Name of the file questions and answers are stored in
		String selection; // What the user believes to be the correct answer
		int numOfQuestions; // Number of questions the user wants to answer
		int choice=-1; // Determines which menu option the user wants to use
		while(fileCheck) {
			System.out.print("\nEnter the name of the file containing the questions: ");
			file = sc.nextLine();
			QuestionReader reader = new QuestionReader();
			// Opens the file containing questions, returns true if the file was read successfully
			if(reader.read(file)) {
				fileCheck=false;
				do {
					try {
						sc.reset();
						System.out.println("Main Menu:");
						System.out.println("1. Take a quiz\n2. See questions and answers\n3. Exit");
						System.out.print("Enter the number of your selection: ");
						choice = Integer.parseInt(sc.nextLine());
					}catch(Exception ex) {
						System.out.println("Invalid input. Try again");
						continue;
					}
					if(choice==1) {
						System.out.println();
						while(true) {
							try {
								System.out.print("How many questions would you like to do?: ");
								numOfQuestions = Integer.parseInt(sc.nextLine());
								break;
							}catch(Exception ex) {
								System.out.println("Invalid integer. Please try again.");
							}
						}
						quiz.setAnswers(reader.getAnswers()); // Gets the answers from the file, and stores them in an array in the quizzer class
						// Prints a question, asks for an answer, and checks the answer
						for(int i=0; i<numOfQuestions; i++) {
							quiz.getRandomQuestion(reader.getQuestionList());
							System.out.print("Answer: ");
							selection = ch.nextLine();
							quiz.QuestionCheck(selection);
						}
						System.out.println("You got: " +quiz.getNumCorrect() + " out of "+ numOfQuestions+ " questions correct.");
						quiz.setNumCorrect(0);
					}else if(choice ==2) {
						quiz.getQuestionsAndAnswers(reader.getQuestionList()); // Prints the questions and answers to the quiz
					}else if(choice==3) {
						System.out.println("Thank you for using my program.");
						sc.close();
						ch.close();
					}
					else {
						System.out.println("Invalid input. Please try again");
					}
				}while(choice!=3); // Checks for invalid inputs
			}
		}
	}
}
