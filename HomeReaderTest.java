import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class HomeReaderTest {
	HomeReader hr;
	DataReader dr;
	String[] homeFiles;
	
	private HomeReaderTest() {
		this.hr = new HomeReader();
		this.dr = new HomeReader();
		this.homeFiles = hr.getHomeDataFiles();
	}

	@Test
	/**
	 * This tests that the HomeReader class is correctly pulling all 
	 * home values for a zip code listed in each file
	 */
	void testPresentZipCode() {
		HomeReaderTest hrt = new HomeReaderTest();
		HashMap<String, DataBook> homeData = dr.loopFiles(hrt.homeFiles);
		String zipCode = "95608";
		
		double oneBed = homeData.get(zipCode).getOneBedroomPrice();
		double twoBed = homeData.get(zipCode).getTwoBedroomPrice();
		double threeBed = homeData.get(zipCode).getThreeBedroomPrice();
		double fourBed = homeData.get(zipCode).getFourBedroomPrice();
		double fivePlusBed = homeData.get(zipCode).getFivePlusBedroomPrice();
		double allHomes = homeData.get(zipCode).getHomePriceOverall();
		
		assertEquals(308870.0, oneBed);
		assertEquals(288104.0, twoBed);
		assertEquals(398038.0, threeBed);
		assertEquals(505998.0, fourBed);
		assertEquals(692881.0, fivePlusBed);
		assertEquals(424774.0, allHomes);
	}
	
	@Test
	/**
	 * This tests that the HomeReader class is correctly pulling all 
	 * home values for a zip code listed in some of the zillow files
	 * but not all of them
	 */
	void testHalfPresentZipCode() {
		HomeReaderTest hrt = new HomeReaderTest();
		HashMap<String, DataBook> homeData = dr.loopFiles(hrt.homeFiles);
		String zipCode = "91210";
		
		double oneBed = homeData.get(zipCode).getOneBedroomPrice();
		double twoBed = homeData.get(zipCode).getTwoBedroomPrice();
		double threeBed = homeData.get(zipCode).getThreeBedroomPrice();
		double fourBed = homeData.get(zipCode).getFourBedroomPrice();
		double fivePlusBed = homeData.get(zipCode).getFivePlusBedroomPrice();
		double allHomes = homeData.get(zipCode).getHomePriceOverall();
		
		assertEquals(0.0, oneBed);
		assertEquals(957506.0, twoBed);
		assertEquals(0.0, threeBed);
		assertEquals(0.0, fourBed);
		assertEquals(0.0, fivePlusBed);
		assertEquals(958086.0, allHomes);
	}
}
