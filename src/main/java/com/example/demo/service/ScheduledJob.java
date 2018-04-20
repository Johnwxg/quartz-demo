/**
 * Copyright (C), 2018-2018
 * FileName: ScheduledJob
 * Author:   WXG
 * Date:     2018/4/20 14:19
 * Description: 定时任务job1
 */
package com.example.demo.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScheduledJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("####### job1开始执行 #######");
    }
}
