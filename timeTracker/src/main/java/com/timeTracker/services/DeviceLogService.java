package com.timeTracker.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.timeTracker.dtos.DeviceLogDto;
import com.timeTracker.entities.DeviceLogEntity;

@Service
public class DeviceLogService {

	public String getNextDateString(String inputDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = null;
        try {
        	calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(inputDate));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public String generateTableName(String dateStr) {
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        try {
            // Parse the input date string to a Date object
            calendar.setTime(sdfInput.parse(dateStr));

            // Extract month and year from the parsed date
            int month = calendar.get(Calendar.MONTH) + 1; // Adding 1 because Calendar.MONTH is zero-based
            int year = calendar.get(Calendar.YEAR);

            // Format the result string
            return "dbo.DeviceLogs_" + month + "_" + year;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public DeviceLogDto fillDeviceLogDto(List<DeviceLogEntity> deviceLogEntityList) {
		Map<String,String> resultMap = getCalculatedInOutTime(deviceLogEntityList);
		DeviceLogDto deviceLogDto = new DeviceLogDto(resultMap.get("USERID"),
				Timestamp.valueOf(resultMap.get("LOGDATE")),
				resultMap.get("INTIME"),
				resultMap.get("OUTTIME"));
		
		return deviceLogDto;
	}
	
	private Map<String,String> getCalculatedInOutTime(List<DeviceLogEntity> deviceLogEntityList){
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("USERID", deviceLogEntityList.get(0).getUserId());
		resultMap.put("LOGDATE", deviceLogEntityList.get(0).getLogDate().toString());
		int i = 1;
		Timestamp dateTime1 = null;
		Timestamp dateTime2 = null;
		long inTime = 0;
		long outTime = 0;
		if(deviceLogEntityList.size()%2 != 0) {
			DeviceLogEntity deviceLogEntity = new DeviceLogEntity();
			Date date = new Date();
	        Timestamp timestampFromDate = new Timestamp(date.getTime());
			deviceLogEntity.setUserId(deviceLogEntityList.get(0).getUserId());
			deviceLogEntity.setLogDate(timestampFromDate);
			deviceLogEntityList.add(deviceLogEntity);
		}
		for(DeviceLogEntity deviceLogEntity : deviceLogEntityList) {
			if(i == 1) {
				dateTime1 = deviceLogEntity.getLogDate();
			}else if(i %2 == 0) {
				dateTime2 = deviceLogEntity.getLogDate();
				inTime = inTime + dateTime2.getTime() - dateTime1.getTime();
			}else {
				dateTime1 = deviceLogEntity.getLogDate();
				outTime = outTime +  dateTime1.getTime() - dateTime2.getTime();
			}
			i++;
		}
		long secondsDifference = inTime / 1000;
		long hoursDifference = secondsDifference / 3600;
		long minutesDifference = (secondsDifference % 3600) / 60;
		long secondsRemaining = secondsDifference % 60;
		String inTimeResult = hoursDifference + " hours, " + minutesDifference + " minutes, " + secondsRemaining + " seconds.";
		secondsDifference = outTime / 1000;
		hoursDifference = secondsDifference / 3600;
		minutesDifference = (secondsDifference % 3600) / 60;
		secondsRemaining = secondsDifference % 60;
		String outTimeResult = hoursDifference + " hours, " + minutesDifference + " minutes, " + secondsRemaining + " seconds.";
		resultMap.put("INTIME", inTimeResult);
		resultMap.put("OUTTIME", outTimeResult);
		return resultMap;
	}
}
