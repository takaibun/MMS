package com.takaibun.plexmetadatamanager.quartz.enums;

import org.springframework.scheduling.quartz.QuartzJobBean;

import com.takaibun.plexmetadatamanager.enums.TaskType;
import com.takaibun.plexmetadatamanager.quartz.exception.QuartzSchedulerException;
import com.takaibun.plexmetadatamanager.quartz.job.ServerHealthCheckJob;

/**
 * QuartzJob类型枚举
 *
 * @author takaibun
 * @since 2023/03/01
 */
public enum QuartzJobType {

    /**
     * 媒体服务器健康检查
     */
    SERVER_HEALTH_CHECK("ServerHealthCheckJob", ServerHealthCheckJob.class),;

    private final String jobName;
    private final Class<? extends QuartzJobBean> jobClass;

    QuartzJobType(String jobName, Class<? extends QuartzJobBean> jobClass) {
        this.jobName = jobName;
        this.jobClass = jobClass;
    }

    /**
     * 根据TaskDetailVo.TaskType获取QuartzJobType
     */
    public static QuartzJobType getJobType(TaskType taskType) {
        for (QuartzJobType quartzJobType : QuartzJobType.values()) {
            if (quartzJobType.getJobName().startsWith(taskType.getName())) {
                return quartzJobType;
            }
        }
        throw new QuartzSchedulerException("task type is not support: " + taskType.getName());
    }

    public String getJobName() {
        return jobName;
    }

    public Class<? extends QuartzJobBean> getJobClass() {
        return jobClass;
    }
}
