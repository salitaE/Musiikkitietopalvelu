package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private static final String URL = "jdbc:sqlite:C:\\Koodijutut\\sqlite\\ChinookData.sqlite";
	//private static final String URL = "JDBC_DATABASE_URL";
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(URL);
	}
	
	public void close(Connection connection, PreparedStatement statement, ResultSet results) {
		closeResultSet(results);
		closeStatement(statement);
		closeConnection(connection);
	}
	
	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void closeStatement(PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void closeResultSet(ResultSet results) {
		if (results != null) {
			try {
				results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}



}
