import java.io.*;
import java.util.*;


/**
 * This class will be used to read any non-API Data files
 * used for our project and output the data into a HashMap
 * of based on ZipCode
 */
public class DataReader {
	private HashMap<String, ZipCode> homeMap = new HashMap<>();
	private HashMap<String, DailyWeather> weatherMap = new HashMap<>();

	/**
	 * This method parses through a file and logs home prices
	 * based on zip code and the number of bedrooms it has
	 * @param fileName the file that needs to be parse
	 * @param noOfBeds the number of bedrooms the home data is logged for
	 */
	public void readHomeData(String fileName, int noOfBeds){
		File f = new File(fileName);
		try {
			Scanner fileReader = new Scanner(f);
			if (fileReader.hasNextLine())
				fileReader.nextLine();
			while (fileReader.hasNextLine()) {
				readLine(fileReader.nextLine(),noOfBeds);
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find or read file");
		}
		
		/*
		//for each ZipCode, print out the values of each home
		for(String mapKey :homeMap.keySet()){
			System.out.println(mapKey + ": { One bedroom  Price:" + homeMap.get(mapKey).getOneBedroomPrice() + " | "
					 + "Two bedroom   Price:" + homeMap.get(mapKey).getTwoBedroomPrice() + " | "
					 + "Three bedroom  Price:" + homeMap.get(mapKey).getThreeBedroomPrice() + " | "
					 + "Four bedroom  Price:" + homeMap.get(mapKey).getFourBedroomPrice() + " | "
					 + "Five plus bedroom  Price:" + homeMap.get(mapKey).getFivePlusBedroomPrice() + " | "
					 + "Home Price:" + homeMap.get(mapKey).getHomePriceOverall() + " } "
					);
		}
		*/
	}
	
	public void readWeatherDataFiles(String fileName,int temperatureType){
		File f = new File(fileName);
		try {
			Scanner fileReader = new Scanner(f);
			if (fileReader.hasNextLine())
				fileReader.nextLine();
			while (fileReader.hasNextLine()) {
				calculateAverage(fileReader.nextLine(),temperatureType);
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find or read file");
		}
	}
	
	private void calculateAverage(String nextLine, int temperatureType) {
		// TODO Auto-generated method stub
		String[] lineElements = nextLine.split(",");
		int validEntries = 0;
		double total = 0;
		for(int i=1 ;i < lineElements.length ;i++){
			if(!lineElements[i].trim().isEmpty()){
				try{
					total+= Double.parseDouble(lineElements[i]);
					validEntries++;
				}
				catch(Exception ex) {}
				
			}
		}
		double average = total / validEntries;
		String key =  lineElements[0];
		DailyWeather dailyWeather = new DailyWeather("", "",key, 0, 0, 0, 0);
		if(weatherMap.containsKey(key))
			dailyWeather = weatherMap.get(key);		
	
		switch(temperatureType){
			case 1: dailyWeather.setTempMin(average); break;
			case 2: dailyWeather.setTempMax(average); break;
			case 3: dailyWeather.setSnow(average); break;
			case 4: dailyWeather.setPrecipitation(average); break;
		}
		weatherMap.put(key, dailyWeather);
	
	}

	public void readWeatherData()
	{
		readWeatherDataFiles("!CA_TMIN.csv",1);
		readWeatherDataFiles("!CA_TMAX.csv",2);
		readWeatherDataFiles("!CA_SNOW.csv",3);
		readWeatherDataFiles("!CA_PRCP.csv",4);
	}
	
	public static void main(String [] args){
		DataReader reader = new DataReader();
		reader.readWeatherData();
		
	}

	/**
	 * This method parses through a file of census data and
	 * pulls population statistics by Zip Code
	 * 
	 * !!SHOULD LOOK INTO COMBINING THIS METHOD WITH readHomeData
	 * METHOD. WILL HELP WITH DRYNESS
	 * @param censusFile the name of the census file to be read
	 * @return long representing
	 */
	public void readCensusData(String censusFile) {
		//Parse through file
		
		//For each Zip Code in file
			//if there is already a record of the zip
			//in a HashMap with home prices, set the population for that Zip Code
			//with the population listed
		
		
			//if the zip is not already in the hashMap and it is a CA
			//zip, add it to the HashMap with the associated population
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
			ZipCode zip = new ZipCode(0, 0, 0, 0, 0, 0, 0.0, 0.0, 0);	
			if(homeMap.containsKey(key))
				zip = homeMap.get(key);			
			int price = Integer.parseInt(lineElements[293]);				
			switch(noOfBeds){
				case 1: zip.setOneBedroomPrice(price); break;
				case 2: zip.setTwoBedroomPrice(price); break;
				case 3: zip.setThreeBedroomPrice(price); break;
				case 4: zip.setFourBedroomPrice(price); break;
				case 5: zip.setFivePlusBedroomPrice(price); break;
				case 6: zip.setHomePriceOverall(price); break;
			}			
			homeMap.put(key, zip);
		}
		
		
	}
	
	/**
	 * 
	 * @return HashMap of all data by ZipCode
	 */
	public HashMap<String, ZipCode> getHashMapOfHomePrices(){
		return this.homeMap;
	}
}
