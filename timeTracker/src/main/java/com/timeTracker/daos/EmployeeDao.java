package com.timeTracker.daos;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.timeTracker.entities.EmployeeEntity;

@Repository
public class EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = Logger.getLogger(EmployeeDao.class.getName());

	public List<EmployeeEntity> getAllEmployeeDetails() {
		String query = "SELECT * FROM employees";
		return getResults(query);
	}

	public EmployeeEntity getEmployeeDetailsFromEmail(String email) {
		String query = "SELECT * FROM employees where employees.Email = '"+ email +"'";
		List<EmployeeEntity> resultList = getResults(query);
		try {
			return resultList.getFirst();
		}catch(NoSuchElementException | NullPointerException e) {
			logger.log(Level.SEVERE, "No employee record found for the email <" + email + ">");
			return null;
		}
	}
	
	public EmployeeEntity getEmployeeDetailsFromCode(String employeeCode) {
		String query = "SELECT * FROM employees where employees.EmployeeCode = '"+ employeeCode +"'";
		List<EmployeeEntity> resultList = getResults(query);
		try {
			return resultList.getFirst();
		}catch(NoSuchElementException | NullPointerException e) {
			logger.log(Level.SEVERE, "No employee record found for the employee code <" + employeeCode + ">");
			return null;
		}
	}

	private List<EmployeeEntity> getResults(String query) {
		return jdbcTemplate.query(query, (rs, rowNum) -> {
			EmployeeEntity employee = new EmployeeEntity();
			employee.setEmployeeCode(rs.getString("EmployeeCode"));
			employee.setEmployeeName(rs.getString("EmployeeName"));
			employee.setEmail(rs.getString("Email"));
			return employee;
		});
	}
}
