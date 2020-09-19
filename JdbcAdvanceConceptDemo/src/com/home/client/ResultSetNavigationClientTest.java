package com.home.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.home.util.JdbcUtil;

public class ResultSetNavigationClientTest {

	public static void main(String[] args) {
		resultSetNavigatioMethod();
	}

	private static void resultSetNavigatioMethod() {
		String SQL = "select * from employee_table";
		try (Connection connection = JdbcUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE)) {
			ResultSet rs = ps.executeQuery();
			
			rs.absolute(2);
			int row = rs.getRow();
			System.out.println(row);
			
			rs.afterLast();
			boolean afterLast = rs.isAfterLast();
			System.out.println(afterLast);
			
			rs.beforeFirst();
			boolean beforeFirst = rs.isBeforeFirst();
			System.out.println(beforeFirst);
			
			rs.first();
			boolean first = rs.isFirst();
			System.out.println(first);
			
			rs.last();
			
			rs.relative(-2);
			int row2=rs.getRow();
			System.out.println(row2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
