import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 * This class is responsible for opening and reading a formatted JSON file that contains a list of:
 * Questions, choices, and answers
 * This class also stores an ArrayList of question Objects, and an ArrayList of answers that will be used by other classes
 * @author malik
 *
 */
public class QuestionReader {
	Question questions = new Question();
	ArrayList<Question> questionList = new ArrayList<Question>();
	ArrayList<String> answers = new ArrayList<String>();
	/**
	 * This function opens and reads a formatted JSON file that contains questions, choices, and answers
	 * @param fname Name / Directory of the file that will be opened
	 * @return True if the file is read successfully, false if something went wrong
	 */
	public boolean read(String fname){
		if(fname.toUpperCase().contains(".JSON")){
			try {
				// Objects that allow the JSON file to be opened/ read
				FileReader reader = new FileReader(new File(fname));
				JSONParser parser = new JSONParser();
				JSONObject all = (JSONObject) parser.parse(reader);
				JSONArray arr= (JSONArray) all.get("questions");
				Iterator itr = arr.iterator();
				
				JSONObject questionObject;
				String question;
				String answer;
				// Reads the whole file, and stores data into respective variables
				//Also adds the data into the arrayList of Question objects
				while(itr.hasNext()) {
					LinkedHashMap<String, String> choices = new LinkedHashMap<String,String>();
					questionObject = (JSONObject) itr.next();
					question = questionObject.get("question").toString()+"\n";
					choices.put("a", questionObject.get("a").toString()+"\n");
					choices.put("b", questionObject.get("b").toString()+"\n");
					choices.put("c", questionObject.get("c").toString()+"\n");
					choices.put("d", questionObject.get("d").toString()+"\n");
					answer =questionObject.get("answer").toString();
					answers.add(answer +"\n" );
					questionList.add(new Question(question, choices,answer));
				}
				reader.close();
				return true;
			}catch(Exception ex) {
				System.out.println("An exception has occured");
				return false;
			}
		}
		else {
			System.out.println("That is a unsuported file type please try again");
			return false;
		}
	}
	/**
	 * Gets the list of questions stored in the questionList arrayList
	 * @return lists of questions
	 */
	public ArrayList<Question> getQuestionList(){
		return questionList;
	}
	/**
	 * 
	 * @return Answers stored in the answer arrayList
	 */
	public ArrayList<String> getAnswers(){
		return answers;
	}
}
