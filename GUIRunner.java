import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

public class GUIRunner implements ActionListener {
	JFrame landingFrame = new JFrame("Home Finder");
	JLabel title;
	JLabel description;
	JButton startButton;

	/**
	 * This constructor initializes a simple home/landing
	 * frame that starts our program
	 */
	GUIRunner() {
		
		landingFrame.setSize(400, 400);
		landingFrame.setVisible(true);
		landingFrame.setLayout(null);
		landingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		title = new JLabel("Welcome to Home Finder!");
		landingFrame.add(title);
		title.setBounds(125, 30, 200, 30);
		
		description = new JLabel("Let's find your next California home");
		Font f = description.getFont();
		description.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
		landingFrame.add(description);
		description.setBounds(100, 70, 300, 30);
		
		
		startButton = new JButton("Get Started!");
		landingFrame.getContentPane().add(startButton);
		startButton.setBounds(125, 300, 150, 30);
		startButton.addActionListener(this);
		
	}

	/**
	 * Run main main program (HousingGUI) once the 
	 * start button is selected
	 */
	public void actionPerformed(ActionEvent e) {
		//close this frame
		landingFrame.dispose();
		
		//Run Main Program
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HousingGUI().setVisible(true);
            }
        });
	}
}