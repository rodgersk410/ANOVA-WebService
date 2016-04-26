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
            DatabaseMetaData dm = (DatabaseMetaData) session.getMetaData();
            System.out.println("Driver name: " + dm.getDriverName());
            System.out.println("Driver version: " + dm.getDriverVersion());
            System.out.println("Product name: " + dm.getDatabaseProductName());
            System.out.println("Product version: " + dm.getDatabaseProductVersion());
            //conn.close();
        }
    } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
	return session;
	}

	public void insertIndexValues(String featuresIndexes) throws SQLException{
		Connection conn = this.getSqlLiteConnection();
		String sql = "INSERT INTO AnovaResultsTable (featuresIndexes) VALUES ('" + featuresIndexes + "');";
		final Logger log = LoggerFactory.getLogger(this.getClass());
		log.info(sql);

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		conn.close();
	}
	
}
