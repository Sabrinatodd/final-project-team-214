/**
 * This class represents the main class used for our final
 * project. This class will use the data and user input
 * to provide a list of recommended zip codes
 *
 */
public class HomeFinder {
	
	
	/**
	 * this class will serve as the main method.
	 * running and utilizing all of the other methods we create
	 */
	public void run() {
		 java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                new HousingGUI().setVisible(true);
	            }
	        });
	}
}
