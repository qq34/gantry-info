package com.eagle.cloud.route.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间类操作，一律使用此文件
 * @author Administrator
 */
public class DateUtil {


    public static String defaultDateFormat = "yyyy-MM-dd";
    public static String defaultDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    public static String defaultAllDateTimeFormat = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String YYYYMM = "yyyyMM";
    public static final String DATETIME = "yyyyMMddHHmmss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String YYYY_MM_DD_CN = "yyyy年MM月dd日";
    public static final String DATETIME_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    public static final String DATETIME_M_CN = "yyyy年MM月dd日  HH时mm分";
    public static final String DATE_SPRIT = "yyyy/MM/dd";

    /**
     * 获取系统当前13位时间
     * @return
     */
    public static long getCurrentTimeMillis(){
        return System.currentTimeMillis();
    }


    public static Date getCurrentDate(){
        return new Date();
    }
    /**
     * 获取当前日期字符串
     * @return yyyy-MM-dd
     */
    public static String getCurrentDateStr(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultDateFormat);
        return simpleDateFormat.format(getCurrentDate());
    }
    public static String getCurrentDateTimeStr(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultDateTimeFormat);
        return simpleDateFormat.format(getCurrentDate());
    }

    public static String getCurrentAllDateTimeStr(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultAllDateTimeFormat);
        return simpleDateFormat.format(getCurrentDate());
    }
    /**
     * 获取当前日期字符串
     * @return format格式的当前时间
     */
    public static String getCurrentDateStrByFormat(String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(getCurrentDate());
    }
    /**
     * 格式化当前时间
     * @param pattern   格式
     * @return
     */
    public static Date getCurrentDate(String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse( getCurrentDateTimeStr());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 格式化日期，返回固定格式
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return returnValue;
    }


    public static String formatDateTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultDateTimeFormat);
        return simpleDateFormat.format(date);
    }
    public static String formatYYYY_MM_DD(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD);
        return simpleDateFormat.format(date);
    }
    /**
     * 返回时间：yyyy-MM-dd HH:mm:ss（字符串转换）
     * @param str
     * @return
     */
    public static Date paresStringToDateTime(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(defaultDateTimeFormat);
        try {
            return dateFormat.parse(str);
        } catch (Exception ex) {
            return null;
        }
    }
    /**
     * 返回时间
     * @param str   日期
     * @param pattern   日期对应格式
     * @return
     */
    public static Date paresStringToDateTime(String str, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(str);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date parseDateTime(long s){
        return new Date(s);
    }

    public static int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getCurrentMonth(){
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentMaxDay(){
        return Calendar.getInstance().getMaximum(Calendar.DAY_OF_MONTH);
    }


    /**
     * 将时间yyyy-MM-dd HH:mm:ss转换为yyyyMMdd样式的字符串
     * @return
     */
    public static String formatFullDateToString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String a[] = sdf.format(date).split(" ");
        return a[0];
    }

    /**
     * 将时间转换为pattern样式的字符串
     * @param date   时间
     * @param pattern   日期对应格式
     * @return
     */
    public static String formatDateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String d = sdf.format(date);
        return d;
    }
    

    public static int getHour(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    public static int getMinute(long s){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDateTime(s));
        return calendar.get(Calendar.MINUTE);
    }

    public static int second2Minute(long s){
        return (int) (s/1000/60);
    }

    /**
     * 比较两个时间间间隔的分钟，不足一分钟按一分钟计算
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    public static long getSplitMinutes(Date startDate,Date endDate) {
        int oneMinute = 1000 * 60;
        long splitMilS = endDate.getTime() - startDate.getTime();
        long count = splitMilS / oneMinute ;
        if(splitMilS % oneMinute != 0) count += 1;
        return count;
    }

    /**
     * 添加几天
     * @param date 日期
     * @param days 添加的几天
     * @return
     */
    public static Date addDays(Date date, int days){
        Calendar thisDay = new GregorianCalendar();
        thisDay.setTime(date);
        thisDay.add(Calendar.DATE, days);
        return thisDay.getTime();
    }
    
    /**
     * 添加几个月
     * @param date
     * @param months
     * @return
     */
    public static Date addMonths(Date date, int months){
    	 Calendar thisDay = new GregorianCalendar();
         thisDay.setTime(date);
         thisDay.add(Calendar.MONTH, months);
         return thisDay.getTime();
    }
    
    /**
     * 添加几年
     * @param date
     * @param years
     * @return
     */
    public static Date addYears(Date date, int years){
    	Calendar thisDay = new GregorianCalendar();
        thisDay.setTime(date);
        thisDay.add(Calendar.YEAR, years);
        return thisDay.getTime();
    }

    /**
     * 添加几分
     * @param date 日期
     * @param mun 分钟
     * @return
     */
    public static Date addMinutes(Date date, int mun){
        Calendar thisDay = new GregorianCalendar();
        thisDay.setTime(date);
        thisDay.add(Calendar.MINUTE, mun);
        return thisDay.getTime();
    }
    /**
     * 添加几小时
     * @param date 日期
     * @param mun 分钟
     * @return
     */
    public static Date addHours(Date date, int mun){
        Calendar thisDay = new GregorianCalendar();
        thisDay.setTime(date);
        thisDay.add(Calendar.HOUR, mun);
        return thisDay.getTime();
    }
    /**
     * 获取指定月天数
     * @param year
     * @param month
     * @return
     */
    public static int getMonthLastDay(int year, int month)  
    {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
    
    /**
     * 返回当前的小时
     */
    public static int getHour() {
    	GregorianCalendar gcNow = new GregorianCalendar();
    	return gcNow.get(GregorianCalendar.HOUR);
    }
    
    /**
     * 返回当前的分钟
     * @return int 返回当前的分钟
     */
    public static int getMinute() {
    	GregorianCalendar gcNow = new GregorianCalendar();
    	return gcNow.get(GregorianCalendar.MINUTE);
    }
    
    /**
     * 返回当前的秒数
     * @return int 第几秒
     */
    public static int getSecond() {
    	GregorianCalendar gcNow = new GregorianCalendar();
    	return gcNow.get(GregorianCalendar.SECOND);
    }
    
	/**
     * 返回今天是本月的第几天
     */
    public static int getToDayOfMonth() {
        GregorianCalendar gcNow = new GregorianCalendar();
        return gcNow.get(GregorianCalendar.DAY_OF_MONTH);
    }
    /**
     * 返回今天是本年的第几天
     */
    public static int getToDayOfYear() {
        GregorianCalendar gcNow = new GregorianCalendar();
        return gcNow.get(GregorianCalendar.DAY_OF_YEAR);
    }
    /**
     * 返回本月第一天00:00:00
     */
    public static Date getFirstDateOfMonth() {
    	String dateStr = getCurrentDateStr().substring(0,7)+"-01 00:00:00";
        return paresStringToDateTime(dateStr);
    }
    
    /**
     * 返回本年第一天00:00:00
     */
    public static Date getFirstDateOfYear() {
    	String dateStr = getCurrentDateStr().substring(0,4)+"-01-01 00:00:00";
        return paresStringToDateTime(dateStr);
    }
    
    /**
     * 返回本日00:00:00
     */
    public static Date getFirstTimeOfDay() {
    	String dateStr = getCurrentDateStr()+" 00:00:00";
        return paresStringToDateTime(dateStr);
    }
    /**
     * 返回本周第一天00:00:00
     */
    public static Date getFirstTimeOfWeek(){
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	Date date = cal.getTime();
    	return date;
    	}
    
    
    public static Date convert2Date(String dateStr) {
		Date date = null;
		int length = dateStr.length();
		SimpleDateFormat sdf = null;
		if (length == 16) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		} else if(length == 10){
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		} else if(length == 7){
			sdf = new SimpleDateFormat("yyyy-MM");
		}else{
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}
    
    /**
	 * 获取小时和分钟，24小时制
	 * @param date  需要转换的日期
	 * @return 返回DateTimeStame类型的当前时间
	 * @throws java.text.ParseException
	 * 
	 */
	public static String getHourAndMinute(Date date) {
		if (date == null)
			return null;
		String dateStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		dateStr = sdf.format(date);
		return dateStr;
	}
	/**
	 * 返回两个日期之间的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDifferentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
	/**
	 * 计算两个时间点之间的周数
	 */
	public static int getWeeksBy2Date(Date startDate,Date endDate){
		return (int)((startDate.getTime()-startDate.getTime())/86400000/7);
	}
	/**
	 * 计算两个时间点之间的月数
	 */
	public static int getMonthsBy2Date(Date startDate,Date endDate){
		return (int)((startDate.getTime()-startDate.getTime())/86400000/30);
	}
	/**
	 * 计算两个时间点之间的季数
	 */
	public static int getQuarterBy2Date(Date startDate,Date endDate){
		return (int)((startDate.getTime()-startDate.getTime())/86400000/90);
	}
}
