import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class will be used to take user input on their home
 * preferences and score 
 */
public class HomeMatchScorer {
	HashMap<String, DataBook> hashOfAllCleanedData;
	//UserInput() userPreferences;
	
	

	
	public HomeMatchScorer(HashMap<String, DataBook> hashOfAllDataByZip) {
		this.hashOfAllCleanedData = hashOfAllDataByZip;
	}
	
	/**
	 * This method provides a score based on the user's preferences
	 * for the home price and number of bedrooms. All values above
	 * the user's preferred home price will be omitted however, as
	 * there budget is nonflexible
	 * @return double representing the score of a zip code based on the user's preferred home price
	 */
	public Double scoreHomeValue(double userPreferredBedrooms, double userBudget, double homeCost) {
		double score = 0.0;
		
		if (homeCost <= userBudget) {
			// When all other scores are equal, we would like to score
			// lower cost zip codes (i.e. those with a larger difference
			// from the user's budget) more highly because it offers
			// an additional saving opportunity for buyers
			score = 1 - (homeCost / userBudget);
		}

		return score;
	}
	
	
	/**
	 * This method scores a given variable based on how close it is to
	 * the user's preferences. In this case, it does not matter if a value
	 * is above or below the user's preferred value, rather just compares 
	 * how close it is. Values closer to the user's preferences are scored
	 * higher.
	 * @return double representing the score of a zip code based on the user's preferred temperature
	 */
	public Double scoreProximity(double preferredValue, double actualValue) {
		
		double difference = Math.abs(actualValue - preferredValue);
		double score = 1 - (difference / preferredValue);

		return score;
	}
	
	/**
	 * This method takes all the data collected for each zip code
	 * and returns an overall score based on the user preferences
	 * collected
	 * 
	 * @param allData HashMap of all data for each Zip Code
	 * @return A sorted ArrayList of Zip Codes from smallest scores
	 * (worst) to largest scores (best)
	 */
	//public HashMap<String, Double> generateTopZipCode(int userPreferredBedrooms, double userBudget, double preferredTemperature, double preferredPrecipitation,
	//		double preferredDaysFreezing, double preferredTotalPopulation, double preferredMedianAge, double preferredAvgHouseHoldSize){
	public ArrayList<String> generateTopZipCode(int userPreferredBedrooms, double userBudget, double preferredTemperature, double preferredPrecipitation,
				double preferredDaysFreezing, double preferredTotalPopulation, double preferredMedianAge, double preferredAvgHouseHoldSize){
		
		HashMap<String, Double> scoresMap = new HashMap<String, Double>();
		
		//for each zip code in the map
		for(String zipCode : hashOfAllCleanedData.keySet()) {
			DataBook locationData = hashOfAllCleanedData.get(zipCode);
			
			//determine which house value to retrieve based on number of bedrooms provided
			double homeValue;
			if(userPreferredBedrooms < 5) {
				homeValue = locationData.get(userPreferredBedrooms - 1);
			}
			else {
				homeValue = locationData.get(5);
			}
			
			double homeScore = scoreHomeValue(userPreferredBedrooms, userBudget, homeValue);
			double temperatureScore = scoreProximity(preferredTemperature, locationData.get(6));
			double precipitationScore = scoreProximity(preferredPrecipitation, locationData.get(7));
			double daysFreezingScore = scoreProximity(preferredDaysFreezing, locationData.get(8));
			double poulationScore = scoreProximity(preferredTotalPopulation, locationData.get(9));
			double medianAgeScore =  scoreProximity(preferredMedianAge, locationData.get(10));
			double avgHouseHoldSizeScore = scoreProximity(preferredAvgHouseHoldSize, locationData.get(11));
			
			

			double totalScore = totalAreaScore(homeScore, temperatureScore, precipitationScore, daysFreezingScore, poulationScore, medianAgeScore, avgHouseHoldSizeScore);

			
			//put zip code and score into hashmap
			scoresMap.put(zipCode, totalScore);
		}
		
		//add Zip Code and total score to HashMap to be returned		
		//once all Zip Codes are scored, rank and sort Zip Codes
		//return hashMap
		return sortByValue(scoresMap);
	}
	
	
	/**
	 * this method provides a total score for a zip code based on its score
	 * across the home value, temperature and precipitation preferences set
	 * by the user
	 * 
	 * @param homeValueScore the score assigned to the zip code based on the user's preferred home price
	 * @param temperatureScore the score assigned to the zip code based on the user's preferred temperature
	 * @param precipitationScore the score assigned to the zip code based on the user's preferred precipitation levels
	 * @return total score across all factors for a given zip code
	 */
	public double totalAreaScore(double homeValueScore, double temperatureScore, double precipitationScore,
			double daysFreezingScore, double totalPopulationScore, double medianAgeScore, double avgHouseHoldSizeScore) {
		//take an average of the home value score, temperature score, and
		//precipitation score to provide an overall score for a zip code
		return (homeValueScore + temperatureScore + precipitationScore + 
				totalPopulationScore + medianAgeScore + avgHouseHoldSizeScore )/ 6;
	}
	
