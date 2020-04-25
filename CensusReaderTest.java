import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class CensusReaderTest {
	CensusReader cr;
	DataReader dr;
	String[] censusFile;
	
	private CensusReaderTest() {
		this.cr = new CensusReader();
		this.dr = new CensusReader();
		this.censusFile = cr.getCensusDataFile();
	}

	@Test
	/**
	 * This tests that the CensusReader class is correctly pulling all 
	 * census data for a zip code with actual data
	 */
	void testZipWithData() {
		CensusReaderTest crt = new CensusReaderTest();
		HashMap<String, DataBook> censusData = dr.loopFiles(crt.censusFile);
		String zipCode = "90057";
		
		double population = censusData.get(zipCode).getTotalPopulation();
		double medianAge = censusData.get(zipCode).getMedianAge();
		double householdSize = censusData.get(zipCode).getAverageHouseholdSize();
		
		assertEquals(44998, population);
		assertEquals(31.2, medianAge);
		assertEquals(2.81, householdSize);
	}
	
	@Test
	/**
	 * This tests that the CensusReader class is correctly pulling all 
	 * census data for a zip code with values of 0
	 */
	void testZipWithNoData() {
		CensusReaderTest crt = new CensusReaderTest();
		HashMap<String, DataBook> censusData = dr.loopFiles(crt.censusFile);
		String zipCode = "90506";
		
		double population = censusData.get(zipCode).getTotalPopulation();
		double medianAge = censusData.get(zipCode).getMedianAge();
		double householdSize = censusData.get(zipCode).getAverageHouseholdSize();
		
		assertEquals(0.0, population);
		assertEquals(0.0, medianAge);
		assertEquals(0.0, householdSize);
	}
}
