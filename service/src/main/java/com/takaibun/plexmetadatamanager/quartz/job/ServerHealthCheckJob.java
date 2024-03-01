package com.takaibun.plexmetadatamanager.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * MediaServerHealthJob
 *
 * @author takaibun
 * @since 2024/3/1
 */
@Slf4j
public class ServerHealthCheckJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Object mediaServerName = jobDataMap.get("media_server_name");
        log.info("check media server health: {}", mediaServerName);
    }
}
