package date_time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDate {

	// Convert a Calendar object to sql.Date object
	public java.sql.Date toDate(Calendar cal) {
		// I don't know why but it works if I decrease a month :))
		cal.add(Calendar.MONTH, -1);
		return new java.sql.Date(cal.getTime().getTime());
	}

	// Convert String pattern (dd/MM/yyyy) to sql.Date
	public java.sql.Date toDate(String date) {
		String[] data = date.split("/");
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(data[2]), Integer.parseInt(data[1]),
				Integer.parseInt(data[0]));
		// I don't know why but it works when I decrease a month :))
		// Maybe because the Month_index starts at 0, like an array.
		cal.add(Calendar.MONTH, -1);
		java.sql.Date d = new java.sql.Date(cal.getTime().getTime());
		return d;
	}

	// Covert util.Date to sql.Date
	public java.sql.Date convertToSQLDate(java.util.Date utilDate) {
		return new java.sql.Date(utilDate.getTime());
	}

	// convert sql.Date to util.Date
	public java.util.Date convertToUtilDate(java.sql.Date sqlDate) {
		return new java.util.Date(sqlDate.getTime());
	}

	// Convert sql.Date to String(yyyy/MM/dd)
	public String toString(java.sql.Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	// check if date pattern is valid.
	// date pattern maybe dd/MM/yyyy or dd-MM-yyyy
	public boolean isValidDateString(String datePattern) {
		String dateRegType1 = "(([012][0-9])||([3][01]))[/](([0][0-9])||[1][0-2])[/](19||20)[0-9][0-9]";
		String dateRegType2 = "(([012][0-9])||([3][01]))[-](([0][0-9])||[1][0-2])[-](19||20)[0-9][0-9]";
		// So khop 
		return datePattern.matches(dateRegType1)||datePattern.matches(dateRegType2);
	}

	public static void main(String[] args) {
		System.out.println(new MyDate().isValidDateString("12-03-1994"));
		
		
		Calendar cal = Calendar.getInstance();
		cal.set(2016, 12, 25);

		// Date date = cal.getTime();
		System.out.println(new MyDate().toDate("1990/05/15"));
		System.out.println(new MyDate().toDate(cal));
		java.sql.Date d = null;
		System.out.println(d = new MyDate().convertToSQLDate(new Date()));
		System.out.println(new MyDate().convertToUtilDate(d));
		System.out.println("to String: " + new MyDate().toString(d));
	}
}
