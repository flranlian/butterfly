package com.chain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lian.ran on 2018/1/24.
 */
public class DateUtils {

    public static String getDataToStr(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return  sdf.format(date);
    }
}
