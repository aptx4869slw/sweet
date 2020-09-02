package com.song.sweet.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GeneratorUtils {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorUtils.class);

    // Date时间简单格式化
    public static SimpleDateFormat SIMPLE_DATE_SDF = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat COMMON_DATE_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat ZERO_POINT_SDF = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    public static SimpleDateFormat MIDNIGHT_SDF = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    public static SimpleDateFormat TIMESTAMP_SDF = new SimpleDateFormat("yyyyMMddHHmmss");
    public static SimpleDateFormat MILLI_DATE_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static SimpleDateFormat TIME_RANGE_SDF = new SimpleDateFormat("HH:mm");

    // LocalDateTime时间简单格式化
    public static DateTimeFormatter SIMPLE_DATE_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter COMMON_DATE_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter ZERO_POINT_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
    public static DateTimeFormatter MIDNIGHT_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59");
    public static DateTimeFormatter TIMESTAMP_DTF = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static DateTimeFormatter MILLI_DATE_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 获取字符串类型的随机主键ID
     *
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
