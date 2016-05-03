package anovaApp;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.geworkbench.components.anova.Anova;
import org.geworkbench.components.anova.AnovaException;
import org.geworkbench.components.anova.data.AnovaInput;
import org.geworkbench.components.anova.data.AnovaOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
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
	
	private final AtomicLong counter = new AtomicLong();
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
//    @Async("threadPoolTaskExecuter")
    public String showForm(AnovaForm anovaForm) {
        return "form";
    }

    /*
    @RequestMapping("/{0}")
    @ResponseBody
    public AnovaOutput getById(@PathVariable Long id) {
        return output.getById(id);
    }
    */
    
	

	@RequestMapping(value="/", method=RequestMethod.POST)
//    @ResponseBody
//    @Async("threadPoolTaskExecuter")
    public String checkAnovaInfo(@ModelAttribute @Valid AnovaForm anovaForm, Model model, BindingResult bindingResult) throws SQLException, InterruptedException {

    	/*
        if (bindingResult.hasErrors()) {
        	return "form";
        }    
        */
        //Thread.sleep(5000);
    	
        anovaForm.setObservedValues(anovaForm.getObservedValuesFileName());
        
		AnovaInput input = new AnovaInput();
		input.setA(anovaForm.getObservedValues2());
		input.setFalseDiscoveryRateControl(anovaForm.getFalseDiscoveryRateControl());
		input.setFalseSignificantGenesLimit(anovaForm.getFalseSignificantGenesLimit());
		input.setGroupAssignments(anovaForm.groupAssignments);
		input.setNumGenes(anovaForm.getNumGenes());
		input.setNumSelectedGroups(anovaForm.getNumSelectedGroups());
		input.setPermutationsNumber(anovaForm.getPermutationsNumber());
		input.setPValueEstimation(anovaForm.getPValueEstimation());
		input.setPvalueth(anovaForm.getPvalueth());


			Database blankRows = new Database();
			blankRows.createBlankDbRow();
			Anova anova = new Anova(input);
			Database d1 = new Database();
			

			logger.info("Request received");
			MyRunnable runnable = new MyRunnable(anova, d1);
			Thread t = new Thread(runnable, "anova new thread");
			t.start();
			logger.info("Servlet thread released");
			
			/*
			model.addAttribute("featuresIndexes", Arrays.toString(output.getFeaturesIndexes()));
			model.addAttribute("result2DArray", Arrays.deepToString(output.getResult2DArray()));
			model.addAttribute("significances", Arrays.toString(output.getSignificances()));
			*/
			
			model.addAttribute("integerJobId", blankRows.getIntegerJobId());
			

        return "results";
    }
	
    @RequestMapping(value="/results", method=RequestMethod.GET)
//  @Async("threadPoolTaskExecuter")
  public String showResultsForm() {
      return "results";
  }	
    

	

}