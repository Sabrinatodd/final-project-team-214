<<<<<<< HEAD
/**
=======

 /**
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
 * This class creates a DailyWeather object that will store 
 * characteristics for weather on a daily basis. This will be
 * used in conjunction with the WeatherAPI Class.
 *
 */
public class DailyWeather {
<<<<<<< HEAD
	private String date;
	private String station;
	private String zipCode;
	private double tempMax;
	private double tempMin;
	private double precipitation;
	private double snow;
=======
	String date;
	String station;
	String zipCode;
	double tempMax;
	double tempMin;
	double precipitation;
	double snow;
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
	
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
<<<<<<< HEAD
=======
	 * 
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
<<<<<<< HEAD
=======
	 * 
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
<<<<<<< HEAD
	 * @return max temperature
=======
	 * 
	 * @return max temp
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
	 */
	public double getTempMax() {
		return tempMax;
	}

	/**
<<<<<<< HEAD
=======
	 * 
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
	 * @param tempMax
	 */
	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	/**
<<<<<<< HEAD
	 * @return minimum temperature
=======
	 * 
	 * @return min temp
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
	 */
	public double getTempMin() {
		return tempMin;
	}

	/**
<<<<<<< HEAD
=======
	 * 
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
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
<<<<<<< HEAD
=======
	 * 
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
	 * @param station
	 */
	public void setStation(String station) {
		this.station = station;
	}

	/**
<<<<<<< HEAD
=======
	 * 
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
	 * @return ZIP code
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
<<<<<<< HEAD
=======
	 * 
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
	 * @param zipCode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}	
	
	/**
<<<<<<< HEAD
	 * @return precipitation
=======
	 * 
	 * @return preciitation
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
	 */
	public double getPrecipitation() {
		return precipitation;
	}

<<<<<<< HEAD
	 /**
=======
	/**
	 * 
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
	 * @param precipitation
	 */
	public void setPrecipitation(double precipitation) {
		this.precipitation = precipitation;
	}

	/**
<<<<<<< HEAD
=======
	 * 
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
	 * @return snow
	 */
	public double getSnow() {
		return snow;
	}

	/**
<<<<<<< HEAD
=======
	 * 
>>>>>>> 153fd32788d30934e6e8f5897ed6d7f59a89366b
	 * @param snow
	 */
	public void setSnow(double snow) {
		this.snow = snow;
	}
}