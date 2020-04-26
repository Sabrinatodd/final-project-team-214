import java.awt.Font;
import javax.swing.*;

/**
 * This class presents the final output of top zip code
 * matches for the User in a GUI Frame.
 * 
 * @author john.connolly, michael hoffman, sabrina todd
 *
 */
public class EndFrame {
	JFrame f1 = new JFrame("Home Finder");
	JLabel header;
	JButton b1;

	
	public EndFrame(String zipCodes) {
		
		//Create Frame
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setVisible(true);
		f1.setLayout(null);
		f1.setSize(400, 400);
		
		//Add Header
		header = new JLabel("Here are your top Zip Code matches:");
		f1.add(header);
		header.setBounds(100, 30, 250, 30);
		
		//get individual zip codes
		String[] zips = zipCodes.split(", ");
		int yLocation = 60;
		
		//print each zip code in order
		for(String zipCode : zips) {
			JLabel newLabel = new JLabel(zipCode);
			Font f = newLabel.getFont();
			newLabel.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			f1.add(newLabel);
			newLabel.setBounds(180, yLocation, 60, 30);
			yLocation = yLocation + 20;
		}		
	}
}
