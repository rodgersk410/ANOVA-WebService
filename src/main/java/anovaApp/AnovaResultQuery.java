package anovaApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnovaResultQuery {

	private int integerJobId;
	private String featuresIndexes;
	private String result2DArray;
	private String significances;
	
	public int getIntegerJobId() {
		return integerJobId;
	}
	
	public void setIntegerJobId(int integerJobId) {
		this.integerJobId = integerJobId;
	}
	
	public String queryFeaturesIndexes(int integerJobId) throws SQLException{
		AnovaDatabase db = new AnovaDatabase();
		Connection conn = db.getSqlLiteConnection();
		
		String sql = "SELECT featuresIndexes FROM AnovaResultsTable "
				+ "WHERE integerJobId = " + integerJobId + ";";
		//TO DO: set status to Complete
		final Logger log = LoggerFactory.getLogger(this.getClass());
		log.info(sql);

		PreparedStatement ps = conn.prepareStatement(sql);
		featuresIndexes = ps.executeQuery().getString(1);
		conn.close();
		log.info("feature indexes: " + featuresIndexes);
		
		return featuresIndexes;
	}
	
	public String queryResult2DArray(int integerJobId) throws SQLException{
		AnovaDatabase db = new AnovaDatabase();
		Connection conn = db.getSqlLiteConnection();
		
		String sql = "SELECT result2DArray FROM AnovaResultsTable "
				+ "WHERE integerJobId = " + integerJobId + ";";
		//TO DO: set status to Complete
		final Logger log = LoggerFactory.getLogger(this.getClass());
		log.info(sql);

		PreparedStatement ps = conn.prepareStatement(sql);
		result2DArray = ps.executeQuery().getString(1);
		conn.close();
		log.info("2d array: " + result2DArray);
		
		return result2DArray;
	}
	
	public String querySignificances(int integerJobId) throws SQLException{
		AnovaDatabase db = new AnovaDatabase();
		Connection conn = db.getSqlLiteConnection();
		
		String sql = "SELECT significances FROM AnovaResultsTable "
				+ "WHERE integerJobId = " + integerJobId + ";";
		//TO DO: set status to Complete
		final Logger log = LoggerFactory.getLogger(this.getClass());
		log.info(sql);

		PreparedStatement ps = conn.prepareStatement(sql);
		significances = ps.executeQuery().getString(1);
		conn.close();
		log.info("Significances: " + significances);
		
		return significances;
	}
}
