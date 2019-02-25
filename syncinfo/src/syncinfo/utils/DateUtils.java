package syncinfo.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;


/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author vincent
 * @date 2015-8-7
 */
/**
 * @author xuchy
 *
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = { "yyyy-MM","yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}
	
    
	public static Date getDateStart(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd")+" 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getDateEnd(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd") +" 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 判断字符串是否是日期
	 * @param timeString
	 * @return
	 */
	public static boolean isDate(String timeString){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try{
			format.parse(timeString);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * 格式化时间
	 * @param timestamp
	 * @return
	 */
	public static String dateFormat(Date timestamp){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(timestamp);
	}
	
	/**
	 * 获取系统时间Timestamp
	 * @return
	 */
	public static Timestamp getSysTimestamp(){
		return new Timestamp(new Date().getTime());
	}
	
	/**
	 * 获取系统时间Date
	 * @return
	 */
	public static Date getSysDate(){
		return new Date();
	}
	
	/**
	 * 生成时间随机数 
	 * @return
	 */
	public static String getDateRandom(){
		String s=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		return s;
	}
	
	/**
	 * 生成毫秒值
	 * @return
	 */
	public static String getCurrentTimeMillis(){
		Calendar cal = Calendar.getInstance();
		
		return String.valueOf(cal.getTimeInMillis());
	}
	
	
	/**
	 * 获取执行时间差
	 * @param request
	 * @return
	 */
	public static String getExecuteTime(long begin){
		long now = System.currentTimeMillis();
	
		return String.valueOf(now - begin);
	}
	/**
	 * 获取当前月第一天
	 * @param request
	 * @return
	 */
	public static String getCurrentMonthFirst(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar ca= Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH));  
        String first = format.format(ca.getTime());
		return first;
	}
	/**
	 * 获取当前月最后一天
	 * @param request
	 * @return
	 */
	public static String getCurrentMonthLast(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String last = format.format(ca.getTime());
        return last;
	}
	/**
	 * 获取指定年月最后一天
	 * @param request
	 * @return
	 */
	public static String getMonthLast(int year,int month){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.YEAR,year);    
        ca.set(Calendar.MONTH, month-1);  
        int lastDay = ca.getActualMaximum(Calendar.DAY_OF_MONTH);  
        ca.set(Calendar.DAY_OF_MONTH, lastDay);  
        String lastDayOfMonth = format.format(ca.getTime());
		return lastDayOfMonth;
	}
	/**
	 * 获取指定年月第一天
	 * @param request
	 * @return
	 */
	public static String getMonthFirst(int year,int month){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.YEAR,year);    
        ca.set(Calendar.MONTH, month-1);  
        int firstDay = ca.getActualMinimum(Calendar.DAY_OF_MONTH);  
        ca.set(Calendar.DAY_OF_MONTH, firstDay);  
        String firstDayOfMonth = format.format(ca.getTime());
		return firstDayOfMonth;
	}
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {

	}
	
}
