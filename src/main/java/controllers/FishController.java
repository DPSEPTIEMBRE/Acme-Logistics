package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Fish;
import services.FishService;

@Controller
@RequestMapping("/fish")
public class FishController {

	@Autowired
	FishService fishService;

	@RequestMapping(value = "/administrator/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(fishService.create(), null);

		return result;
	}

	@RequestMapping(value="/administrator/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Fish fish, BindingResult binding) {
	ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(fish, null);
		} else {
			try {
				fishService.save(fish);
				result = new ModelAndView("redirect:/fish/actor/list.do");
			} catch (Throwable th) {
				result = createEditModelAndView(fish, "fish.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(Fish fish, String message) {
		ModelAndView result;
		result = new ModelAndView("fish/create");
		result.addObject("fish", fish);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/actor/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("fish/list");
		result.addObject("fish", fishService.findAll());

		return result;
	}
	
	@RequestMapping(value = "/actor/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam Fish q) {
		ModelAndView result;

		result = new ModelAndView("fish/view");
		result.addObject("fish", q);

		return result;
	}

	@RequestMapping(value = "/administrator/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Fish q) {
		ModelAndView result;
		result = new ModelAndView("fish/edit");
		result.addObject("fish", q);
		return result;
	}

	@RequestMapping(value="/administrator/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Fish fish, BindingResult binding) {
	ModelAndView result;
	if (binding.hasErrors()) {
		result = createEditModelAndView(fish, null);
	} else {
		try {
			fishService.save(fish);
				result = new ModelAndView("redirect:/fish/actor/list.do");
			} catch (Throwable th) {
				result = createEditModelAndView(fish, "fish.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Fish fish, String message) {
		ModelAndView result = new ModelAndView("fish/edit");

		result.addObject("fish", fish);
		result.addObject("message", message);

		return result;
	}

}
