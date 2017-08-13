package br.com.personalassistant.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataAtual {

	public static Date getDataAtual(){
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		
		return calendar.getTime();
	}
	
}
