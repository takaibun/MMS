package com.takaibun.plexmetadatamanager.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * MediaServerHealthJob
 *
 * @author takaibun
 * @since 2024/3/1
 */
public class MediaServerHealthJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

    }
}
