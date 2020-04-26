import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.*;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

class WeatherAPITest {

	@Test
	/**
	 * This tests that the output for a valid API URL is not empty
	 */
	void testMakingAPICall() {
		WeatherAPI wAPI = new WeatherAPI();
		try {
			String url = "https://www.ncdc.noaa.gov/cdo-web/api/v2/data?datasetid=GHCND&locationid=ZIP:93955&startdate=2019-01-01&enddate=2019-12-31&units=standard&limit=500&datatypeid=TMAX";
			String output = wAPI.makeAPICall(url);
			
			assertNotEquals("{}", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	/**
	 * This tests that the output for a API URL for a zip code with
	 * no available data is coming back empty, rather than null
	 */
	void testMakingEmptyAPICall() {
		WeatherAPI wAPI = new WeatherAPI();
		try {
			String url = "https://www.ncdc.noaa.gov/cdo-web/api/v2/data?datasetid=GHCND&locationid=ZIP:90403&startdate=2019-01-01&enddate=2019-12-31&units=standard&limit=500&datatypeid=TMAX";
			String output = wAPI.makeAPICall(url);
			
			assertNotNull(output);
			assertEquals("{}", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	/**
	 * This tests that the parseWeatherJSON method is correctly
	 * pulling data from a JSON String
	 */
	void testParseJSON() {
		WeatherAPI wAPI = new WeatherAPI();
		String zip = "93955";
		String sDate = "2019-01-01";
		String eDate = "2019-01-01";
		String dataType = "TMAX";
		
		try {
			//Build API URL
			String url = "https://www.ncdc.noaa.gov/cdo-web/api/v2/data?datasetid=GHCND&locationid=ZIP:" + zip 
					+ "&startdate=" + sDate + "&enddate=" + eDate + "&units=standard&limit=500&datatypeid=" + dataType;
			
			//Get API Response
			String response = wAPI.makeAPICall(url);
			
			//Get Array of Values from Response
			ArrayList<Double> result = new ArrayList<Double>();
			try {
				result = wAPI.parseWeatherJSON(response, zip, dataType);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			//Expected output
			Double[] expectedResult = {57.0};
			assertArrayEquals(expectedResult, result.toArray());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * This tests that the parseWeatherJSON method is correctly
	 * pulling data from a JSON String with more than one value
	 */
	void testParseJSONTwo() {
		WeatherAPI wAPI = new WeatherAPI();
		String zip = "93955";
		String sDate = "2019-01-01";
		String eDate = "2019-01-05";
		String dataType = "TMIN";
		
		try {
			//Build API URL
			String url = "https://www.ncdc.noaa.gov/cdo-web/api/v2/data?datasetid=GHCND&locationid=ZIP:" + zip 
					+ "&startdate=" + sDate + "&enddate=" + eDate + "&units=standard&limit=500&datatypeid=" + dataType;
			//Get API Response
			String response = wAPI.makeAPICall(url);
			
			//Get Array of Values from Response
			ArrayList<Double> result = new ArrayList<Double>();
			try {
				result = wAPI.parseWeatherJSON(response, zip, dataType);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			//Expected Values
			ArrayList<Double> expectedResult = new ArrayList<>(Arrays.asList(37.0, 39.0, 39.0, 41.0, 45.0));
			
			assertTrue(result.containsAll(expectedResult));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
