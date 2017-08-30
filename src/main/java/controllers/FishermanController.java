package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Fisherman;
import security.LoginService;
import services.FishermanService;

@Controller
@RequestMapping("/fisherman")
public class FishermanController {

	@Autowired
	FishermanService fishermanService;
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/actor/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(fishermanService.create(), null);

		return result;
	}

	@RequestMapping(value="/actor/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Fisherman fisherman, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createNewModelAndView(fisherman, null);
		} else {
			try {
				fishermanService.save(fisherman);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable th) {
				th.printStackTrace();
				result = createNewModelAndView(fisherman, "fisherman.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(Fisherman fisherman, String message) {
		ModelAndView result;
		result = new ModelAndView("fisherman/create");
		result.addObject("fisherman", fisherman);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/actor/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("fisherman/list");
		result.addObject("fisherman", fishermanService.findAll());

		return result;
	}

	@RequestMapping(value = "/fisherman/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		result = new ModelAndView("fisherman/edit");
		result.addObject("fisherman", loginService.selectSelf());
		return result;
	}

	@RequestMapping(value = "/fisherman/edit", method = RequestMethod.POST,params = "delete")
	public ModelAndView deleteEdit(@Valid Fisherman fisherman) {
		ModelAndView result;

		try {
			fishermanService.delete(fisherman);
			result = new ModelAndView("redirect:/fisherman/list.do");
		} catch (Throwable th) {
			result = createEditModelAndView(fisherman, "fisherman.commit.error");
		}

		return result;
	}

	@RequestMapping(value="/fisherman/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Fisherman fisherman, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(fisherman, null);
		} else {
			try {
				fishermanService.save(fisherman);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable th) {
				result = createEditModelAndView(fisherman, "fisherman.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Fisherman fisherman, String message) {
		ModelAndView result = new ModelAndView("fisherman/edit");

		result.addObject("fisherman", fisherman);
		result.addObject("message", message);

		return result;
	}

}
