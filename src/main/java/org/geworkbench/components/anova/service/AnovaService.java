package org.geworkbench.components.anova.service;

import org.geworkbench.components.anova.AnovaException;
import org.geworkbench.components.anova.data.AnovaInput;
import org.geworkbench.components.anova.data.AnovaOutput;
import org.geworkbench.components.anova.Anova;

public class AnovaService {

	public AnovaOutput execute(AnovaInput input) throws InterruptedException {
		 
		AnovaOutput output = null;
		try {
		try {
			output = new Anova(
					input).execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return output;
	}
}
