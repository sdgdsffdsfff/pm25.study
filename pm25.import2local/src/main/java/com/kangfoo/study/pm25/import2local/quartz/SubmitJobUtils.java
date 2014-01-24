package com.kangfoo.study.pm25.import2local.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;
/**
 * Created with IntelliJ IDEA.
 * User: linkang
 * Date: 14-1-7
 * Time: 下午6:02
 * To change this template use File | Settings | File Templates.
 */

public class SubmitJobUtils {
    private static Logger log = LoggerFactory.getLogger(SubmitJobUtils.class);

    /*
      *
      * @throws SchedulerException
      * @throws ParseException
     */
    public void submit(JobInfoBean jobInfoBean) throws ParseException, SchedulerException, ClassNotFoundException {
        log.info("------- Initializing ----------------------");
        //从调度管理器中获取调度对象
        Scheduler sched = QuartzScheduleMgr.getInstanceScheduler();
        log.info("------- Initialization Complete -----------");

        JobDetail job = new JobDetail(jobInfoBean.getJobName(), jobInfoBean.getGroup(), Class.forName(jobInfoBean.getJobClass()));
        CronTrigger trigger = new CronTrigger(jobInfoBean.getTriggerName(), jobInfoBean.getGroup(), jobInfoBean.getJobName(), jobInfoBean.getGroup(), jobInfoBean.getCronExpression());
        sched.addJob(job, true);
        Date ft = sched.scheduleJob(trigger);
        log.info(job.getFullName() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

    }
}
