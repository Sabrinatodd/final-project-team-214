/**
 * This class will be used  to take user input on their home
 * preferences and score 
 *
 */
public class HomeMatchScorer {
	
	/**
	 * this method provides a score based on the user's preferences
	 * for home price in a given area 
	 * @return double representing the score of a zip code based on the user's preferred home price
	 */
	public double scoreHomeValue(ArrayList<ZipCode>cleanedHomeValueData) {
		
	}
	
	/**
	 * this method provides a score based on the user's preferences
	 * for temperature in a given area throughout the year
	 * @return double representing the score of a zip code based on the user's preferred temperature
	 */
	public double scoreTemperatureRequirements(ArrayList<DailyWeather>cleanedWeatherData) {
		
	}
	
	/**
	 * this method provides a score based on the user's preferences
	 * for precipitation in a given area
	 * @return double representing the score of a zip code based on the user's preferred precipitation levels
	 */
	public double scorePrecipitationRequirements(ArrayList<DailyWeather>cleanedWeatherData) {
		
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
		
	}
}
