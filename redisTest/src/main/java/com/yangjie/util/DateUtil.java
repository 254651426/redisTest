package com.yangjie.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getaddcurdatehour(String day, int hour) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return "";
		System.out.println("front:" + format.format(date)); // 显示输入的日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);// 24小时制
		date = cal.getTime();
		System.out.println("after:" + format.format(date)); // 显示更新后的日期
		cal = null;
		return format.format(date);
	}

	/**
	 * 日期类型格式化
	 * 
	 * @param d
	 * @return
	 */
	public static String DatetoString(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}

	/**
	 * 当前日期格式化
	 * 
	 * @return
	 */
	public static String DatetoString() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("当前时间：" + sdf.format(d));
		return sdf.format(d);
	}

	/**
	 * 字符串转日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date Stringtodate(String date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 注意月份是MM
		Date d2 = simpleDateFormat.parse(date);
		return d2;
	}

	/**
	 * 时间戳转换成日期格式字符串
	 * 
	 * @param seconds
	 *            精确到秒的字符串
	 * @param formatStr
	 * @return
	 */
	public static String timeStamp2Date(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty()) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds)));
	}

	// 给一个时间减5分钟
	public static String timeStampaddfivemin(String timeStamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(Long.valueOf(timeStamp));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, -5);// 24小时制
		date = cal.getTime();
		return format.format(date);
	}

	public static void main(String[] arg) throws ParseException {
		System.out.println(DatetoString(new Date()));
		System.out.println(timeStamp2Date(new Date().getTime() + "", null));
		System.out.println(new Date(Long.valueOf(new Date().getTime() + "")));
		System.out.println(timeStampaddfivemin((new Date().getTime() + "")));
	}

	 

}
