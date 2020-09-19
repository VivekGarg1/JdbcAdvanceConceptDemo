package com.home.client;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.home.model.Employee;
import com.home.util.JdbcUtil;

public class ResultSetInsertClientTest {

	public static void main(String[] args) {
		createEmployee();
	}

	private static void createEmployee() {
		Employee employee=new Employee();
		employee.setEmployeeName("Shubham");
		employee.setEmail("s@gmail.com");
		employee.setSalary(17000.0);
		employee.setDateOfJoining(new Date());
		employee.setBonus(new BigDecimal(700));
		
		String SQL = "select * from employee_table";
		try (Connection connection = JdbcUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
			ResultSet rs = ps.executeQuery();
			rs.moveToInsertRow();
			rs.updateString("employee_name", employee.getEmployeeName());
			rs.updateString("email", employee.getEmail());
			rs.updateDouble("salary",employee.getSalary());
			rs.updateDate("date_of_joining", new java.sql.Date(employee.getDateOfJoining().getTime()));
			rs.updateBigDecimal("bonus", employee.getBonus());
			
			rs.insertRow();
			
			while (rs.next()) {
				int employee_Id = rs.getInt("employee_id");
				String employeeName = rs.getString("employee_name");
				String email = rs.getString("email");
				Double salary = rs.getDouble("salary");
				Date joiningDate = rs.getDate("date_of_joining");
				BigDecimal bonus = rs.getBigDecimal("bonus");
				Employee employee1 = new Employee();
				employee1.setEmployeeId(employee_Id);
				employee1.setEmployeeName(employeeName);
				employee1.setEmail(email);
				employee1.setSalary(salary);
				employee1.setDateOfJoining(joiningDate);
				employee1.setBonus(bonus);
				System.out.println(employee1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
