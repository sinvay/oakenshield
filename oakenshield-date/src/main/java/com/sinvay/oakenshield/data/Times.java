package com.sinvay.oakenshield.data;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Time kits
 *
 * @author pizzalord
 * @since 1.0
 */
public abstract class Times {
    /**
     * 一天有多少ms
     */
    public static final long MS_OF_ONE_DAY = 24 * 60 * 60 * 1000;

    /**
     * 一小时有多少ms
     */
    public static final long MS_OF_ONE_HOUR = 60 * 60 * 1000;

    /**
     * 一分钟有多少ms
     */
    public static final long MS_OF_ONE_MINUTE = 60 * 1000;

    /**
     * Convert date to string
     *
     * @param date Date
     * @return A date string
     */
    public static String dateToString(Date date, TimeFormat timeFormat) {
        return timeFormat.format(date);
    }

    /**
     * Convert string to date
     *
     * @param dateStr Date string
     * @return A date
     * @throws ParseException Parse exception
     */
    public static Date stringToDate(String dateStr, TimeFormat timeFormat) throws ParseException {
        return timeFormat.parse(dateStr);
    }

    /**
     * Get the date from today by days
     *
     * @param days Days from today
     * @return The day from today by days
     */
    public static Date daysFromToday(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * Get the date from today by years
     *
     * @param years Years from today
     * @return The day from today by years
     */
    public static Date yearsFromToday(int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }

    /**
     * Get current year
     *
     * @return Current year
     */
    public static int getCurrentYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * Get year of the date
     *
     * @param date Date
     * @return The year of the date
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Get a week in a year
     *
     * @return A week in year
     */
    public static String weekInYear() {
        Calendar c = Calendar.getInstance();
        int weekNum = c.get(Calendar.WEEK_OF_YEAR);
        String weekStr = null;
        if (weekNum < 10) {
            weekStr = "0" + Integer.toString(weekNum);
        } else {
            weekStr = Integer.toString(weekNum);
        }
        String weekOfYear = c.get(Calendar.YEAR) + weekStr;
        return weekOfYear;
    }

    /**
     * Get age
     *
     * @param birthday 生日
     * @return Age
     */
    public static int getAge(String birthday) {
        birthday = birthday
                .replace("-", "")
                .replace("/", "");

        String currentDate = TimeFormat.FORMAT_YMD_NUMBERS_ONLY.format(new Date());
        // 今天的年份
        int year = Integer.parseInt(currentDate.substring(0, 4));
        // 今天的月日
        int monthAndDay = Integer.parseInt(currentDate.substring(4));
        // 输入的年份
        int yearIn = Integer.parseInt(birthday.substring(0, 4));
        // 输入的月日
        int monthAndDayIn = Integer.parseInt(birthday.substring(4));
        // 计算年龄
        int age = year - yearIn;
        if (monthAndDay < monthAndDayIn)
            age = age - 1;
        return age;
    }

    /**
     * Get week index of a week
     *
     * @param date Input date
     * @return Week index
     */
    public static int getWeekIndex(Date date) {
        // Date date = getDateForYMD(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 西方世界中的一个星期是从星期日开始计数的,需要减1
        int chnWeekNum = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (chnWeekNum == 0) // 星期日
            chnWeekNum = 7;
        return chnWeekNum;
    }

    /**
     * Get week number in year
     *
     * @param date Input date
     * @return Week index In year
     */
    public static int getWeekNoInYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int weekIndex = calendar.get(Calendar.WEEK_OF_YEAR);
        return weekIndex;
    }

    /**
     * Get days of a month
     *
     * @param year  Year
     * @param month Month
     * @return Days of month
     */
    public static int getDaysOfMonth(int year, int month) {
        if (month < 1 || month > 12) {
            throw new RuntimeException("月份应在1~12之间");
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);// Java月份才0开始算
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * Get current sql timestamp
     *
     * @return Current sql timestamp
     */
    public static Timestamp currentSqlTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * Get time distance between from time and to to time
     *
     * @param from From time
     * @param to   To time
     * @return Time distance
     */
    public static String timesFromDeadline(Date from, Date to) {
        long fromTime = from.getTime();
        long toTime = to.getTime();
        if (fromTime >= toTime) {
            throw new RuntimeException("已经超过截止时间");
        }

        long msTotal = toTime - fromTime;
        long days = msTotal / MS_OF_ONE_DAY;
        long hours = (msTotal - (days * MS_OF_ONE_DAY)) / MS_OF_ONE_HOUR;
        long minutes = (msTotal - (days * MS_OF_ONE_DAY) - hours * MS_OF_ONE_HOUR) / MS_OF_ONE_MINUTE;
        return days + "天" + hours + "小时" + minutes + "分钟";
    }
}
