package com.shooterj.common.util;

import io.vavr.Tuple2;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.joda.time.PeriodType.days;

/**
 * 日期工具类，主要封装了部分joda-time中的方法，让很多代码一行完成，同时统一了日期到字符串的pattern格式。
 *
 * @author shooterj
 */
public class MyDateUtil {

    /**
     * 统一的日期pattern，今后可以根据自己的需求去修改。
     */
    public static final String COMMON_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 统一的日期时间pattern，今后可以根据自己的需求去修改。
     */
    public static final String COMMON_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 统一的短日期时间pattern，今后可以根据自己的需求去修改。
     */
    public static final String COMMON_SHORT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String CST_TIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";

    /**
     * 缺省日期格式化器，提前获取提升运行时效率。
     */
    private static final DateTimeFormatter DATE_PARSE_FORMATTER =
            DateTimeFormat.forPattern(MyDateUtil.COMMON_DATE_FORMAT);
    /**
     * 缺省日期时间格式化器，提前获取提升运行时效率。
     */
    private static final DateTimeFormatter DATETIME_PARSE_FORMATTER =
            DateTimeFormat.forPattern(MyDateUtil.COMMON_DATETIME_FORMAT);

    /**
     * 缺省短日期时间格式化器，提前获取提升运行时效率。
     */
    private static final DateTimeFormatter DATETIME_SHORT_PARSE_FORMATTER =
            DateTimeFormat.forPattern(MyDateUtil.COMMON_SHORT_DATETIME_FORMAT);

    /**
     * 获取一天的开始时间的字符串格式，如2019-08-03 00:00:00.000。
     *
     * @param dateTime 待格式化的日期时间对象。
     * @return 格式化后的字符串。
     */
    public static String getBeginTimeOfDay(DateTime dateTime) {
        return dateTime.withTimeAtStartOfDay().toString(COMMON_DATETIME_FORMAT);
    }

    /**
     * 获取一天的结束时间的字符串格式，如2019-08-03 23:59:59.999。
     *
     * @param dateTime 待格式化的日期时间对象。
     * @return 格式化后的字符串。
     */
    public static String getEndTimeOfDay(DateTime dateTime) {
        return dateTime.withTime(23, 59, 59, 999).toString(COMMON_DATETIME_FORMAT);
    }

    /**
     * 获取一天中的开始时间和结束时间的字符串格式，如2019-08-03 00:00:00.000 和 2019-08-03 23:59:59.999。
     *
     * @param dateTime 待格式化的日期时间对象。
     * @return 包含格式后字符串的二元组对象。
     */
    public static Tuple2<String, String> getDateTimeRangeOfDay(DateTime dateTime) {
        return new Tuple2<>(getBeginTimeOfDay(dateTime), getEndTimeOfDay(dateTime));
    }

    /**
     * 获取本月第一天的日期格式。如2019-08-01。
     *
     * @param dateTime 待格式化的日期对象。
     * @return 格式化后的字符串。
     */
    public static String getBeginDateOfMonth(DateTime dateTime) {
        return dateTime.withDayOfMonth(1).toString(COMMON_DATE_FORMAT);
    }

    /**
     * 获取本月第一天的日期格式。如2019-08-01。
     *
     * @param dateString 待格式化的日期字符串对象。
     * @return 格式化后的字符串。
     */
    public static String getBeginDateOfMonth(String dateString) {
        DateTime dateTime = toDate(dateString);
        return dateTime.withDayOfMonth(1).toString(COMMON_DATE_FORMAT);
    }

    /**
     * 计算指定日期距离今天相差的天数。
     *
     * @param dateTime 待格式化的日期时间对象。
     * @return 相差天数。
     */
    public static int getDayDiffToNow(DateTime dateTime) {
        return new Period(dateTime, new DateTime(), days()).getDays();
    }

    /**
     * 将日期对象格式化为缺省的字符串格式。
     *
     * @param dateTime 待格式化的日期对象。
     * @return 格式化后的字符串。
     */
    public static String toDateString(DateTime dateTime) {
        return dateTime.toString(COMMON_DATE_FORMAT);
    }

