package anovaApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * The purpose of this class is to query the database for the important output field values so that
 * I can use them in any operation or view
 */
public class AnovaResultQuery {

	private int jobId;
	private String featuresIndexes;
	private String result2DArray;
	private String significances;
	
	public int getJobId() {
		return jobId;
	}
	
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	/*
	 * The following are get methods for fields: featuresIndexes, result2DArray, significances
	 * Each takes a given input jobId and returns the associated field value
	 */
	
	public String queryFeaturesIndexes(int jobId) throws SQLException{
		AnovaDatabase db = new AnovaDatabase();
		Connection conn = db.getSqlLiteConnection();
		
		String sql = "SELECT featuresIndexes FROM AnovaResultsTable "
				+ "WHERE jobId = " + jobId + ";";

		PreparedStatement ps = conn.prepareStatement(sql);
		featuresIndexes = ps.executeQuery().getString(1);
		conn.close();
		
		return featuresIndexes;
	}
	
	public String queryResult2DArray(int jobId) throws SQLException{
		AnovaDatabase db = new AnovaDatabase();
		Connection conn = db.getSqlLiteConnection();
		
		String sql = "SELECT result2DArray FROM AnovaResultsTable "
				+ "WHERE jobId = " + jobId + ";";

		PreparedStatement ps = conn.prepareStatement(sql);
		result2DArray = ps.executeQuery().getString(1);
		conn.close();
		
		return result2DArray;
	}
	
	public String querySignificances(int jobId) throws SQLException{
		AnovaDatabase db = new AnovaDatabase();
		Connection conn = db.getSqlLiteConnection();
		
		String sql = "SELECT significances FROM AnovaResultsTable "
				+ "WHERE jobId = " + jobId + ";";

		PreparedStatement ps = conn.prepareStatement(sql);
		significances = ps.executeQuery().getString(1);
		conn.close();
		
		return significances;
	}
}
