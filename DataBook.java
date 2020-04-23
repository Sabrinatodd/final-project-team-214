import java.util.ArrayList;

/**
 * This class will store each field of data collected
 * for a Zip Code. This will allow for the data to be
 * used and called more easily.
 */
public class DataBook {
	//real estate data
	private double oneBedroomPrice;
	private double twoBedroomPrice;
	private double threeBedroomPrice;
	private double fourBedroomPrice;
	private double fivePlusBedroomPrice;
	private double homePriceOverall;
	
	//weather data
	private double dailyTemperature;
	private double monthlyPrecipitation;
	private double annualDaysBelowFreezing;
	
	//demographic data
	private double totalPopulation;
	private double medianAge;
	private double averageHouseholdSize;

	/**
	 * This constructor allows for a DataBook object to be initialized
	 * without any data. This is useful for the DataReader Class.
	 */
	public DataBook() {}

	/**
	 * @param homePrice         overall home price for a given Zip Code
	 * @param oneBedroom        price of a one bedroom home
	 * @param twoBedroom        price of a two bedroom home
	 * @param threeBedroom      price of a three bedroom home
	 * @param fourBedroom       price of a four bedroom home
	 * @param fivePlusBedroom   price of homes with five or more bedrooms
	 * @param precipitation     average daily precipitation
	 * @param temperature       average daily temperature
	 * @param daysBelowFreezing number of days below freezing temperature
	 * @param population		the population of a given area
	 * @param medianAge			the median age of the population in a given area
	 * @param avgHouseholdSize  the average number of people in a household in a given area
	 */
	public DataBook(double homePrice, double oneBedroom, double twoBedroom, double threeBedroom, double fourBedroom,
			double fivePlusBedroom, double precipitation, double temperature, double daysBelowFreezing,
			double population, double medianAge, double avgHouseholdSize) {
		
		this.oneBedroomPrice = oneBedroom;
		this.twoBedroomPrice = twoBedroom;
		this.threeBedroomPrice = threeBedroom;
		this.fourBedroomPrice = fourBedroom;
		this.fivePlusBedroomPrice = fivePlusBedroom;
		this.homePriceOverall = homePrice;
		this.dailyTemperature = temperature;
		this.monthlyPrecipitation = precipitation;
		this.annualDaysBelowFreezing = daysBelowFreezing;
		this.totalPopulation = population;
		this.medianAge = medianAge;
		this.averageHouseholdSize = avgHouseholdSize;

	}
	
	/**
	 * This method acts to cleanly parse through each of the 
	 * data fields contained in the DataBook class. This is
	 * beneficial when trying to identify/pull any data within
	 * an instance of a DataBook Object.
	 * 
	 * Index Values include:
	 * 
	 * 0 - oneBedroom        
	 * 1 - twoBedroom        
	 * 2 - threeBedroom      
	 * 3 - fourBedroom       
	 * 4 - fivePlusBedroom
	 * 5 - homePriceOverall  
	 * 6 - dailyTemperature     
	 * 7 - monthlyPrecipitation      
	 * 8 - daysBelowFreezing 
	 * 9 - population	
	 * 10 - medianAge
	 * 11 - avgHouseholdSize
	 * 
	 * @param index the assigned index to a given variable
	 * @return Double representing the value of the given variable
	 */
	public double get(int index) {
		switch(index) {
		case 0:
			return this.oneBedroomPrice;
		case 1:
			return this.twoBedroomPrice;
		case 2:
			return this.threeBedroomPrice;
		case 3:
			return this.fourBedroomPrice;
		case 4:
			return this.fivePlusBedroomPrice;
		case 5:
			return this.homePriceOverall;
		case 6:
			return this.dailyTemperature;
		case 7:
			return this.monthlyPrecipitation;
		case 8:
			return this.annualDaysBelowFreezing;
		case 9:
			return this.totalPopulation;
		case 10:
			return this.medianAge;
		case 11:
			return this.averageHouseholdSize;
		default:
			return 0;
		}
	}
	
