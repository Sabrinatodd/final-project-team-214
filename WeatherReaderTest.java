import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class WeatherReaderTest {
	WeatherReader wr;
	DataReader dr;
	String[] weatherFiles;
	
	private WeatherReaderTest() {
		this.wr = new WeatherReader();
		this.dr = new WeatherReader();
		this.weatherFiles = wr.getWeatherDataFiles();
	}

	@Test
	/**
	 * This tests that the WeatherReader class is correctly pulling all 
	 * weather data for a zip code listed in each file
	 */
	void testPresentZipCode() {
		WeatherReaderTest wrt = new WeatherReaderTest();
		HashMap<String, DataBook> weatherData = dr.loopFiles(wrt.weatherFiles);
		String zipCode = "92251";
		
		double freezing = weatherData.get(zipCode).getAnnualDaysBelowFreezing();
		double temperature = weatherData.get(zipCode).getDailyTemperature();
		double precipitation = weatherData.get(zipCode).getMonthlyPrecipitation();
		
		assertEquals(4, Math.round(freezing));
		assertEquals(74, Math.round(temperature));
		assertEquals(0, Math.round(precipitation));
	}
	
	@Test
	/**
	 * This tests that the WeatherReader class is correctly pulling all 
	 * weather data for a zip code listed in some of the weather files
	 */
	void testHalfPresentZipCode() {
		WeatherReaderTest wrt = new WeatherReaderTest();
		HashMap<String, DataBook> weatherData = dr.loopFiles(wrt.weatherFiles);
		String zipCode = "92009";
		
		double freezing = weatherData.get(zipCode).getAnnualDaysBelowFreezing();
		double temperature = weatherData.get(zipCode).getDailyTemperature();
		double precipitation = weatherData.get(zipCode).getMonthlyPrecipitation();
		
		assertEquals(0, Math.round(freezing));
		assertEquals(0, Math.round(temperature));
		assertEquals(1, Math.round(precipitation));
	}

}
