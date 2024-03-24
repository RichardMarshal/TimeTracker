package com.timeTracker.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

public class DeviceLogDto implements Serializable{

	private static final long serialVersionUID = -4099901376624546735L;
	
	private String deviceLog_userId;
	private String deviceLog_employeeName;
	private Timestamp deviceLog_logDate;
	private String deviceLog_totalInTime;
	private String deviceLog_totalOutTime;
	
	public DeviceLogDto(String deviceLog_userId,
			Timestamp deviceLog_logDate,
			String deviceLog_totalInTime,
			String deviceLog_totalOutTime) {
		super();
		this.deviceLog_userId = deviceLog_userId;
		this.deviceLog_logDate = deviceLog_logDate;
		this.deviceLog_totalInTime = deviceLog_totalInTime;
		this.deviceLog_totalOutTime = deviceLog_totalOutTime;
	}

	public DeviceLogDto() {
		super();
	}

	/**
	 * @return the deviceLog_userId
	 */
	public String getDeviceLog_userId() {
		return deviceLog_userId;
	}

	/**
	 * @param deviceLog_userId the deviceLog_userId to set
	 */
	public void setDeviceLog_userId(String deviceLog_userId) {
		this.deviceLog_userId = deviceLog_userId;
	}

	/**
	 * @return the deviceLog_employeeName
	 */
	public String getDeviceLog_employeeName() {
		return deviceLog_employeeName;
	}

	/**
	 * @param deviceLog_employeeName the deviceLog_employeeName to set
	 */
	public void setDeviceLog_employeeName(String deviceLog_employeeName) {
		this.deviceLog_employeeName = deviceLog_employeeName;
	}

	/**
	 * @return the deviceLog_logDate
	 */
	public Timestamp getDeviceLog_logDate() {
		return deviceLog_logDate;
	}

	/**
	 * @param deviceLog_logDate the deviceLog_logDate to set
	 */
	public void setDeviceLog_logDate(Timestamp deviceLog_logDate) {
		this.deviceLog_logDate = deviceLog_logDate;
	}

	/**
	 * @return the deviceLog_totalInTime
	 */
	public String getDeviceLog_totalInTime() {
		return deviceLog_totalInTime;
	}

	/**
	 * @param deviceLog_totalInTime the deviceLog_totalInTime to set
	 */
	public void setDeviceLog_totalInTime(String deviceLog_totalInTime) {
		this.deviceLog_totalInTime = deviceLog_totalInTime;
	}

	/**
	 * @return the deviceLog_totalOutTime
	 */
	public String getDeviceLog_totalOutTime() {
		return deviceLog_totalOutTime;
	}

	/**
	 * @param deviceLog_totalOutTime the deviceLog_totalOutTime to set
	 */
	public void setDeviceLog_totalOutTime(String deviceLog_totalOutTime) {
		this.deviceLog_totalOutTime = deviceLog_totalOutTime;
	}

	
}
