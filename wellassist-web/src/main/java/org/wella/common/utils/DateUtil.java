package org.wella.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.util.Assert;

public abstract class DateUtil {
    public static int FORMATSTANDARD = 1;
    public static int FORMATONLYDAY = 2;
    public static int FORMATONLYDAYCHINA = 3;
    public static int FORMATHHMM = 5;
    public static final String MATRIX_NULL_FLAG = "/";
    public static final long MILSEC_PER_DAY = 86400000L;
    public static final FastDateFormat FAST_DAY_FORMATTER = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final FastDateFormat FAST_MINUTE_FORMATTER = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
    public static final FastDateFormat FAST_SECOND_FORMATETR = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public static final FastDateFormat FAST_SHORT_FORMATETR = FastDateFormat.getInstance("yyyyMMdd");
    public static final FastDateFormat FAST_ONLY_MINUTE_FORMATETR = FastDateFormat.getInstance("HH:mm");
    public static final long cncbTimeDive=202003200000L;


    public DateUtil() {
    }

    public static String getLastDate(int year, int month) {
        int[] monthDay = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
            monthDay[1] = 29;
        }

        int monthDayNum = monthDay[month - 1];
        String end = year + "-" + month + "-" + monthDayNum;
        return end;
    }

    public static String getDateFormat(Date date, int formatType) {
        if(date == null) {
            return "";
        } else {
            SimpleDateFormat formatter;
            switch(formatType) {
                case 1:
                    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    break;
                case 2:
                    formatter = new SimpleDateFormat("yyyy-MM-dd");
                    break;
                case 3:
                    formatter = new SimpleDateFormat("yyyy年MM月dd日");
                    break;
                case 4:
                default:
                    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    break;
                case 5:
                    formatter = new SimpleDateFormat("HH:mm");
            }

            return formatter.format(date);
        }
    }

    public static int getmonthDayNum(int year, int month) {
        int[] monthDay = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
            monthDay[1] = 29;
        }

        int monthDayNum = monthDay[month - 1];
        return monthDayNum;
    }

    public static String getShortDate(Date myDate) {
        return FAST_SHORT_FORMATETR.format(myDate);
    }

    public static String formatDateTime(Date myDate, String pattern) {
        FastDateFormat format = FastDateFormat.getInstance(pattern);
        return format.format(myDate);
    }

    public static Date isDate(Date myDate) {
        return myDate == null?new Date():myDate;
    }

    public static Date currentDate() {
        return currentDate((DateFormat)null);
    }

    public static Date currentDateIgnoreTime() {
        return DateUtils.truncate(new Date(), 5);
    }

    public static Date currentDate(DateFormat format) {
        Date today = new Date();
        if(format != null) {
            String string = format.format(today);
            today = parse(string, format);
        }

        return today;
    }

    public static String getCurrentDateBefore(int n) {
        Calendar day = Calendar.getInstance();
        day.add(5, -n);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(day.getTime());
        return result;
    }

    public static long getQuot(Date time1, Date time2) {
        long quot = 0L;

        try {
            quot = time1.getTime() - time2.getTime();
            quot = quot / 1000L / 60L / 60L / 24L;
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return quot;
    }

    public static long getQuoteIgnoreTime(Date time1, Date time2) {
        return getQuot(DateUtils.truncate(time1, 5), DateUtils.truncate(time2, 5));
    }

    public static Date max(Date time1, Date time2) {
        return time1.before(time2)?time2:time1;
    }

    public static Date min(Date time1, Date time2) {
        return time1.before(time2)?time1:time2;
    }

    public static boolean between(Date date, Date start, Date end) {
        return !date.before(start) && !date.after(end);
    }

    public static int dayOfWeek(Object date1) {
        Date date = (Date)date1;
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        int x = aCalendar.get(7);
        return x;
    }

    public static int dayOfWeek3(Object date1) {
        Date date = (Date)date1;
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        int x = aCalendar.get(7);
        return x != 1?x - 1:7;
    }

    public static String dayOfWeek2(Object date1) {
        Date date = (Date)date1;
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        int x = aCalendar.get(7);
        return dayOfWeekByDayNum(x);
    }

    public static String dayOfWeekByDayNum(int x) {
        String str = "周日";
        if(x == 7) {
            str = "周六";
        } else if(x == 6) {
            str = "周五";
        } else if(x == 5) {
            str = "周四";
        } else if(x == 4) {
            str = "周三";
        } else if(x == 3) {
            str = "周二";
        } else if(x == 2) {
            str = "周一";
        }

        return str;
    }

    public static Date getDateOfCurrentWeek(int day) {
        Calendar aCalendar = Calendar.getInstance();
        int x = aCalendar.get(7);
        aCalendar.add(7, day - (x - 1));
        return aCalendar.getTime();
    }

    public static int getWeekOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(4);
    }

    public static Date addSomeDay(Date oldDate, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(5, num);
        return calendar.getTime();
    }

    public static String Dateformat(String date) {
        int i = date.length();
        StringBuffer newdate = new StringBuffer(date.substring(0, 4));
        if(i == 8) {
            newdate.append(0);
            newdate.append(date.substring(5, 6));
            newdate.append(0);
            newdate.append(date.substring(7, 8));
        } else if(i == 9) {
            if(date.substring(7, 8).equalsIgnoreCase("-")) {
                newdate.append(date.substring(5, 7));
                newdate.append(0);
                newdate.append(date.substring(8, 9));
            } else {
                newdate.append(0);
                newdate.append(date.substring(5, 6));
                newdate.append(date.substring(7, 9));
            }
        } else {
            newdate.append(date.substring(5, 7));
            newdate.append(date.substring(8, 10));
        }

        return newdate.toString();
    }

    public static String formatDate(Date myDate) {
        return FAST_DAY_FORMATTER.format(myDate);
    }

    public static String formatDateMinutes(Date myDate) {
        return FAST_MINUTE_FORMATTER.format(myDate);
    }

    public static String formatDateTime(Date myDate) {
        return FAST_SECOND_FORMATETR.format(myDate);
    }

    public static String formatDateMinutesTime(Date myDate) {
        return FAST_ONLY_MINUTE_FORMATETR.format(myDate);
    }

    public static Date getDateByString(String rq) {
        SimpleDateFormat df = new SimpleDateFormat();
        Date d = null;

        try {
            d = df.parse(rq);
        } catch (Exception var4) {
            ;
        }

        return d;
    }

    public static Date getDateByString(String str, String pattern) {
        SimpleDateFormat sdf = null;

        try {
            sdf = new SimpleDateFormat(pattern);
            return sdf.parse(str);
        } catch (Exception var4) {
            return null;
        }
    }

    public static boolean equals(Date start, Date end) {
        return start != null && end != null && start.getTime() == end.getTime();
    }

    public static final Date convertStringToDate(String aMask, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        try {
            date = df.parse(strDate);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return date;
    }

    public static Date getCurrentMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        calendar.setTime(date);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static String getCurrentTime() {
        Date myDate = new Date(System.currentTimeMillis());
        return formatDateTime(myDate);
    }

    public static boolean isSameDay(Date c1, Date c2) {
        return formatDate(c1).equals(formatDate(c2));
    }

    public static Calendar string2Cal(String arg) {
        SimpleDateFormat sdf = null;
        String trimString = arg.trim();
        if(trimString.length() > 14) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }

        Date d = null;

        try {
            d = sdf.parse(trimString);
        } catch (ParseException var5) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal;
    }

    public static boolean isInPeriod(String timePeriod, String time) {
        String startTime = timePeriod.substring(0, 5);
        String endTime = timePeriod.substring(6);
        String timeTime = time;
        if(!startTime.equalsIgnoreCase(time) && !endTime.equalsIgnoreCase(time)) {
            SimpleDateFormat d = new SimpleDateFormat("HH:mm");

            try {
                Date e = d.parse(startTime);
                Date endDate = d.parse(endTime);
                Date timeDate = d.parse(timeTime);
                if(timeDate.after(e) && timeDate.before(endDate)) {
                    return true;
                }
            } catch (ParseException var9) {
                var9.printStackTrace();
            }

            return false;
        } else {
            return true;
        }
    }

    public static boolean isBetween(Date date, Date from, Date to) {
        Assert.notNull(date, "date cannot be null.");
        Assert.notNull(from, "from cannot be null.");
        Assert.notNull(to, "to cannot be null.");
        Assert.isTrue(!from.after(to), "from cannot be after to.");
        return !date.before(from) && !date.after(to);
    }

    public static Date ifNull(Date date, Date defaultDate) {
        return date != null?date:defaultDate;
    }

    public static String format(Date date, DateFormat df) {
        return date == null?"":(df != null?df.format(date).toLowerCase():FAST_DAY_FORMATTER.format(date));
    }

    public static String format(Date date) {
        return format(date, (DateFormat)null);
    }

    public static Date parseUseDefaultFormat(String date) {
        return parse(date, getDayFormatter());
    }

    public static Date parse(String date, DateFormat df) {
        try {
            return df.parse(date);
        } catch (ParseException var3) {
            throw new RuntimeException("parse date [" + date + "] failed in use [" + getDayFormatter() + "]", var3);
        }
    }

    public static Date addMonth(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(2, num);
        return startDT.getTime();
    }

    public static Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(5, num);
        return startDT.getTime();
    }

    public static List<Date> splitDays(Date start, Date end) {
        return splitDays(start, end, (int[])null);
    }

    public static List<Date> splitDays(Date start, Date end, int[] dayOfWeeks) {
        ArrayList dates = new ArrayList();

        for(Date date = start; !date.after(end); date = addDay(date, 1)) {
            if(ArrayUtils.isEmpty(dayOfWeeks) || ArrayUtils.contains(dayOfWeeks, date.getDay() + 1)) {
                dates.add(date);
            }
        }

        return dates;
    }

    public static List<Date> splitDays2(Date start, Date end, int[] dayOfWeeks) {
        ArrayList dates = new ArrayList();

        for(Date date = start; !date.after(end); date = addDay(date, 1)) {
            if(ArrayUtils.isEmpty(dayOfWeeks) || ArrayUtils.contains(dayOfWeeks, dayOfWeek3(date))) {
                dates.add(date);
            }
        }

        return dates;
    }

    public static List<String> getDayPeriodFullMatrix(String dayStart, String dayEnd) {
        ArrayList retList = new ArrayList();
        GregorianCalendar calStart = new GregorianCalendar();
        GregorianCalendar calEnd = new GregorianCalendar();
        String str = null;

        try {
            calStart.setTime(getDayFormatter().parse(dayStart));
            calEnd.setTime(getDayFormatter().parse(dayEnd));
            calEnd.add(5, 1);

            int e;
            for(e = calStart.get(7); e > 1; --e) {
                retList.add("/");
            }

            while(calStart.before(calEnd)) {
                str = FAST_DAY_FORMATTER.format(calStart.getTime());
                retList.add(str);
                calStart.add(5, 1);
            }

            e = calEnd.get(7);
            if(e != 1) {
                while(e != 1 && e <= 7) {
                    retList.add("/");
                    ++e;
                }
            }

            if(retList.size() < 42) {
                int length = 42 - retList.size();

                for(int i = 0; i < length; ++i) {
                    retList.add("/");
                }
            }

            return retList;
        } catch (ParseException var9) {
            throw new RuntimeException(var9);
        }
    }

    public static List<String> getDayPeriodFullMatrix(Date dayStart, Date dayEnd) {
        return getDayPeriodFullMatrix(format(dayStart), format(dayEnd));
    }

    public static SimpleDateFormat getDayFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public static SimpleDateFormat getMinuteFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public static SimpleDateFormat getMonthFormatter() {
        return new SimpleDateFormat("yyyy-MM");
    }

    public static SimpleDateFormat getSecondFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static Date string2DateTime(String str) {
        SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            date = fo.parse(str);
        } catch (ParseException var4) {
            var4.printStackTrace();
        }

        return date;
    }

    public static Date getNoHHMMDate(Date date) {
        return DateUtils.truncate(date, 5);
    }

    public static Date addDays(Date date, int i) {
        return DateUtils.addDays(date, i);
    }

    public static long diff(Date d1, Date d2, String field) {
        long d1t = d1.getTime();
        long d2t = d2.getTime();
        return "middleNight".equalsIgnoreCase(field)?d1t / 86400000L - d2t / 86400000L:d2t - d1t;
    }

    public static String getLikeTimePointCode(Date myDate) {
        String myTime = formatDateMinutesTime(myDate);
        String[] myTimeList = myTime.split(":");
        return myTimeList[0] + myTimeList[1] + "00";
    }

    public static List<Date> getDates(Date start, Date end, Integer[] dayOfWeeks) {
        ArrayList list = new ArrayList();
        Date date = start;

        for(int i = 1; i <= 7; ++i) {
            if(!ArrayUtils.contains(dayOfWeeks, Integer.valueOf(dayOfWeek(date)))) {
                date = addDays(date, 1);
            } else {
                while(date.compareTo(end) <= 0) {
                    list.add(date);
                    date = addDays(date, 7);
                }

                date = addDays(start, i);
            }
        }

        return list;
    }

    public static boolean constractDate(Date d1, Date d2) {
        if(d1 != null && d2 != null) {
            Date date1 = string2DateTime(formatDate(d1));
            Date date2 = string2DateTime(formatDate(d2));
            return date1.equals(date2)?true:date1.before(date2);
        } else {
            return false;
        }
    }

    public static int getWeekByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(7);
    }

    public static boolean equalDate(Date d1, Date d2) {
        if(d1 != null && d2 != null) {
            Date date1 = string2DateTime(formatDate(d1));
            Date date2 = string2DateTime(formatDate(d2));
            return date1.equals(date2);
        } else {
            return false;
        }
    }

    public static String explainDayOfWeek(Date date) {
        int departDay = dayOfWeek(date);
        return departDay == 1?"7":(departDay == 2?"1":String.valueOf(departDay - 1));
    }

    public static boolean gtAfter(Date date) {
        return date.before(addDay(new Date(), -1));
    }

    public static int getIntervalDays(Date fDate, Date oDate) {
        if(fDate != null && oDate != null) {
            Calendar d1 = Calendar.getInstance();
            Calendar d2 = Calendar.getInstance();
            d1.setTime(fDate);
            d2.setTime(oDate);
            d1.set(10, 0);
            d1.set(12, 0);
            d1.set(13, 0);
            fDate = d1.getTime();
            d2.set(10, 0);
            d2.set(12, 0);
            d2.set(13, 0);
            oDate = d2.getTime();
            long intervalMilli = oDate.getTime() - fDate.getTime();
            return (int)(intervalMilli / 86400000L);
        } else {
            return -1;
        }
    }

    public static int getIntervalDays2(Date fDate, Date oDate) {
        if(fDate != null && oDate != null) {
            long intervalMilli = oDate.getTime() - fDate.getTime();
            return (int)(intervalMilli / 86400000L);
        } else {
            return -1;
        }
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static String getYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(date);
    }

    public static String getMonth(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("M");
        return sdf.format(date);
    }

    public static String getDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("d");
        return sdf.format(date);
    }

    public static Date getSomeDate(int i) {
        Calendar c = Calendar.getInstance();
        c.add(5, i);
        return c.getTime();
    }

    public static long stringToTimestamp(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date timeDate = null;

        try {
            timeDate = dateFormat.parse(dateString);
        } catch (ParseException var4) {
            var4.printStackTrace();
        }

        return timeDate.getTime();
    }

    public static String timestampToDateString(long ts2) {
        Timestamp ts = new Timestamp(ts2);
        String tsStr = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            tsStr = sdf.format(ts);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return tsStr;
    }

    public static Date timestampToDate(long ts2) {
        Timestamp ts = new Timestamp(ts2);
        Object date = new Date();

        try {
            date = ts;
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return (Date)date;
    }


    public static String cncbDate(Date date){
        DateFormat df=new SimpleDateFormat("yyyyMMdd");
        String res=df.format(new Date(date.getTime()+cncbTimeDive));
        return res;
    }
}