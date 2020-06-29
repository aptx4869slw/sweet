package com.song.sweet.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GeneratorUtils {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorUtils.class);

    // Date时间简单格式化
    public static SimpleDateFormat simpleDateSDF = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat commonDateSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat zeroPointSDF = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    public static SimpleDateFormat midnightSDF = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    public static SimpleDateFormat timestampSDF = new SimpleDateFormat("yyyyMMddHHmmss");
    public static SimpleDateFormat milliDateSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static SimpleDateFormat timeRangeSDF = new SimpleDateFormat("HH:mm");

    // LocalDateTime时间简单格式化
    public static DateTimeFormatter simpleDateDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter commonDateDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter zeroPointDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
    public static DateTimeFormatter midnightDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59");
    public static DateTimeFormatter timestampDTF = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static DateTimeFormatter milliDateDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

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
