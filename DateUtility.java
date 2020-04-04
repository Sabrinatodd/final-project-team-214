import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtility {
	int year;
	int month;
	int day;
	
	public DateUtility(String date){
		this.year = Integer.parseInt(date.substring(0, 4));
		this.month = Integer.parseInt(date.substring(5,7));
		this.day = Integer.parseInt(date.substring(8,10));
	}
	
	public String addOneDayCalendar(String date) throws ParseException{	  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar c = Calendar.getInstance();
	    c.setTime(sdf.parse(date));
	    c.add(Calendar.DATE, 1);
	    return sdf.format(c.getTime());
	}
	
	
	public static void main(String[] args) {
		DateUtility du = new DateUtility("2019-05-01");
		System.out.println(du.getYear());
		System.out.println(du.getMonth());
		System.out.println(du.getDay());
		
		try {
			System.out.println(du.addOneDayCalendar("2019-05-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
	

