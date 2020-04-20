package client.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.FlowLayout;

/**
 * this class prompts the user to enter the name of the student that they
 * wish to give/take a course. It will also provide them with a list of all the students and the courses
 * they currently have.
 * @author Hashir Ahmed(30070165) and Pin Long (30068063)
 *
 */
public class ChooseStudent extends JFrame{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JPanel conPanel;
	private JTextArea console;
	private JTextField StuName = new JTextField(10);
	private JButton cont = new JButton("Continue");
	
	public ChooseStudent(String text) {
		conPanel = new JPanel();
		setSize(476,376);
		setLocationRelativeTo(null);
		
		console = new JTextArea(13,40);
		console.setEditable(false);
		console.append(text);
		console.update(console.getGraphics());
	    console.setBounds(60, 11, 341, 251);

		JScrollPane scroll = new JScrollPane(console);
	    //scroll.setViewportView(console);
	    
	    JPanel panel = new JPanel();
	    panel.setBounds(60, 252, 341, 40);
	    getContentPane().add(panel);
	    
	    JPanel centrePanel = new JPanel();
	    panel.add(centrePanel);
	    centrePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    
	    JLabel enterName = new JLabel("Enter the name of the Student");
	    centrePanel.add(enterName);
	    
	   
	    centrePanel.add(StuName);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBounds(177, 303, 106, 25);
	    getContentPane().add(panel_1);
	    
	    panel_1.add(cont);
	    
	    JPanel panel_2 = new JPanel();
	    panel_2.setBounds(71, 26, 330, 181);
	    panel_2.add(scroll);
	    getContentPane().add(panel_2);
	    
	    //pack();
	    this.setVisible(true);
	}
	/**
	 * will print all the information of the students on to the console.
	 * @param text the info to be printed on the console.
	 */
	public void setConsole(String text) {
		console.setText(text);
		console.update(console.getGraphics());
	}
	/**
	 * listens to see if the user has clicked the continue button.
	 * @param listenContinue the action listener.
	 */
	public void addContinueListener(ActionListener listenContinue) {
		cont.addActionListener(listenContinue);
	}
	public String getStuName() {
		return StuName.getText().trim();
	}
}
