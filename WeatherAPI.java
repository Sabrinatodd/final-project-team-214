import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import org.json.*;


/**
 * This class demos how to make an API call, parse the JSON response and uses the response
 * values to create an ArrayList of RecipePuppyRecipe objects.
 *
 */
public class WeatherAPI {
	
	/**
	 * Parse the JSON response String
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
	 * @return
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
	 * this is the main function to getWeatherData by ZipCode in CA.
	 * !!!THIS NEEDS TO BE UPDATED TO ACCOUNT FOR ZIPCODES THAT HAVE
	 * NO WEATHER STATION AVAILABLE
	 */
	public void getWeatherData() {
		String dateForAPICall = "2019-01-01";
		DateUtility du = new DateUtility(dateForAPICall);
		
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
				for(int i = 0; i < 2 ; i++) {

					//set API Parameters
					String queryParams = "?datasetid=GHCND&locationid=ZIP:" + tempZip + "&startdate=" + dateForAPICall + "&enddate=" + dateForAPICall + "&units=standard";
					String weatherDailiesURL = endPoint + path + queryParams;
					
					//create WeatherAPI
					WeatherAPI wAPI = new WeatherAPI();
					ArrayList<DailyWeather> dailyWeather = new ArrayList<>();
					
					
					try {
						//make the API call and get a String response
						String jsonResponse = wAPI.makeAPICall(weatherDailiesURL);
						
						//parse the response and get an ArrayList of weather objects
						dailyWeather = wAPI.parseWeatherJSON(jsonResponse);
						//view the results in a proper Java object
						for(DailyWeather day : dailyWeather) {
							System.out.println("Date: " + day.getDate() + "   TMax: "+ day.getTempMax() + "   TMin "+ day.getTempMin());
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				
					//increment date up by one day
					try {
						dateForAPICall= du.addOneDayCalendar(dateForAPICall);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				myScanner.close();
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		WeatherAPI api = new WeatherAPI();
		api.getWeatherData();
	} 
}
	
