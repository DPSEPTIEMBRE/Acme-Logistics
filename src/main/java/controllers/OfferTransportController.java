package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.OfferTransport;
import services.OfferTransportService;

@Controller
@RequestMapping("/offertransport")
public class OfferTransportController {

	@Autowired
	OfferTransportService offertransportService;

	@RequestMapping(value = "/transporter/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(offertransportService.create(), null);

		return result;
	}

	@RequestMapping(value="/transporter/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid OfferTransport offertransport, BindingResult binding) {
		
		for(FieldError e : binding.getFieldErrors()) {
			System.out.println(e.getField() + " , " + e.getDefaultMessage());
		}
		
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(offertransport, null);
		} else {
			try {
				offertransportService.save(offertransport);
				result = new ModelAndView("redirect:/offertransport/transporter/list.do");
			} catch (Throwable th) {
				result = createNewModelAndView(offertransport, "offertransport.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(OfferTransport offertransport, String message) {
		ModelAndView result;
		result = new ModelAndView("offertransport/create");
		result.addObject("offertransport", offertransport);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/transporter/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("offertransport/list");
		result.addObject("offertransport", offertransportService.selectSelf());

		return result;
	}
	
	@RequestMapping(value = "/transporter/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam OfferTransport q) {
		ModelAndView result;

		result = new ModelAndView("offertransport/view");
		result.addObject("offertransport", q);

		return result;
	}
	
	@RequestMapping(value = "/transporter/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam OfferTransport q) {
		ModelAndView result;

		offertransportService.delete(q);
		result = new ModelAndView("redirect:/offertransport/transporter/list.do");

		return result;
	}

}
