package com.kangfoo.study.pm25.import2local;

import java.text.ParseException;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kangfoo.study.pm25.import2local.quartz.JobInfoBean;
import com.kangfoo.study.pm25.import2local.quartz.QuartzScheduleMgr;
import com.kangfoo.study.pm25.import2local.quartz.SubmitJobUtils;

public class Launcher {

	private final static Logger logger = LoggerFactory.getLogger("Launcher");

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Scheduler scheduler = QuartzScheduleMgr.getInstanceScheduler();

		String jobName = "job_GetPM25Data2LogFile";

		String groupId = scheduler.getSchedulerInstanceId();

		// 判斷job的操作
		JobDetail jobDetail = scheduler.getJobDetail(jobName, groupId);

		JobInfoBean jb = new JobInfoBean();
		jb.setJobClass(com.kangfoo.study.pm25.import2local.ImportDataJob.class
				.getName());
		jb.setCronExpression("0 42 */1 * * ?");

		if (jobDetail == null) {
			createJob(jb, jobName, groupId);
		} else {

			if (jb.getOperationType().equalsIgnoreCase("create")) {
				logger.error("請不要重複提交已有的JOB。jobId:[" + jobName + "], groupId:["
						+ groupId + "].");
				return;
			} else if (jb.getOperationType().equalsIgnoreCase("delete")) {
				scheduler.deleteJob(jobName, groupId);
				logger.error("已刪除jobId:[" + jobName + "], groupId:[" + groupId
						+ "].");
			}
		}

		scheduler.start();

	}

	private static void createJob(JobInfoBean jb, String jobName, String groupId)
			throws ParseException, SchedulerException, ClassNotFoundException {
		// 默認新建job
		logger.info("開始新建並提交job" + jb.getJobClass());

		JobInfoBean jobInfoBean = new JobInfoBean(jb.getJobClass(), jobName,
				groupId, jb.getCronExpression());

		new SubmitJobUtils().submit(jobInfoBean);

		logger.info("結束提交" + jb.getJobClass());

	}

}
