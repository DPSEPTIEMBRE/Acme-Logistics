package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Transporter;
import security.LoginService;
import services.TransporterService;

@Controller
@RequestMapping("/transporter")
public class TransporterController {

	@Autowired
	TransporterService transporterService;
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/actor/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(transporterService.create(), null);

		return result;
	}

	@RequestMapping(value="/actor/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Transporter transporter, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(transporter, null);
		} else {
			try {
				transporterService.save(transporter);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable th) {
				result = createNewModelAndView(transporter, "transporter.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(Transporter transporter, String message) {
		ModelAndView result;
		result = new ModelAndView("transporter/create");
		result.addObject("transporter", transporter);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/actor/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("transporter/list");
		result.addObject("transporter", transporterService.findAll());

		return result;
	}

	@RequestMapping(value = "/transporter/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		result = new ModelAndView("transporter/edit");
		result.addObject("transporter", loginService.selectSelf());
		return result;
	}

	@RequestMapping(value = "/transporter/edit", method = RequestMethod.POST,params = "delete")
	public ModelAndView deleteEdit(@Valid Transporter transporter) {
		ModelAndView result;

		try {
			transporterService.delete(transporter);
			result = new ModelAndView("redirect:/transporter/list.do");
		} catch (Throwable th) {
			result = createEditModelAndView(transporter, "transporter.commit.error");
		}

		return result;
	}

	@RequestMapping(value="/transporter/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Transporter transporter, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(transporter, null);
		} else {
			try {
				transporterService.save(transporter);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable th) {
				result = createEditModelAndView(transporter, "transporter.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Transporter transporter, String message) {
		ModelAndView result = new ModelAndView("transporter/edit");

		result.addObject("transporter", transporter);
		result.addObject("message", message);

		return result;
	}

}
