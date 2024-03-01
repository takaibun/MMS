package com.takaibun.plexmetadatamanager.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * DataSource配置
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Configuration
public class DataSourceConfig {
    private static final String DATABASE_FILE_PATH = "~/config";
    private static final String SERVICE_DB_NAME = "service";
    private static final String QUARTZ_DB_NAME = "quartz";
    private static final String DRIVER_CLASS_NAME = "org.h2.Driver";

    /**
     * 数据源
     *
     * @return 数据源
     */
    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:h2:%s/%s", DATABASE_FILE_PATH, SERVICE_DB_NAME));
        config.setDriverClassName(DRIVER_CLASS_NAME);
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);
        return new HikariDataSource(config);
    }

    @Bean
    @QuartzDataSource
    public DataSource quartzDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:h2:%s/%s", DATABASE_FILE_PATH, QUARTZ_DB_NAME));
        config.setDriverClassName(DRIVER_CLASS_NAME);
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);
        return new HikariDataSource(config);
    }

}
