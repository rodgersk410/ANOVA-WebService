package anovaApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnovaDatabase {

	int jobId;

	//get connection to SQLite database
	public Connection getSqlLiteConnection() {
		Connection session = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:anovaResults.db";
			session = DriverManager.getConnection(dbURL);

			if (session != null) {
				System.out.println("Connected to the database");
				// conn.close();
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return session;
	}

	// post the anova calculation result to the db based on job id
	public void updateIndexValues(String featuresIndexes, String result2DArray, String significances)
			throws SQLException {

		Connection conn = this.getSqlLiteConnection();
		jobId = getJobId();

		// Insert the Anova calculated result into the db
		String sql = "UPDATE AnovaResultsTable SET " + "featuresIndexes='" + featuresIndexes + "', result2DArray='"
				+ result2DArray + "'," + "significances='" + significances + "'" + "WHERE jobId='" + jobId + "';";

		// Prepare statement and execute
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		conn.close();
	}

	// when user submits the form, create a new record in the db (job id increments by default)
	public void createBlankDbRow() throws SQLException {

		Connection conn = this.getSqlLiteConnection();

		// Creating a blank row to get the integer job id
		String sql = "INSERT INTO [AnovaResultsTable] DEFAULT VALUES;";

		// Prepare statement and execute
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		conn.close();
	}

	// get job id so we can use for updating the table
	public int getJobId() throws SQLException {

		Connection conn = this.getSqlLiteConnection();

		// Get count from AnovaResultsTable to set as the job id
		String sql = "SELECT COUNT(*) FROM AnovaResultsTable;";

		// Prepare statement, execute, and get the integer job id
		PreparedStatement ps = conn.prepareStatement(sql);
		jobId = ps.executeQuery().getInt(1);
		conn.close();

		return jobId;
	}

}
