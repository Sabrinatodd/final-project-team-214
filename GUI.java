import javax.swing.*;        

/**
 * This class creates a swing-based GUI. 
 * The user will use it to navigate through aspects of the shopping experience
 * 
 * @author michaelhoffmann johnconnolly sabrinatodd
 *
 */
public class GUI {
 
	/**
     * Creates the GUI and shows it. 
     */
    private static void createAndShowGUI() {
        //Create and set up the window that welcomes the user.
        JFrame frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Begins user experience 
        JLabel label = new JLabel("Are you ready to buy your next home?");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    
    /**
     * Tests the GUI welcome screen
     * @param args
     */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
