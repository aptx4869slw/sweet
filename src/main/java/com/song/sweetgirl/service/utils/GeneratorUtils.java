package com.song.sweetgirl.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Random;

public class GeneratorUtils {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorUtils.class);

    public static SimpleDateFormat timeRangeDateFormat = new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat milliFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static SimpleDateFormat zeroPointDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    public static SimpleDateFormat midnightDateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

    /**
     * 获取字符串类型的随机主键ID
     * @return
     */
    public static String getStringID() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);
        return str;
    }

}
