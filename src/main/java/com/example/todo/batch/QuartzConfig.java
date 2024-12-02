package com.example.todo.batch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

@Configuration
public class QuartzConfig {
    private final DataSource quartzDataSource;

    public QuartzConfig(DataSource quartzDataSource) {
        this.quartzDataSource = quartzDataSource;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(quartzDataSource); // Quartz 전용 데이터소스 설정
        factory.setApplicationContextSchedulerContextKey("applicationContext");
        return factory;
    }
}
