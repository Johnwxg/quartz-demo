/**
 * Copyright (C), 2018-2018
 * FileName: ScheduleTask
 * Author:   WXG
 * Date:     2018/4/19 20:55
 * Description: 定时任务执行类
 */
package com.example.demo.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleTask {
    public void taskTest(){
        System.out.print("-----定时任务开始执行----");
    }
}
