package com.timeTracker.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.timeTracker.daos.DeviceLogDao;
import com.timeTracker.daos.EmployeeDao;
import com.timeTracker.dtos.DeviceLogDto;
import com.timeTracker.entities.DeviceLogEntity;
import com.timeTracker.entities.EmployeeEntity;
import com.timeTracker.services.AuthenticationService;
import com.timeTracker.services.DeviceLogService;


@Controller
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DeviceLogDao deviceLogDao;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private DeviceLogService deviceLogService;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/authenticate")
	public String authenticate(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isAuthenticated = authenticationService.beginLdapAuthentication(username, password);
		if(isAuthenticated) {
			EmployeeEntity employee = employeeDao.getEmployeeDetailsFromEmail(username);
			request.getSession().setAttribute("employeeName", employee.getEmployeeName());
			request.getSession().setAttribute("employeeCode", employee.getEmployeeCode());
			return "redirect:/employeeTimeTracker";
		}else {
			model.addAttribute("error", "Invalid username or password. Please try again.");
			return "login";
		}
	}

	@RequestMapping("/employees")
	public String listEmployees(HttpServletRequest request, Model model) {
		List<EmployeeEntity> employees = employeeDao.getAllEmployeeDetails();
		model.addAttribute("employees", employees);
		return "employeeList";
	}
	
	@RequestMapping("/employeeTimeTracker")
	public String employeeTimeTracker(HttpServletRequest request, Model model) {
		String employeeName = (String) request.getSession().getAttribute("employeeName");
		DeviceLogDto deviceLogDto = new DeviceLogDto();
		deviceLogDto.setDeviceLog_employeeName(employeeName);
		model.addAttribute("deviceLog", deviceLogDto);
		return "employeeTimeTracker";
	}
	
	@ModelAttribute("selectedDate")
    public LocalDate getDefaultDate(HttpServletRequest request) {
		String inputDate = request.getParameter("dateSelector");
		if(inputDate == null){
			return LocalDate.now();
		}else {
			return LocalDate.parse(inputDate);
		}
    }
	
	@ModelAttribute("employeeName")
	public String getDefaultName(HttpServletRequest request) {
		String employeeName = (String) request.getSession().getAttribute("employeeName");
		if(employeeName == null){
			return "";
		}else {
			return employeeName;
		}
	}
	
	@PostMapping("/employeeTimeTracker")
	public String employeeTimeTracker(HttpServletRequest request, @RequestParam("dateSelector") String inputDate, Model model) {
		String userId = (String) request.getSession().getAttribute("employeeCode");
		List<DeviceLogEntity> deviceLogEntityList = deviceLogDao.getDeviceLog(userId,inputDate);
		DeviceLogDto deviceLogDto = deviceLogService.fillDeviceLogDto(deviceLogEntityList);
		model.addAttribute("totalTime", deviceLogDto);
		return "employeeTimeTracker";
	}
}
