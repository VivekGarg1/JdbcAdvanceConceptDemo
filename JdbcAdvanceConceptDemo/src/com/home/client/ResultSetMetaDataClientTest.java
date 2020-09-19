package com.home.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.home.util.JdbcUtil;

public class ResultSetMetaDataClientTest {

	public static void main(String[] args) {
		String SQL = "select * from employee_table";
		try (Connection connection = JdbcUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery()) {
			ResultSetMetaData metaData = rs.getMetaData();	
			int columnCount = metaData.getColumnCount();
			System.out.println("Total no if columns: "+columnCount);
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnName(i);
				String columnType = metaData.getColumnTypeName(i);
				System.out.println(columnName+" is type of "+columnType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
