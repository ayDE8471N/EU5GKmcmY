// 代码生成时间: 2025-09-13 09:10:26
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.JobBuilder;
import org.quartz.CronScheduleBuilder;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.Date;
import java.util.Set;
import java.util.Map;

// 定时任务调度器类
public class ScheduledTaskManager {

    // 创建调度器
    private static Scheduler scheduler = null;

    // 初始化调度器
    public static void initializeScheduler() throws SchedulerException {
        // 获取调度器实例
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 启动调度器
        scheduler.start();
    }

    // 添加定时任务
    public static void scheduleJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        // 调度器调度任务
        scheduler.scheduleJob(jobDetail, trigger);
    }

    // 获取所有作业组的名称
    public static Set<String> getAllJobGroups() throws SchedulerException {
        return scheduler.getJobGroupNames();
    }

    // 获取指定组的所有作业
    public static Set<JobKey> getJobsInGroup(String group) throws SchedulerException {
        return scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group));
    }

    // 暂停所有作业
    public static void pauseAllJobs() throws SchedulerException {
        scheduler.pauseAll();
    }

    // 恢复所有作业
    public static void resumeAllJobs() throws SchedulerException {
        scheduler.resumeAll();
    }

    // 暂停指定组的所有作业
    public static void pauseJobsInGroup(String group) throws SchedulerException {
        scheduler.pauseJobs(GroupMatcher.jobGroupEquals(group));
    }

    // 恢复指定组的所有作业
    public static void resumeJobsInGroup(String group) throws SchedulerException {
        scheduler.resumeJobs(GroupMatcher.jobGroupEquals(group));
    }

    // 暂停作业
    public static void pauseJob(JobKey jobKey) throws SchedulerException {
        scheduler.pauseJob(jobKey);
    }

    // 恢复作业
    public static void resumeJob(JobKey jobKey) throws SchedulerException {
        scheduler.resumeJob(jobKey);
    }

    // 删除作业
    public static void deleteJob(JobKey jobKey) throws SchedulerException {
        scheduler.deleteJob(jobKey);
    }

    // 调度器是否运行
    public static boolean isSchedulerRunning() {
        return scheduler != null && scheduler.isStarted();
    }

    // 关闭调度器
    public static void shutdownScheduler() throws SchedulerException {
        if (scheduler != null && scheduler.isStarted()) {
            scheduler.shutdown();
        }
    }

    // 定时任务实现
    public static class MyJob implements Job {

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            // 任务执行逻辑
            System.out.println("Executing Job: " + context.getJobDetail().getKey().getName());
        }
    }

    // 测试方法
    public static void main(String[] args) {
        try {
            // 初始化调度器
            initializeScheduler();

            // 定义任务
            JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob", "group1") // 任务名，任务组
                .build();

            // 定义触发器
            Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1") // 触发器名，触发器组
                .startNow() // 立即执行
                .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?")) // 每30秒执行一次
                .build();

            // 添加任务和触发器
            scheduleJob(job, trigger);

            // 等待任务执行
            Thread.sleep(20000);

            // 关闭调度器
            shutdownScheduler();
        } catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
