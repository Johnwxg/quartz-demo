/**
 * Copyright (C), 2018-2018
 * FileName: QuartzConfigration
 * Author:   WXG
 * Date:     2018/4/19 20:48
 * Description: Quartz配置类
 */
package com.example.demo.config;

import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfigration {


    /**
     * 配置定时任务
     * @param task
     * @return
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduleTask task){ // ScheduleTask为需要执行的任务
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        /*
         *  是否并发执行
         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         *  如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(false);
        jobDetail.setName("TEST_JOB"); // 设置任务的名字
        jobDetail.setGroup("TEST_GROUP"); // 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用
        /*
         * 为需要执行的实体类对应的对象
         */
        jobDetail.setTargetObject(task);
        /*
         * 指定需要执行的方法
         * 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的哪个方法
         */
        jobDetail.setTargetMethod("taskTest");
        return jobDetail;
    }

    /**
     *  配置定时任务的触发器，也就是什么时候触发执行定时任务
     * @param jobDetail
     * @return
     */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail){
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(jobDetail.getObject());
        tigger.setCronExpression("0/6 * * * * ?"); //初始时的cron表达式 表示每隔6秒钟执行一次
        tigger.setName("testTrigger"); //trigger的name
        return tigger;
    }

    /**
     * 定义quartz调度工厂
     * @param cronJobTrigger
     * @return
     */
    @Bean( name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger){
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        /*设置是否任意一个已定义的Job会覆盖现在的Job。默认为false，即已定义的Job不会覆盖现有的Job。
         用于quartz集群,QuartzScheduler 启动时更新己存在的Job*/
        scheduler.setOverwriteExistingJobs(true);
        // 延时启动,应用启动5秒后,定时器才开始启动
        scheduler.setStartupDelay(5);
        // 注册定时触发器
        scheduler.setTriggers(cronJobTrigger);
        return scheduler;
    }

    /**
     * 多任务时的Scheduler，动态设置Trigger。一个SchedulerFactoryBean可能会有多个Trigger
     * @return
     */
    @Bean(name = "multitaskScheduler")
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        return schedulerFactoryBean;
    }
}
