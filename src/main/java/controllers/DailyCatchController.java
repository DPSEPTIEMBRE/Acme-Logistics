package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.DailyCatch;
import domain.Fisherman;
import security.LoginService;
import services.DailyCatchService;
import services.FishService;

@Controller
@RequestMapping("/dailycatch")
public class DailyCatchController {

	@Autowired
	DailyCatchService dailycatchService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	FishService fishService;

	@RequestMapping(value = "/fisherman/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		DailyCatch d = dailycatchService.create();
		
		result = createNewModelAndView(d, null);

		return result;
	}

	@RequestMapping(value="/fisherman/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid DailyCatch dailycatch, BindingResult binding) {
	ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(dailycatch, null);
		} else {
			try {
				dailycatchService.save(dailycatch);
				result = new ModelAndView("redirect:/dailycatch/fisherman/list.do");
			} catch (Throwable th) {
				result = createNewModelAndView(dailycatch, "dailycatch.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(DailyCatch dailycatch, String message) {
		ModelAndView result;
		result = new ModelAndView("dailycatch/create");
		result.addObject("dailycatch", dailycatch);
		result.addObject("message", message);
		result.addObject("fishes", fishService.findAll());
		return result;
	}

	@RequestMapping(value = "/fisherman/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Fisherman a  = (Fisherman) loginService.selectSelf();
		
		result = new ModelAndView("dailycatch/list");
		result.addObject("dailycatch", a.getDailyCatchs());
		

		return result;
	}

}
