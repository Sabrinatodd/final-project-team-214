import java.util.*;

/**
 * This class will be used  to take user input on their home
 * preferences and score 
 *
 */
public class HomeMatchScorer {
	
	/**
	 * this method provides a score based on the user's preferences
	 * for the home price and number of bedrooms in a Zip Code
	 * @return double representing the score of a zip code based on the user's preferred home price
	 */
	public double scoreHomeValue(ArrayList<ZipCode> cleanedHomeValueData, int userPreferredBedrooms, long userBudget) {
		
		//for each zip code test to see if home price value is below the 
		//user's budget given the number of bedrooms they wish to have
		
		//take all Zip Codes that fall below budget and determine how much
		//lower than the budget the average home price is. A large
		//difference will be scored more highly because it demonstrates
		//a saving opportunity for the buyers.
		int nCount = 0;
		int total = 0;
		
		for( ZipCode homeData :cleanedHomeValueData){
			int homePrice = homeData.getPriceByRooms(userPreferredBedrooms);
			if(homePrice <= userBudget){
				total += homePrice;
				nCount++;
			}
		}
		double averageHomePrice  =  total / nCount;
		// Add score logic
		
		return averageHomePrice;
	}
	
	
	/**
	 * this method provides a score based on the user's preferences
	 * for temperature in a given area throughout the year
	 * @return double representing the score of a zip code based on the user's preferred temperature
	 */
	public double scoreTemperatureRequirements(ArrayList<DailyWeather> cleanedWeatherData, double preferredTemperate) {
		//determine the average daily temperature by finding the
		//median between the yearly min and max
		
		//score Zip Codes based on how closely they score to the user's provided
		//temperature preference
		
		int nCount = 0;
		int total = 0;

		for( DailyWeather weatherData :cleanedWeatherData){
			double temperature = (weatherData.getTempMin() + weatherData.getTempMax()) / 2;
			if(temperature <= preferredTemperate){
				total += temperature;
				nCount++;
			}
		}
		double averageTemperature  =  total / nCount;
		
		// Add score logic
		
		return averageTemperature;
	}
	
	/**
	 * this method provides a score based on the user's preferences
	 * for precipitation in a given area
	 * @return double representing the score of a zip code based on the user's preferred precipitation levels
	 */
	public double scorePrecipitationRequirements(ArrayList<DailyWeather>cleanedWeatherData,double preferredPrecipitation) {
		
		//determine the average daily temperature by finding the
		//median between the yearly min and max
		
		//score Zip Codes based on how closely they score to the user's provided
		//temperature preference
		
		int nCount = 0;
		int total = 0;

		for( DailyWeather weatherData :cleanedWeatherData){
			double precipitation = weatherData.getPrecipitation();
			if(precipitation <= preferredPrecipitation){
				total += precipitation;
				nCount++;
			}
		}
		double averagePrecipitation  =  total / nCount;
		
		// Add score logic
		
		return averagePrecipitation;
	}
	
	/**
	 * this method provides a total score for a zip code based on its score
	 * across the home value, temperature and precipitation preferences set
	 * by the user
	 * @param homeValueScore the score assigned to the zip code based on the user's preferred home price
	 * @param temperatureScore the score assigned to the zip code based on the user's preferred temperature
	 * @param precipitationScore the score assigned to the zip code based on the user's preferred precipitation levels
	 * @return total score across all factors for a given zip code
	 */
	public double totalAreaScore(ZipCode zip, double homeValueScore, double temperatureScore, double precipitationScore) {
		//take an average of the home value score, temperature score, and
		//precipitation score to provide an overall score for a zip code
		return (homeValueScore + temperatureScore + precipitationScore)/ 3;
	}
	
	/**
	 * This method takes all the data collected for each zip code
	 * and returns an overall score based on the user preferences
	 * collected
	 * @param allData HashMap of all data for each Zip Code
	 * @return HashMap that maps and ranks overall scores to each Zip Code in CA
	 */
	public HashMap<String, Double> topZipCodes(HashMap<String, ZipCode> allData, ArrayList<DailyWeather>cleanedWeatherData){
		//for each zip code in provided hashmap, collect an totalAreaScore
		
		//add Zip Code and total score to HashMap to be returned
		
		//once all Zip Codes are scored, rank and sort Zip Codes
		
		//return hashMap
		
		HashMap<String, Double>  scoreMap = new HashMap<>();
		for(String zipcode : allData.keySet()){
			double totalScore = totalAreaScore(allData.get(zipcode).getHomePriceOverall() , cleanedWeatherData.get(index) , precipitationScore)
		}
	}
}