	/**
	 * This method acts to set a data variable within an instance
	 * of a DataBook object using an assigned index value
	 * 
	 * Index Values include:
	 * 
	 * 0 - oneBedroom        
	 * 1 - twoBedroom        
	 * 2 - threeBedroom      
	 * 3 - fourBedroom       
	 * 4 - fivePlusBedroom
	 * 5 - homePriceOverall  
	 * 6 - dailyTemperature     
	 * 7 - monthlyPrecipitation      
	 * 8 - daysBelowFreezing 
	 * 9 - population	
	 * 10 - medianAge
	 * 11 - avgHouseholdSize
	 * 
	 * @param value the value of the variable being set
	 * @param index the variable whose value to be set/updated
	 */
	public void put(double value, int index) {
		switch(index) {
		case 0:
			setOneBedroomPrice(value);
			break;
		case 1:
			setTwoBedroomPrice(value);
			break;
		case 2:
			setThreeBedroomPrice(value);
			break;
		case 3:
			setFourBedroomPrice(value);
			break;
		case 4:
			setFivePlusBedroomPrice(value);
			break;
		case 5:
			setHomePriceOverall(value);
			break;
		case 6:
			setDailyTemperature(value);
			break;
		case 7:
			setMonthlyPrecipitation(value);
			break;
		case 8:
			setAnnualDaysBelowFreezing(value);
			break;
		case 9:
			setTotalPopulation(value);
			break;
		case 10:
			setMedianAge(value);
			break;
		case 11:
			setAverageHouseholdSize(value);
			break;
		}
	}
	
	//not sure i need this anymore
	public ArrayList<Object> toArray(DataBook dataBook){
		//by adding each value to a designated index, this array
		//will have a predictable order
		ArrayList<Object> valuesArray = new ArrayList<Object>();
		
		//add one bedroom value
		valuesArray.add(0, dataBook.getOneBedroomPrice());
		//add two bedroom value
		valuesArray.add(1, dataBook.getTwoBedroomPrice());
		//add three bedroom value
		valuesArray.add(2, dataBook.getThreeBedroomPrice());
		//add four bedroom value
		valuesArray.add(3, dataBook.getFourBedroomPrice());
		//add five bedroom value
		valuesArray.add(4, dataBook.getFivePlusBedroomPrice());
		//add average home price
		valuesArray.add(5, dataBook.getHomePriceOverall());
		//add average daily temperature
		valuesArray.add(6, dataBook.getDailyTemperature());
		//add monthly precipitation
		valuesArray.add(7, dataBook.getMonthlyPrecipitation());
		//add days below freeezing
		valuesArray.add(8, dataBook.getAnnualDaysBelowFreezing());
		//add population
		valuesArray.add(9, dataBook.getTotalPopulation());
		//add median Age
		valuesArray.add(10, dataBook.getMedianAge());
		//add average household size
		valuesArray.add(11, dataBook.getAverageHouseholdSize());
		
		return valuesArray;
	}
	
	/**
	 * This method returns the home value for a DataBook object
	 * based on the number of bedrooms
	 * @param nBedRooms number of bedrooms
	 * @return HomeValue for a home with the provided number of bedrooms
	 */
	public double getPriceByRooms(int nBedRooms){
		switch(nBedRooms){
			case 1: return this.getOneBedroomPrice();
			case 2: return this.getTwoBedroomPrice();
			case 3: return this.getThreeBedroomPrice();
			case 4: return this.getFourBedroomPrice();
			case 5: return this.getFivePlusBedroomPrice();
		}
		return this.getHomePriceOverall();
	}

	/**
	 * @return ZIP code's average overall home price
	 */
	public double getHomePriceOverall() {
		return homePriceOverall;
	}
	
	/**
	 * @param homePriceOverall overall home price
	 */
	public void setHomePriceOverall(double homePriceOverall) {
		this.homePriceOverall = homePriceOverall;
	}

	/**
	 * @return ZIP code's average price for one bedroom homes
	 */
	public double getOneBedroomPrice() {
		return oneBedroomPrice;
	}
	
