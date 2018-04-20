/**
 * Copyright (C), 2018-2018
 * FileName: QuartzMngController
 * Author:   WXG
 * Date:     2018/4/20 15:58
 * Description: Quartz定时任务管理类
 */
package com.example.demo.controller;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: wxg
 * @Date: 2018/4/20 15:58
 * @Description:
 */
@RestController
public class QuartzMngController {
    @Autowired
    private Scheduler scheduler;

    /**
     * 停止一个job任务
     * @throws SchedulerException
     */
    @RequestMapping(value = "/pauseJob")
    public  void pauseJob() throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey("TEST_JOB","TEST_GROUP"));
    }

    /**
     * 恢复相关的job任务
     * @throws SchedulerException
     */
    @RequestMapping(value = "/resumeJob")
    public  void resumeJob()throws SchedulerException   {
        scheduler.resumeJob(JobKey.jobKey("TEST_JOB","TEST_GROUP"));
    }

    /**
     * 停止使用相关的触发器
     * @throws SchedulerException
     */
    @RequestMapping(value = "/pauseTrigger")
    public  void pauseTrigger() throws SchedulerException   {
        scheduler.pauseTrigger(TriggerKey.triggerKey("testTrigger"));
    }

    /**
     * 恢复相关的触发器
     * @throws SchedulerException
     */
    @RequestMapping(value = "/resumeTrigger")
    public  void resumeTrigger() throws SchedulerException   {
        scheduler.resumeTrigger(TriggerKey.triggerKey("testTrigger"));
    }
}
