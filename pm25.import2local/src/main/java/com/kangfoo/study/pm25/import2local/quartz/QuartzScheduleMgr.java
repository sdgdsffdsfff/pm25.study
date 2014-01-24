package com.kangfoo.study.pm25.import2local.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 由于spring3.2.x 和 quartz 的相互支持不是很完善。这里不使用blueprint的对 quartz 进行注入。 直接使用 quartz
 * 的原生 API。因此需要提供一个工具类对 quartz Scheduler 进行管理。
 * <p/>
 * quartz Scheduler 在 osgi(blueprint) -> register 时实例化， 在 osgi 容器销毁时 unregister
 * 同时预先销毁 quartz 队列 ... ... 并销毁 scheduler 实例。
 * <p/>
 * 
 * 在 测试类中，请使用 spring3.x, quartz 1.8.x 版本。
 */
public class QuartzScheduleMgr {

	private static Logger logger = LoggerFactory
			.getLogger(QuartzScheduleMgr.class);

	private static Scheduler scheduler = getScheduler();

	public static Scheduler getInstanceScheduler() {
		return scheduler;
	}

	private static Scheduler getScheduler() {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = null;
		try {
			logger.info("scheduler installing... ...");
			scheduler = sf.getScheduler();
			logger.info("scheduler installed. ");
		} catch (SchedulerException e) {
			logger.error("实例化 scheduler 出现异常", e);
		}
		return scheduler;
	}

	public void start() throws SchedulerException {

		scheduler.start();
		logger.info("scheduler.isStarted: " + scheduler.isStarted());

	}

	public boolean isStart() throws SchedulerException {
		return scheduler.isStarted();
	}

	public void shutDown() throws SchedulerException {
		logger.info("scheduler will shutdown... ...");
		scheduler.shutdown();
		logger.info("scheduler has shutdown. ");

	}

	public Date scheduleJob(JobDetail jobdetail, Trigger trigger)
			throws SchedulerException {
		return scheduler.scheduleJob(jobdetail, trigger);
	}

	public Date scheduleJob(Trigger trigger) throws SchedulerException {
		return scheduler.scheduleJob(trigger);
	}

	public void pauseAll() throws SchedulerException {
		scheduler.pauseAll();
	}

	public void resumeAll() throws SchedulerException {
		scheduler.resumeAll();
	}
}
