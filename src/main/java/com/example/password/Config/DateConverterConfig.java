package com.example.password.Config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 全局日期处理类
 */
@Component
public class DateConverterConfig implements Converter<String, Date> {
    public static SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    private static final String[] FORMATS = new String[6];

    static {
        FORMATS[0] = "yyyy-MM-dd";
        FORMATS[1] = "yyyy-MM-dd hh:mm:ss";
        FORMATS[2] = "yyyy-MM";
        FORMATS[3] = "yyyy-MM-dd hh:mm";
        FORMATS[4] = "yyyy-MM-dd hh";
        FORMATS[5] = "yyyy/MM/dd";
    }

    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }

        if ("".equals(source.trim())) {
            return null;
        }

        try {
            return DateUtils.parseDate(source.trim(), FORMATS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String DataFormat(Date date) {
        return ft.format(date);
    }
}