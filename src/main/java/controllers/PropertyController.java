package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Fish;
import domain.Property;
import services.PropertyService;

@Controller
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	PropertyService propertyService;

	@RequestMapping(value = "/administrator/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, @RequestParam Fish q) {
		ModelAndView result;

		request.getSession().setAttribute("fish_id", q.getId());
		result = createNewModelAndView(propertyService.create(), null);

		return result;
	}

	@RequestMapping(value="/administrator/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(HttpServletRequest request, @Valid Property property, BindingResult binding) {
	ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(property, null);
		} else {
			try {
				int fish_id = (int) request.getSession().getAttribute("fish_id");
				
				propertyService.save(property, fish_id);
				result = new ModelAndView("redirect:/fish/administrator/edit.do?q=" + fish_id);
				
				request.getSession().removeAttribute("fish_id");
			} catch (Throwable th) {
				result = createNewModelAndView(property, "property.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(Property property, String message) {
		ModelAndView result;
		result = new ModelAndView("property/create");
		result.addObject("property", property);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/administrator/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("property/list");
		result.addObject("property", propertyService.findAll());

		return result;
	}
	
	@RequestMapping(value = "/administrator/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam Property q, @RequestParam Fish f) {
		ModelAndView result;

		propertyService.delete(q, f);
		result = new ModelAndView("redirect:/fish/administrator/edit.do?q=" + f.getId());

		return result;
	}
}
