package com.kangfoo.study.pm25.import2local;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetPM25Data2LogFile {

	private final static Logger logger = LoggerFactory
			.getLogger("GetPM25Data2LogFile");
	
	private final static String pm25Url = "http://www.pm25.in/api/querys/pm2_5.json?token=%s&city=%s";
	private final static String pm10Url = "http://www.pm25.in/api/querys/pm10.json?token=%s&city=%s";
	private final static String coUrl = "http://www.pm25.in/api/querys/co.json?token=%s&city=%s";
	private final static String no2Url = "http://www.pm25.in/api/querys/no2.json?token=%s&city=%s";
	private final static String so2Url = "http://www.pm25.in/api/querys/so2.json?token=%s&city=%s";
	private final static String o3Url = "http://www.pm25.in/api/querys/o3.json?token=%s&city=%s";
	private final static String aqiDetailsUrl = "http://www.pm25.in/api/querys/aqi_details.json?token=%s&city=%s";
	private final static String onlyAqiUrl = "http://www.pm25.in/api/querys/only_aqi.json?token=%s&city=%s";
	
	private final static String token = "5j1znBVAsnSf5xQyNQyq";
	private static Map<String, String> urls = null;
	
	private static List<String> citys = null;

	static {
		citys = new ArrayList<String>();
		citys.add("beijing");
		citys.add("shanghai");
		citys.add("ningbo");
		citys.add("wuhan");
		citys.add("guangzhou");
		citys.add("chengdu");
		
		urls = new HashMap<String, String>();
		urls.put("mp2.5", pm25Url);
		urls.put("pm10", pm10Url);
		urls.put("co", coUrl);
		urls.put("no2", no2Url);
		urls.put("so2", so2Url);
		urls.put("o3", o3Url);
		urls.put("aqiDetails", aqiDetailsUrl);
		urls.put("onlyAqi", onlyAqiUrl);
		urls.put("mp2.5", pm25Url);
		urls.put("mp2.5", pm25Url);
		
	}

	public void importData() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {

			for (Iterator<String> it = citys.iterator(); it.hasNext();) {
				String city = (String) it.next();

				for (Iterator u = urls.keySet().iterator(); u
						.hasNext();) {
					String key = (String) u.next();
					String urlstr = String.format(urls.get(key),token, city);
					
					getData(httpclient, city, key, urlstr);
				}
			}
		} catch (ClientProtocolException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}

	private void getData(CloseableHttpClient httpclient, String city, String key,
			String urlstr) throws IOException, ClientProtocolException {
		HttpGet httpget = new HttpGet(urlstr);

		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

			public String handleResponse(final HttpResponse response)
					throws ClientProtocolException, IOException {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils
							.toString(entity) : null;
				} else {
					throw new ClientProtocolException(
							"Unexpected response status: " + status);
				}
			}
		};
		String responseBody = httpclient.execute(httpget,
				responseHandler);

		logger.info(city + "\t" + key + "\t" + responseBody);
	}
}
