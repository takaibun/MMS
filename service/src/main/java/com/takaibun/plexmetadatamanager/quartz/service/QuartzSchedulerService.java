package com.takaibun.plexmetadatamanager.quartz.service;

import java.util.Map;

import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.takaibun.plexmetadatamanager.enums.SchedulerType;
import com.takaibun.plexmetadatamanager.enums.TaskStatus;
import com.takaibun.plexmetadatamanager.http.vo.TaskDetailVo;
import com.takaibun.plexmetadatamanager.quartz.enums.QuartzJobType;
import com.takaibun.plexmetadatamanager.quartz.exception.QuartzSchedulerException;
import com.takaibun.plexmetadatamanager.service.SchedulerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuartzSchedulerService implements SchedulerService {
    private final Scheduler scheduler;

    public QuartzSchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void addTask(TaskDetailVo taskDetail) {
        try {
            scheduler.addJob(buildJobDetail(taskDetail), true);
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void deleteTask(TaskDetailVo taskDetail) {
        JobKey jobKey = getJobKey(taskDetail);
        try {
            verifyJobExists(jobKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void updateTask(TaskDetailVo taskDetail) {
        try {
            JobKey jobKey = getJobKey(taskDetail);
            verifyJobExists(jobKey);
            scheduler.addJob(buildJobDetail(taskDetail), true);
            if (taskDetail.getTaskStatus() == TaskStatus.START) {
                TriggerKey triggerKey = getTriggerKey(taskDetail);
                if (scheduler.checkExists(triggerKey)) {
                    scheduler.unscheduleJob(triggerKey);
                }
                scheduler.scheduleJob(buildTrigger(taskDetail));
            }
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void startTask(TaskDetailVo taskDetail) {
        try {
            scheduler.scheduleJob(buildTrigger(taskDetail));
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void stopTask(TaskDetailVo taskDetail) {
        TriggerKey triggerKey = getTriggerKey(taskDetail);
        try {
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void pauseTask(TaskDetailVo taskDetail) {
        JobKey jobKey = getJobKey(taskDetail);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void resumeTask(TaskDetailVo taskDetail) {
        JobKey jobKey = getJobKey(taskDetail);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void triggerTask(TaskDetailVo taskDetail) {
        JobKey jobKey = getJobKey(taskDetail);
        try {
            verifyJobExists(jobKey);
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getLocalizedMessage());
        }
    }

    private void verifyJobExists(JobKey jobKey) throws SchedulerException {
        if (!scheduler.checkExists(jobKey)) {
            log.error("job is not exists.");
            throw new QuartzSchedulerException("job is not exists.");
        }
    }

    private JobKey getJobKey(TaskDetailVo taskDetail) {
        return new JobKey(taskDetail.getId(), taskDetail.getGroupId());
    }

    private TriggerKey getTriggerKey(TaskDetailVo taskDetail) {
        return new TriggerKey(taskDetail.getId(), taskDetail.getGroupId());
    }

    private Trigger buildTrigger(TaskDetailVo taskDetail) {
        TriggerKey triggerKey = getTriggerKey(taskDetail);
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger().forJob(getJobKey(taskDetail))
            .withIdentity(triggerKey).withDescription(taskDetail.getDescription());
        Map<String, Object> taskParams = taskDetail.getTaskParams();
        SchedulerType schedulerType = taskDetail.getSchedulerType();
        return switch (schedulerType) {
            case CRON -> triggerBuilder
                .withSchedule(CronScheduleBuilder.cronSchedule(taskParams.get("cron_expression").toString())).build();
            case INTERVAL -> triggerBuilder
                .withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().withInterval(
                    Integer.parseInt(taskParams.get("interval").toString()), DateBuilder.IntervalUnit.MILLISECOND))
                .build();
            default -> triggerBuilder.build();
        };
    }

    private JobDetail buildJobDetail(TaskDetailVo taskDetail) {
        JobKey jobKey = getJobKey(taskDetail);
        Class<? extends QuartzJobBean> jobClass = QuartzJobType.getJobType(taskDetail.getTaskType()).getJobClass();
        JobDetail jobDetail = JobBuilder.newJob().ofType(jobClass).withIdentity(jobKey)
            .withDescription(taskDetail.getDescription()).storeDurably().build();
        jobDetail.getJobDataMap().putAll(taskDetail.getTaskParams());
        return jobDetail;
    }
}
