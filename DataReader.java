import java.io.*;
import java.util.*;


/**
 * This class will be used to read any non-API Data files
 * used for our project and output the data into a HashMap
 * of based on ZipCode
 */
public class DataReader {
	private HashMap<String, DataBook> hashOfAllDataByZip = new HashMap<>();
	//private HashMap<String, DailyWeather> weatherMap = new HashMap<>();
	
	private String[] weatherDataFiles;
	private String[] homeDataFiles;
	private String censusDataFile;
	
	/**
	 * To ensure runtime is not long, the WeatherAPI Class,
	 * has already been run to collect and pass weather data
	 * into distinct CSV files. This constructor simply
	 * initializes the these files and adds them into an array.
	 */
	public DataReader() {
		//initialize weather data files
		weatherDataFiles = new String[4];
		weatherDataFiles[0]= "!CA_TMIN.csv";
		weatherDataFiles[1]= "!CA_TMAX.csv";
		weatherDataFiles[2]= "!CA_SNOW.csv";
		weatherDataFiles[3]= "!CA_PRCP.csv";
		
		//initialize home data files
		homeDataFiles = new String[6];
		homeDataFiles[0] = "Zip_Zhvi_1bedroom.csv";
		homeDataFiles[1] = "Zip_Zhvi_2bedroom.csv";
		homeDataFiles[2] = "Zip_Zhvi_3bedroom.csv";
		homeDataFiles[3] = "Zip_Zhvi_4bedroom.csv";
		homeDataFiles[4] = "Zip_Zhvi_5bedroomOrMore.csv";
		homeDataFiles[5] = "Zip_Zhvi_AllHomes.csv";
		
		//initialize census data
		censusDataFile = "!CA_Census_Populations_by_Zip_Code.csv";
	}
	
	/**
	 * This method reads a specific line within a file
	 * of home prices 
	 * @param nextLine a string variable of the line to read
	 * @param noOfBeds the number of bedrooms the home data is logged for
	 */
	private void readLine(String nextLine, int noOfBeds) {
		String[] lineElements = nextLine.split(",");
		
		//For all CA ZipCodes
		if(lineElements[3].equalsIgnoreCase("CA"))
		{
			//make the ZipCode the Key for the hashMap
			String key =  lineElements[1];
			
			//Make a ZipCode object for the value
			DataBook zip = new DataBook(0, 0, 0, 0, 0, 0, 0.0, 0.0, 0, 0, 0.0, 0.0);	
			if(hashOfAllDataByZip.containsKey(key))
				zip = hashOfAllDataByZip.get(key);			
			int price = Integer.parseInt(lineElements[293]);				
			switch(noOfBeds){
				case 1: zip.setOneBedroomPrice(price); break;
				case 2: zip.setTwoBedroomPrice(price); break;
				case 3: zip.setThreeBedroomPrice(price); break;
				case 4: zip.setFourBedroomPrice(price); break;
				case 5: zip.setFivePlusBedroomPrice(price); break;
				case 6: zip.setHomePriceOverall(price); break;
			}			
			hashOfAllDataByZip.put(key, zip);
		}
	}
	
	
	/**
	 * This method parses through a file and logs home prices
	 * based on zip code and the number of bedrooms it has
	 * @param fileName the file that needs to be parse
	 * @param noOfBeds the number of bedrooms the home data is logged for
	 */
	public void readHomeData(){
		int numberOfBeds = 0;
		//go through each file that contains home data
		for(String file : this.homeDataFiles) {
			File f = new File(file);
			numberOfBeds++;
			
			try {
				Scanner fileReader = new Scanner(f);
				//if (fileReader.hasNextLine()) fileReader.nextLine();
				while (fileReader.hasNextLine()) {
					String lineToRead = fileReader.nextLine();
					readLine(lineToRead, numberOfBeds);
				}
				fileReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("Could not find or read file");
			}
		}
	}


