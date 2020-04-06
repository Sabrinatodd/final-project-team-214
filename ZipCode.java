/**
 * This class will store each field of data collected
 * for a Zip Code. This will allow for the data to be
 * used and called more easily.
 */
public class ZipCode {
	private int homePriceOverall;
	private int oneBedroomPrice;
	private int twoBedroomPrice;
	private int threeBedroomPrice;
	private int fourBedroomPrice;
	private int fivePlusBedroomPrice;
	
	private double monthlyPrecipitation;
	private double dailyTemperature;
	private int annualDaysBelowFreezing;

	
	/**
	 * @param homePrice overall home price for a given Zip Code
	 * @param oneBedroom price of a one bedroom home 
	 * @param twoBedroom price of a two bedroom home 
	 * @param threeBedroom price of a three bedroom home 
	 * @param fourBedroom price of a four bedroom home 
	 * @param fivePlusBedroom price of homes with five or more bedrooms
	 * @param precipitation average daily precipitation 
	 * @param temperature average daily temperature
	 * @param daysBelowFreezing number of days below freezing temperature
	 */
	public ZipCode(int homePrice, int oneBedroom, int twoBedroom, int threeBedroom, int fourBedroom, int fivePlusBedroom, double precipitation, double temperature, int daysBelowFreezing) {
		this.homePriceOverall = homePrice;
		this.oneBedroomPrice = oneBedroom;
		this.twoBedroomPrice = twoBedroom;
		this.threeBedroomPrice = threeBedroom;
		this.fourBedroomPrice = fourBedroom;
		this.fivePlusBedroomPrice = fivePlusBedroom;
		this.monthlyPrecipitation = precipitation;
		this.dailyTemperature = temperature;
		this.annualDaysBelowFreezing = daysBelowFreezing;
	
	}

	/**
	 * @return ZIP code's average overall home price
	 */

	public int getHomePriceOverall() {
		return homePriceOverall;
	}
	
	/**
	 * 
	 * @param homePriceOverall overall home price
	 */
	public void setHomePriceOverall(Integer homePriceOverall) {
		this.homePriceOverall = homePriceOverall;
	}

	/**
	 * @return ZIP code's average price for one bedroom homes
	 */
	public int getOneBedroomPrice() {
		return oneBedroomPrice;
	}
	
	/**
	 * 
	 * @param oneBedroomPrice ZIP code's average price for one bedroom homes
	 */
	public void setOneBedroomPrice(Integer oneBedroomPrice) {
		this.oneBedroomPrice = oneBedroomPrice;
	}

	/**
	 * @return ZIP code's average price for two bedroom homes
	 */
	public int getTwoBedroomPrice() {
		return twoBedroomPrice;
	}
	
	/**
	 * 
	 * @param twoBedroomPrice ZIP code's average price for two bedroom homes
	 */
	public void setTwoBedroomPrice(Integer twoBedroomPrice) {
		this.twoBedroomPrice = twoBedroomPrice;
	}

	/**
	 * @return ZIP code's average price for three bedroom homes
	 */
	public int getThreeBedroomPrice() {
		return threeBedroomPrice;
	}
	
	/**
	 * 
	 * @param threeBedroomPrice ZIP code's average price for three bedroom homes
	 */
	public void setThreeBedroomPrice(Integer threeBedroomPrice) {
		this.threeBedroomPrice = threeBedroomPrice;
	}
	
	/**
	 * 
	 * @return ZIP code's average price for four bedroom homes
	 */
	public Integer getFourBedroomPrice() {
		return fourBedroomPrice;
	}

	/**
	 * 
	 * @param fourBedroomPrice ZIP code's average price for four bedroom homes
	 */
	public void setFourBedroomPrice(Integer fourBedroomPrice) {
		this.fourBedroomPrice = fourBedroomPrice;
	}

	/**
	 * 
	 * @return ZIP code's average price for five plus bedroom homes
	 */
	public Integer getFivePlusBedroomPrice() {
		return fivePlusBedroomPrice;
	}

	/**
	 * 
	 * @param fivePlusBedroomPrice ZIP code's average price for five plus bedroom homes
	 */
	public void setFivePlusBedroomPrice(Integer fivePlusBedroomPrice) {
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
	 * 
	 * @param temperature ZIP code's average daily temperature
	 */
	public void setDailyTemperature(double temperature) {
		this.dailyTemperature = temperature;
	}

	/**
	 * @return ZIP code's average annual days below freezing
	 */
	public int getAnnualDaysBelowFreezing() {
		return annualDaysBelowFreezing;
	}
	
	/**
	 * 
	 * @param daysBelowFreezing ZIP code's average annual days below freezing
	 */
	public void setAnnualDaysBelowFreezing(int daysBelowFreezing) {
		this.annualDaysBelowFreezing = daysBelowFreezing;
	}
	
	
	
}
