package anovaApp;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Controller
public class WebController extends WebMvcConfigurerAdapter {

	  private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String showForm(AnovaForm anovaForm) {
        return "form";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String checkAnovaInfo(@Valid AnovaForm anovaForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
          log.info("SOMETHING WENT WRONG");
        	return "form";
        }

        anovaForm.setObservedValues(anovaForm.getObservedValuesFileName());
        log.info("SUCCSESFULLY DID THAT THING");
        log.info(anovaForm.getObservedValues().get(0)==null ? "was null" : anovaForm.getObservedValues().get(0));
        return "redirect:/results";
    }
}