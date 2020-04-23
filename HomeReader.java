import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HomeReader extends DataReader{
	private HashMap<String, DataBook> bookOfHomeData;
	private String[] homeDataFiles;
	
	/**
	 * This constructor serves to instantiate the HashMap of 
	 * zip codes and databook objects that will store all of 
	 * the home data collected and insert the name of the
	 * home files to be read.
	 */
	public HomeReader() {
		//initialize home data files
		homeDataFiles = new String[6];
		homeDataFiles[0] = "Zip_Zhvi_1bedroom.csv";
		homeDataFiles[1] = "Zip_Zhvi_2bedroom.csv";
		homeDataFiles[2] = "Zip_Zhvi_3bedroom.csv";
		homeDataFiles[3] = "Zip_Zhvi_4bedroom.csv";
		homeDataFiles[4] = "Zip_Zhvi_5bedroomOrMore.csv";
		homeDataFiles[5] = "Zip_Zhvi_AllHomes.csv";
		
		bookOfHomeData = new HashMap<String, DataBook>();
	}
	
	
	@Override
	/**
	 * This method overrides that designated in the DataReader superclass.
	 * It is used to read all home data and add it to the main HashMap
	 * instantiated in the constructor.
	 */
	public HashMap<String, DataBook> readData(File fileToRead, int fileNumber) {
		try {
			Scanner fileReader = new Scanner(fileToRead);
			while (fileReader.hasNextLine()) {
				String lineToRead = fileReader.nextLine();
				
				//read line of text and get values
				HashMap<String, ArrayList<String>> homeValue = readLine(lineToRead, fileNumber);
				
				//store values in ourr instance variable
				storeData(homeValue, fileNumber);
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find or read file");
		}

		return bookOfHomeData;
	}
	
	
	/**
	 * This method takes an ArrayList of home data and stores it 
	 * into the main HashMap of all home Data.
	 * 
	 * @param homeData HashMap of home data by zip code
	 * @param numberOfBedrooms the number of bedrooms the data represents. Used to correctly
	 * place data in a DataBook object
	 */
	private void storeData(HashMap<String, ArrayList<String>> homeData, int numberOfBedrooms) {
		
		for (String zipCode : homeData.keySet()) {
			double homeValue = Double.parseDouble(homeData.get(zipCode).get(0));
			
			//if zip code already has data entered within the home data
			//hashmap then pull that databook object, otherwise create a
			//new object
			DataBook db;
			if (bookOfHomeData.containsKey(zipCode)) {
				db = bookOfHomeData.get(zipCode);
			} else {
				db = new DataBook();
			}
			
			//set the home value based on the number of bedrooms in a
			//given home. this corresponds with the file number in the
			//ReadData method.
			switch (numberOfBedrooms) {
			case 1:
				db.setOneBedroomPrice(homeValue);
				break;
			case 2:
				db.setTwoBedroomPrice(homeValue);
				break;
			case 3:
				db.setThreeBedroomPrice(homeValue);
				break;
			case 4:
				db.setFourBedroomPrice(homeValue);
				break;
			case 5:
				db.setFivePlusBedroomPrice(homeValue);
				break;
			case 6:
				db.setHomePriceOverall(homeValue);
				break;
			}
			
			//place all data in the hashmap
			bookOfHomeData.put(zipCode, db);
		}
	}
	
	/**
	 * This method takes a line from a home data file and reads it
	 * to collect the data needed to measure against user input. It then 
	 * outputs it as a HashMap containing the Zip Code as the key with
	 * the data stored in an ArrayList of Strings.
	 * 
	 * @param lineToRead the line of home data to read
	 * @param numberOfBeds
	 * @return HashMap containing the Zip Code as the key with
	 * the home data stored in an ArrayList of Strings
	 */
	private HashMap<String, ArrayList<String>> readLine(String lineToRead, int numberOfBeds) {
		HashMap<String, ArrayList<String>> homeData = new HashMap<String, ArrayList<String>>();
		String[] lineElements = lineToRead.split(",");

		// For all CA ZipCodes
		if (lineElements[3].equalsIgnoreCase("CA")) {
			String zipCode = lineElements[1];
			
			// Get the 2019 Zillow Average Home Value for a home with
			ArrayList<String> homeValue = new ArrayList<String>();
			homeValue.add(lineElements[293]);
			
			//insert it into a hashmap to be returned
			homeData.put(zipCode, homeValue);
		}
		return homeData;
	}
	

	/**
	 * @return HashMap of all home data
	 */
	public String[] getHomeDataFiles() {
		return this.homeDataFiles;
	}
}
