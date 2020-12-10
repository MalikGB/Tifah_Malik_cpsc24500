import java.util.ArrayList;
import java.util.Random;
/**
 * This class does the following:
 * responsible for picking a random question that will eventually be printed to the screen
 * checks the user inputted answer to the correct answer
 * returns the number of correct answers the user gave for a quiz
 * helps questionPrinter get the questions and answers, if the user selects a certain option
 * @author malik
 *
 */
public class Quizzer {
	private QuestionPrinter qp;
	private int questionNum;
	private int numCorrect;
	private ArrayList<String> answers;
	/**
	 * Constructor function that initializes different variables
	 */
	public Quizzer() {
		qp = new QuestionPrinter();
		numCorrect =0;
		answers = new ArrayList<String>();
		questionNum =0;
	}
	/**
	 * Gets a random question from the arrayList that is passed into it
	 * @param questions ArrayList of question objects that is passed into it
	 * @return the question number 
	 */
	public int getRandomQuestion(ArrayList<Question> questions){
		Random rnd = new Random();
		questionNum = rnd.nextInt(questions.size()-1);
		qp.printRandomQuestion(questions.get(questionNum));
		return questionNum;
	}
	/**
	 * Stores the answers from the JSON file into an arrayList to be used later
	 * @param answers
	 */
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
	/**
	 * Checks what the user inputted to be the correct answer against my personal correct answer
	 * @param selection user selection
	 */
	public void QuestionCheck(String selection) {
		// If the user is correct
		if(selection.equalsIgnoreCase(answers.get(questionNum).trim())){
			System.out.println("Correct");
			numCorrect+=1;
			//If the user is incorrect
		}else {
			System.out.println("Incorrect. The correct answer was " + answers.get(questionNum));
		}
	}
	/**
	 * 
	 * @return The number of questions the user got correct
	 */
	public int getNumCorrect() {
		return numCorrect;
	}
	/**
	 * This function sets the number correct variable. 
	 * This is primarily used when reseting the numCorrect to 0 after the user finished the quiz
	 * @param reset varaiable used to reset the number of questions the user got correct
	 */
	public void setNumCorrect(int reset) {
		numCorrect = reset;
	}
	/**
	 * This function pushes an arrayList of questions into one of the questionPrinter functions
	 * (End goal is to print the questions and answers of each question
	 * @param questions ArrayList of questions passed into it
	 */
	public void getQuestionsAndAnswers(ArrayList<Question> questions) {
		qp.printFormattedQuestionsAnswers(questions);
	}
}
