package com.takaibun.plexmetadatamanager.quartz.enums;

import com.takaibun.plexmetadatamanager.http.vo.TaskDetailVo;
import com.takaibun.plexmetadatamanager.quartz.job.MediaServerHealthJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

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
    MEDIA_SERVER_HEALTH("MediaServerHealthJob", MediaServerHealthJob.class),
    ;

    private final String jobName;
    private final Class<? extends QuartzJobBean> jobClass;

    QuartzJobType(String jobName, Class<? extends QuartzJobBean> jobClass) {
        this.jobName = jobName;
        this.jobClass = jobClass;
    }

    /**
     * 根据TaskDetailVo.TaskType获取QuartzJobType
     */
    public static QuartzJobType getJobType(TaskDetailVo.TaskType taskType) {
        for (QuartzJobType quartzJobType : QuartzJobType.values()) {
            if (quartzJobType.getJobName().equals(taskType.getName())) {
                return quartzJobType;
            }
        }
        return null;
    }

    public String getJobName() {
        return jobName;
    }

    public Class<? extends QuartzJobBean> getJobClass() {
        return jobClass;
    }
}
