import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPooling {
	private static BasicDataSource dataSource = null;

	static {
		dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:sqlserver://192.168.168.12;databaseName=_Tharun2022");
		dataSource.setUsername("NewJoinee2022");
		dataSource.setPassword("P@ssw0rd");
		
		
		dataSource.setMinIdle(5);
		dataSource.setMaxIdle(10);
		dataSource.setMaxTotal(25);

	}

	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			try {
				connection = dataSource.getConnection();
				System.out.println("Database Connected");
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from hotelcustomer");
			while (resultSet.next()) {
				System.out.println("\nbookingid: " + resultSet.getInt("booking_id"));
				System.out.println("customername: " + resultSet.getString("customer_name"));
				System.out.println("hotelname: " + resultSet.getString("hotel_name"));
				System.out.println("datevisited: " + resultSet.getDate("date_visited"));
			}
		} finally {

			resultSet.close();
			statement.close();
			connection.close();
		}
	}
}
