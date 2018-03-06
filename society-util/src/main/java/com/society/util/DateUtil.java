package com.society.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 * 
 * @author lewis
 * 
 */
public class DateUtil {

	public final static String TIME_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

	public final static String yyyyMMddHHmm = "yyyyMMddHHmm";

	public final static String yyyy1MM1dd = "yyyy-MM-dd";

	public final static String yyyyMMdd = "yyyyMMdd";

	public final static String yyyy1MM1dd2HH3mm = "yyyy-MM-dd HH:mm";

	/**
	 * 日期字符串转换为整型
	 * 
	 * @param datestr
	 *            yyyy-MM-dd
	 * @return yyyyMMdd
	 */
	public static int str2Int(String datestr) {
		if (null == datestr || datestr.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(datestr.replace("-", "").replace(" ", ""));
	}

	/**
	 * 日期字符串转换为整型
	 * 
	 * @param datestr
	 *            yyyy-MM-dd HH:mm
	 * @return yyyyMMddHHmm
	 */
	public static long minstr2Int(String minstr) {
		if (null == minstr || minstr.isEmpty()) {
			return 0;
		}
		return Long.parseLong(minstr.replace("-", "").replace(" ", "").replace(":", ""));
	}

	/**
	 * 判断日期是否“yyyy-MM-dd”格式
	 * 
	 * @param datestr
	 *            日期字符串
	 * @return
	 */
	public static boolean isDateFormat(String datestr) {
		if (null == datestr) {
			return false;
		}
		String eL = "[1-9][0-9]{3}-[0-9]{2}-[0-9]{2}";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(datestr);
		boolean dateFlag = m.matches();
		return dateFlag;
	}

	/**
	 * 判断日期是否“yyyyMMddHHmm”格式
	 * 
	 * @param datestr
	 *            日期字符串
	 * @return boolean
	 */
	public static boolean isDateFormat2(String datestr) {
		if (null == datestr) {
			return false;
		}
		String eL = "[1-9][0-9]{3}[0-1][0-9][0-3][0-9][0-2][0-9][0-6][0-9]";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(datestr);
		boolean dateFlag = m.matches();
		return dateFlag;
	}

	/**
	 * 判断日期是否“yyyyMMdd”格式
	 * 
	 * @param datestr
	 *            日期字符串
	 * @return boolean
	 */
	public static boolean isDateFormat3(String datestr) {
		if (null == datestr) {
			return false;
		}
		String eL = "[1-9][0-9]{3}[0-1][0-9][0-3][0-9]";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(datestr);
		boolean dateFlag = m.matches();
		return dateFlag;
	}

	/**
	 * yyyyMMddHHmm格式转换成yyyy年MM月dd日
	 */
	public static String datestrFormat(String datestr) {
		if (null == datestr) {
			return datestr;
		}
		return datestr.substring(0, 4) + "年" + datestr.substring(4, 6) + "月" + datestr.substring(6, 8) + "日";

	}

	/**
	 * 日期转换成秒
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static long toSecond(Date date) {
		return date.getTime() / 1000;
	}

	/**
	 * 字符串日期转换成Date
	 * 
	 * @param dateString
	 * 
	 * @return
	 */
	public static Date str2Date(String dateString, String format) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException("时间转化格式错误!" + "[dateString=" + dateString + "]" + "[format=" + format + "]");
		}
	}

	/**
	 * 字符串日期转换成Date
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date str2Date(String dateString) {
		return str2Date(dateString, TIME_FORMAT_STRING);
	}

	public static String date2String(Date date, String format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	public static String date2String(Date date) {
		return date2String(date, TIME_FORMAT_STRING);
	}

	public static String date2StringDate(Date date) {
		return date2String(date, yyyy1MM1dd);
	}

	public static Date getDayBefore(Date date, int day) {
		return new Date(date.getTime() - day * TimeUtil.ONE_DAY_MILLI_SECONDS);
	}

	public static Date getDayBefore(int day) {
		return getDayBefore(new Date(), day);
	}

	public static String getDayBeforeStr(int day) {
		return getDayBeforeStr(day, yyyy1MM1dd);
	}

	public static String getDayBeforeStr(int day, String format) {
		return date2String(getDayBefore(day), format);
	}

	public static Date getYesterDay() {
		return getDayBefore(1);
	}

	public static String getYesterDayStr() {
		return getDayBeforeStr(1);
	}

	public static String getYesterDayStr(String format) {
		return getDayBeforeStr(1, format);
	}

	public static String getTwoDayBeforeStr() {
		return getDayBeforeStr(2);
	}

	public static Date getWeekBefore(Date date) {
		return new Date(date.getTime() - TimeUtil.ONE_WEEK_MILLI_SECONDS);
	}

	public static String getWeekBeforeStr() {
		return date2String(getWeekBefore(new Date()), yyyy1MM1dd);
	}

	public static String getWeekBeforeStr(String format) {
		return date2String(getWeekBefore(new Date()), format);
	}

	public static String getTodayDayStr() {
		return getTodayDayStr(yyyy1MM1dd);
	}

	public static String getTodayDayStr2() {
		return getTodayDayStr(yyyyMMdd);
	}

	public static String getTodayDayStr(String format) {
		return date2String(new Date(), format);
	}

	/**
	 * 获取当前分钟，格式：2017-02-10 12:30
	 * 
	 * @return
	 */
	public static String getTodayMinStr() {
		return date2String(new Date(), yyyy1MM1dd2HH3mm);
	}

	/**
	 * 获取当前分钟，格式：201702101230
	 * 
	 * @return
	 */
	public static String getMinuteStr() {
		return date2String(new Date(), yyyyMMddHHmm);
	}

	/**
	 * 获取当前分钟，格式：201702101230
	 * 
	 * @param date
	 *            时间
	 * @return
	 */
	public static String getMinuteStr(Date date) {
		return date2String(date, yyyyMMddHHmm);
	}

	/**
	 * 获取前几分钟
	 * 
	 * @param minute
	 *            前几分钟
	 * @return
	 */
	public static String getBeforeMinuteStr(int minute) {
		long time = (new Date()).getTime() - (60 * 1000 * minute);
		return date2String(new Date(time), yyyyMMddHHmm);
	}

	/**
	 * 获取前几分钟
	 * 
	 * @param minute
	 *            前几分钟
	 * @return
	 */
	public static String getAfterMinuteStr(int minute) {
		return getAfterMinuteStr(new Date(), minute);
	}

	public static String getAfterMinuteStr(Date date, int minute) {
		long time = date.getTime() + (60 * 1000 * minute);
		return date2String(new Date(time), yyyyMMddHHmm);
	}

	/**
	 * 获取某个时间的前几分钟
	 * 
	 * @param minute
	 *            前几分钟
	 * @return
	 */
	public static String getBeforeMinuteStr(Date date, int minute) {
		long time = date.getTime() - (60 * 1000 * minute);
		return date2String(new Date(time), yyyyMMddHHmm);
	}

	public static String getTomorrowDayStr() {
		return date2String(getTomorrowDay(new Date()), yyyy1MM1dd);
	}

	public static String getTomorrowDayStr2() {
		return date2String(getTomorrowDay(new Date()), yyyyMMdd);
	}

	private static Date getTomorrowDay(Date date) {
		return new Date(date.getTime() + TimeUtil.ONE_DAY_MILLI_SECONDS);
	}

	public static boolean between(String date, String startDate, String endDate) {
		return startDate.compareTo(date) <= 0 && endDate.compareTo(date) >= 0;
	}

	public static boolean todayBetween(String startDate, String endDate) {
		return between(getTodayDayStr(), startDate, endDate);
	}

	/**
	 * 判断日期是否距离今天几天之内
	 * 
	 * @param Date
	 *            日期
	 * @param days
	 *            天数
	 * @return
	 */
	public static boolean between(Date date, double hours) {
		long begin = date.getTime();
		long end = (new Date()).getTime();
		if (begin > end) {
			return false;
		}
		double d = (end - begin) / (1000 * 60 * 60);
		if (d > hours) {
			return false;
		}
		return true;
	}

	/**
	 * 计算两个日期的天数差，格式为：yyyyMMdd，如：20170316
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int diffDay(int startDate, int endDate) {
		Calendar startC = Calendar.getInstance();
		int yearS = startDate / 10000;
		int monthS = (startDate % 10000) / 100 - 1;
		int dayS = startDate % 100;
		startC.set(yearS, monthS, dayS);

		Calendar endC = Calendar.getInstance();
		int yearE = endDate / 10000;
		int monthE = (endDate % 10000) / 100 - 1;
		int dayE = endDate % 100;
		endC.set(yearE, monthE, dayE);

		long diffMill = endC.getTimeInMillis() - startC.getTimeInMillis();
		return (int) (diffMill / (1000 * 60 * 60 * 24));
	}

	public static int diffDay(String startDate, String endDate) {
		Date d1 = str2Date(startDate, yyyy1MM1dd);
		Date d2 = str2Date(endDate, yyyy1MM1dd);
		long t1 = d1.getTime();
		long t2 = d2.getTime();
		long diffMill;
		if (t1 > t2) {
			diffMill = t1 - t2;
		} else {
			diffMill = t2 - t1;
		}
		return (int) (diffMill / (1000 * 60 * 60 * 24));
	}

	public static int diffDay(String startDate, String endDate, String format) {
		Date d1 = str2Date(startDate, format);
		Date d2 = str2Date(endDate, format);
		long t1 = d1.getTime();
		long t2 = d2.getTime();
		long diffMill;
		if (t1 > t2) {
			diffMill = t1 - t2;
		} else {
			diffMill = t2 - t1;
		}
		return (int) (diffMill / (1000 * 60 * 60 * 24));
	}

	public static int diffToday(String date) {
		Date d1 = str2Date(date, yyyy1MM1dd);
		long t1 = d1.getTime();
		long t2 = (new Date()).getTime();
		long diffMill;
		if (t1 > t2) {
			diffMill = t1 - t2;
		} else {
			diffMill = t2 - t1;
		}
		return (int) (diffMill / (1000 * 60 * 60 * 24));
	}

	/**
	 * 日期天数加法运算
	 * 
	 * @param date
	 *            被操作日期，格式为：yyyyMMdd，如：20170316
	 * @param days
	 *            增加天数
	 * @return
	 */
	public static int addDay(int date, int days) {
		Calendar dateC = Calendar.getInstance();
		int year = date / 10000;
		int month = (date % 10000) / 100 - 1;
		int day = date % 100;
		dateC.set(year, month, day);
		dateC.add(Calendar.DAY_OF_YEAR, days);
		year = dateC.get(Calendar.YEAR);
		month = dateC.get(Calendar.MONTH) + 1;
		day = dateC.get(Calendar.DAY_OF_MONTH);
		return year * 10000 + month * 100 + day;
	}

	/**
	 * 
	 * @param date
	 *            被操作日期，格式为：yyyyMMdd，如：20170316
	 * @return 返回格式为：yyyy-MM-dd
	 */
	public static String int2String(int date) {
		StringBuilder dateStr = new StringBuilder(10);
		dateStr.append(date);
		dateStr.insert(6, '-');
		dateStr.insert(4, '-');
		return dateStr.toString();
	}

	/**
	 * 
	 * @param date
	 *            被操作日期，格式为：yyyyMMddHHmm，如：201703161200
	 * @return 返回格式为：yyyy-MM-dd HH:mm
	 */
	public static String int2MinStr(long dateMin) {
		StringBuilder dateStr = new StringBuilder(10);
		dateStr.append(dateMin);
		dateStr.insert(10, ':');
		dateStr.insert(8, ' ');
		dateStr.insert(6, '-');
		dateStr.insert(4, '-');
		return dateStr.toString();
	}

	/**
	 * 日期天数加法运算
	 * 
	 * @param date
	 *            被操作日期，格式为：yyyyMMddHHmm，如：201703161200
	 * @param days
	 *            增加天数
	 * @return
	 */
	public static long addMin(long dateMin, int mins) {
		long yearRate = 100000000;
		long monthRate = 1000000;
		long dayRate = 10000;
		long hourRate = 100;
		Calendar dateC = Calendar.getInstance();
		long year = dateMin / yearRate;
		long month = (dateMin % yearRate) / monthRate - 1;
		long day = (dateMin % monthRate) / dayRate;
		long hourOfDay = (dateMin % dayRate) / hourRate;
		long minute = dateMin % hourRate;
		dateC.set((int) year, (int) month, (int) day, (int) hourOfDay, (int) minute);
		dateC.add(Calendar.MINUTE, mins);
		year = dateC.get(Calendar.YEAR);
		month = dateC.get(Calendar.MONTH) + 1;
		day = dateC.get(Calendar.DAY_OF_MONTH);
		hourOfDay = dateC.get(Calendar.HOUR_OF_DAY);
		minute = dateC.get(Calendar.MINUTE);
		return year * yearRate + month * monthRate + day * dayRate + hourOfDay * hourRate + minute;
	}

	/**
	 * 获取星期几<br>
	 * {@link Calendar.SUNDAY}
	 * 
	 * @param date
	 *            日期，格式：yyyyMMdd
	 * @return
	 */
	public static int getWeekday(int date) {
		Calendar dateC = Calendar.getInstance();
		int year = date / 10000;
		int month = (date % 10000) / 100 - 1;
		int day = date % 100;
		dateC.set(year, month, day);
		int weekday = dateC.get(Calendar.DAY_OF_WEEK);
		return weekday;
	}

	/**
	 * @param statTime
	 *            yyyyMMddHHmm
	 * @param endTime
	 *            yyyyMMddHHmm
	 * @return
	 */
	public static long diffMin(long startTime, long endTime) {
		Calendar startC = Calendar.getInstance();
		long yearS = startTime / 100000000;
		long monthS = (startTime % 100000000) / 1000000 - 1;
		long dayS = (startTime % 1000000) / 10000;
		long hourS = (startTime % 10000) / 100;
		long minS = startTime % 100;
		startC.set((int) yearS, (int) monthS, (int) dayS, (int) hourS, (int) minS);

		Calendar endC = Calendar.getInstance();
		long yearE = endTime / 100000000;
		long monthE = (endTime % 100000000) / 1000000 - 1;
		long dayE = (endTime % 1000000) / 10000;
		long hourE = (endTime % 10000) / 100;
		long minE = endTime % 100;
		endC.set((int) yearE, (int) monthE, (int) dayE, (int) hourE, (int) minE);

		long diffMill = endC.getTimeInMillis() - startC.getTimeInMillis();
		return (int) (diffMill / (1000 * 60));
	}

	/**
	 * 获取星期几
	 * 
	 * @return
	 */
	public static int getTodayWeekday() {
		Calendar dateC = Calendar.getInstance();
		String todayStr = DateUtil.getTodayDayStr();
		int today = DateUtil.str2Int(todayStr);
		int year = today / 10000;
		int month = (today % 10000) / 100 - 1;
		int day = today % 100;
		dateC.set(year, month, day);
		int weekday = dateC.get(Calendar.DAY_OF_WEEK);
		return weekday;
	}

	/**
	 * 小时数字转小时字符串
	 * 
	 * @param dataHour，0-23
	 * @return
	 */
	public static String getHourStr(Integer hour) {
		String hourStr = String.valueOf(hour);
		if (hourStr.length() == 1) {
			return "0" + hour + ":00";
		}
		return hour + ":00";
	}

	/**
	 * 判断日期字符串是否是今天
	 * 
	 * @param date
	 *
	 * @return
	 */
	public static boolean isToday(String date, String format) {
		if (null == date || null == format) {
			return false;
		}
		String todayDayStr = getTodayDayStr(format);
		if (todayDayStr.equals(date)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取当前HH
	 * 
	 * @return
	 */
	public static Integer getNowHour() {
		String nowHour = DateUtil.getTodayDayStr(DateUtil.TIME_FORMAT_STRING).substring(11, 13);
		return Integer.valueOf(nowHour);
	}

	/**
	 * 获取当前mm
	 * 
	 * @return
	 */
	public static Integer getNowMinute() {
		String nowMinute = DateUtil.getTodayDayStr(DateUtil.TIME_FORMAT_STRING).substring(14, 16);
		return Integer.valueOf(nowMinute);
	}

	/**
	 * 获取当前HH:mm
	 * 
	 * @return
	 */
	public static String getNowTime() {
		return DateUtil.getTodayDayStr(DateUtil.TIME_FORMAT_STRING).substring(11, 16);
	}

	/**
	 * 判断时间HH:mm是否在指定区间内
	 * 
	 * @param nowMinute
	 * @param beginMinute
	 * @param endMinute
	 * @return
	 */
	public static boolean betweenMinute(String nowMinute, String beginMinute, String endMinute) {
		Integer now = Integer.valueOf(nowMinute.replace(":", ""));
		Integer begin = Integer.valueOf(beginMinute.replace(":", ""));
		Integer end = Integer.valueOf(endMinute.replace(":", ""));
		if (now > begin && now < end) {
			return true;
		}
		return false;
	}

	/**
	 * 判断日期是否超过今天
	 * 
	 * @param dateStr
	 * @return
	 */
	public static boolean exToday(String dateStr) {
		int today = Integer.valueOf(getTodayDayStr(yyyyMMdd));
		int date = Integer.valueOf(dateStr);
		if (today - date < 0) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
	}

}
