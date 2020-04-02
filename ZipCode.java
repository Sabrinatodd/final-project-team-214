
public class ZipCode {
	private int homePriceOverall;
	private int oneBedroomPrice;
	private int twoBedroomPrice;
	private int threeBedroomPrice;
	private double monthlyPrecipitation;
	private double dailyTemperature;
	private int annualDaysBelowFreezing;

	
	/**
	 * Constructor for ZIP codes
	 * @param homePrice
	 * @param oneBedroom
	 * @param twoBedroom
	 * @param threeBedroom
	 * @param precipitation
	 * @param temperature
	 * @param daysBelowFreezing
	 */
	public ZipCode(int homePrice, int oneBedroom, int twoBedroom, int threeBedroom, double precipitation, double temperature, int daysBelowFreezing) {
		this.homePriceOverall = homePrice;
		this.oneBedroomPrice = oneBedroom;
		this.twoBedroomPrice = twoBedroom;
		this.threeBedroomPrice = threeBedroom;
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
	 * @return ZIP code's average price for one bedroom homes
	 */
	public int getOneBedroomPrice() {
		return oneBedroomPrice;
	}

	/**
	 * @return ZIP code's average price for two bedroom homes
	 */
	public int getTwoBedroomPrice() {
		return twoBedroomPrice;
	}

	/**
	 * @return ZIP code's average price for three bedroom homes
	 */
	public int getThreeBedroomPrice() {
		return threeBedroomPrice;
	}

	/**
	 * @return ZIP code's average monthly precipitation
	 */
	public double getMonthlyPrecipitation() {
		return monthlyPrecipitation;
	}

	/**
	 * @return ZIP code's average daily temperature
	 */
	public double getDailyTemperature() {
		return dailyTemperature;
	}

	/**
	 * @return ZIP code's average annual days below freezing
	 */
	public int getAnnualDaysBelowFreezing() {
		return annualDaysBelowFreezing;
	}
	
	
	
}