	/**
	 * This method goes through each of the CSV files that contains
	 * weather data and returns a HashMap of DailyWeather Objects
	 * by ZipCode.
	 * @return HashMap representing DailyWeather object for each Zip Code available
	 */
	public HashMap<String, DailyWeather> calculateDailyWeatherAverages() {	
		HashMap<String, DailyWeather> allWeatherDataByZipCode = new HashMap<String, DailyWeather>();
		int counter = 0;
		
		//for each weather file, read the data
		for(String weatherFile : this.weatherDataFiles) {
			counter++;
			File f = new File(weatherFile);
			
			try {
				Scanner fileReader = new Scanner(f);
				while (fileReader.hasNextLine()) {
					String lineToRead = fileReader.nextLine();
					HashMap<String, Double> weatherValueByZip = calculateAverage(lineToRead);
					
					//add weather data to the hashmap of dailyWeather objects to be returned
					for(String zip : weatherValueByZip.keySet()) {
						String temporaryZipCode = zip;
						double temporaryWeatherValue = weatherValueByZip.get(zip);
						
						//if zipcode is already listed in array, add the weather value to
						if(allWeatherDataByZipCode.containsKey(temporaryZipCode)) {
							DailyWeather dw = allWeatherDataByZipCode.get(temporaryZipCode);
							
							//add weather data to the daily weather object
							switch(counter){
								case 1: dw.setTempMin(temporaryWeatherValue); break;
								case 2: dw.setTempMax(temporaryWeatherValue); break;
								case 3: dw.setPrecipitation(temporaryWeatherValue); break;
								case 4: dw.setSnow(temporaryWeatherValue); break;
							}	
							
							//update and set back in hashmap
							allWeatherDataByZipCode.replace(temporaryZipCode, dw);
						}
						//otherwise, if zipcode is not already listed in hashmap, then add it
						else {
							DailyWeather dw = new DailyWeather();
							
							//add weather data to the daily weather object
							switch(counter){
								case 1: dw.setTempMin(temporaryWeatherValue); break;
								case 2: dw.setTempMax(temporaryWeatherValue); break;
								case 3: dw.setPrecipitation(temporaryWeatherValue); break;
								case 4: dw.setSnow(temporaryWeatherValue); break;
							}	
							
							//add zip code to hashmap
							allWeatherDataByZipCode.put(temporaryZipCode, dw);
						}
					}
				}
				
				fileReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("Could not find or read file");
			}
		}
		
		return allWeatherDataByZipCode;
	}
	
	
	private HashMap<String, Double> calculateAverage(String nextLine) {
		HashMap<String, Double> dataToReturn = new HashMap<String, Double>();
		String[] lineElements = nextLine.split(",");
		int validEntries = 0;
		double total = 0;
		
		//Sum all values within the line and count the number
		//of data points available
		for(int i = 1 ; i < lineElements.length; i++){
			if(!lineElements[i].trim().isEmpty()){
				try{
					total+= Double.parseDouble(lineElements[i]);
					validEntries++;
				}
				catch(Exception ex) {}
			}
		}
		
		double average = total / validEntries;
		String zipCode =  lineElements[0];
		dataToReturn.put(zipCode, average);
		
		return dataToReturn;
	}
	

