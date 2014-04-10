package com.royaltechnosoft.inquiry.util;

import java.util.Calendar;
import java.util.Date;

public class Validate {
	
	public static boolean stringValue(String string){
		return stringValue(string, null, null);
	}
	
	public static boolean stringValue(String string, Integer minLength, Integer maxLength){
		if(string==null)
			return false;
		String testString = new String(string).trim();
		if(testString==null || testString.length()==0)
			return false;
		if(minLength!=null && testString.length()<minLength)
			return false;
		if(maxLength!=null && testString.length()>maxLength)
			return false;
		return true;
	}
	
	public static boolean dateValue(Date date){
		return dateValue(date, null, null);
	}
	
	public static boolean dateValue(Date date, Date minDate, Date maxDate){
		if(date==null)
			return false;
		if(minDate!=null && date.compareTo(minDate)<0)
			return false;
		if(maxDate!=null && date.compareTo(maxDate)>0)
			return false;
		return true;
	}
	
	public static boolean dateValue(Date date, Integer daysFromToday){
		if(date==null)
			return false;
		if(daysFromToday==null)
			return true;
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		Calendar extremeDate = Calendar.getInstance();
		extremeDate.setTime(today.getTime());
		extremeDate.add(Calendar.DATE, daysFromToday);
		if(today.compareTo(extremeDate)<0)
			return dateValue(date, today.getTime(), extremeDate.getTime());
		if(today.compareTo(extremeDate)>0)
			return dateValue(date, extremeDate.getTime(), today.getTime());
		else
			return dateValue(date);
	}
	
	public static boolean integerValue(Integer number){
		return integerValue(number, null, null);
	}
	
	public static boolean integerValue(Integer number, Integer min, Integer max){
		if(number==null)
			return false;
		if(max!=null && number>max)
			return false;
		if(min!=null && number<min)
			return false;
		return true;
	}
	
	public static boolean longValue(Long number){
		return longValue(number, null, null);
	}
	
	public static boolean longValue(Long number, Long min, Long max){
		if(number==null)
			return false;
		if(max!=null && number>max)
			return false;
		if(min!=null && number<min)
			return false;
		return true;
	}
	
	public static boolean shortValue(Short number){
		return shortValue(number, null, null);
	}
	
	public static boolean shortValue(Short number, Short min, Short max){
		if(number==null)
			return false;
		if(max!=null && number>max)
			return false;
		if(min!=null && number<min)
			return false;
		return true;
	}
	
	public static boolean byteValue(Byte number){
		return byteValue(number, null, null);
	}
	
	public static boolean byteValue(Byte number, Byte min, Byte max){
		if(number==null)
			return false;
		if(max!=null && number>max)
			return false;
		if(min!=null && number<min)
			return false;
		return true;
	}
	
	public static boolean booleanValue(Boolean value){
		if(value==null)
			return false;
		return true;
	}
	
	
	
}
