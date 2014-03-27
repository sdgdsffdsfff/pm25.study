package com.kangfoo.study.pm25.import2local;

import com.kangfoo.study.pm25.import2local.impl.GetWeatherImpl;
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
		logger.debug("轮询ImportDataJob开始....");
		
		GetPM25Data2LogFile data2LogFile = new GetPM25Data2LogFile();
		data2LogFile.importData();
		
		AllCityPM25Data2LogFile allCity = new AllCityPM25Data2LogFile();
		allCity.importData();
		
		APIRankingData2LogFile apiRanking = new APIRankingData2LogFile();
		apiRanking.importData();

        GetWeatherImpl getWeather = new GetWeatherImpl();
        getWeather.importData();

		logger.debug("轮询ImportDataJob结束");
	}

}
