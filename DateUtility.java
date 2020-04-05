import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * this class provides utility using dates that are
 * subsequently used in our WeatherAPI Class
 */
public class DateUtility {
	int year;
	int month;
	int day;
	
	/**
	 * this constructor takes a "yyyy-mmm-dd" date format
	 * and turns it into
	 * @param date a "yyyy-mmm-dd" string variable date
	 */
	public DateUtility(String date){
		this.year = Integer.parseInt(date.substring(0, 4));
		this.month = Integer.parseInt(date.substring(5,7));
		this.day = Integer.parseInt(date.substring(8,10));
	}
	
	/**
	 * this method increments a string variable date and increments
	 * it up by one day
	 * @param date the starting date
	 * @return String representing the date plus one day
	 * @throws ParseException
	 */
	public String incrementCalendar(String date) throws ParseException{	  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar c = Calendar.getInstance();
	    c.setTime(sdf.parse(date));
	    c.add(Calendar.DATE, 1);
	    return sdf.format(c.getTime());
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
}
	

