package com.timeTracker.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.util.collections.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.timeTracker.entities.DeviceLogEntity;
import com.timeTracker.services.DeviceLogService;

@Repository
public class DeviceLogDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DeviceLogService deviceLogService;
	
	public List<DeviceLogEntity> getDeviceLog(String userId, String inputDate){
		String query = "select * from " +  deviceLogService.generateTableName(inputDate) + " d "
				+ "where d.userId = '"+ userId + "' and d.logDate > '"+ inputDate +" 06:00:00.000' "
						+ "and d.logDate < '"+ deviceLogService.getNextDateString(inputDate) +" 06:00:00.000' "
								+ "order by LogDate";
		List<DeviceLogEntity> resultList = getResults(query);
		if(CollectionHelper.isEmpty(resultList)) {
			return new ArrayList<DeviceLogEntity>();
		}
		return resultList;
	}
	
	private List<DeviceLogEntity> getResults(String query) {
		return jdbcTemplate.query(query, (rs, rowNum) -> {
			DeviceLogEntity deviceLog = new DeviceLogEntity();
			deviceLog.setLogDate(rs.getTimestamp("LogDate"));
			deviceLog.setUserId(rs.getString("UserId"));
			return deviceLog;
		});
	}
}
