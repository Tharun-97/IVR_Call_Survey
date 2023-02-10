package org.CallSurvey.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

public class CallSurveyDAO {
	String url = null;
	String username = null;
	String password = null;

	public String propertyRead() throws IOException {
		FileInputStream f = new FileInputStream("D:/IVR Projects/CallSurvey/data/JDBC.properties");

		Properties p = new Properties();
		p.load(f);

		url = p.getProperty("url");
		username = p.getProperty("username");
		password = p.getProperty("password");
		return url + username + password;

	}

	public BasicDataSource getConnectionDetails() {
		BasicDataSource datasource = new BasicDataSource();

		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		datasource.setMinIdle(5);
		datasource.setMaxIdle(10);
		datasource.setMaxTotal(25);

		return datasource;

	}

	public ResultSet connectionPooling(String query) throws SQLException {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnectionDetails().getConnection();
			System.out.println("Database Connected Succesfully");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		statement = connection.createStatement();
		resultSet = statement.executeQuery(query);

		return resultSet;

	}

	public PreparedStatement DataInsert(String query) throws SQLException {
		Connection connection = null;
		PreparedStatement insert= null;
		try {
			connection = getConnectionDetails().getConnection();
			System.out.println("Database Connected Succesfully");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		insert = connection.prepareStatement(query);
		
		return insert;
	}

}
