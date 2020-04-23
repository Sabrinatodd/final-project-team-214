import java.util.*;

/**
 * This class will be used  to take user input on their home
 * preferences and score 
 */
public class HomeMatchScorer {
	HashMap<String, DataBook> hashOfAllCleanedData;
	
	public HomeMatchScorer(HashMap<String, DataBook> hashOfAllDataByZip) {
		this.hashOfAllCleanedData = hashOfAllDataByZip;
	}
	
	/**
	 * this method provides a score based on the user's preferences
	 * for the home price and number of bedrooms in a Zip Code
	 * @return double representing the score of a zip code based on the user's preferred home price
	 */
	public HashMap<String, Double> scoreHomeValue(int userPreferredBedrooms, long userBudget) {
		HashMap<String, Double> homeScores = new HashMap<String, Double>();
		
		//take all Zip Codes that fall below budget and determine how much
		//lower than the budget the average home price is
		for(String zipCode : hashOfAllCleanedData.keySet()) {
			DataBook allData = hashOfAllCleanedData.get(zipCode);
			int homePrice = allData.getPriceByRooms(userPreferredBedrooms);
			double score = 0.0;
			if(homePrice <= userBudget) {
				//When all other scores are equal, we would like to score
				//lower cost zip codes (i.e. those with a larger difference
				//from the user's budget) more highly because it offers 
				//an additional saving opportunity for buyers
				score = 1 - (homePrice / userBudget);
			}
			
			homeScores.put(zipCode, score);
		}
		
		return homeScores;
	}
	
	
	/**
	 * this method provides a score based on the user's preferences
	 * for temperature in a given area throughout the year
	 * @return double representing the score of a zip code based on the user's preferred temperature
	 */
	public HashMap<String, Double> scoreTemperatureRequirements(double preferredTemperature) {
		//determine the average daily temperature by finding the
		//median between the yearly min and max
		
		//score Zip Codes based on how closely they score to the user's provided
		//temperature preference
		HashMap<String, Double> temperatureScores = new HashMap<String, Double>();
		for(String zipCode : hashOfAllCleanedData.keySet()) {
			DataBook allData = hashOfAllCleanedData.get(zipCode);
			double score = 0.0;
			if(allData.getDailyTemperature()  <= preferredTemperature) {
				score = 1 - (allData.getDailyTemperature()  / preferredTemperature);
			}
			temperatureScores.put(zipCode, score);
		}
		
		return temperatureScores;
	}
	
	/**
	 * this method provides a score based on the user's preferences
	 * for precipitation in a given area
	 * @return double representing the score of a zip code based on the user's preferred precipitation levels
	 */
	public HashMap<String, Double> scorePrecipitationRequirements(double preferredPrecipitation) {
		
		//determine the average daily temperature by finding the
		//median between the yearly min and max
		
		//score Zip Codes based on how closely they score to the user's provided
		//temperature preference
		
		HashMap<String, Double> precipitationScores = new HashMap<String, Double>();
		for(String zipCode : hashOfAllCleanedData.keySet()) {
			DataBook allData = hashOfAllCleanedData.get(zipCode);
			double score = 0.0;
			if(allData.getMonthlyPrecipitation()  <= preferredPrecipitation) {
			
				score = 1 - (allData.getMonthlyPrecipitation()  / preferredPrecipitation);
			}
			
			precipitationScores.put(zipCode, score);
		}		
		return precipitationScores;
	
	}
	
	public HashMap<String, Double> scoreTotalPopulation(double preferredPopulation) {
			
			//score Zip Codes based on how closely they score to the user's provided
			//preferred population
			
			HashMap<String, Double> populationScores = new HashMap<String, Double>();
			for(String zipCode : hashOfAllCleanedData.keySet()) {
				DataBook allData = hashOfAllCleanedData.get(zipCode);
				double score = 0.0;
				if(allData.getTotalPopulation()  <= preferredPopulation) {
				
					score = 1 - (allData.getTotalPopulation()  / preferredPopulation);
				}
				
				populationScores.put(zipCode, score);
			}		
			return populationScores;
		
	}
	