	/**
	 * @param oneBedroomPrice ZIP code's average price for one bedroom homes
	 */
	public void setOneBedroomPrice(double oneBedroomPrice) {
		this.oneBedroomPrice = oneBedroomPrice;
	}

	/**
	 * @return ZIP code's average price for two bedroom homes
	 */
	public double getTwoBedroomPrice() {
		return twoBedroomPrice;
	}
	
	/**
	 * @param twoBedroomPrice ZIP code's average price for two bedroom homes
	 */
	public void setTwoBedroomPrice(double twoBedroomPrice) {
		this.twoBedroomPrice = twoBedroomPrice;
	}

	/**
	 * @return ZIP code's average price for three bedroom homes
	 */
	public double getThreeBedroomPrice() {
		return threeBedroomPrice;
	}
	
	/**
	 * @param threeBedroomPrice ZIP code's average price for three bedroom homes
	 */
	public void setThreeBedroomPrice(double threeBedroomPrice) {
		this.threeBedroomPrice = threeBedroomPrice;
	}
	
	/**
	 * @return ZIP code's average price for four bedroom homes
	 */
	public double getFourBedroomPrice() {
		return fourBedroomPrice;
	}

	/**
	 * @param fourBedroomPrice ZIP code's average price for four bedroom homes
	 */
	public void setFourBedroomPrice(double fourBedroomPrice) {
		this.fourBedroomPrice = fourBedroomPrice;
	}

	/**
	 * @return ZIP code's average price for five plus bedroom homes
	 */
	public double getFivePlusBedroomPrice() {
		return fivePlusBedroomPrice;
	}

	/**
	 * @param fivePlusBedroomPrice ZIP code's average price for five plus bedroom homes
	 */
	public void setFivePlusBedroomPrice(double fivePlusBedroomPrice) {
		this.fivePlusBedroomPrice = fivePlusBedroomPrice;
	}

	/**
	 * @return ZIP code's average monthly precipitation
	 */
	public double getMonthlyPrecipitation() {
		return monthlyPrecipitation;
	}
	
	/**
	 * 
	 * @param precipitation ZIP code's average monthly precipitation
	 */
	public void setMonthlyPrecipitation(double precipitation) {
		this.monthlyPrecipitation = precipitation;
	}

	/**
	 * @return ZIP code's average daily temperature
	 */
	public double getDailyTemperature() {
		return dailyTemperature;
	}
	
	/**
	 * @param temperature ZIP code's average daily temperature
	 */
	public void setDailyTemperature(double temperature) {
		this.dailyTemperature = temperature;
	}

	/**
	 * @return ZIP code's average annual days below freezing
	 */
	public double getAnnualDaysBelowFreezing() {
		return annualDaysBelowFreezing;
	}
	
	/**
	 * @param daysBelowFreezing ZIP code's average annual days below freezing
	 */
	public void setAnnualDaysBelowFreezing(double daysBelowFreezing) {
		this.annualDaysBelowFreezing = daysBelowFreezing;
	}
	
	/**
	 * @return the population of a given zip code
	 */
	public double getTotalPopulation() {
		return totalPopulation;
	}
	
	/**
	 * @param totalPopulation the population of a given zip code
	 */
	public void setTotalPopulation(double totalPopulation) {
		this.totalPopulation = totalPopulation;
	}

	/**
	 * @return the median age of a given zip code
	 */
	public double getMedianAge() {
		return medianAge;
	}

	/**
	 * @param medianAge the median age of a zip code
	 */
	public void setMedianAge(double medianAge) {
		this.medianAge = medianAge;
	}

	/**
	 * @return the average number of people per household in a given zip code
	 */
	public double getAverageHouseholdSize() {
		return averageHouseholdSize;
	}

	/**
	 * @param averageHouseholdSize the average number of people per household in a given zip code
	 */
	public void setAverageHouseholdSize(double averageHouseholdSize) {
		this.averageHouseholdSize = averageHouseholdSize;
	}
}
