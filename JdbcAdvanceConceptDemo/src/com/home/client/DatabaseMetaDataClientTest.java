package com.home.client;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import com.home.util.JdbcUtil;

public class DatabaseMetaDataClientTest {

	public static void main(String[] args) {
		try (Connection connection = JdbcUtil.getConnection()) {
			DatabaseMetaData metaData = connection.getMetaData();	
			int databaseMinorVersion = metaData.getDatabaseMinorVersion();
			System.out.println("Minor version: "+databaseMinorVersion);
			int databaseMajorVersion = metaData.getDatabaseMajorVersion();
			System.out.println("Major version: "+databaseMajorVersion);
			String userName = metaData.getUserName();
			System.out.println("UserName: "+userName);
			String url = metaData.getURL();
			System.out.println("URL: "+url);
			boolean readOnly = metaData.isReadOnly();
			System.out.println("ReadOnly: "+readOnly);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
