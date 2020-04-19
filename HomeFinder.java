import java.util.HashMap;

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
		
		//Read and clean all data to be analyzed
		DataReader reader = new DataReader();
		HashMap<String, DataBook> allLocationData = reader.readAllData();
		
		//Use GUI to get User Input on desirable home characteristics
		
		//Parse through data and score ZipCodes based on User Input Match
		HomeMatchScorer scorer = new HomeMatchScorer(allLocationData);
		
		//Rank ZipCodes by their assigned score
		
		//Output ZipCodes to User
	}
}