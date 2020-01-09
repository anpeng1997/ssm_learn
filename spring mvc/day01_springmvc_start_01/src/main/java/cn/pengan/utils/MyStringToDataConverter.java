package cn.pengan.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyStringToDataConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        if (s.isEmpty() || s == null){
            throw new RuntimeException("时间格式字符串为空");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("时间格式转换错误！");
        }
    }
}
