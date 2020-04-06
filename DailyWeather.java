
 /**
 * This class creates a DailyWeather object that will store 
 * characteristics for weather on a daily basis. This will be
 * used in conjunction with the WeatherAPI Class.
 *
 */
public class DailyWeather {
	String date;
	String station;
	String zipCode;
	double tempMax;
	double tempMin;
	double precipitation;
	double snow;
	
	/**
	 * Constructor for DailyWeather
	 * @param date
	 * @param station
	 * @param zipCode
	 * @param max
	 * @param min
	 * @param precip
	 * @param snowfall
	 */
	public DailyWeather(String date, String station, String zipCode, double max, double min, double precip, double snowfall) {
		super();
		this.date = date;
		this.station = station;
		this.zipCode = zipCode;
		this.tempMax = max;
		this.tempMin = min;
		this.precipitation = precip;
		this.snow = snowfall;
	}
	

	/**
	 * 
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 
	 * @return max temp
	 */
	public double getTempMax() {
		return tempMax;
	}

	/**
	 * 
	 * @param tempMax
	 */
	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	/**
	 * 
	 * @return min temp
	 */
	public double getTempMin() {
		return tempMin;
	}

	/**
	 * 
	 * @param tempMin
	 */
	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}

	/**
	 * 
	 * @return station
	 */
	public String getStation() {
		return station;
	}

	/**
	 * 
	 * @param station
	 */
	public void setStation(String station) {
		this.station = station;
	}

	/**
	 * 
	 * @return ZIP code
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 
	 * @param zipCode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}	
	
	/**
	 * 
	 * @return preciitation
	 */
	public double getPrecipitation() {
		return precipitation;
	}

	/**
	 * 
	 * @param precipitation
	 */
	public void setPrecipitation(double precipitation) {
		this.precipitation = precipitation;
	}

	/**
	 * 
	 * @return snow
	 */
	public double getSnow() {
		return snow;
	}

	/**
	 * 
	 * @param snow
	 */
	public void setSnow(double snow) {
		this.snow = snow;
	}
}
