package com.takaibun.plexmetadatamanager.quartz.service;

import com.takaibun.plexmetadatamanager.http.vo.TaskDetailVo;
import com.takaibun.plexmetadatamanager.quartz.enums.QuartzJobType;
import com.takaibun.plexmetadatamanager.quartz.exception.QuartzSchedulerException;
import com.takaibun.plexmetadatamanager.service.SchedulerService;
import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.Map;

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
            throw new QuartzSchedulerException(e.getMessage());
        }
    }

    @Override
    public void deleteTask(TaskDetailVo taskDetail) {
        JobKey jobKey = getJobKey(taskDetail);
        try {
            if (!scheduler.checkExists(jobKey)) {
                throw new QuartzSchedulerException("job is not exists.");
            }
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getMessage());
        }
    }


    @Override
    public void updateTask(TaskDetailVo taskDetail) {
        try {
            JobKey jobKey = getJobKey(taskDetail);
            if (!scheduler.checkExists(jobKey)) {
                throw new QuartzSchedulerException("job is not exists.");
            }
            JobDetail jobDetail = buildJobDetail(taskDetail);
            scheduler.addJob(jobDetail, true);
            TriggerKey triggerKey = getTriggerKey(taskDetail);
            if (scheduler.checkExists(triggerKey)) {
                Trigger trigger = buildTrigger(taskDetail);
                scheduler.unscheduleJob(triggerKey);
                scheduler.scheduleJob(trigger);
            }
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getMessage());
        }
    }

    @Override
    public void startTask(TaskDetailVo taskDetail) {
        try {
            scheduler.scheduleJob(buildTrigger(taskDetail));
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getMessage());
        }
    }


    @Override
    public void stopTask(TaskDetailVo taskDetail) {
        TriggerKey triggerKey = getTriggerKey(taskDetail);
        try {
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getMessage());
        }
    }

    @Override
    public void pauseTask(TaskDetailVo taskDetail) {
        JobKey jobKey = getJobKey(taskDetail);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getMessage());
        }
    }

    @Override
    public void resumeTask(TaskDetailVo taskDetail) {
        JobKey jobKey = getJobKey(taskDetail);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            throw new QuartzSchedulerException(e.getMessage());
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
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(taskDetail.getDescription());
        Map<String, Object> taskParams = taskDetail.getTaskParams();
        TaskDetailVo.SchedulerType schedulerType = taskDetail.getSchedulerType();
        return switch (schedulerType) {
            case CRON ->
                    triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(taskParams.get("cronExpression").toString())).build();
            case INTERVAL ->
                    triggerBuilder.withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().withInterval(Integer.parseInt(taskParams.get("interval").toString()), DateBuilder.IntervalUnit.MILLISECOND)).build();
            default -> TriggerBuilder.newTrigger().build();
        };
    }

    private JobDetail buildJobDetail(TaskDetailVo taskDetail) {
        JobKey jobKey = getJobKey(taskDetail);
        Class<? extends QuartzJobBean> jobClass = QuartzJobType.getJobType(taskDetail.getTaskType()).getJobClass();
        if (null == jobClass) {
            throw new QuartzSchedulerException("task type is not support");
        }
        JobDetail jobDetail = JobBuilder.newJob().ofType(jobClass).withIdentity(jobKey).withDescription(taskDetail.getDescription()).build();
        jobDetail.getJobDataMap().putAll(taskDetail.getTaskParams());
        return jobDetail;
    }
}
