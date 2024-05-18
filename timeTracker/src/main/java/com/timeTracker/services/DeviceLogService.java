package com.timeTracker.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.internal.util.collections.CollectionHelper;
import org.springframework.stereotype.Service;

import com.timeTracker.dtos.DeviceLogDto;
import com.timeTracker.entities.DeviceLogEntity;

@Service
public class DeviceLogService {

	private static final String NO_TIME = "00";
	
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
	
	public DeviceLogDto fillDeviceLogDto(List<DeviceLogEntity> deviceLogEntityList, String userId, String inputDate) {
		Map<String,String> resultMap = new HashMap<>();
		if(CollectionHelper.isEmpty(deviceLogEntityList)) {
			return new DeviceLogDto(userId, Timestamp.valueOf(inputDate += " 00:00:00"), NO_TIME, NO_TIME, NO_TIME, NO_TIME, NO_TIME, NO_TIME);
		}
		resultMap = getCalculatedInOutTime(deviceLogEntityList);
		DeviceLogDto deviceLogDto = new DeviceLogDto(resultMap.get("USERID"),
				Timestamp.valueOf(resultMap.get("LOGDATE")),
				resultMap.get("INHOUR"),
				resultMap.get("INMIN"),
				resultMap.get("INSEC"),
				resultMap.get("OUTHOUR"),
				resultMap.get("OUTMIN"),
				resultMap.get("OUTSEC"));
		
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
		resultMap.put("INHOUR", hoursDifference < 10 ? "0"+String.valueOf(hoursDifference) : String.valueOf(hoursDifference));
		resultMap.put("INMIN", minutesDifference < 10 ? "0"+String.valueOf(minutesDifference) : String.valueOf(minutesDifference));
		resultMap.put("INSEC", secondsRemaining < 10 ? "0"+String.valueOf(secondsRemaining) : String.valueOf(secondsRemaining));
//		String inTimeResult = hoursDifference + " hours, " + minutesDifference + " minutes, " + secondsRemaining + " seconds.";
		secondsDifference = outTime / 1000;
		hoursDifference = secondsDifference / 3600;
		minutesDifference = (secondsDifference % 3600) / 60;
		secondsRemaining = secondsDifference % 60;
		resultMap.put("OUTHOUR", hoursDifference < 10 ? "0"+String.valueOf(hoursDifference) : String.valueOf(hoursDifference));
		resultMap.put("OUTMIN", minutesDifference < 10 ? "0"+String.valueOf(minutesDifference) : String.valueOf(minutesDifference));
		resultMap.put("OUTSEC", secondsRemaining < 10 ? "0"+String.valueOf(secondsRemaining) : String.valueOf(secondsRemaining));
//		String outTimeResult = hoursDifference + " hours, " + minutesDifference + " minutes, " + secondsRemaining + " seconds.";
//		resultMap.put("INTIME", inTimeResult);
//		resultMap.put("OUTTIME", outTimeResult);
		return resultMap;
	}
}
