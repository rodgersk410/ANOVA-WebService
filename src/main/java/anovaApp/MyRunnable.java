package anovaApp;

import org.geworkbench.components.anova.Anova;
import org.geworkbench.components.anova.AnovaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRunnable implements Runnable {
	
	Anova anova = null;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public MyRunnable(Anova anova){
		this.anova = anova;
	}

    public void run(){
    	try {
			this.anova.execute();
		} catch (AnovaException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
    }
  }