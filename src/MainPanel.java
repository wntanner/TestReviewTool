import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;

/**
 * Holds all of the user interface objects controls input and output of random
 * questions
 * 
 * @author William
 *
 */
public class MainPanel extends JPanel {

	public static Font FONT = new Font("Tahoma", Font.PLAIN, 20);

	private Controller c;
	private JTextField qSetTxtFld; // question set text field for getting question sets
	private JTextArea responseTxtArea; // area for displaying randomly selected questions
	private JSpinner spinner; // controls # random questions selected

	public MainPanel() {
		setLayout(new BorderLayout(0, 0));

		c = Controller.getController();

		// displays how question sets should be formatted
		JPanel pnlFormat_North = new JPanel();
		add(pnlFormat_North, BorderLayout.NORTH);
		pnlFormat_North.setLayout(new BorderLayout(0, 0));

		// JLabels displaying format of question sets
		JLabel lblFormatLbl = new JLabel("Question Set Format: ");
		lblFormatLbl.setFont(FONT);
		pnlFormat_North.add(lblFormatLbl, BorderLayout.WEST);

		JLabel lblFormatBody = new JLabel("Label1: num1; Label2: num2;...");
		lblFormatBody.setFont(FONT);
		pnlFormat_North.add(lblFormatBody);

		// gets the question set information and button to select random questions in
		// north
		// displays random questions in center
		JPanel pnlQuestionSetInformation_Center = new JPanel();
		add(pnlQuestionSetInformation_Center, BorderLayout.CENTER);
		pnlQuestionSetInformation_Center.setLayout(new BorderLayout(0, 0));

		// holds onto the input for question sets
		JPanel pnlQuestionSet = new JPanel();
		pnlQuestionSetInformation_Center.add(pnlQuestionSet, BorderLayout.NORTH);
		pnlQuestionSet.setLayout(new BorderLayout(0, 0));

		JLabel lblEnterQuestionSet = new JLabel("Enter Question Set: ");
		lblEnterQuestionSet.setFont(FONT);
		pnlQuestionSet.add(lblEnterQuestionSet, BorderLayout.WEST);

		qSetTxtFld = new JTextField();
		qSetTxtFld.setFont(FONT);
		pnlQuestionSet.add(qSetTxtFld, BorderLayout.CENTER);
		qSetTxtFld.setColumns(20);

		JButton btnSelect = new JButton("Select Questions");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onGenerateQuestionsClick();
			}
		});

		btnSelect.setFont(FONT);
		pnlQuestionSet.add(btnSelect, BorderLayout.EAST);

		JPanel pnlQuestionsPerSet = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlQuestionsPerSet.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlQuestionSet.add(pnlQuestionsPerSet, BorderLayout.NORTH);

		JLabel lblQuestionsPerSet = new JLabel("Questions Per Set:");
		lblQuestionsPerSet.setFont(FONT);
		pnlQuestionsPerSet.add(lblQuestionsPerSet);

		spinner = new JSpinner();
		spinner.setPreferredSize(new Dimension(50, 22));
		spinner.setFont(FONT);
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));

		spinner.setFont(FONT);
		pnlQuestionsPerSet.add(spinner);

		// displays the random questions
		JPanel pnlRandomQuestions = new JPanel();
		pnlQuestionSetInformation_Center.add(pnlRandomQuestions, BorderLayout.CENTER);
		pnlRandomQuestions.setLayout(new BorderLayout(0, 0));

		responseTxtArea = new JTextArea();
		responseTxtArea.setFont(FONT);
		pnlRandomQuestions.add(responseTxtArea);

	}

	/**
	 * Generates random questions from user input
	 */
	private void onGenerateQuestionsClick() {

		System.out.println(spinner.getValue());
		String questions = this.c.generateQuestions(this.qSetTxtFld, (int) spinner.getValue());
		this.responseTxtArea.setText(questions);

		// JOptionPane.showMessageDialog(this, "wrong format for questions");

	}

}
