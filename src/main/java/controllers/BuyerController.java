package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Buyer;
import security.LoginService;
import services.BuyerService;

@Controller
@RequestMapping("/buyer")
public class BuyerController {

	@Autowired
	BuyerService buyerService;
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/actor/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(buyerService.create(), null);

		return result;
	}

	@RequestMapping(value="/actor/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Buyer buyer, BindingResult binding) {
	ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(buyer, null);
		} else {
			try {
				buyerService.save(buyer);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable th) {
				result = createNewModelAndView(buyer, "buyer.commit.error");
			}
		}
		
		return result;
	}

	protected ModelAndView createNewModelAndView(Buyer buyer, String message) {
		ModelAndView result;
		result = new ModelAndView("buyer/create");
		result.addObject("buyer", buyer);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/actor/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("buyer/list");
		result.addObject("buyer", buyerService.findAll());

		return result;
	}

	@RequestMapping(value = "/buyer/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		result = new ModelAndView("buyer/edit");
		result.addObject("buyer", loginService.selectSelf());
		return result;
	}

	@RequestMapping(value = "/buyer/edit", method = RequestMethod.POST,params = "delete")
	public ModelAndView deleteEdit(@Valid Buyer buyer) {
		ModelAndView result;

		try {
			buyerService.delete(buyer);
			result = new ModelAndView("redirect:/buyer/list.do");
		} catch (Throwable th) {
			result = createEditModelAndView(buyer, "buyer.commit.error");
		}

		return result;
	}

	@RequestMapping(value="/buyer/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Buyer buyer, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(buyer, null);
		} else {
			try {
				buyerService.save(buyer);
				result = new ModelAndView("redirect:/buyer/list.do");
			} catch (Throwable th) {
				result = createEditModelAndView(buyer, "buyer.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Buyer buyer, String message) {
		ModelAndView result = new ModelAndView("buyer/edit");

		result.addObject("buyer", buyer);
		result.addObject("message", message);

		return result;
	}

}
