package com.kangfoo.study.pm25.import2local;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportDataJob implements Job {
	private final static Logger logger = LoggerFactory
			.getLogger("ImportDataJob");

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("轮询ImportDataJob开始....");
		
		GetPM25Data2LogFile data2LogFile = new GetPM25Data2LogFile();
		data2LogFile.importData();
		
		logger.info("轮询ImportDataJob结束");
	}

}
