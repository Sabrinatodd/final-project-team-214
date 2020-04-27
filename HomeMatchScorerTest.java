import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class HomeMatchScorerTest {
	
	
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
	/**
	 * This tests the scoreHomeValue method. Lower priced homes
	 * should appear score higher than those higher priced.
	 */
	void testHomeValueScorer() {
		HomeMatchScorer hms = new HomeMatchScorer();
		
		double userBudget = 100000.0;
		double userBedrooms = 2.0;
		
		double homeValue1 = 80000.0;
		double homeValue2 = 95000.0;
		double homeValue3 = 67050.0;
		double homeValue4 = 82007.9;
		
		double scoreH1 = hms.scoreHomeValue(userBedrooms, userBudget, homeValue1);
		double scoreH2 = hms.scoreHomeValue(userBedrooms, userBudget, homeValue2);
		double scoreH3 = hms.scoreHomeValue(userBedrooms, userBudget, homeValue3);
		double scoreH4 = hms.scoreHomeValue(userBedrooms, userBudget, homeValue4);
		
		assertTrue(scoreH1 > scoreH2);
		assertTrue(scoreH1 > scoreH4);
		assertTrue(scoreH3 > scoreH1);
		assertTrue(scoreH4 > scoreH2);
		
	}
	
	@Test
	/**
	 * This tests the scoreHomeValue method. Homes overbudget should
	 * receive a score of -5.
	 */
	void testOverbudgetHome() {
		HomeMatchScorer hms = new HomeMatchScorer();
		
		double userBudget = 250000.0;
		double userBedrooms = 2.0;
		
		double homeValue1 = 332050.0;
		double homeValue2 = 245000.0;

		
		double scoreH1 = hms.scoreHomeValue(userBedrooms, userBudget, homeValue1);
		double scoreH2 = hms.scoreHomeValue(userBedrooms, userBudget, homeValue2);
		
		assertEquals(-5, scoreH1);
		assertTrue(scoreH2 > 0);

		
	}
	
	@Test
	/**
	 * This tests the accuracy of the proximity score.
	 * Values that are equidistant from the user's input
	 * should be scored equally.
	 */
	void testProximityScore() {
		HomeMatchScorer hms = new HomeMatchScorer();
		
		double userPreferredPopulation = 10000.0;
		
		double area1 = 8000.0;
		double area2 = 12000.0;
		double area3 = 9500.0;
		double area4 = 6210.0;
		
		double scoreArea1 = hms.scoreProximity(userPreferredPopulation, area1);
		double scoreArea2 = hms.scoreProximity(userPreferredPopulation, area2);
		double scoreArea3 = hms.scoreProximity(userPreferredPopulation, area3);
		double scoreArea4 = hms.scoreProximity(userPreferredPopulation, area4);
		
		assertTrue(scoreArea1 == scoreArea2);
		assertTrue(scoreArea3 > scoreArea2);
		assertTrue(scoreArea1 > scoreArea4);
		assertTrue(scoreArea2 > scoreArea4);
	}
	
	@Test
	/**
	 * This tests the output of the getTopScores method that is
	 * used to print the top zip codes that meet a user's criteria
	 */
	void testMatchOutput() {
		DataCompiler dc = new DataCompiler();
		HashMap<String, DataBook> allData = dc.compile();
		HomeMatchScorer hms = new HomeMatchScorer(allData);
		
		ArrayList<String> topMatches = hms.generateTopZipCode(2, 330000.0, 67.0, 2.2, 3.0, 80000.0, 42.0, 1.7);
		String finalList = hms.getTopScores(topMatches, 5);
		String expectedResult = "93920, 96031, 96002, 90095, 96097";
		
		assertTrue(finalList.equals(expectedResult));
	}
	
	
	@Test
	/**
	 * Test of the scoreHomeValue method with actual data
	 * for a one bedroom home
	 */
	public void scoreOneBedHome() {
		double homePrice = allData.get("90302").get(0);
		double score = homeScorer.scoreHomeValue(1, 500000, homePrice);
		assertEquals(df.format(score) , "0.2587");

	}
	
	@Test
	/**
	 * Test of the scoreHomeValue method with actual data
	 * for a two bedroom home
	 */
	public void scoreTwoBedHome() {
		double homePrice = allData.get("90302").get(1);
		double score = homeScorer.scoreHomeValue(2, 500000, homePrice);
		assertEquals(df.format(score) , "0.0239");
	}
	
	@Test
	/**
	 * Test of the scoreHomeValue method with actual data
	 * for a three bedroom home
	 */
	public void scoreThreeBedHome() {
		double homePrice = allData.get("90302").get(2);
		double score = homeScorer.scoreHomeValue(3, 1000000, homePrice);
		assertEquals(df.format(score) , "0.3327");
	}
	
	@Test
	/**
	 * Test of the scoreHomeValue method with actual data
	 * for a four bedroom home
	 */
	public void scoreFourBedHome() {
		double homePrice = allData.get("90302").get(3);
		double score = homeScorer.scoreHomeValue(4, 1000000, homePrice);
		assertEquals(df.format(score) , "0.159");
	}
	
	@Test
	/**
	 * Test of the scoreHomeValue method with actual data
	 * for a five plus bedroom home
	 */
	public void scoreFiveBedHome() {
		double homePrice = allData.get("90302").get(4);
		double score = homeScorer.scoreHomeValue(5, 1000000, homePrice);
		assertEquals(df.format(score) , "0.0848");
	}

}