	public HashMap<String, Double> scoreMedianAge(double preferredMedianAge) {
		
		//score Zip Codes based on how closely they score to the user's provided
		//preferred median age
		
		HashMap<String, Double> medianAgeScores = new HashMap<String, Double>();
		for(String zipCode : hashOfAllCleanedData.keySet()) {
			DataBook allData = hashOfAllCleanedData.get(zipCode);
			double score = 0.0;
			if(allData.getTotalPopulation()  <= preferredMedianAge) {
			
				score = 1 - (allData.getMedianAge()  / preferredMedianAge);
			}
			
			medianAgeScores.put(zipCode, score);
		}		
		return medianAgeScores;
	
	}
	
	public HashMap<String, Double> scoreAvgHouseHoldSize(double preferredAvgHouseHoldSize) {
		
		//score Zip Codes based on how closely they score to the user's provided
		//preferred average house hold size
		
		HashMap<String, Double> AvgHouseHoldSizeScores = new HashMap<String, Double>();
		for(String zipCode : hashOfAllCleanedData.keySet()) {
			DataBook allData = hashOfAllCleanedData.get(zipCode);
			double score = 0.0;
			if(allData.getTotalPopulation()  <= preferredAvgHouseHoldSize) {
			
				score = 1 - (allData.getAverageHouseholdSize() / preferredAvgHouseHoldSize);
			}
			
			AvgHouseHoldSizeScores.put(zipCode, score);
		}		
		return AvgHouseHoldSizeScores;
	
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
	public double totalAreaScore(double homeValueScore, double temperatureScore, double precipitationScore,
			double totalPopulationScore, double medianAgeScore, double avgHouseHoldSizeScore) {
		//take an average of the home value score, temperature score, and
		//precipitation score to provide an overall score for a zip code
		return (homeValueScore + temperatureScore + precipitationScore + 
				totalPopulationScore + medianAgeScore + avgHouseHoldSizeScore )/ 6;
	}
	
	/**
	 * This method takes all the data collected for each zip code
	 * and returns an overall score based on the user preferences
	 * collected
	 * @param allData HashMap of all data for each Zip Code
	 * @return HashMap that maps and ranks overall scores to each Zip Code in CA
	 */
	public HashMap<String, Double> topZipCodes(int userPreferredBedrooms, long userBudget,double preferredTemperature,double preferredPrecipitation,
			double preferredTotalPopulation,double preferredMedianAge,double preferredAvgHouseHoldSize){
		
		HashMap<String, Double> totalScoresMap = new HashMap<String, Double>();
		HashMap<String, Double> homeScores = scoreHomeValue(userPreferredBedrooms, userBudget);
		HashMap<String, Double> temperatureScores = scoreTemperatureRequirements(preferredTemperature);
		HashMap<String, Double> precipitationScores = scorePrecipitationRequirements(preferredPrecipitation);
		HashMap<String, Double> poulationScores = scoreTotalPopulation(preferredTotalPopulation);
		HashMap<String, Double> medianAgeScores =  scoreMedianAge(preferredMedianAge);
		HashMap<String, Double> avgHouseHoldSizeScores = scoreAvgHouseHoldSize(preferredAvgHouseHoldSize);
	
		//for each zip code in provided hashmap, collect an totalAreaScore
		
		for(String zipCode : hashOfAllCleanedData.keySet()) {
			double totalScore = totalAreaScore(homeScores.get(zipCode) , temperatureScores.get(zipCode) , precipitationScores.get(zipCode),
					poulationScores.get(zipCode), medianAgeScores.get(zipCode), avgHouseHoldSizeScores.get(zipCode));
			totalScoresMap.put(zipCode, totalScore);
		}
		
		//add Zip Code and total score to HashMap to be returned		
		//once all Zip Codes are scored, rank and sort Zip Codes
		//return hashMap
		return sortByValue(totalScoresMap);
	}
	
	 public  HashMap<String, Double> sortByValue(HashMap<String, Double> hm) 
	    { 
	        // Create a list from elements of HashMap 
	        List<Map.Entry<String, Double> > list = 
	               new LinkedList<Map.Entry<String, Double> >(hm.entrySet()); 
	  
	        // Sort the list 
	        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() { 
	            public int compare(Map.Entry<String, Double> o1,  
	                               Map.Entry<String, Double> o2) 
	            { 
	                return (o1.getValue()).compareTo(o2.getValue()); 
	            } 
	        }); 
	          
	        // put data from sorted list to hashmap  
	        HashMap<String, Double> temp = new LinkedHashMap<String, Double>(); 
	        for (Map.Entry<String, Double> aa : list) { 
	            temp.put(aa.getKey(), aa.getValue()); 
	        } 
	        return temp; 
	    } 
}
