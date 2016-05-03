package anovaApp;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;
import javax.validation.Valid;
import org.geworkbench.components.anova.Anova;
import org.geworkbench.components.anova.data.AnovaInput;
import org.geworkbench.components.anova.data.AnovaOutput;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class WebController extends WebMvcConfigurerAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showForm(AnovaForm anovaForm) {
		return "form";
	}

	/*
	 * @RequestMapping("/{0}")
	 * 
	 * @ResponseBody public AnovaOutput getById(@PathVariable Long id) { return
	 * output.getById(id); }
	 */

	@RequestMapping(value = "/", method = RequestMethod.POST)
	// @ResponseBody
	public String checkAnovaInfo(@ModelAttribute @Valid AnovaForm anovaForm, Model model, BindingResult bindingResult)
			throws SQLException, InterruptedException {

		//if (bindingResult.hasErrors()) { return "form"; }

		// read the file and populate the 2D Array
		anovaForm.setObservedValues(anovaForm.getObservedValuesFileName());

		// create an AnovaInput object and apply the form's values to it
		AnovaInput input = new AnovaInput();
		input.setA(anovaForm.getobservedValuesArray());
		input.setFalseDiscoveryRateControl(anovaForm.getFalseDiscoveryRateControl());
		input.setFalseSignificantGenesLimit(anovaForm.getFalseSignificantGenesLimit());
		input.setGroupAssignments(anovaForm.groupAssignments);
		input.setNumGenes(anovaForm.getNumGenes());
		input.setNumSelectedGroups(anovaForm.getNumSelectedGroups());
		input.setPermutationsNumber(anovaForm.getPermutationsNumber());
		input.setPValueEstimation(anovaForm.getPValueEstimation());
		input.setPvalueth(anovaForm.getPvalueth());

		// create a new in the database to get the integer job id
		// add the integer job id to the model so it can be shown in the view
		Database blankRows = new Database();
		blankRows.createBlankDbRow();
		model.addAttribute("integerJobId", blankRows.getIntegerJobId());
		
		// create and Anova output object with the input as a parameter for execution.
		// open database connection to send data once execution is done
		// process this calculation on a new thread
		Anova anova = new Anova(input);
		Database d1 = new Database();
		logger.info("Before new thread is created for Anova calculation");
		MyRunnable runnable = new MyRunnable(anova, d1);
		Thread t = new Thread(runnable, "anova new thread");
		t.start();
		logger.info("Current thread continues. Anova calculation is running on another thread");

		/*
		 * model.addAttribute("featuresIndexes",
		 * Arrays.toString(output.getFeaturesIndexes()));
		 * model.addAttribute("result2DArray",
		 * Arrays.deepToString(output.getResult2DArray()));
		 * model.addAttribute("significances",
		 * Arrays.toString(output.getSignificances()));
		 */

		return "results";
	}

	@RequestMapping(value = "/Query", method = RequestMethod.GET)
	public String showQueryForm(AnovaResultQuery anovaResultQuery) {
		return "InputQueryForm";
	}

	@RequestMapping(value = "/Query", method = RequestMethod.POST)
	public String queryResults(@ModelAttribute AnovaResultQuery anovaResultQuery, Model model) {
		try {
			model.addAttribute("featuresIndexes",
					anovaResultQuery.queryFeaturesIndexes(anovaResultQuery.getIntegerJobId()));
			model.addAttribute("result2DArray",
					anovaResultQuery.queryResult2DArray(anovaResultQuery.getIntegerJobId()));
			model.addAttribute("significances",
					anovaResultQuery.querySignificances(anovaResultQuery.getIntegerJobId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Query";
	}

}