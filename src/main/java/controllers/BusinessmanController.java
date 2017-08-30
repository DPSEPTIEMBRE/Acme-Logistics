package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Businessman;
import security.LoginService;
import services.BusinessmanService;

@Controller
@RequestMapping("/businessman")
public class BusinessmanController {

	@Autowired
	BusinessmanService businessmanService;
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/actor/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(businessmanService.create(), null);

		return result;
	}

	@RequestMapping(value="/actor/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Businessman businessman, BindingResult binding) {
	ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(businessman, null);
		} else {
			try {
				businessmanService.save(businessman);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable th) {
				th.printStackTrace();
				result = createNewModelAndView(businessman, "businessman.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(Businessman businessman, String message) {
		ModelAndView result;
		result = new ModelAndView("businessman/create");
		result.addObject("businessman", businessman);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/actor/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("businessman/list");
		result.addObject("businessman", businessmanService.findAll());

		return result;
	}

	@RequestMapping(value = "/businessman/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		result = new ModelAndView("businessman/edit");
		result.addObject("businessman", loginService.selectSelf());
		return result;
	}

	@RequestMapping(value="/businessman/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Businessman businessman, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(businessman, null);
		} else {
			try {
				businessmanService.save(businessman);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable th) {
				result = createEditModelAndView(businessman, "businessman.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Businessman businessman, String message) {
		ModelAndView result = new ModelAndView("businessman/edit");

		result.addObject("businessman", businessman);
		result.addObject("message", message);

		return result;
	}

}