	/**
	 * This method sorts a HashMap from smallest to largest by
	 * value
	 * @param hm HashMap to be sorted
	 * @return ArrayList of keys in order from smallest to largest
	 */
	 public ArrayList<String> sortByValue(HashMap<String, Double> hm) { 
	        // Create a list from elements of HashMap 
	        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(hm.entrySet()); 
	  
	        // Sort the list 
	        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() { 
	            public int compare(Map.Entry<String, Double> o1,  
	                               Map.Entry<String, Double> o2) 
	            { 
	                return (o1.getValue()).compareTo(o2.getValue()); 
	            } 
	        }); 
	          
	        // put data from sorted list to hashmap  
	        ArrayList<String> orderedList = new ArrayList<String>();
	        int i=0;
	        for (Map.Entry<String, Double> aa : list) { 
	        	orderedList.add(i, aa.getKey());
	        	i++;
	        } 
	        return orderedList; 
	    }
	 
	 /**
	  * Given a sorted ArrayList of Strings, this method prints the
	  * top n number of Strings from the bottom of the list. This 
	  * method assumes that the list is ordered smallest to largest
	  * @param allScores ArrayList of Strings sorted smallest to largest
	  * @param numberOfScores the number of results to be printed
	  * 
	  */
	 public void prettyPrintScores(ArrayList<String> allScores, int numberOfScores) {
		 for(int i = 0; i < numberOfScores; i++) {
			 int listSize = allScores.size();
			 System.out.println(i+1 + ". " + allScores.get(listSize - (1 + i)));
		 }
	 }
	 

	 /**
	  * Given a sorted ArrayList of Strings, this method returns the top 
	  * n number of Strings from the bottom of the list. This method assumes
	  * the that the list is ordered smallest to largest. 
	  * @param allScores
	  * @param numberOfScores
	  * @return
	  */
	 public String getTopScores(ArrayList<String> allScores, int numberOfScores) {
		 ArrayList<String> list = new ArrayList<String>();
		 String finalList = "";
		 for(int i = 0; i < numberOfScores; i++) {
			 int listSize = allScores.size();
			 String score = i+1 + ". " + allScores.get(listSize - (1 + i));
			 list.add(score);
		 }
		 finalList = list.get(0) + "\n" + list.get(1) + "\n" + list.get(2) + "\n" + list.get(3) + "\n" + list.get(4);
		 return finalList;
	 }
	 
	 public static void main(String[] args) {
		DataCompiler dc = new DataCompiler();
		HashMap<String, DataBook> allData = dc.compile();
		HomeMatchScorer hms = new HomeMatchScorer(allData);
		
		//HashMap<String, Double> topMatches = s.generateTopZipCode(2, 650000.0, 67.0, 2.2, 3.0, 500000.0, 42.0, 1.7);
		ArrayList<String> topMatches = hms.generateTopZipCode(2, 330000.0, 67.0, 2.2, 3.0, 80000.0, 42.0, 1.7);
		//hms.prettyPrintScores(topMatches, 5);
		String finalList = hms.getTopScores(topMatches, 5);
		System.out.println(finalList);
		
	}
}
