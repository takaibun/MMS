package com.takaibun.plexmetadatamanager.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * DataSource配置
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Configuration
public class DataSourceConfig {

    private static final String DATABASE_PATH = "config.db";

    /**
     * 数据源
     *
     * @return 数据源
     */
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:sqlite:%s", DATABASE_PATH));
        config.setDriverClassName("org.sqlite.JDBC");
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);
        return new HikariDataSource(config);
    }


}
