package anovaApp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Database {
	
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
	
	public Integer getIntegerJobId(){
		Connection conn = this.getSqlLiteConnection();
		
		//TO DO: Check if there are records in the db
		String sqlQueryTableCount = "Select COUNT(*) from AnovaResultsTable";
		PreparedStatement TableCountQuery = conn.prepareStatement(sqlQueryTableCount);
		int TableCountResult = TableCountQuery.executeQuery(sqlQueryTableCount).getInt(0);
		
		if(TableCountResult == 0){
			//
			integerJobId = 1;
		}
		else{
			integerJobId = TableCountResult + 1;
		}
		return integerJobId;
	}
	
	// TO DO: insert the Integer Id in the db before the calculation
	// public insertIntegerId(){
	
	//insert IntegerId
	//set status to 'Pending'
//}

	public void insertIndexValues(String featuresIndexes, 
			String result2DArray, String significances) throws SQLException{
		
		Connection conn = this.getSqlLiteConnection();
		
		//TO DO: Query the db for the integer id
		String sql = "INSERT INTO AnovaResultsTable "
				+ "(featuresIndexes, result2DArray, significances) "
				+ "VALUES ('" + featuresIndexes + "','" + result2DArray + "','" + significances + "');";
		//TO DO: set status to Complete
		final Logger log = LoggerFactory.getLogger(this.getClass());
		log.info(sql);

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		conn.close();
	}
	
}
