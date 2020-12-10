import java.util.LinkedHashMap;
/**
 * This is the model class for each question object.
 * Contains the data and functions for each aspect of a question (question, choices, answer)
 * @author malik
 *
 */
public class Question {
	private String question;
	private LinkedHashMap<String,String>choices;
	private String answer;
	/**
	 * Constructor function that initialized variables for the question object
	 */
	public Question() {
		question = "";
		choices = new LinkedHashMap<String,String>();
		answer = "";
	}
	/**
	 * Secondary constructor that sets the values of the variables for each question object
	 * @param Question Question on the quiz
	 * @param choices the choices associated with each question
	 * @param answer the correct answer to the question
	 */
	public Question(String Question, LinkedHashMap<String,String> choices, String answer) {
		setQuestion(Question);
		setChoices(choices);
		setAnswer(answer);
	}
	/**
	 * gets the question stored in the object
	 * @return question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 *  Sets the question
	 * @param question: question that is going to be set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * 
	 * @return the choices currently stored
	 */
	public LinkedHashMap<String,String> getChoice() {
		return choices; 
	}
	/**
	 * sets the choices
	 * @param choice choices that are going to be set
	 */
	public void setChoices(LinkedHashMap<String,String> choice) {
		this.choices = choice;
	}
	/**
	 * 
	 * @return answer to the current question
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * Sets the answer
	 * @param answer answer that is going to be set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * Overrided toString function that is called when you want store or print a question Object
	 * Contains the string for the question, choices, and answer
	 */
	@Override
	public String toString() {
		return String.format("%s %s %s", question, choices, answer);
	}
}
