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

public class MainPanel extends JPanel {
	public static Font FONT = new Font("Tahoma", Font.PLAIN, 20);
	private Controller c;
	private JTextField qSetTxtFld;
	private JTextArea responseTxtArea;
	private JSpinner spinner;

	public MainPanel() {
		setLayout(new BorderLayout(0, 0));

		c = Controller.getController();

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblFormat = new JLabel("Question Set Format: ");
		lblFormat.setFont(FONT);
		panel.add(lblFormat, BorderLayout.WEST);

		JLabel lblLabelnnNN = new JLabel("Label1: num1; Label2: num2;...");
		lblLabelnnNN.setFont(FONT);
		panel.add(lblLabelnnNN);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblEnterQuestionSet = new JLabel("Enter Question Set: ");
		lblEnterQuestionSet.setFont(FONT);
		panel_2.add(lblEnterQuestionSet, BorderLayout.WEST);

		qSetTxtFld = new JTextField();
		qSetTxtFld.setFont(FONT);
		panel_2.add(qSetTxtFld, BorderLayout.CENTER);
		qSetTxtFld.setColumns(20);

		JButton btnSelect = new JButton("Select Questions");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onGenerateQuestionsClick();
			}
		});

		btnSelect.setFont(FONT);
		panel_2.add(btnSelect, BorderLayout.EAST);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_4, BorderLayout.NORTH);

		JLabel lblQuestionsPerSet = new JLabel("Questions Per Set:");
		lblQuestionsPerSet.setFont(FONT);
		panel_4.add(lblQuestionsPerSet);

		spinner = new JSpinner();
		spinner.setPreferredSize(new Dimension(50, 22));
		spinner.setFont(FONT);
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));

		spinner.setFont(FONT);
		panel_4.add(spinner);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		responseTxtArea = new JTextArea();
		responseTxtArea.setFont(FONT);
		panel_3.add(responseTxtArea);

	}

	private boolean verifyQuestionFormat() {
		boolean isFine = true;
		// TODO

		return isFine;
	}

	private void onGenerateQuestionsClick() {

		boolean fine = verifyQuestionFormat();

		if (fine) {
			System.out.println(spinner.getValue());
			String questions = this.c.generateQuestions(this.qSetTxtFld, (int)spinner.getValue());
			this.responseTxtArea.setText(questions);
		} else {
			JOptionPane.showMessageDialog(this, "wrong format for questions");
		}
	}

}
