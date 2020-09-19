package com.home.client;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.JdbcRowSet;

import com.home.model.Employee;

public class EmployeeServiceRowSetListener implements RowSetListener {

	@Override
	public void rowSetChanged(RowSetEvent event) {
		System.out.println("RowSet changed event");
		Object source = event.getSource();
		System.out.println(source);
	}

	@Override
	public void rowChanged(RowSetEvent event) {
		System.out.println("Row changed event");
		Object source = event.getSource();
		System.out.println(source);
	}

	@Override
	public void cursorMoved(RowSetEvent event) {
		Object source = event.getSource();
		JdbcRowSet jdbcRowSet=(JdbcRowSet)source;
		try {
			if(jdbcRowSet.isAfterLast() || jdbcRowSet.isBeforeFirst()) {
				//do nothing
			}
			else {
				System.out.println("Cursor moved event occour with following details");
				int employee_Id = jdbcRowSet.getInt("employee_id");
				String employeeName = jdbcRowSet.getString("employee_name");
				String email = jdbcRowSet.getString("email");
				Double salary = jdbcRowSet.getDouble("salary");
				Date joiningDate = jdbcRowSet.getDate("date_of_joining");
				BigDecimal bonus = jdbcRowSet.getBigDecimal("bonus");
				Employee employee = new Employee();
				employee.setEmployeeId(employee_Id);
				employee.setEmployeeName(employeeName);
				employee.setEmail(email);
				employee.setSalary(salary);
				employee.setDateOfJoining(joiningDate);
				employee.setBonus(bonus);
				System.out.println(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
