import java.util.Scanner;

/**
 * The UserInput class will store user-generated data on their home preferences. 
 * 
 * We will ask California residents a series of questions to assist their home buying journeys. 
 * We may refine these questions as the project progresses. 
 * 
 * 1. What is the maximum dollar amount you are willing to spend on a home? Please enter a number. 
 * 2. How many bedrooms? Please enter a number between 1 and 5. 
 * 3. Minimum average daily temperature? Please enter a number between X and Y. (Depending on CA range) 
 * 4. Maximum amount of monthly precipitation ? Please enter a number between X and Y. Note that the California average is Z. 
 * 5. Maximum days below freezing? Please enter a number between X and Y. Note that the California average is Z. 
 * 
 * @author michaelhoffmann johnconnolly sabrinatodd
 *
 */
public class UserInput {
	private int maxPrice;
	private int numberOfBedrooms;
	private int minTemperature;
	private int maxPrecipitation;
	private int maxDaysBelowFreezing;

	
	/**
	 * Constructor for user input
	 * @param maxPrice
	 * @param numberOfBedrooms
	 * @param minTemperature
	 * @param maxPrecipitation
	 * @param maxDaysBelowFreezing
	 */
	public UserInput(int maxPrice, int numberOfBedrooms, int minTemperature, int maxPrecipitation, int maxDaysBelowFreezing) {
		this.maxPrice = maxPrice;
		this.numberOfBedrooms = numberOfBedrooms;
		this.minTemperature = minTemperature;
		this.maxPrecipitation = maxPrecipitation;
		this.maxDaysBelowFreezing = maxDaysBelowFreezing;
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
	 * @return user's minimum number of bedrooms
	 */
	public int getNumberOfBedrooms() {
		return numberOfBedrooms;
	}

	/**
	 * 
	 * @return user's minimum daily temperature
	 */
	public int getMinTemperature() {
		return minTemperature;
	}

	/**
	 * 
	 * @return user's maximimum monthly precipitation
	 */
	public int getMaxPrecipitation() {
		return maxPrecipitation;
	}


	/**
	 * 
	 * @return user's maxmimum annual days below freezing
	 */
	public int getMaxDaysBelowFreezing() {
		return maxDaysBelowFreezing;
	}
	
	/**
	 * Main method to test scanning in user input to the UserInput class. 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("What is the maximum dollar amount you are willing to spend on a home? Please enter a number.");
		int price = in.nextInt();
		System.out.println("How many bedrooms would you like? Please enter a number between 1 and 5.");
		int bedrooms = in.nextInt();
		System.out.println("What minimum average daily temperature do you prefer? Please enter a number between X and Y. (Depending on CA range)");
		int temperature = in.nextInt();
		System.out.println("What is the maximum amount of monthly precipitation you prefer? Please enter a number between X and Y. Note that the California average is Z.");
		int precipitation = in.nextInt();
		System.out.println("What is the maximum number of days below freezing per year you prefer? Please enter a number between X and Y. Note that the California average is Z. ");
		int freezingDays = in.nextInt();
		UserInput inputs = new UserInput(price, bedrooms, temperature, precipitation, freezingDays);
		in.close();
	}
	
	
}
