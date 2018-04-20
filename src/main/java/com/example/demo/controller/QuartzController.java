/**
 * Copyright (C), 2018-2018
 * FileName: QuartzController
 * Author:   WXG
 * Date:     2018/4/20 14:24
 * Description: 定时任务控制类
 */
package com.example.demo.controller;

import com.example.demo.service.ScheduledJob;
import com.example.demo.service.ScheduledJob2;
import org.quartz.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class QuartzController {

    @Resource(name = "multitaskScheduler")
    private Scheduler scheduler;


    @ResponseBody
    @RequestMapping("/multitask")
    public String multitask() throws SchedulerException {
        scheduleJob(scheduler);
        return "多任务";

    }

    @ResponseBody
    @RequestMapping("/multitask2")
    public String multitask2() throws SchedulerException {
        scheduleJob2(scheduler);
        return "这个也是多任务";

    }

    /**
     * 具体的定时任务
     * @param scheduler
     * @throws SchedulerException
     */
    public void scheduleJob(Scheduler scheduler) throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class).withIdentity("job1", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");
        // 每3s执行一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

    public void scheduleJob2(Scheduler scheduler) throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob2.class).withIdentity("job2","group2").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/6 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2","group2").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }


}
