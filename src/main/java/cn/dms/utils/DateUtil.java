package cn.dms.utils;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;


public class DateUtil {
	public static String getNDaysLater(String date, String days){
		String dateFormat[] = {"yyyyMMdd"};
		Date d = org.apache.http.client.utils.DateUtils.parseDate(date, dateFormat);
		Date d1 = DateUtils.addDays(d, Integer.parseInt(days));
		return org.apache.http.client.utils.DateUtils.formatDate(d1, dateFormat[0]);
	}

}	