	/**
	 * This method is used to clean the weather data into three actionable
	 * data points stored in the location data class. These datapoints include
	 * the monthlyAveragePrecipitation level, averageDailyTemperature, and 
	 * numberOfDaysBelowFreezing.
	 * @param weatherDataHash HashMap of daily averages for maximum temperature, 
	 * minimum temperature, snow fall, and precipitation for each zip code
	 * @return HashMap of cleaned weather data by each zip code. The values included
	 * for each Zip Code(key) include monthlyAveragePrecipitation, verageDailyTemperature, and 
	 * numberOfDaysBelowFreezing.
	 */
	public HashMap<String, ArrayList<Double>> cleanWeatherData (HashMap<String, DailyWeather> weatherDataHash){
		HashMap<String, ArrayList<Double>> cleanedData = new HashMap<String, ArrayList<Double>>();
		
		for(String zipCode : weatherDataHash.keySet()) {
			ArrayList<Double> dataValues = new ArrayList<Double>();
			
			//get monthly precipitation
			double snow = weatherDataHash.get(zipCode).getSnow();
			double rain = weatherDataHash.get(zipCode).getPrecipitation();
			double averageDailyPrecipitation = (snow + rain) / 2;
			double averageMonthlyPrecipitation = (averageDailyPrecipitation * 365) / 12.0;
			
			dataValues.add(0, averageMonthlyPrecipitation);
			
			//get daily average temperature
			double tempMin = weatherDataHash.get(zipCode).getTempMin();
			double tempMax = weatherDataHash.get(zipCode).getTempMax();
			double averageDailyTemperature = (tempMin + tempMax) / 2;
			dataValues.add(1, averageDailyTemperature);
			
			
			//get number of annual days below freezing
			int zipMatch = 0;
			File f = new File(this.weatherDataFiles[0]);
			
			try {
				Scanner fileReader = new Scanner(f);
				
				//go through each line of file until a match for the current zip
				//code if found
				while (fileReader.hasNextLine() && zipMatch == 0) {
					String lineToRead = fileReader.nextLine();
					HashMap<String, Integer> daysFreezingHash = countDaysBelowFreezing(lineToRead, zipCode);
					
					//if the zip in file matches the current zip code
					//then add it to our hashmap
					if(daysFreezingHash.containsKey(zipCode)) {
						zipMatch = 1;
						double numberOfDaysBelowFreezing = (double) daysFreezingHash.get(zipCode);
						dataValues.add(2, numberOfDaysBelowFreezing);
					}
				
				}
				//if we go through the entire file and the current zip code
				//is not included within, set value to zero
				if(zipMatch == 0) {
					dataValues.add(2, 0.0);
				}
				
				fileReader.close();
			} catch(FileNotFoundException e) {
				System.out.println("Could not find or read file");
			}
			
			
			cleanedData.put(zipCode, dataValues);
		}
		
		return cleanedData;
	}
	
	
	/**
	 * This method takes a line of temperature values for a given zip code
	 * and counts the number of days that fall below freezing (32 degrees
	 * fahrenheit). This method will always output 0 unless the supplied zip code
	 * and the zip code within the line of data match.
	 * @param nextLine Line of temperature values
	 * @param zipToMatch the zip code which the temperature values should match
	 * @return HashMap with a single Zip Code Key and the number of days where the
	 * temperature measured below the freezing point
	 */
	private HashMap<String, Integer> countDaysBelowFreezing(String nextLine, String zipToMatch) {
		HashMap<String, Integer> dataToReturn = new HashMap<String, Integer>();
		String[] lineElements = nextLine.split(",");	
		int counter = 0;
		String zipInFile =  lineElements[0];
		
		//make sure zip code of focus and zip code in file match
		if(zipToMatch.equals(zipInFile)) {
			
			//For every data point in the given line
			for(int i = 1 ; i < lineElements.length; i++) {
				
				//make sure line isnt empty
				if(!lineElements[i].trim().isEmpty()) {
					
					//turn the string datapoints into and integer
					double temperatureValue = Double.parseDouble(lineElements[i]);
					
					//if the minimum temperature is below freezing (32 degrees farhenheit)
					//then count it
					if(temperatureValue <= 32.0) {
						counter++;
					}
				}
			}
		}
		dataToReturn.put(zipInFile, counter);
		
		return dataToReturn;
	}
	
	
	/**
	 * This method takes a hashmap of cleaned weather data and adds it
	 * into the master hashmap of all weather, home price and population
	 * data.
	 * @param cleanWeatherData hashmap of cleaned weather data where the keys
	 * represent individual zip codes
	 */
	public void addWeatherDataToZipCode(HashMap<String, ArrayList<Double>> cleanWeatherData) {
		
		for(String s : cleanWeatherData.keySet()) {
			if(this.hashOfAllDataByZip.containsKey(s)) {
				DataBook tempZipObject = this.hashOfAllDataByZip.get(s);
				tempZipObject.setMonthlyPrecipitation(cleanWeatherData.get(s).get(0));
				tempZipObject.setDailyTemperature(cleanWeatherData.get(s).get(1));
				tempZipObject.setAnnualDaysBelowFreezing(cleanWeatherData.get(s).get(2));
			}
		}
	}
	
	

