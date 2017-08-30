package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Wall;
import services.WallService;

@Controller
@RequestMapping("/wall")
public class WallController {

	@Autowired
	WallService wallService;

	@RequestMapping(value = "/actor/view", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) Actor q) {
		ModelAndView result;

		result = new ModelAndView("wall/view");
		result.addObject("wall", q == null ? wallService.selectSelfWall() : q.getWall());

		return result;
	}

	@RequestMapping(value = "/actor/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Wall q) {
		ModelAndView result;
		result = new ModelAndView("wall/edit");
		result.addObject("wall", q);
		return result;
	}

	@RequestMapping(value="/actor/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Wall wall, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(wall, null);
		} else {
			try {
				wallService.save(wall);
				result = new ModelAndView("redirect:/wall/actor/view.do");
			} catch (Throwable th) {
				result = createEditModelAndView(wall, "wall.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Wall wall, String message) {
		ModelAndView result = new ModelAndView("wall/edit");

		result.addObject("wall", wall);
		result.addObject("message", message);

		return result;
	}

}
