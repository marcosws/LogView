package logview.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Common {
	
	public static String formatDateTime(String dateTime){
		
		String dateTimeFormated = "";
		SimpleDateFormat oldFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			dateTimeFormated = newFormat.format(oldFormat.parse(dateTime));
		} 
		catch(ParseException e) {
			dateTimeFormated = dateTime;
		}
		return dateTimeFormated;
	} // EEE MMM dd HH:mm:ss z yyyy
	
	public static String formatDate(String date){
		
		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		df.setTimeZone(TimeZone.getTimeZone("BRT"));
		DateFormat utcFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return utcFormat.format(d); 
	}
	public static String formatTime(String time){
		
		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		df.setTimeZone(TimeZone.getTimeZone("BRT"));
		DateFormat utcFormat = new SimpleDateFormat("HH:mm:ss");
		Date d = null;
		try {
			d = df.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utcFormat.format(d); 
	}
}
