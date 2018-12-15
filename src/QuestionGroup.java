/**
 * Group of questions comprising a homework set.
 * Either a single question given as min or a range of values from min to max
 * 
 * @author William
 *
 */
public class QuestionGroup {
	
	private String label; // name of question set
	private int num; // # questions in the group
	
	public QuestionGroup(int num, String label) {
		this.num = num;
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}

	public int getNum() {
		return this.num;
	}

}
