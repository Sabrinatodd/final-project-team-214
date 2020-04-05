/**
 * this class creates a DailyWeather object that will store 
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
	


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTempMax() {
		return tempMax;
	}

	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	public double getTempMin() {
		return tempMin;
	}

	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}	
	
	public double getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(double precipitation) {
		this.precipitation = precipitation;
	}

	public double getSnow() {
		return snow;
	}

	public void setSnow(double snow) {
		this.snow = snow;
	}
}
