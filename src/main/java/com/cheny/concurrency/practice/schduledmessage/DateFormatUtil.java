package com.cheny.concurrency.practice.schduledmessage;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public final class DateFormatUtil {
    private DateFormatUtil(){}
    private static final String format = "yyyy-MM-dd HH:mm:ss";
    private static final FastDateFormat dateFormat = FastDateFormat.getInstance(format);

    public static long removeMills(Calendar cal) throws ParseException{
        String date = dateFormat.format(cal.getTime());
        Date nowTime = new SimpleDateFormat(format).parse(date);
        return nowTime.getTime();
    }
}
