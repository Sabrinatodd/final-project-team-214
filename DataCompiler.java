import java.util.HashMap;

/**
 * The purpose of this class is to consolidate the each of the
 *  *Reader classes into a single class, that can be succinctly
 *  called.
 */
public class DataCompiler {

	/**
	 * The method uses the DataReader superclass and associated
	 * subclasses to neatly compile all of the data into a single
	 * HashMap that will be measured against the user input
	 * @return HashMap that contains all Home, Demographic, and Weather
	 * data to be compared to a user's desired characteristics
	 */
	public HashMap<String, DataBook> compile(){
		HomeReader hr = new HomeReader();
		CensusReader cr = new CensusReader();
		WeatherReader wr = new WeatherReader();
		
		//gather home data
		DataReader dr = new HomeReader();
		String[] f = hr.getHomeDataFiles();
		HashMap<String, DataBook> homeData = dr.loopFiles(f);
		
		//gather census data
		dr = new CensusReader();
		String[] c = cr.getCensusDataFile();
		HashMap<String, DataBook> censusData = dr.loopFiles(c);
		
		//gather weather data
		dr = new WeatherReader();
		String[] w = wr.getWeatherDataFiles();
		HashMap<String, DataBook> weatherData = dr.loopFiles(w);
		
		//merge all data
		HashMap<String, DataBook> completeBook = dr.mergeData(homeData, censusData);
		completeBook = dr.mergeData(completeBook, weatherData);
		
		return completeBook;
	}
	
	public static void main(String[] args) {
		DataCompiler dc = new DataCompiler();
		HashMap<String, DataBook> allData = dc.compile();
		System.out.println(allData);
	}
}
