import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JTextField;

public class Controller {

	private static String grpSep = ";"; // separator for each group of questions
	private static String qSep = ":"; // separator between label and num questions
	private static Controller control;

	private QuestionGroup[] questions;

	public static Controller getController() {
		if (control == null)
			control = new Controller();
		return control;
	}

	public Controller() {

	}

	/**
	 * Parse questions
	 * 
	 * @param field
	 * @return
	 */
	public String generateQuestions(JTextField field, int numPerSet) {
		String q = "broken";
		String input = field.getText();
		System.out.println(input);
		parseQuestionInput(input, numPerSet);
		StringBuffer buf = new StringBuffer();
		for (QuestionGroup c : this.questions) {
			buf.append(String.format("%5s : ", c.getLabel()));

			List<Integer> nums = getRandomQuestions(c.getNum(), numPerSet);

			for (Integer i : nums) {
				buf.append(i.toString() + ", ");
			}

			buf.append(System.getProperty("line.separator"));
		}
		q = buf.toString();
		return q;
	}

	public List<Integer> getRandomQuestions(int limit, int goalSize) {
		ArrayList<Integer> answers = new ArrayList<Integer>();
		Random r = new Random();
		int add;
		int cnt = 0;
		System.out.println("limit is: " + limit);
		while (answers.size() < goalSize) {

			add = r.nextInt(limit) + 1;
			if (!answers.contains(add)) {
				answers.add(add);
			}

			if (cnt >= limit * 2) {
				break;
			}

			cnt++;

		}

		Collections.sort(answers);
		return answers;
	}

	public void parseQuestionInput(String input, int numPerSet) {

		String[] splitInput = input.split(grpSep);
		int numqs = splitInput.length;
		questions = new QuestionGroup[numqs];

		for (int i = 0; i < numqs; i++) {
			String cur = splitInput[i];
			String[] curGroup = cur.split(qSep);

			if (curGroup.length == 1) {
				curGroup = new String[] { "", curGroup[0] };
			}
			System.out.println(curGroup[1]);
			System.out.println(curGroup[0]);

			questions[i] = new QuestionGroup(Integer.parseInt(curGroup[1].trim()), curGroup[0]);
		}

	}

	public boolean verifyQuestionFormat() {
		boolean isFine = true;
		// TODO walk down to find patter : char char char char... char : int ; char char
		// ... char == : int ;

		return isFine;
	}

}
