import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;

/**
 * This class pulls weather data by ZipCode
 * using an API for the NOAA. This data can then be cleaned
 * and added to the HashMap of ZipCode objects.
 */
public class WeatherAPI {
	ArrayList<String> weatherDataIDs;
	
	public WeatherAPI () {
		weatherDataIDs = new ArrayList<String>();
		weatherDataIDs.add(0, "TMAX");
		weatherDataIDs.add(1, "TMIN");
		weatherDataIDs.add(2, "PRCP");
		weatherDataIDs.add(3, "SNOW");
	}
	
	

	/**
	 * This method parses the JSON output from the API call
	 * to collect weather data for each available date.
	 * @param jsonResponse the JSON output as a string from the API call
	 * @param zip the zip code for which the data is being collected
	 * @param dataType the type of weather data being collected
	 * @return ArrayList of Doubles for every available day of data
	 * @throws JSONException
	 */
	public ArrayList<Double> parseWeatherJSON(String jsonResponse, String zip, String dataType) throws JSONException {
		HashMap<String, Double> temporaryDailyWeatherData = new HashMap<String, Double>();
		ArrayList<Double> annualWeatherDataForZip = new ArrayList<Double>();
		
		//create a JSON object with the String response
		JSONObject jObj = new JSONObject(jsonResponse);
		//Look at the raw String response
		//Look for the results key
		//After the colon there is a square bracket indicating a JSONArray
		JSONArray jArray = jObj.getJSONArray("results");
		
		//loop through each day items in array to find other characteristics
		for(int i = 0; i < jArray.length(); i++) {
			//get date and clean it
			String date = jArray.getJSONObject(i).getString("date");
			date = date.substring(0, 10);
			
			// initialize other variable
			double value = 0.0;
			
			//Do not want duplicate data for the same day
			//make sure date is not already in hashmap before adding data
			if(!temporaryDailyWeatherData.containsKey(date)) {
				//get  data
				value = jArray.getJSONObject(i).getDouble("value");
				temporaryDailyWeatherData.put(date, value);
				
			}
		}
		
		//take hashmap and put values into an arrayList
		for(String s : temporaryDailyWeatherData.keySet()) {
			annualWeatherDataForZip.add(temporaryDailyWeatherData.get(s));
		}

		return annualWeatherDataForZip;
	}
	
	/**
	 * Makes the API call and returns the JSON result as a String
	 * @param url the URL String for the API call
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
	 * @param dataType the type of weather data being collected (i.e. TMAX, TMIN)
	 * @return ArrayList of weather data for each Zip Code for each day of the year
	 */
	public HashMap<String, ArrayList<Double>> getWeatherData(String dataType) {
		HashMap<String, ArrayList<Double>> weatherByZipCode = new HashMap<String, ArrayList<Double>>();
		File f = new File("CA_ZipCodes");

		try {
			Scanner myScanner = new Scanner(f);

			// create the API URL
			String endPoint = "https://www.ncdc.noaa.gov";
			String path = "/cdo-web/api/v2/data";

			// for every zip code in California
			while (myScanner.hasNextLine()) {
				ArrayList<Double> annualWeatherData = new ArrayList<Double>();
				String temporaryZip = myScanner.nextLine();

				// set API Parameters
				String queryParams = "?datasetid=GHCND&locationid=ZIP:" + temporaryZip
						+ "&startdate=2019-01-01&enddate=2019-12-31&units=standard&limit=500&datatypeid="
						+ dataType;
				String weatherDailiesURL = endPoint + path + queryParams;

				// create WeatherAPI
				WeatherAPI wAPI = new WeatherAPI();

				try {
					// make the API call and get a String response
					String jsonResponse = wAPI.makeAPICall(weatherDailiesURL);

					// If there is data available for the given zip code, on the given day
					// then parse through the data and get an ArrayList of values for given data
					// type
					if (!jsonResponse.equals("{}")) {
						annualWeatherData = wAPI.parseWeatherJSON(jsonResponse, temporaryZip, dataType);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}

				// if weather data was collected for zip code, add it to hashmap
				// otherwise ignore
				if (annualWeatherData.size() > 0) {
					weatherByZipCode.put(temporaryZip, annualWeatherData);
				}
			}

			myScanner.close();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		return weatherByZipCode;
	}
	
	
	/**
	 * This method takes a HashMap of weather data stored by Zip Code
	 * and prints it to a CSV File to be read for cleaning and output.
	 * 
	 * @param weatherData HashMap of all data values for a given zip code
	 * @param dataType the type of data variable being printed (i.e. TMAX, TMIN)
	 */
	public void writeWeatherDataToFile(HashMap<String, ArrayList<Double>> weatherData, String dataType){
		HashMap<String, ArrayList<String>> tempMaxHash = new HashMap<String, ArrayList<String>>();
		
		//get the max temperature for each day for each zip code
		for(String zip : weatherData.keySet()) {
			ArrayList<Double> dwTemp = weatherData.get(zip);
			ArrayList<String> dataPoints = new ArrayList<String>();
			
			//for each day, get max temp and add it to arraylist
			for(int i = 0; i < dwTemp.size(); i++) {

				//make sure to pull correct weather variable
				double variableToPull = dwTemp.get(i);
				String tempAsString = Double.toString(variableToPull);
				dataPoints.add(tempAsString);
			}
			
			tempMaxHash.put(zip, dataPoints);
		}

		
		try {
			FileWriter fw = new FileWriter("!CA_" + dataType + ".csv");

			try {
				//add column headers
				fw.append("Zip Code");
				fw.append(",");
				for(int j = 1; j < 366; j++) {
					fw.append("" + j);
					fw.append(",");
				}
				fw.append("\n");

				//add rows of data
				for (String zip : tempMaxHash.keySet()) {
					ArrayList<String> rowData = tempMaxHash.get(zip);
					//add zip code to array list so it is included in first column of csv
					rowData.add(0, zip);

					fw.append(String.join(",", rowData));
					fw.append("\n");
				}

				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * For submission, we chose not to run this class to avoid long run-times. 
	 * In theory, this class could be called overnight to update data before
	 * users interact with the rest of the program.
	 * 
	 * The main method within can be used to sample the functionality of this 
	 * class
	 */
	public static void main(String[] args) {
		WeatherAPI api = new WeatherAPI();
		int counter = 0;
		
		//For each type of weather data
		for(String dataType : api.weatherDataIDs) {
			//get values
			HashMap<String, ArrayList<Double>> weatherData = api.getWeatherData(dataType);
			//print to file
			api.writeWeatherDataToFile(weatherData, dataType);
			
			//move on to next dataType
			counter++;
			System.out.println("Process " + counter + " completed...");
		}

		System.out.println("Finished Gathering Weather Data!");
	} 
}
	
