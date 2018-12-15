import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JTextField;

/**
 * Computational object for reading in QuestionGroup objects and generating
 * random questions
 * 
 * @author William
 *
 */
public class Controller {

	private static String grpSep = ";"; // separator for each group of questions
	private static String qSep = ":"; // separator between label and num questions

	private static Controller control; // singleton controller

	// global QuestionGroup[] for holding onto new QuestionGroups each time user
	// generates new question sets ...
	private QuestionGroup[] questions;

	public static Controller getController() {
		if (control == null)
			control = new Controller();
		return control;
	}

	private Controller() {

	}

	/**
	 * Parse questions given a JTextField holding String of question input and #
	 * questions per set
	 * 
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public String generateQuestions(JTextField field, int numPerSet) throws Exception {

		String input = field.getText();
		System.out.println(input);

		// create all the questiongroups from the input so we can get random questions
		parseQuestionInput(input, numPerSet);

		// display the QuestionGroup label and random questions for the user
		StringBuffer buf = new StringBuffer();
		for (QuestionGroup c : this.questions) {
			buf.append(String.format("%5s : ", c.getLabel()));

			List<Integer> nums = getRandomQuestions(c.getNum(), numPerSet);

			for (Integer i : nums) {
				buf.append(i.toString() + ", ");
			}

			buf.append(System.getProperty("line.separator"));
		}

		return buf.toString();

	}

	/**
	 * Create a list of random numbers with given goalSize and within a certain
	 * range from 0 to limit : int
	 * 
	 * @param limit
	 * @param goalSize
	 * @return
	 */
	public List<Integer> getRandomQuestions(int limit, int goalSize) {
		
		ArrayList<Integer> answers = new ArrayList<Integer>();
		Random r = new Random();
		int add; // random number to add to list
		int cnt = 0; // number of iterations finding random number
		System.out.println("limit is: " + limit);
		
		while (answers.size() < goalSize) {
			
			// only add the unique numbers to answers list
			add = r.nextInt(limit) + 1;
			if (!answers.contains(add)) {
				answers.add(add);
			}
			
			// if we randomly select a number already inside of list 1000 times, just break out. really bad luck
			if (cnt >= 1000) {
				break;
			}

			cnt++;

		}
		// so the user can easily go from question 1 to 2 to 3 instead of 5 to 2 to 7
		Collections.sort(answers);
		return answers;
	}

	/**
	 * Parses a String input into QuestionGroup
	 * 
	 * @param input
	 * @param numPerSet
	 * @throws Exception
	 */
	public void parseQuestionInput(String input, int numPerSet) throws Exception {
		// get the number of QuestionGroup objects to make and hold onto them with
		// global variable to manipulate in other places
		String[] splitInput = input.split(grpSep);
		int numqs = splitInput.length;
		questions = new QuestionGroup[numqs];

		// create QuestionGroup objects with appropriate label and number
		for (int i = 0; i < numqs; i++) {
			String cur = splitInput[i];
			String[] curGroup = cur.split(qSep);

			// user can enter numbers with no labels, so we just give empty labels for
			// number-only input, otherwise we can try to parse an i
			if (curGroup.length == 1) {
				curGroup = new String[] { "", curGroup[0] };
			}

			System.out.println(curGroup[1]);
			System.out.println(curGroup[0]);
			try {
				questions[i] = new QuestionGroup(Integer.parseInt(curGroup[1].trim()), curGroup[0]);
			} catch (Exception e) {
				throw new Exception();
			}
		}

	}

}
