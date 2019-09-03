package io.nebula.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/15
 */
public class DateUtil {
    public static final String COMPACT_DATE_FORMAT = "yyyyMMdd";
    public static final String YM = "yyyyMM";
    public static final String NORMAL_DATE_FORMAT = "yyyy-MM-dd";
    public static final String NORMAL_DATE_FORMAT_NEW = "yyyy-mm-dd hh24:mi:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_ALL = "yyyyMMddHHmmssS";

    public static Long strDateToNum(String paramString) throws Exception {
        if (paramString == null) {
            return null;
        }
        String[] arrayOfString = null;
        String str = "";
        if (paramString.indexOf("-") >= 0) {
            arrayOfString = paramString.split("-");
            for (int i = 0; i < arrayOfString.length; ++i) {
                str = str + arrayOfString[i];
            }
            return Long.valueOf(Long.parseLong(str));
        }
        return Long.valueOf(Long.parseLong(paramString));
    }

    public static Long strDateToNum1(String paramString) throws Exception {
        if (paramString == null) {
            return null;
        }
        String[] arrayOfString = null;
        String str = "";
        if (paramString.indexOf("-") >= 0) {
            arrayOfString = paramString.split("-");
            for (int i = 0; i < arrayOfString.length; ++i) {
                if (arrayOfString[i].length() == 1) {
                    str = str + "0" + arrayOfString[i];
                } else {
                    str = str + arrayOfString[i];
                }
            }
            return Long.valueOf(Long.parseLong(str));
        }
        return Long.valueOf(Long.parseLong(paramString));
    }

    public static String numDateToStr(Long paramLong) {
        if (paramLong == null) {
            return null;
        }
        String str = null;
        str = paramLong.toString().substring(0, 4) + "-"
                + paramLong.toString().substring(4, 6) + "-"
                + paramLong.toString().substring(6, 8);
        return str;
    }

