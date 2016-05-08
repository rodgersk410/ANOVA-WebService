package anovaApp;

import java.sql.SQLException;
import javax.validation.Valid;
import org.geworkbench.components.anova.Anova;
import org.geworkbench.components.anova.data.AnovaInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class AnovaWebController extends WebMvcConfigurerAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showForm(AnovaForm anovaForm, AnovaResultQuery anovaResultQuery) {
		return "form";
	}

	@RequestMapping(value = "/confirmation", method = RequestMethod.POST, params = "calculate")
	public String calculateAnova(AnovaForm anovaForm, Model model, BindingResult bindingResult)
			throws SQLException, InterruptedException {

		// if (bindingResult.hasErrors()) { return "form"; }

		// read the file and populate the 2D Array
		anovaForm.setObservedValues(anovaForm.getObservedValuesFileName());

		// create an AnovaInput object and apply the form's values to it
		AnovaInput input = new AnovaInput();
		input.setA(anovaForm.getObservedValuesArray());
		input.setFalseDiscoveryRateControl(anovaForm.getFalseDiscoveryRateControl());
		input.setFalseSignificantGenesLimit(anovaForm.getFalseSignificantGenesLimit());
		input.setGroupAssignments(anovaForm.groupAssignments);
		input.setNumGenes(anovaForm.getNumGenes());
		input.setNumSelectedGroups(anovaForm.getNumSelectedGroups());
		input.setPermutationsNumber(anovaForm.getPermutationsNumber());
		input.setPValueEstimation(anovaForm.getPValueEstimation());
		input.setPvalueth(anovaForm.getPvalueth());

		/*
		 * create a new record in the database for the transaction and get the
		 * value, intergerJobId, as a confirmation number
		 */
		AnovaDatabase newAnovaOutputRecord = new AnovaDatabase();
		newAnovaOutputRecord.createBlankDbRow();
		model.addAttribute("jobId", newAnovaOutputRecord.getJobId());

		/*
		 * create an Anova output object with the input as a parameter for
		 * execution. open database connection to send data once execution is
		 * done process this calculation on a new thread
		 */
		Anova anova = new Anova(input);
		AnovaDatabase d1 = new AnovaDatabase();
		logger.info("Before new thread is created for Anova calculation");
		MyRunnable runnable = new MyRunnable(anova, d1);
		Thread t = new Thread(runnable, "anova new thread");
		t.start();
		logger.info("Current thread continues. Anova calculation is running on another thread");

		return "confirmation";
	}

	// control the query action
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String queryResults(AnovaResultQuery anovaResultQuery, Model model, BindingResult bindingResult2) {
		// query the database based on the jobId and add the resulting data to
		// the model
		try {
			model.addAttribute("featuresIndexes", anovaResultQuery.queryFeaturesIndexes(anovaResultQuery.getJobId()));
			model.addAttribute("result2DArray", anovaResultQuery.queryResult2DArray(anovaResultQuery.getJobId()));
			model.addAttribute("significances", anovaResultQuery.querySignificances(anovaResultQuery.getJobId()));
			model.addAttribute("jobId", anovaResultQuery.getJobId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "query";
	}

}