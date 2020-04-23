import java.io.File;
import java.util.*;

/**
 * This class serves as the parent (super) class to read the data used for our
 * project. Because we are using three different types of data in this project,
 * (home, census, weather),  these files can utilize 
 *
 */
public abstract class DataReader {
	/**
	 * Three different types of data are read and used in this project,
	 * (home, census, weather), each one will need their own method to
	 * read as the data output varies between files.
	 * @param f the file to be read
	 * @param fileNumber the file number being read in a sequence
	 * @return HashMap of String Zip Codes as Keys, and DataBook objects
	 * holding respective data variable(s)
	 */
	public abstract HashMap<String, DataBook> readData(File f, int fileNumber);
	
	/**
	 * The method is used to loop through a series of files to collect the
	 * data available in each file
	 * @param dataFiles String Array of files to be looped
	 * @return HashMap of String Zip Codes as Keys, and DataBook objects
	 * holding respective data variable(s) 
	 */
	public HashMap<String, DataBook> loopFiles(String[] dataFiles) {
		HashMap<String, DataBook> fileData = new HashMap<String, DataBook>();
		int fileNumber = 1;
		
		for(String file : dataFiles) {
			File f = new File(file);
			fileData = readData(f, fileNumber);
			fileNumber++;
		}
		
		return fileData;
	}
	
	/**
	 * This method merges two hashmaps that contain data across the different
	 * data types (home, demographic, weather) into a single hashmap to output
	 * as a means to consolidate all data.
	 * @param dataset1 the first HashMap of data to be merged
	 * @param dataset2 the second HashMap of data to be merged
	 * @return HashMap of all data into a single hashmap
	 */
	public HashMap<String, DataBook> mergeData(HashMap<String, DataBook> dataset1, HashMap<String, DataBook> dataset2) {
		HashMap<String, DataBook> mergedData = new HashMap<String, DataBook>();
		
		//go through the first dataset and determine to merge the data
		//with dataset2 if the zip code appears in both datasets. Otherwise
		//add the data 
		for (String zipCode : dataset1.keySet()) {
			DataBook db1 = dataset1.get(zipCode);
			
			//for zip codes that appear in both datasets
			//merge the data by summing the variables together.
			//there should never be an instance where two datasets have
			//values for the same variable
			if (dataset2.containsKey(zipCode)) {
				DataBook db2 = dataset2.get(zipCode);
				DataBook mergedDB = new DataBook();

				for (int i = 0; i < 12; i++) {
					double sum = db1.get(i) + db2.get(i);
					mergedDB.put(sum, i);
				}
				
				mergedData.put(zipCode, mergedDB);
			}
			//add data from the first data set that does not
			//have a match in the second dataset
			else {
				mergedData.put(zipCode, db1);
			}
		}
		
		//now ensure that there are no zip codes in the second
		//dataset that are not accounted for.
		for(String zip : dataset2.keySet()) {
			if(!dataset1.containsKey(zip)) {
				mergedData.put(zip, dataset2.get(zip));
			}
		}
		
		return mergedData;
	}
}
