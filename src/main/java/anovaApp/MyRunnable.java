package anovaApp;

import java.sql.SQLException;
import java.util.Arrays;

import org.geworkbench.components.anova.Anova;
import org.geworkbench.components.anova.AnovaException;
import org.geworkbench.components.anova.data.AnovaOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRunnable implements Runnable {
	
	Anova anova = null;
	AnovaOutput output = null;
	Database d1;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public MyRunnable(Anova anova, Database d1){
		this.anova = anova;
		this.d1 = d1;
	}

    public void run(){
    	try {
			output = this.anova.execute();
			d1.insertIndexValues(Arrays.toString(output.getFeaturesIndexes()), 
					Arrays.deepToString(output.getResult2DArray()), 
					Arrays.toString(output.getSignificances()));
		} catch (AnovaException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  }