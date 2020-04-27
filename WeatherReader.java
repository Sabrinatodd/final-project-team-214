import java.io.*;
import java.util.*;

/**
 * As a child class to the DataReader class, this
 * class cleans and outputs the final weather data
 * to be used.
 */
public class WeatherReader extends DataReader {
	HashMap<String, DataBook> bookOfAllWeatherData;
	HashMap<String, DailyWeather> rawWeatherData;
	HashMap<String, ArrayList<Double>> cleanWeatherData;
	private String[] weatherDataFiles;
	
	/**
	 * This constructor serves to instantiate the HashMap of 
	 * zip codes and databook objects that will store all of 
	 * the weather data collected and insert the name of the
	 * weather files to be read.
	 */
	public WeatherReader() {
		//initialize weather data files
		weatherDataFiles = new String[4];
		weatherDataFiles[0]= "!CA_TMIN.csv";
		weatherDataFiles[1]= "!CA_TMAX.csv";
		weatherDataFiles[2]= "!CA_SNOW.csv";
		weatherDataFiles[3]= "!CA_PRCP.csv";
		
		bookOfAllWeatherData = new HashMap<String, DataBook>();
		rawWeatherData = new HashMap<String, DailyWeather>();
		cleanWeatherData = new HashMap<String, ArrayList<Double>>();
	}

	/**
	 * This method overrides that designated in the DataReader superclass.
	 * It is used to read all weather data and add it to the main HashMap
	 * instantiated in the constructor.
	 */
	public HashMap<String, DataBook> readData(File f, int fileNumber) {
		try {
			Scanner fileReader = new Scanner(f);
			while (fileReader.hasNextLine()) {
				String lineToRead = fileReader.nextLine();
				
				//calculate the daily average for each type of weather data field
				HashMap<String, Double> weatherValueByZip = calculateDailyAverages(lineToRead);
				
				//use the daily average of each weather data type to convert into
				//the desired weather data used in scoring
				collectRawWeatherData(weatherValueByZip, fileNumber);
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find or read file");
		}
		
		//clean the hash of all raw data 
		cleanWeatherData = cleanWeatherData(rawWeatherData);
		storeData();

		return this.bookOfAllWeatherData;
	}
	
	
	/**
	 * This method is used to take a HashMap of cleaned weather data
	 * and store it into a HashMap of DataBook objects sorted by 
	 * zip code
	 */
	private void storeData() {
		for(String s : cleanWeatherData.keySet()) {
			DataBook tempZipObject = new DataBook();
			tempZipObject.setMonthlyPrecipitation(cleanWeatherData.get(s).get(0));
			tempZipObject.setDailyTemperature(cleanWeatherData.get(s).get(1));
			tempZipObject.setAnnualDaysBelowFreezing(cleanWeatherData.get(s).get(2));
			
			this.bookOfAllWeatherData.put(s, tempZipObject);
		}
	}

	/**
	 * This method takes a line of data from a weather file and calculates the 
	 * 2019 daily average for that data variable
	 * 
	 * @param nextLine line of data from the weather file
	 * @return HashMap with the zip code and daily average for the associated
	 * weather variable
	 */
	private HashMap<String, Double> calculateDailyAverages(String nextLine) {
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
	 * 
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
			File f = new File(this.weatherDataFiles[0]);
			String lineToRead = parseFileForZipCode(f, zipCode);

			if (lineToRead != null) {
				HashMap<String, Integer> daysFreezingHash = countDaysBelowFreezing(lineToRead, zipCode);
				double numberOfDaysBelowFreezing = (double) daysFreezingHash.get(zipCode);
				dataValues.add(2, numberOfDaysBelowFreezing);
			} else {
				dataValues.add(2, 0.0);
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
	 * 
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
	 * This method is used to build a HashMap of raw weather data 
	 * (max temperature daily average, min temperature daily average,
	 * etc.) that can be used to clean, calculate and store the
	 * weather variables needed for scoring.
	 * 
	 * @param weatherValues HashMap of daily weather average for a given zip code
	 * @param fileType the type of weather file. Used to determine which type of 
	 * weather data has been collected (Max Temp, Min Temp, Precipitation, Snow)
	 */
	public void collectRawWeatherData(HashMap<String, Double> weatherValues, int fileType) {
		//add weather data to the hashmap of dailyWeather objects to be returned
		for(String zip : weatherValues.keySet()) {
			String temporaryZipCode = zip;
			double temporaryWeatherValue = weatherValues.get(zip);
			
			//if zipcode is already listed in array, add the weather value to
			DailyWeather dw;
			if(rawWeatherData.containsKey(temporaryZipCode)) {
				dw = rawWeatherData.get(temporaryZipCode);
			}
			else {
				dw = new DailyWeather();
			}
				
				//add weather data to the daily weather object
				switch(fileType){
					case 1: dw.setTempMin(temporaryWeatherValue); break;
					case 2: dw.setTempMax(temporaryWeatherValue); break;
					case 3: dw.setPrecipitation(temporaryWeatherValue); break;
					case 4: dw.setSnow(temporaryWeatherValue); break;
				}	
				
				//update and set back in hashmap
				rawWeatherData.put(temporaryZipCode, dw);
			
		}
	}
	

	/**
	 * This method parses through a file to determine if a particular zip code
	 * is listed in the file and, if so, outputs the String
	 * of data for that zip code.
	 * 
	 * @param f file to parse
	 * @param zipCode the zip code that is trying to be matched/found
	 * @return String of data for given zip code. Null if N/A
	 */
	public String parseFileForZipCode(File f, String zipCode) {
		String output = null;
		int zipMatch = 0;
		try {
			Scanner fileReader = new Scanner(f);
			
			//go through each line of file until a match for the current zip
			//code is found
			while (fileReader.hasNextLine() && zipMatch == 0) {
				String currentLine = fileReader.nextLine();
				
				//make sure zipcode in string doesnt match the 
				//zip code being targeted

				if(currentLine.substring(0,5).equals(zipCode)) {
					zipMatch = 1;
					output = currentLine;
				}
			}
			
			fileReader.close();
		} catch(FileNotFoundException e) {
			System.out.println("Could not find or read file");
		}
		
		return output;
	}

	
	/**
	 * @return HashMap of all Weather Data
	 */
	public String[] getWeatherDataFiles() {
		return weatherDataFiles;
	}
}