    /**
     * 将日期时间对象格式化为缺省的字符串格式。
     *
     * @param dateTime 待格式化的日期对象。
     * @return 格式化后的字符串。
     */
    public static String toDateTimeString(DateTime dateTime) {
        return dateTime.toString(COMMON_DATETIME_FORMAT);
    }

    /**
     * 将缺省格式的日期字符串解析为日期对象。
     *
     * @param dateString 待解析的字符串。
     * @return 解析后的日期对象。
     */
    public static DateTime toDate(String dateString) {
        return DATE_PARSE_FORMATTER.parseDateTime(dateString);
    }

    /**
     * 将缺省格式的日期字符串解析为日期对象。
     *
     * @param dateTimeString 待解析的字符串。
     * @return 解析后的日期对象。
     */
    public static DateTime toDateTime(String dateTimeString) {
        return DATETIME_PARSE_FORMATTER.parseDateTime(dateTimeString);
    }

    /**
     * 将缺省格式的(不包含毫秒的)日期时间字符串解析为日期对象。
     *
     * @param dateTimeString 待解析的字符串。
     * @return 解析后的日期对象。
     */
    public static DateTime toDateTimeWithoutMs(String dateTimeString) {
        return DATETIME_SHORT_PARSE_FORMATTER.parseDateTime(dateTimeString);
    }

    /**
     * 截取时间到天。如2019-10-03 01:20:30 转换为 2019-10-03 00:00:00。
     * 由于没有字符串的中间转换，因此效率更高。
     *
     * @param date 待截取日期对象。
     * @return 转换后日期对象。
     */
    public static Date truncateToDay(Date date) {
        return DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 截取时间到月。如2019-10-03 01:20:30 转换为 2019-10-01 00:00:00。
     * 由于没有字符串的中间转换，因此效率更高。
     *
     * @param date 待截取日期对象。
     * @return 转换后日期对象。
     */
    public static Date truncateToMonth(Date date) {
        return DateUtils.truncate(date, Calendar.MONTH);
    }

    /**
     * 截取时间到年。如2019-10-03 01:20:30 转换为 2019-01-01 00:00:00。
     * 由于没有字符串的中间转换，因此效率更高。
     *
     * @param date 待截取日期对象。
     * @return 转换后日期对象。
     */
    public static Date truncateToYear(Date date) {
        return DateUtils.truncate(date, Calendar.YEAR);
    }


    /**
     * 格式化时间，格式为 yyyyMMddHHmmss
     *
     * @param localDateTime LocalDateTime
     * @return 格式化后的字符串
     */
    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    /**
     * 根据传入的格式，格式化时间
     *
     * @param localDateTime LocalDateTime
     * @param format        格式
     * @return 格式化后的字符串
     */
    public static String formatFullTime(LocalDateTime localDateTime, String format) {
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 根据传入的格式，格式化时间
     *
     * @param date   Date
     * @param format 格式
     * @return 格式化后的字符串
     */
    public static String getDateFormat(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        return simpleDateFormat.format(date);
    }

    /**
     * 格式化 CST类型的时间字符串
     *
     * @param date   CST类型的时间字符串
     * @param format 格式
     * @return 格式化后的字符串
     * @throws ParseException 异常
     */
    public static String formatCstTime(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CST_TIME_PATTERN, Locale.US);
        Date usDate = simpleDateFormat.parse(date);
        return getDateFormat(usDate, format);
    }

    /**
     * 格式化 Instant
     *
     * @param instant Instant
     * @param format  格式
     * @return 格式化后的字符串
     */
    public static String formatInstant(Instant instant, String format) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(java.time.format.DateTimeFormatter.ofPattern(format));
    }

    /**
     * 判断当前时间是否在指定时间范围
     *
     * @param from 开始时间
     * @param to   结束时间
     * @return 结果
     */
    public static boolean between(LocalTime from, LocalTime to) {
        LocalTime now = LocalTime.now();
        return now.isAfter(from) && now.isBefore(to);
    }


    /**
     * 对日期的【秒】进行加/减
     *
     * @param date    日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date    日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date  日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date  日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date   日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date  日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     * 私有构造函数，明确标识该常量类的作用。
     */
    private MyDateUtil() {
    }


}
