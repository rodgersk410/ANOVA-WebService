package anovaApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AnovaDatabase {
	
	int integerJobId;
	
	public Connection getSqlLiteConnection(){	
		Connection session = null;
    try {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:anovaResults.db";
        session = DriverManager.getConnection(dbURL);
        /*Statement st = conn.createStatement();
        st.executeUpdate("CREATE table anovaResultsTable (indexes varchar(10000))");
        */
        
        if (session != null) {
            System.out.println("Connected to the database");
            //conn.close();
        }
    } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
	return session;
	}

	//post the anova calculation result to the db based on integer job id
	public void updateIndexValues(String featuresIndexes, 
			String result2DArray, String significances) throws SQLException{
		
		Connection conn = this.getSqlLiteConnection();
		integerJobId = getIntegerJobId();
		
		//Insert the Anova calculated result into the db
		//TO DO: set status to Complete
		String sql = "UPDATE AnovaResultsTable SET "
				+ "featuresIndexes='" + featuresIndexes + "', result2DArray='" + result2DArray + "',"
						+ "significances='" + significances + "'"
								+ "WHERE integerJobId='" + integerJobId + "';";
		final Logger log = LoggerFactory.getLogger(this.getClass());
		log.info(sql);

		//Prepare statement and execute
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		conn.close();
	}
	
	//when user submits the form, create a new record in the db
	public void createBlankDbRow() throws SQLException{
		
		Connection conn = this.getSqlLiteConnection();
		
		//Creating a blank row to get the integer job id
		//TO DO: set status to Pending
		String sql = "INSERT INTO [AnovaResultsTable] DEFAULT VALUES;";
		final Logger log = LoggerFactory.getLogger(this.getClass());
		log.info(sql);

		//Prepare statement and execute
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		conn.close();
	}
	
	//get integer job id so we can use for updating the table
	public int getIntegerJobId() throws SQLException{
		
		Connection conn = this.getSqlLiteConnection();
		
		//TO DO: Query the db for the integer id
		String sql = "SELECT COUNT(*) FROM AnovaResultsTable;";
		final Logger log = LoggerFactory.getLogger(this.getClass());
		log.info(sql);

		//Prepare statement, execute, and get the integer job id
		PreparedStatement ps = conn.prepareStatement(sql);
		integerJobId = ps.executeQuery().getInt(1);
		conn.close();
		log.info("Integer Job Id " + integerJobId);
		
		return integerJobId;
	}
	
}
