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

import domain.BusinessOrder;
import domain.OfferMarket;
import services.BusinessOrderService;
import services.FishService;
import services.OfferTransportService;

@Controller
@RequestMapping("/businessorder")
public class BusinessOrderController {

	@Autowired
	BusinessOrderService businessorderService;
	@Autowired
	OfferTransportService offerTransportService;
	@Autowired
	FishService fishService;
	
	@RequestMapping(value = "/businessman/delivered", method = RequestMethod.GET)
	public ModelAndView delivered(@RequestParam BusinessOrder q) {
		ModelAndView result;

		businessorderService.setDelivered(q);
		result = new ModelAndView("redirect:/businessorder/actor/view.do?q=" + q.getId());

		return result;
	}
	
	@RequestMapping(value = "/businessman/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, @RequestParam OfferMarket q) {
		ModelAndView result;

		result = createNewModelAndView(businessorderService.create(q), null);
		result.addObject("offerTransport", offerTransportService.getAvaliableTransports());
		
		request.getSession().setAttribute("isStatic", q.getIsEstatic());

		return result;
	}

	@RequestMapping(value="/businessman/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(HttpServletRequest request, @Valid BusinessOrder businessorder, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(businessorder, null);
		} else {
			try {
				boolean isStatic = (boolean) request.getSession().getAttribute("isStatic");
				
				result = new ModelAndView("redirect:/businessorder/actor/view.do?q=" + businessorderService.save(businessorder, isStatic).getId());
			} catch (Throwable th) {
				result = createNewModelAndView(businessorder, "businessorder.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(BusinessOrder businessorder, String message) {
		ModelAndView result;
		result = new ModelAndView("businessorder/create");
		result.addObject("businessorder", businessorder);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/actor/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("businessorder/list");
		result.addObject("businessorder", businessorderService.selectSelf());

		return result;
	}
	
	@RequestMapping(value = "/actor/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam BusinessOrder q) {
		ModelAndView result;

		result = new ModelAndView("businessorder/view");
		result.addObject("businessorder", q);

		return result;
	}
}