	/**
	 * This method parses through the file with census data
	 * and stores the desired variables of total population,
	 * median population age, and average household size into
	 * a hashmap with by Zip Code
	 * @return HashMap with Keys for each Zip Code in Census file with data
	 * for desired variables in an arraylist of strings
	 */
	public HashMap<String, ArrayList<String>> readCensusData() {
		HashMap<String, ArrayList<String>> censusData = new HashMap<String, ArrayList<String>>();
		File f = new File(this.censusDataFile);

		try {
			Scanner fileReader = new Scanner(f);

			// for each zip code in file
			int rowNum = 1;
			while (fileReader.hasNextLine()) {
				String lineToRead = fileReader.nextLine();
				
				if(rowNum > 10) {
					ArrayList<String> dataTemporarilyStored = new ArrayList<String>();
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
				}
				rowNum++;
			}

			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find or read file");
		}
		
		return censusData;
	}
	
	public void storeCensusData(HashMap<String, ArrayList<String>> cleanedCensusData) {
		
		for(String zipCode : cleanedCensusData.keySet()) {
			ArrayList<String> zipCensusData = cleanedCensusData.get(zipCode);
			
			//if zip code is already in hashmap that add it
			if(this.hashOfAllDataByZip.containsKey(zipCode)) {
				DataBook tempZipObject = this.hashOfAllDataByZip.get(zipCode);
				
				//if the population is greater than 1,000
				if(zipCensusData.get(0).contains(",")){
					String populationTrim = zipCensusData.get(0).substring(1, zipCensusData.get(0).length() - 1);
					long pop = Long.valueOf(populationTrim.replaceAll(",", "").toString());
					tempZipObject.setTotalPopulation(pop);
				}
				else {
					tempZipObject.setTotalPopulation(Long.parseLong(zipCensusData.get(0)));
				}
				
				tempZipObject.setMedianAge(Double.parseDouble(zipCensusData.get(1)));
				tempZipObject.setAverageHouseholdSize(Double.parseDouble(zipCensusData.get(2)));
			}
		}
	}
	
	
	
	
	/**
	 * This is the primary method used in this class. It uses
	 * each of the other methods in the class to create and update
	 * the master hash map of all location data for each zip code
	 * @return hashmap of all data sorted by zip code
	 */
	public HashMap<String, DataBook> readAllData(){
		//get home data
		readHomeData();
		
		//get weather data
		HashMap<String, DailyWeather> weatherData = calculateDailyWeatherAverages();
		HashMap<String, ArrayList<Double>> cleanData = cleanWeatherData(weatherData);
		addWeatherDataToZipCode(cleanData);
		
		//get population data
		HashMap<String, ArrayList<String>> censusData = readCensusData();
		storeCensusData(censusData);
		
		return this.hashOfAllDataByZip;
		
	}
	
	//for testing only
	public static void main(String [] args){
		DataReader reader = new DataReader();
		reader.readAllData();
		System.out.println();
	}
	
	
	/**
	 * @return HashMap of all data by ZipCode
	 */
	public HashMap<String, DataBook> getHashMapOfHomePrices(){
		return this.hashOfAllDataByZip;
	}
}
