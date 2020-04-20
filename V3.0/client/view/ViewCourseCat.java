package client.view;

import java.awt.Insets;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
/**
 * 
 * @author Hashir Ahmed (30070165) and Pin Long (30068063)
 * this class displays the entire course catalogue in a new frame.
 *
 */
public class ViewCourseCat {
	private JTextArea console;
	 
	 public ViewCourseCat(String text) {
		 JPanel panel = new JPanel();
		 console = new JTextArea(5,20);
		 console.setFont(new Font("Times New Roman", Font.BOLD, 14));
		 console.setForeground(new Color(0, 0, 0));
		 console.setEditable(false);
		 
		 panel.add("Center", console);
		 JScrollPane scroll = new JScrollPane();
		 panel.add("Center", scroll);
		 console.setText(text);
		 
		 console.setLineWrap(true);
	     console.setWrapStyleWord(true);
	     console.setMargin(new Insets(100,100,100,100));
	     scroll.getViewport().setView(console);
	     JOptionPane.showMessageDialog(null, scroll);
	 }
}
