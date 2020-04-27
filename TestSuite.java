import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DecimalFormat;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;


/**
 * The class is a suite of tests for the DataReader
 * class.
 */
public class TestSuite {
	
	
	static DataCompiler dc = new DataCompiler();
	static HashMap<String, DataBook> allData = null;
	DecimalFormat df = new DecimalFormat("#.####");
	static HomeMatchScorer homeScorer = null;
		
	@BeforeClass
	public static void  readHomeData(){
		allData = dc.compile();	
		homeScorer = new HomeMatchScorer(allData);
	}

	@Test
	public void testReadHomeDataOneBedroom() {
		int oneBedroomPrice = (int) allData.get("90806").getOneBedroomPrice();
		assertEquals(oneBedroomPrice, 356254);
	}
	
	@Test
	public void testReadHomeDataTwoBedroom() {
		int twoBedroomPrice = (int) allData.get("90806").getTwoBedroomPrice();
		assertEquals(twoBedroomPrice, 538298);
	}
	
	@Test
	public void testReadHomeDataThreeBedroom() {
		int threeBedroomPrice = (int) allData.get("90806").getThreeBedroomPrice();
		assertEquals(threeBedroomPrice, 561363);
	}
	
	@Test
	public void testReadHomeDataFourBedroom() {
		int fourBedroomPrice = (int) allData.get("90806").getFourBedroomPrice();
		assertEquals(fourBedroomPrice, 612094);
	}
	
	@Test
	public void testReadHomeDataFiveBedroom() {
		int fiveBedroomPrice = (int) allData.get("90806").getFivePlusBedroomPrice();
		assertEquals(fiveBedroomPrice, 747094);
	}
	
	@Test
	public void testReadHomeDaOverallHome() {
		int homePrice = (int) allData.get("90806").getHomePriceOverall();
		assertEquals(homePrice, 570978);
	}
	
	@Test
	public void testDailyTemperature() {
		double dailyTemperature = allData.get("94903").getDailyTemperature() ;
		assertEquals(df.format(dailyTemperature) , "59.1589");
	}
	
	@Test
	public void testMonthlyPrecipitation() {
		double dailyPrecipitation = allData.get("96094").getMonthlyPrecipitation() ;
		assertEquals(df.format(dailyPrecipitation) , "1.3804");
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
}