    public static Date stringToDate(String paramString1, String paramString2) throws Exception {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
                paramString2);
        localSimpleDateFormat.setLenient(false);
        try {
            return localSimpleDateFormat.parse(paramString1);
        } catch (ParseException localParseException) {
            throw new Exception("解析日期字符串时出错！");
        }
    }

    public static String dateToString(Date paramDate, String paramString) {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
                paramString);
        localSimpleDateFormat.setLenient(false);
        return localSimpleDateFormat.format(paramDate);
    }

    public static Date compactStringToDate(String paramString) throws Exception {
        return stringToDate(paramString, "yyyyMMdd");
    }

    public static String dateToCompactString(Date paramDate) {
        return dateToString(paramDate, "yyyyMMdd");
    }

    public static String dateToNormalString(Date paramDate) {
        return dateToString(paramDate, "yyyy-MM-dd");
    }

    public static String compactStringDateToNormal(String paramString) throws Exception {
        return dateToNormalString(compactStringToDate(paramString));
    }

    public static int getDaysBetween(Date paramDate1, Date paramDate2) throws Exception {
        Calendar localCalendar1 = Calendar.getInstance();
        Calendar localCalendar2 = Calendar.getInstance();
        localCalendar1.setTime(paramDate1);
        localCalendar2.setTime(paramDate2);
        if (localCalendar1.after(localCalendar2)) {
            throw new Exception("起始日期小于终止日期!");
        }
        int i = localCalendar2.get(6) - localCalendar1.get(6);
        int j = localCalendar2.get(1);
        if (localCalendar1.get(1) != j) {
            localCalendar1 = (Calendar) localCalendar1.clone();
            do {
                i += localCalendar1.getActualMaximum(6);
                localCalendar1.add(1, 1);
            } while (localCalendar1.get(1) != j);
        }
        return i;
    }

    public static Date addDays(Date paramDate, int paramInt) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        int i = localCalendar.get(6);
        localCalendar.set(6, i + paramInt);
        return localCalendar.getTime();
    }

    public static Date addDays(String paramString1, String paramString2, int paramInt) throws Exception {
        Calendar localCalendar = Calendar.getInstance();
        Date localDate = stringToDate(paramString1, paramString2);
        localCalendar.setTime(localDate);
        int i = localCalendar.get(6);
        localCalendar.set(6, i + paramInt);
        return localCalendar.getTime();
    }

    public static java.sql.Date getSqlDate(Date paramDate) throws Exception {
        java.sql.Date localDate = new java.sql.Date(paramDate.getTime());
        return localDate;
    }

    public static String formatDate(Date paramDate) {
        if (paramDate == null) {
            return null;
        }
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        localSimpleDateFormat.setLenient(false);
        return localSimpleDateFormat.format(paramDate);
    }

    public static String formatDateTime(Date paramDate) {
        if (paramDate == null) {
            return null;
        }
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        localSimpleDateFormat.setLenient(false);
        return localSimpleDateFormat.format(paramDate);
    }

    public static Date parseDate(String paramString) throws Exception {
        if ((paramString == null) || ("".equals(paramString.trim()))) {
            return null;
        }
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        localSimpleDateFormat.setLenient(false);
        try {
            return localSimpleDateFormat.parse(paramString);
        } catch (ParseException localParseException) {
            throw new Exception("日期解析出错！", localParseException);
        }
    }

    public static Date parseDateTime(String paramString) throws Exception {
        if ((paramString == null) || ("".equals(paramString.trim()))) {
            return null;
        }
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        localSimpleDateFormat.setLenient(false);
        try {
            return localSimpleDateFormat.parse(paramString);
        } catch (ParseException localParseException) {
            throw new Exception("时间解析异常！", localParseException);
        }
    }

    /**
     * 时间戳转换成时间
     *
     * @param time 毫秒
     * @return
     * @throws ParseException
     */
    public static Date parseDateTimeByStamp(Long time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String d = format.format(time);
        Date date = format.parse(d);
        return date;
    }

    public static Integer getYM(String paramString) throws Exception {
        if (paramString == null) {
            return null;
        }
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        localSimpleDateFormat.setLenient(false);
        Date localDate;
        try {
            localDate = localSimpleDateFormat.parse(paramString);
        } catch (ParseException localParseException) {
            throw new Exception("时间解析异常！", localParseException);
        }
        return getYM(localDate);
    }

    public static Integer getYM(Date paramDate) {
        if (paramDate == null) {
            return null;
        }
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        int i = localCalendar.get(1);
        int j = localCalendar.get(2) + 1;
        return new Integer(i * 100 + j);
    }

    /**
     * 当前日期加上分钟
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinute(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MILLISECOND, minutes);
        return cal.getTime();
    }

    /**
     * 指定日期加上分钟
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addDateMinute(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MILLISECOND, minutes);
        return cal.getTime();
    }

    public static Date addHours(Date paramDate, int paramInt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(paramDate);
        cal.add(Calendar.HOUR, paramInt);
        return cal.getTime();
    }

    public static int addMonths(int paramInt1, int paramInt2) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.set(1, paramInt1 / 100);
        localCalendar.set(2, paramInt1 % 100 - 1);
        localCalendar.set(5, 1);
        localCalendar.add(Calendar.MONTH, paramInt2);
        return getYM(localCalendar.getTime()).intValue();
    }

    public static Date addMonths(Date paramDate, int paramInt) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        localCalendar.add(Calendar.MONTH, paramInt);
        return localCalendar.getTime();
    }

    public static Date addSeconds(Date paramDate, int paramInt) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        localCalendar.add(Calendar.SECOND, paramInt);
        return localCalendar.getTime();
    }

    public static int monthsBetween(int paramInt1, int paramInt2) {
        int i = paramInt2 / 100 * 12 + paramInt2 % 100
                - (paramInt1 / 100 * 12 + paramInt1 % 100);
        return i;
    }

    public static int monthsBetween(Date paramDate1, Date paramDate2) {
        return monthsBetween(getYM(paramDate1).intValue(), getYM(paramDate2).intValue());
    }

    public static String getChineseDate(Calendar paramCalendar) {
        int i = paramCalendar.get(1);
        int j = paramCalendar.get(2);
        int k = paramCalendar.get(5);
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append(i);
        localStringBuffer.append("年");
        localStringBuffer.append(j + 1);
        localStringBuffer.append("月");
        localStringBuffer.append(k);
        localStringBuffer.append("日");
        return localStringBuffer.toString();
    }

    public static String getChineseWeekday(Calendar paramCalendar) {
        switch (paramCalendar.get(7)) {
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            case 1:
                return "星期日";
            default:
                return "未知";
        }
    }

    /**
     * 取某日期之后多少天的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDateAfterDays(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 获取某一天零点
     *
     * @param date
     * @return
     */
    public static Date getStartTimeOfDay(Date date) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 0);//24小时
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        return day.getTime();
    }

    /**
     * 取某天末点
     *
     * @param date
     * @return
     */
    public static Date getEndTimeOfDay(Date date) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 23);//24小时
        day.set(Calendar.MINUTE, 59);
        day.set(Calendar.SECOND, 59);
        day.set(Calendar.MILLISECOND, 999);
        return day.getTime();

    }

    /**
     * 获取某一天零点
     *
     * @param date
     * @return
     */
    public static Date getStartTimeOfSecond(Date date) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        return day.getTime();
    }

    /**
     * 取某天末点
     *
     * @param date
     * @return
     */
    public static Date getEndTimeOfSecond(Date date) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.SECOND, 59);
        day.set(Calendar.MILLISECOND, 999);
        return day.getTime();
    }
}
