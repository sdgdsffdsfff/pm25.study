package com.kangfoo.study.pm25.import2local;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	private final static String url = "http://www.pm25.in/api/querys/pm2_5.json?token=%s&city=%s";
	private final static String token = "5j1znBVAsnSf5xQyNQyq";

	private static List<String> citys = null;

	static {
		citys = new ArrayList<String>();
		citys.add("beijing");
		citys.add("shanghai");
		citys.add("ningbo");
		citys.add("wuhan");
		citys.add("guangzhou");
		citys.add("chengdu");
	}

	public void importData() {
		// Create an instance of HttpClient.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {

			for (Iterator<String> it = citys.iterator(); it.hasNext();) {
				String city = (String) it.next();
				String urlstr = String.format(url, token, city);

				HttpGet httpget = new HttpGet(urlstr);

				//System.out.println("Executing request " + httpget.getRequestLine());

				// Create a custom response handler
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

				logger.info(city + "\t" + responseBody);

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
}
