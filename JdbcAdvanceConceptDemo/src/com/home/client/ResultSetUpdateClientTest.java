package com.home.client;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.home.model.Employee;
import com.home.util.JdbcUtil;

public class ResultSetUpdateClientTest {

	public static void main(String[] args) {
		updateEmailByEmpId();
	}

	private static void updateEmailByEmpId() {
		int empId = 1;
		String newEmail = "vg@gmail.com";
		String SQL = "select * from employee_table";
		try (Connection connection = JdbcUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int employeeId = rs.getInt("employee_id");
				if (employeeId == empId) {
					rs.updateString("email", newEmail);
					rs.updateRow();
					System.out.println("Email is updated!!!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
