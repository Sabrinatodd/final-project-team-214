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
	JFrame outputFrame = new JFrame("Home Finder");
	JLabel header;

	
	public EndFrame(String zipCodes) {
		
		//Create Frame
		outputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		outputFrame.setVisible(true);
		outputFrame.setLayout(null);
		outputFrame.setSize(400, 400);
		
		//Add Icon Image
		outputFrame.setIconImage(new ImageIcon("icon.png").getImage());
		
		//Add Header
		header = new JLabel("Here are your top Zip Code matches:");
		outputFrame.add(header);
		header.setBounds(100, 30, 250, 30);
		
		//get individual zip codes
		String[] zips = zipCodes.split(", ");
		int yLocation = 60;
		
		//print each zip code in order
		for(String zipCode : zips) {
			JLabel newLabel = new JLabel(zipCode);
			Font f = newLabel.getFont();
			newLabel.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
			outputFrame.add(newLabel);
			newLabel.setBounds(180, yLocation, 60, 30);
			yLocation = yLocation + 20;
		}		
	}
}
