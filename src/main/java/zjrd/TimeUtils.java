package zjrd;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;


/**
 * Created by LinShen on 2017/6/19.
 */
public class TimeUtils {


    public static final ZoneId DEFAULT_TIMEZONE = ZoneId.of("Asia/Shanghai");

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter HOUR_MINUTE_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DAY_HOUR_FORMATTER = DateTimeFormatter.ofPattern("MM-dd HH:mm");
    public static final DateTimeFormatter MONTH_DAY_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");
    public static final DateTimeFormatter DATE_MINUTE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Pattern dp = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Pattern dtp = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$");


    public static String format(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return localDateTime.format(dateTimeFormatter);
    }

    public static LocalDateTime parse(String dateString) {
        if (dtp.matcher(dateString).matches()) {
            return LocalDateTime.parse(dateString, DATETIME_FORMATTER);
        } else if (dp.matcher(dateString).matches()) {
            LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
            return LocalDateTime.of(date, LocalTime.of(0, 0, 0));
        } else {
            throw new RuntimeException("Malformed date string: " + dateString);
        }
    }

    //parse time by adding timeMax for timeRange.getEnd()
    public static LocalDateTime addDayEnd(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
        }
        return null;
    }

    public static LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    public static LocalDateTime getYesterday() {
        return LocalDateTime.now().minusDays(1);
    }

    public static LocalDateTime getTodayStart() {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
    }

    public static LocalDateTime getDayStart(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 0, 0, 0);
    }

    public static LocalDateTime getDayEnd(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 23, 59, 59);
    }

    public static long getTimestamp(LocalDateTime localDateTime) {
        Date date = Date.from(localDateTime.atZone(DEFAULT_TIMEZONE).toInstant());
        return date.getTime();
    }

    public static long getTimestamp(String dateString, DateTimeFormatter dateTimeFormatter) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, dateTimeFormatter);
        return getTimestamp(localDateTime);
    }

    public static LocalDateTime getLocalDateTime(Long timestamp) {
        Date date = new Date(timestamp);
        return LocalDateTime.ofInstant(date.toInstant(), TimeUtils.DEFAULT_TIMEZONE);
    }

    public static long getTimestamp(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MIN).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 根据时间范围返回状态码
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 状态码（0：未开始，1：进行中，2：已结束）
     */
    public static Integer getStatusByDate(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            return null;
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.compareTo(start) < 0) {
            return 0;
        } else if (now.compareTo(start) >= 0 && now.compareTo(end) <= 0) {
            return 1;
        } else if (now.compareTo(end) > 0) {
            return 2;
        } else {
            return null;
        }
    }
    
    /**
     * 日期相隔天数
     * start用旧日期，end用最新日期
     * @param start
     * @param end
     * @return
     */
    public static Integer between(LocalDate start, LocalDate end) {
        return Integer.valueOf(String.valueOf(end.toEpochDay() - start.toEpochDay()));
    }
    
    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2019, 1, 1);
        LocalDate end = LocalDate.of(2019, 3, 2);
        Period period = Period.between(start, end);
        System.out.println(between(start, end));
        System.out.println(period.getDays());
    }
    

}
