package client.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
/**
 * This class is the home frame, it will be the first frame that opens up and asks
 * the user what they would like to do with the program.
 * @author Hashir Ahmed (30070165) and Pin Long (30068063)
 *
 */
public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton addCourse = new JButton("Add course to student courses");
	private JButton removeCourse = new JButton("Remove course from student courses");
	private JButton viewCourseTaken = new JButton("View All courses taken by student");
	private JButton searchCatalogue = new JButton("Search catalog courses");
	private JButton viewCourseCatalogue = new JButton("View All courses in catalog");
	private final JLabel lblNewLabel_3 = new JLabel("");

	
	@SuppressWarnings("static-access")
	public GUI () {
		getContentPane().setBackground(new Color(255, 51, 51));
		getContentPane().setLayout(null);
		 UIManager UI=new UIManager();
	     UI.put("OptionPane.background", Color.red);
	     UI.put("Panel.background", Color.red);
	     UI.put("OptionPane.messageForeground", Color.white);
	     setLocationRelativeTo(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 0));
		panel_1.setBounds(0, 336, 573, 195);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		addCourse.setBounds(192, 30, 199, 21);
		addCourse.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_1.add(addCourse);
		
		removeCourse.setBounds(192, 62, 197, 21);
		removeCourse.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_1.add(removeCourse);
		
		viewCourseTaken.setBounds(194, 163, 197, 21);
		viewCourseTaken.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_1.add(viewCourseTaken);
		
		searchCatalogue.setBounds(194, 127, 197, 22);
		searchCatalogue.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_1.add(searchCatalogue);
		
		viewCourseCatalogue.setBounds(194, 94, 197, 22);
		viewCourseCatalogue.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_1.add(viewCourseCatalogue);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO THE COURSE ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(78, 11, 447, 140);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ADMINISTRATION PROGRAM");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1.setBounds(64, 104, 499, 86);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Please choose one of the following options!");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(75, 264, 438, 47);
		getContentPane().add(lblNewLabel_2);
		lblNewLabel_3.setIcon(new ImageIcon("Images\\UofCLogo.jpg"));
		lblNewLabel_3.setBounds(236, 169, 105, 98);
		
		getContentPane().add(lblNewLabel_3);
		setAutoRequestFocus(false);
		setSize(587,568);
		
		//Other setups
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/**
	 * this is the listener function for the search catalogue button.
	 * @param e the action listener.
	 */
	public void addSearchCatalogueListener(ActionListener e) {
		searchCatalogue.addActionListener(e);
	}
	/**
	 * this is the listener function for the add course button
	 * @param e the action listener.
	 */
	public void addAddCourseListener(ActionListener e) {
		addCourse.addActionListener(e);
	}
	/**
	 * this is the listener function for the remove course button
	 * @param e the action listener.
	 */
	public void addRemoveCourseListener(ActionListener e) {
		removeCourse.addActionListener(e);
	}
	/**
	 * this is the listener function for the view course catalogue button
	 * @param e the action listener.
	 */
	public void addViewCourseCatalogueListener(ActionListener e) {
		viewCourseCatalogue.addActionListener(e);
	}
	/**
	 * this is the listener function for the view course taken by students button
	 * @param e the action listener.
	 */
	public void addviewCourseTakenListener(ActionListener e) {
		viewCourseTaken.addActionListener(e);
	}
	/**
	 * this function will display this JFrame when called.
	 */
	public void displayMenu() {
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
