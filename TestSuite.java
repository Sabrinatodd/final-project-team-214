import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DecimalFormat;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestSuite {
	
	static DataReader dr = new DataReader();
	static HashMap<String, DataBook> allData = null;
	DecimalFormat df = new DecimalFormat("#.####");
	static HomeMatchScorer homeScorer = null;
		
	@BeforeClass
	public static void  readHomeData(){
		allData = dr.readAllData();	
		homeScorer = new HomeMatchScorer(allData);
	}

	@Test
	public void testReadHomeDataOneBedroom() {
		int oneBedroomPrice = allData.get("90806").getOneBedroomPrice();
		assertEquals(oneBedroomPrice, 357615);
	}
	
	@Test
	public void testReadHomeDataTwoBedroom() {
		int twoBedroomPrice = allData.get("90806").getTwoBedroomPrice();
		assertEquals(twoBedroomPrice, 544398);
	}
	
	@Test
	public void testReadHomeDataThreeBedroom() {
		int threeBedroomPrice = allData.get("90806").getThreeBedroomPrice();
		assertEquals(threeBedroomPrice, 581095);
	}
	
	@Test
	public void testReadHomeDataFourBedroom() {
		int fourBedroomPrice = allData.get("90806").getFourBedroomPrice();
		assertEquals(fourBedroomPrice, 631773);
	}
	
	@Test
	public void testReadHomeDataFiveBedroom() {
		int fiveBedroomPrice = allData.get("90806").getFivePlusBedroomPrice();
		assertEquals(fiveBedroomPrice, 764390);
	}
	
	@Test
	public void testReadHomeDaOverallHome() {
		int homePrice = allData.get("90806").getHomePriceOverall();
		assertEquals(homePrice, 578966);
	}
	
	@Test
	public void testDailyTemperature() {
		double dailyTemperature = allData.get("94903").getDailyTemperature() ;
		assertEquals(df.format(dailyTemperature) , "59.1589");
	}
	
	@Test
	public void testMonthlyPrecipitation() {
		double dailyPrecipitation = allData.get("96094").getMonthlyPrecipitation() ;
		assertEquals(df.format(dailyPrecipitation) , "1.2054");
	}


	@Test
	public void testCensusPopulation() {
		double censusPopulation	 = allData.get("90011").getTotalPopulation();
		assertEquals(censusPopulation , 103892);
	}

	@Test
	public void testCensusMedianAge() {
		double medianAge  = allData.get("90011").getMedianAge();
		assertEquals(df.format(medianAge) , "26.2");
	}
	
	@Test
	public void testAverageHousehold() {
		double avgHouseHold = allData.get("90011").getAverageHouseholdSize();
		assertEquals(df.format(avgHouseHold) , "4.67");
	}
	
	@Test
	public void scoreOneBedHome() {
		HashMap<String, Double> oneBedScoreMap = homeScorer.scoreHomeValue(1, 500000);
		double score = oneBedScoreMap.get("90302");
		assertEquals(df.format(score) , "0.2507");

	}
	
	@Test
	public void scoreTwoBedHome() {
		HashMap<String, Double> twoBedScoreMap = homeScorer.scoreHomeValue(2, 500000);
		double score = twoBedScoreMap.get("90302");
		assertEquals(df.format(score) , "0.0018");
	}
	
	@Test
	public void scoreThreeBedHome() {
		HashMap<String, Double> threeBedScoreMap = homeScorer.scoreHomeValue(3, 1000000);
		double score = threeBedScoreMap.get("90302");
		assertEquals(df.format(score) , "0.313");
	}
	
	@Test
	public void scoreFourBedHome() {
		HashMap<String, Double> fourBedScoreMap = homeScorer.scoreHomeValue(4, 1000000);
		double score = fourBedScoreMap.get("90302");
		assertEquals(df.format(score) , "0.14");
	}
	
	@Test
	public void scoreFiveBedHome() {
		HashMap<String, Double> fiveBedScoreMap = homeScorer.scoreHomeValue(5, 1000000);
		double score = fiveBedScoreMap.get("90302");
		assertEquals(df.format(score) , "0.0688");
	}
	

}
