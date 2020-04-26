import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
		
		//Make Frame Visible
		landingFrame.setSize(400, 400);
		landingFrame.setVisible(true);
		landingFrame.setLayout(null);
		landingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		//Set IconImage
		ImageIcon icon = new ImageIcon("icon.png");
		Image img = icon.getImage();
		landingFrame.setIconImage(img);
		
		//Add Icon Logo
		JLabel iconLabel = new JLabel(new ImageIcon(img.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
		landingFrame.add(iconLabel);
		iconLabel.setBounds(120, 30, 150, 150);
		
		//Add Header
		title = new JLabel("Welcome to Home Finder!");
		landingFrame.add(title);
		title.setBounds(125, 200, 200, 30);
		
		//Add Description
		description = new JLabel("Let's find your next California home");
		Font f = description.getFont();
		description.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
		landingFrame.add(description);
		description.setBounds(100, 220, 300, 30);
		
		//Add Button
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