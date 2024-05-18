package com.timeTracker.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

public class DeviceLogDto implements Serializable{

	private static final long serialVersionUID = -4099901376624546735L;
	
	private String deviceLog_userId;
	private String deviceLog_employeeName;
	private Timestamp deviceLog_logDate;
	private String deviceLog_totalInHour;
	private String deviceLog_totalInMin;
	private String deviceLog_totalInSec;
	private String deviceLog_totalOutHour;
	private String deviceLog_totalOutMin;
	private String deviceLog_totalOutSec;
	
	public DeviceLogDto(String deviceLog_userId,
			Timestamp deviceLog_logDate,
			String deviceLog_totalInHour,
	 String deviceLog_totalInMin,
	 String deviceLog_totalInSec,
	 String deviceLog_totalOutHour,
	 String deviceLog_totalOutMin,
	 String deviceLog_totalOutSec) {
		super();
		this.deviceLog_userId = deviceLog_userId;
		this.deviceLog_logDate = deviceLog_logDate;
		this.deviceLog_totalInHour = deviceLog_totalInHour;
		this.deviceLog_totalInMin = deviceLog_totalInMin;
		this.deviceLog_totalInSec = deviceLog_totalInSec;
		this.deviceLog_totalOutHour = deviceLog_totalOutHour;
		this.deviceLog_totalOutMin = deviceLog_totalOutMin;
		this.deviceLog_totalOutSec = deviceLog_totalOutSec;
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
	 * @return the deviceLog_totalInHour
	 */
	public String getDeviceLog_totalInHour() {
		return deviceLog_totalInHour;
	}

	/**
	 * @param deviceLog_totalInHour the deviceLog_totalInHour to set
	 */
	public void setDeviceLog_totalInHour(String deviceLog_totalInHour) {
		this.deviceLog_totalInHour = deviceLog_totalInHour;
	}

	/**
	 * @return the deviceLog_totalInMin
	 */
	public String getDeviceLog_totalInMin() {
		return deviceLog_totalInMin;
	}

	/**
	 * @param deviceLog_totalInMin the deviceLog_totalInMin to set
	 */
	public void setDeviceLog_totalInMin(String deviceLog_totalInMin) {
		this.deviceLog_totalInMin = deviceLog_totalInMin;
	}

	/**
	 * @return the deviceLog_totalInSec
	 */
	public String getDeviceLog_totalInSec() {
		return deviceLog_totalInSec;
	}

	/**
	 * @param deviceLog_totalInSec the deviceLog_totalInSec to set
	 */
	public void setDeviceLog_totalInSec(String deviceLog_totalInSec) {
		this.deviceLog_totalInSec = deviceLog_totalInSec;
	}

	/**
	 * @return the deviceLog_totalOutHour
	 */
	public String getDeviceLog_totalOutHour() {
		return deviceLog_totalOutHour;
	}

	/**
	 * @param deviceLog_totalOutHour the deviceLog_totalOutHour to set
	 */
	public void setDeviceLog_totalOutHour(String deviceLog_totalOutHour) {
		this.deviceLog_totalOutHour = deviceLog_totalOutHour;
	}

	/**
	 * @return the deviceLog_totalOutMin
	 */
	public String getDeviceLog_totalOutMin() {
		return deviceLog_totalOutMin;
	}

	/**
	 * @param deviceLog_totalOutMin the deviceLog_totalOutMin to set
	 */
	public void setDeviceLog_totalOutMin(String deviceLog_totalOutMin) {
		this.deviceLog_totalOutMin = deviceLog_totalOutMin;
	}

	/**
	 * @return the deviceLog_totalOutSec
	 */
	public String getDeviceLog_totalOutSec() {
		return deviceLog_totalOutSec;
	}

	/**
	 * @param deviceLog_totalOutSec the deviceLog_totalOutSec to set
	 */
	public void setDeviceLog_totalOutSec(String deviceLog_totalOutSec) {
		this.deviceLog_totalOutSec = deviceLog_totalOutSec;
	}
	
}
