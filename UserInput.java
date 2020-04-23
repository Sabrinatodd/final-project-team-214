import java.util.Scanner;

/**
 * The UserInput class will store user-generated data on their home preferences. 
 * 
 * @author michaelhoffmann johnconnolly sabrinatodd
 *
 */
public class UserInput {
	private int maxPrice;
	private int numberOfBedrooms;
	private int prefTemperature;
	private int prefPrecipitation;
	private int maxDaysBelowFreezing;
	private int prefTownSize;
	private int prefMedianAge;
	private int prefHouseSize;

	
	/**
	 * Constructor for user input
	 * @param maxPrice
	 * @param numberOfBedrooms
	 * @param minTemperature
	 * @param maxPrecipitation
	 * @param maxDaysBelowFreezing
	 */
	public UserInput(int maxPrice, int numberOfBedrooms, int prefTemperature, int prefPrecipitation, int maxDaysBelowFreezing, int prefTownSize, int prefMedianAge, int prefHouseSize) {
		this.maxPrice = maxPrice;
		this.numberOfBedrooms = numberOfBedrooms;
		this.prefTemperature = prefTemperature;
		this.prefPrecipitation = prefPrecipitation;
		this.maxDaysBelowFreezing = maxDaysBelowFreezing;
		this.prefTownSize = prefTownSize;
		this.prefMedianAge = prefMedianAge;
		this.prefHouseSize = prefHouseSize;
	}

	
	/**
	 * 
	 * @return user's maximum price
	 */
	public int getMaxPrice() {
		return maxPrice;
	}

	/**
	 * 
	 * @return user's preferred number of bedrooms
	 */
	public int getNumberOfBedrooms() {
		return numberOfBedrooms;
	}

	/**
	 * 
	 * @return user's preferred daily temperature
	 */
	public int getPrefTemperature() {
		return prefTemperature;
	}

	/**
	 * 
	 * @return user's preferred monthly precipitation
	 */
	public int getPrefPrecipitation() {
		return prefPrecipitation;
	}


	/**
	 * 
	 * @return user's maximum annual days below freezing
	 */
	public int getMaxDaysBelowFreezing() {
		return maxDaysBelowFreezing;
	}
	
	/**
	 * 
	 * @return user's preferred town size
	 */
	public int getPrefTownSize() {
		return prefTownSize;
	}

	/**
	 * 
	 * @return user's preferred median age
	 */
	public int getPrefMedianAge() {
		return prefMedianAge;
	}

	/**
	 * 
	 * @return user's preferred household size
	 */
	
	public int getPrefHouseSize() {
		return prefHouseSize;
	}
	
	
}
