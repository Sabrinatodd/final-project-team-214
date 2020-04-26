import javax.swing.*;

import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The class provides the landing frame for our application and 
 * links the remaining frames together for a cohesive experience.
 * @author john.connolly
 */
public class GUIRunner implements ActionListener {
	JFrame landingFrame = new JFrame("Home Finder");
	JLabel title;
	JLabel description;
	JButton startButton;

	/**
	 * This constructor initializes a simple home/landing
	 * frame that starts our program
	 * 
	 */
	GUIRunner() {
		
		landingFrame.setSize(400, 400);
		landingFrame.setVisible(true);
		landingFrame.setLayout(null);
		landingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		//Add Icon
		try {
			ImageIcon icon = new ImageIcon(new URL("https://im5.ezgif.com/tmp/ezgif-5-e8ecb9bb159c.png"));
			landingFrame.setIconImage(icon.getImage());
			
			Image image =  icon.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(image);
			JLabel iconLabel = new JLabel(scaledIcon);
			landingFrame.add(iconLabel);
			iconLabel.setBounds(130, 60, 80, 80);
		} catch (MalformedURLException e) {
		}
		*/
		
		//Main Headline
		title = new JLabel("Welcome to Home Finder!");
		landingFrame.add(title);
		title.setBounds(125, 30, 200, 30);
		
		//Add Description
		description = new JLabel("Let's find your next California home");
		Font f = description.getFont();
		description.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
		landingFrame.add(description);
		description.setBounds(100, 150, 300, 30);
		
		//Add Start Button
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