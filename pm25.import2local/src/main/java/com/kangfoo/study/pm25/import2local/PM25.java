package com.kangfoo.study.pm25.import2local;

public class PM25 {
	// "aqi": 82,
	// "area": "珠海",
	// "pm2_5": 31,
	// "pm2_5_24h": 60,
	// "position_name": "吉大",
	// "primary_pollutant": "颗粒物(PM2.5)",
	// "quality": "良",
	// "station_code": "1367A",
	// "time_point": "2013-03-07T19:00:00Z"

	private String aqi;
	private String area;
	private String pm25;
	private String pm25_24h;
	private String positionName;
	private String primaryPollutant;
	private String quality;

	public String getAqi() {
		return aqi;
	}

	public void setAqi(String aqi) {
		this.aqi = aqi;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPm25() {
		return pm25;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}

	public String getPm25_24h() {
		return pm25_24h;
	}

	public void setPm25_24h(String pm25_24h) {
		this.pm25_24h = pm25_24h;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPrimaryPollutant() {
		return primaryPollutant;
	}

	public void setPrimaryPollutant(String primaryPollutant) {
		this.primaryPollutant = primaryPollutant;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getTimePoint() {
		return timePoint;
	}

	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}

	private String stationCode;
	private String timePoint;

}
