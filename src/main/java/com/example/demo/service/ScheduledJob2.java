/**
 * Copyright (C), 2018-2018
 * FileName: ScheduledJob2
 * Author:   WXG
 * Date:     2018/4/20 14:22
 * Description: 定时任务job2
 */
package com.example.demo.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScheduledJob2 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("%%%%%%%%%% job2开始执行 %%%%%%%%%%%%");
    }
}
