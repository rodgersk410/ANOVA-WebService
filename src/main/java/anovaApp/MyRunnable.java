package anovaApp;

import java.sql.SQLException;
import java.util.Arrays;

import org.geworkbench.components.anova.Anova;
import org.geworkbench.components.anova.AnovaException;
import org.geworkbench.components.anova.data.AnovaOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*/
 * This class allows the ANOVA calculation to run on another thread.
 * It implements the Runnable Interface (run method) to do so
 */
public class MyRunnable implements Runnable {

	Anova anova = null;
	AnovaOutput output = null;
	AnovaDatabase d1;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * Construct this method and pass in the anova dataset/db connection
	 */
	public MyRunnable(Anova anova, AnovaDatabase d1) {
		this.anova = anova;
		this.d1 = d1;
	}

	/*
	 *  calculate the anova result and post it to the database
	 */
	public void run() {
		try {
			output = this.anova.execute();
			d1.updateIndexValues(Arrays.toString(output.getFeaturesIndexes()),
					Arrays.deepToString(output.getResult2DArray()), Arrays.toString(output.getSignificances()));
			logger.info("output" + output);
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