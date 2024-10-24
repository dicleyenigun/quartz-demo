package com.example.quartz_demo.config;

import com.example.quartz_demo.job.KurCekmeJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.CronScheduleBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail kurCekmeJobDetail() {
        return JobBuilder.newJob(KurCekmeJob.class)
                .withIdentity("kurCekmeJob")
                .storeDurably() // bellekte deilde kalıcı olarak tutulması için.
                .build();
    }

    @Bean
    public Trigger kurCekmeTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(kurCekmeJobDetail())
                .withIdentity("kurCekmeTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(16, 00)) // Her gün 16:00'da
                .build();
    }
}

