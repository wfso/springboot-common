package com.yioks.springboot.common.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 *
 * Created by Hyphoon on 2017/6/27.
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static final SimpleDateFormat DEFAULT_SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat DETAULT_SIMPLE_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 支持多种格式的字符串转换成日期
     * @param dateString 以字符串表示的日期
     * @return Date
     */
    public static Date parseDate(String dateString) {
        try {
            return DateUtils.parseDate(dateString, "yyyy-MM-dd", "yyyy/MM/dd", "yyyy年MM月dd日",
                    "yy-MM-dd", "yy/MM/dd", "yy年MM月dd日");
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 按默认 yyyy-MM-dd 格式化为String
     * @param date 日期
     * @return String
     */
    public static String toDefaultString(Date date) {
        return DEFAULT_SIMPLE_DATE_FORMAT.format(date);
    }

    /**
     * 按默认 yyyy-MM-dd HH:mm:ss 格式化为String
     * @param date 日期
     * @return String
     */
    public static String toDefualtDateTimeString(Date date) {
        return DETAULT_SIMPLE_DATETIME_FORMAT.format(date);
    }

    /**
     * 时间类型的数值格式化成时间字符串
     * @param dateTime 长整形表示的时间
     * @return String
     */
    public static String toDefualtDateTimeString(long dateTime) {
        Date date = new Date(dateTime);
        return toDefualtDateTimeString(date);
    }

    /**
     * 返回当天凌晨的日期
     * @return Date
     */
    public static Date today() {
        return DateUtils.truncate(new Date(), Calendar.DATE);
    }

    /**
     * 返回指定年月的开始日期 （1号0点） 和  【不包含】结束日期 （下个月1号0点）
     * @param year 年
     * @param month 月
     * @return DateRange
     */
    public static DateRange monthRange(int year, int month) {
        Assert.isTrue(month > 0 && month < 13, "月份必须·介于1到12之间。");
        Calendar b = Calendar.getInstance();
        b.set(year, month - 1, 1);
        Date bDate = b.getTime();
        Date eDate = DateUtils.addMonths(bDate, 1);
        return new DateRange(bDate, eDate);
    }

    /*
     * 日期范围
     */
    public static class DateRange {
        private final Date beginDate;
        private final Date endDate;

        public DateRange(Date beginDate, Date endDate) {
            this.beginDate = beginDate;
            this.endDate = endDate;
        }

        public Date getBeginDate() {
            return beginDate;
        }

        public Date getEndDate() {
            return endDate;
        }
    }

    /**
     * 过去12个月的首日 （不包括 base 当月）
     * @param base 日期
     * @return DateSet
     */
    public static Set<Date> passedTwelveMonthFirstDay(Date base) {
        Set<Date> rst = new TreeSet<>();
        Date tmp = DateUtils.truncate(base, Calendar.MONTH);
        for (int i = 0; i < 12; i++) {
            tmp = DateUtils.addMonths(tmp, -1);
            rst.add(tmp);
        }
        return rst;
    }
}
