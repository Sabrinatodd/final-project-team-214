import java.io.*;
import java.util.*;

/**
 * As a child class to the DataReader class, this
 * class cleans and outputs the final census data
 * to be used.
 */
public class CensusReader extends DataReader {
	private HashMap<String, DataBook> bookOfCensusData;
	private String[] censusDataFile;
	
	/**
	 * This constructor serves to instantiate the HashMap of 
	 * zip codes and databook objects that will store all of 
	 * the census data collected and insert the name of the
	 * census file to be read.
	 */
	public CensusReader() {
		//initialize census data
		censusDataFile = new String[1];
		censusDataFile[0] = "!CA_Census_Populations_by_Zip_Code.csv";
		
		bookOfCensusData = new HashMap<String, DataBook>();
	}
	
	@Override
	/**
	 * This method overrides that designated in the DataReader superclass.
	 * It is used to read all census data and add it to the main HashMap
	 * instantiated in the constructor.
	 */
	public HashMap<String, DataBook> readData(File f, int fileNumber) {
		try {
			Scanner fileReader = new Scanner(f);
			while (fileReader.hasNextLine()) {
				String lineToRead = fileReader.nextLine();

				// filter out rows that do not contain zip code data
				if (lineToRead.substring(0, 5).equals("ZCTA5")) {
					//for lines in the file that contain data related to the Zillow Zip Code, read the
					//data into a temporary HashMap
					HashMap<String, ArrayList<String>> demographicData = readLine(lineToRead, fileNumber);
					
					//store data into the 
					storeData(demographicData, fileNumber);
				}
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find or read file");
		}

		return this.bookOfCensusData;
	}

	/**
	 * This method takes an ArrayList of demographic data and stores it 
	 * into the main HashMap of all Census Data.
	 * 
	 * @param demographicData HashMap of demographic data to be stored
	 * @param numberOfBedrooms not used, kept it in case I wanted to make this method
	 * an abstract method in the parent class
	 */
	private void storeData(HashMap<String, ArrayList<String>> demographicData, int numberOfBedrooms) {
		for (String zipCode : demographicData.keySet()) {
			
			//if zip code already has data entered within the main census data
			//hashmap then pull that databook object, otherwise create a
			//new object
			DataBook db;
			if (bookOfCensusData.containsKey(zipCode)) {
				db = bookOfCensusData.get(zipCode);
			} else {
				db = new DataBook();
			}
			
			String populationString = demographicData.get(zipCode).get(0);
			String medianAgeString = demographicData.get(zipCode).get(1);
			String houseSizeString = demographicData.get(zipCode).get(2);
			
			//if the population is greater than 1,000
			if(populationString.contains(",")){
				String populationTrim = populationString.substring(1, populationString.length() - 1);
				Double pop = Double.valueOf(populationTrim.replaceAll(",", "").toString());
				db.setTotalPopulation(pop);
			}
			else {
				db.setTotalPopulation(Long.parseLong(populationString));
			}
			
			//add median age
			db.setMedianAge(Double.parseDouble(medianAgeString));
			
			//add average household size
			if(!houseSizeString.isEmpty()) {
				db.setAverageHouseholdSize(Double.parseDouble(houseSizeString));
			}
			else {
				db.setAverageHouseholdSize(0.0);
			}
			
			//place all data in the hashmap
			this.bookOfCensusData.put(zipCode, db);
		}
	}

	/**
	 * This method takes a line from the census data file and reads it
	 * to collect the data needed to measure against user input. It then 
	 * outputs it as a HashMap containing the Zip Code as the key with
	 * the data stored in an ArrayList of Strings.
	 * 
	 * @param lineToRead the line of census data to read
	 * @param indexCounter not used, kept it in case I wanted to make this method
	 * an abstract method in the parent class
	 * @return HashMap containing the Zip Code as the key with
	 * the data stored in an ArrayList of Strings
	 */
	private HashMap<String, ArrayList<String>> readLine(String lineToRead, int indexCounter) {
		HashMap<String, ArrayList<String>> censusData = new HashMap<String, ArrayList<String>>();
		ArrayList<String> dataTemporarilyStored = new ArrayList<String>();
		
		// Each field of data is seperated by a comma, but some fields (population)
		// contain a comma in their value. This we want to split the line element
		// only when 
		String[] lineElements = lineToRead.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

			// collect data used in our project
			// temporarily store variables as strings, they will be converted
			// when being added into the DataBook
			String zipCode = lineElements[0].substring(6, 11);
			dataTemporarilyStored.add(0, lineElements[1]); //population
			dataTemporarilyStored.add(1, lineElements[4]); //median Age
			dataTemporarilyStored.add(2, lineElements[7]); //average Household Size
			
			//store in hashmap
			censusData.put(zipCode, dataTemporarilyStored);
		
		return censusData;
	}

	/**
	 * @return HashMap of all Census Data
	 */
	public String[] getCensusDataFile(){
		return this.censusDataFile;
	}
}
