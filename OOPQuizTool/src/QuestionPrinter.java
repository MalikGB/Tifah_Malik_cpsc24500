import java.util.ArrayList;
/**
 * This class is responsible for printing the questions for the quiz to the screen
 * It also prints the Questions and Answers if the user selects the correct option
 * @author malik
 *
 */
public class QuestionPrinter {
	private String question;
	private String choices; // (a,b,c,d)
	private String answer; //The correct answer
	/**
	 * Constructor function that intializes the question, choices, and answer strings for each question
	 */
	public QuestionPrinter(){
		question = "";
		choices = "";
		answer= "";
	}
	/**
	 * This function prints a random question (passed from the Quizzer class), and its corresponding choices
	 * @param questionNum The question number
	 * @param randomQuestion The question object (contains: question, choices, and answer)
	 */
	public void printRandomQuestion(Question randomQuestion) {
		choices = "";
		// This section is used to split the question object's string into parts
		String parts[];
		String line;
		line = randomQuestion.toString();
		parts = line.split("\n");
		// Beginning to assign variables different parts of the question object
		question = parts[0];
		System.out.printf("%s\n",question);
		choices+= (parts[1]+"\n" +parts[2]+"\n"+parts[3]+"\n"+parts[4]); // (choices: a,b,c,d)
		// Formatted the choices string to get rid of commas and brackets, and equal signs (symptoms of using a linkedhashmap to store the data)
		String choicesFormatted = choices.replace("{", "").replace("=",".").replace(",", "").replace("{", ""); 
		System.out.println(choicesFormatted);
	}
	/**
	 * This function prints all the questions on the quiz, and my personal answer to each question
	 * @param questions ArrayList of question objects (contains question, answer, and choices)
	 */
	public void printFormattedQuestionsAnswers(ArrayList<Question> questions) {
		for(int i =0; i<questions.size(); i++) {
			answer = "";
			//Breaking the question toString into parts and assigning variables different parts
			String parts[];
			String line;
			line = questions.get(i).toString();
			parts = line.split("\n");
			question = parts[0];
			answer = parts[5]; //0 = question, 1-4 =choices, 5 = answer
			System.out.println(question);
			System.out.print("Answer:" + answer.replace("}", "") + "\n"); // Had to format the answer to get rid of brackets
		}
	}
}
