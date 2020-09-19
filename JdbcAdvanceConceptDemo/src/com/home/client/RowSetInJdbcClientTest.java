package com.home.client;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import com.home.model.Employee;

public class RowSetInJdbcClientTest {

	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "root";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbcdb";

	public static void main(String[] args) throws SQLException {

		RowSetFactory rowSetFactory = RowSetProvider.newFactory();
		JdbcRowSet jdbcRowSet = rowSetFactory.createJdbcRowSet();

		jdbcRowSet.setUrl(DB_URL);
		jdbcRowSet.setUsername(DB_USERNAME);
		jdbcRowSet.setPassword(DB_PASSWORD);

		jdbcRowSet.setCommand("select * from employee_table");
		jdbcRowSet.execute();

		showEmployeesInfo(jdbcRowSet);
		// createEmployee(jdbcRowSet);
		//updateEmployeeEmailById(jdbcRowSet);
		//deleteEmployeeEmailById(jdbcRowSet);
		System.out.println("--------------------------------------");
		showEmployeesInfo(jdbcRowSet);
	}

	private static void deleteEmployeeEmailById(JdbcRowSet jdbcRowSet) throws SQLException {
		int empId = 8;
		while (jdbcRowSet.next()) {
			int employee_Id = jdbcRowSet.getInt("employee_id");
			if (employee_Id == empId) {
				jdbcRowSet.deleteRow();
				break;
			}
		}
		jdbcRowSet.beforeFirst();
	}

	private static void updateEmployeeEmailById(JdbcRowSet jdbcRowSet) throws SQLException {
		int empId = 8;
		while (jdbcRowSet.next()) {
			int employee_Id = jdbcRowSet.getInt("employee_id");
			if (employee_Id == empId) {
				jdbcRowSet.updateString("email", "hrj@gmail.com");
				jdbcRowSet.updateRow();
			}
		}
		jdbcRowSet.beforeFirst();
	}

	private static void createEmployee(JdbcRowSet jdbcRowSet) throws SQLException {
		jdbcRowSet.moveToInsertRow();
		jdbcRowSet.updateString("employee_name", "Raja");
		jdbcRowSet.updateString("email", "r@gmail.com");
		jdbcRowSet.updateDouble("salary", 20000.0);
		jdbcRowSet.updateTimestamp("date_of_joining", new java.sql.Timestamp(new Date().getTime()));
		jdbcRowSet.updateBigDecimal("bonus", new BigDecimal(600.0));
		jdbcRowSet.insertRow();
		jdbcRowSet.beforeFirst();
	}

	private static void showEmployeesInfo(JdbcRowSet jdbcRowSet) throws SQLException {
		while (jdbcRowSet.next()) {
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
		jdbcRowSet.beforeFirst();
	}

}
