package com.example.util;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private Logger logger = Logger.getLogger(ScheduledTasks.class);

    //@Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0 0/5 * * * ?")
    public void reportCurrentTime() {
        logger.info("现在时间："+dateFormat.format(new Date()));
        logger.info("-------------Memory------------------");
        logger.info("Free Memory is " + Runtime.getRuntime().freeMemory());
        logger.info("Max Memory for JVM is " + Runtime.getRuntime().maxMemory());
        logger.info("Total Memory of JVM is " + Runtime.getRuntime().totalMemory());
    }
}