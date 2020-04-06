import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import org.json.*;

/**
 * This class pulls weather data by ZipCode
 * using an API for the NOAA. This data can then be cleaned
 * and added to the HashMap of ZipCode objects.
 */
public class WeatherAPI {
	
	/**
	 * Parse the JSON Output of the API to find weather data
	 * @param jsonResponse
	 * @return ArrayList of weather objects
	 * @throws JSONException
	 */
	public ArrayList<DailyWeather> parseWeatherJSON(String jsonResponse) throws JSONException {
		//create a JSON object with the String response
		JSONObject jObj = new JSONObject(jsonResponse);
		//Look at the raw String response
		//Look for the results key
		//After the colon there is a square bracket indicating a JSONArray
		JSONArray jArray = jObj.getJSONArray("results");
		ArrayList<DailyWeather> weather = new ArrayList<DailyWeather>();
		
		
		String date = "";
		String station = "";
		String zipCode = "";
		double max = 0.0;
		double min = 0.0;
		double precipitation = 0.0;
		double snow = 0.0;
		
		//for the first object, collect date, station because these values
		//will not change in the remaining objects
		for(int i = 0; i < 1; i++) {
			//get date and clean it
			date = jArray.getJSONObject(i).getString("date");
			date = date.substring(0, 10);
			
			//get station
			station = jArray.getJSONObject(i).getString("station");
		}
		
		//now loop through remaining items in array to find other characteristics
		for(int i = 0; i < jArray.length(); i++) {
			if(jArray.getJSONObject(i).getString("datatype").contentEquals("TMAX")) {
				max = jArray.getJSONObject(i).getDouble("value");
			}
			else if(jArray.getJSONObject(i).getString("datatype").contentEquals("TMIN")) {
				min = jArray.getJSONObject(i).getDouble("value");
			}
			else if(jArray.getJSONObject(i).getString("datatype").contentEquals("PRCP")) {
				precipitation = jArray.getJSONObject(i).getDouble("value");
			}
			else if(jArray.getJSONObject(i).getString("datatype").contentEquals("SNOW")) {
				snow = jArray.getJSONObject(i).getDouble("value");
			}
		}
		
		
		//add weather data to array
		DailyWeather dw = new DailyWeather(date, station, zipCode, max, min, precipitation, snow);
		weather.add(dw);
		
		return weather;
	}
	
	/**
	 * Makes the API call and returns the JSON result as a String
	 * @param url
	 * @return JSON Output of API as a String
	 * @throws IOException
	 */
	public String makeAPICall(String url) throws IOException {
		URL noaaURL;
		URLConnection yc;
		BufferedReader in;
		
		//hit the URL and get the response from it.
		noaaURL = new URL(url);
		yc = noaaURL.openConnection();
		yc.setRequestProperty("token", "sALlKUFmmcBbestaUvanjYvHUsLVGCCH");
		in = new BufferedReader(new InputStreamReader(
                 yc.getInputStream()));
		String inputLine;
		
		StringBuffer response = new StringBuffer();
		//BufferedReader does not have a "hasNext" type method so this is how to check for 
		//more input
		//if it has more input append to the StringBuffer
		while ((inputLine = in.readLine()) != null) {
		     response.append(inputLine);
		}
		in.close();
		     
		return response.toString();
	}
	
	/**
	 * This method uses the NOAA API to collect weather data for
	 * each Zip Code in California that has data available. Data
	 * is collected for each day of the year for each Zip Code.
	 * @return ArrayList of weather data for each Zip Code for each day of the year
	 */
	public ArrayList<DailyWeather> getWeatherData() {
		String dateForAPICall = "2019-01-01";
		DateUtility du = new DateUtility(dateForAPICall);
		ArrayList<DailyWeather> dailyWeatherData = new ArrayList<>();
		File f = new File ("CA_ZipCodes");
		
		try {
			Scanner myScanner = new Scanner(f);
			
			//create the API URL
			String endPoint = "https://www.ncdc.noaa.gov";
			String path = "/cdo-web/api/v2/data";
			
			//for every zip code in California
			while(myScanner.hasNextLine()) {
				String tempZip = myScanner.nextLine();
				
				//collect n number of days worth of weather data
				for(int i = 0; i < 365 ; i++) {

					//set API Parameters
					String queryParams = "?datasetid=GHCND&locationid=ZIP:" + tempZip + "&startdate=" + dateForAPICall + "&enddate=" + dateForAPICall + "&units=standard";
					String weatherDailiesURL = endPoint + path + queryParams;
					
					//create WeatherAPI
					WeatherAPI wAPI = new WeatherAPI();
					
					
					
					try {
						//make the API call and get a String response
						String jsonResponse = wAPI.makeAPICall(weatherDailiesURL);
						
						//parse the response and get an ArrayList of weather objects
						dailyWeatherData = wAPI.parseWeatherJSON(jsonResponse);
						
						//print results
						for(DailyWeather day : dailyWeatherData) {
							System.out.println("Date: " + day.getDate() + "   TMax: "+ day.getTempMax() + "   TMin "+ day.getTempMin());
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				
					//increment date up by one day
					try {
						dateForAPICall= du.incrementCalendar(dateForAPICall);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				myScanner.close();
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		return dailyWeatherData;
	}
	
	
	/**
	 * This method cleans an ArrayList of weather data to make the 
	 * data appropriate for one year intervals. This includes averaging
	 * temperature fields and summing total days above or below certain 
	 * precipitation and temperature thresholds. This method also cleans
	 * Zip Codes that have missing or no weather data available  	 
	 * @return ArrayList of weather data respective of the entire year
	 */
	public ArrayList<DailyWeather> cleanWeatherData(ArrayList<DailyWeather> weatherDataArray){
		//for each weather object in the array
			//check for missing or incomplete data
		
		//average minimum temperature for the year
		//average max temperature for the year
		//average daily snow and precipitation for the year
		//count the number of days below freezing
		
		//store each of these variables in a DailyWeather object that is then stored
		// in an arraylist to be returned.
	}
	
	//This is for testing purposes only. Can be deleted once operational.
	public static void main(String[] args) {
		WeatherAPI api = new WeatherAPI();
		api.getWeatherData();
	} 
}
	
