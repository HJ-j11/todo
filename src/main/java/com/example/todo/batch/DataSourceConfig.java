package com.example.todo.batch;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/postgres")
                .username("admin")
                .password("pass")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @BatchDataSource
    @Bean(name = "batchDataSource")
    public DataSource batchDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/test_db")
                .username("admin")
                .password("pass")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @QuartzDataSource
    @Bean(name = "quartzDataSource")
    public DataSource quartzDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/test_db") // Quartz도 Batch DB에 연결
                .username("admin")
                .password("pass")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
