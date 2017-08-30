/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Administrator;
import security.LoginService;
import services.AdministratorService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------

	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private LoginService loginService;
	
	public AdministratorController() {
		super();
	}

	@RequestMapping("/administrator/dashboard")
	public ModelAndView dashboard() {
		ModelAndView result;

		result = new ModelAndView("administrator/dashboard");
		
		result.addObject("avgMaxMinMessagesSentByActor", administratorService.avgMaxMinMessagesSentByActor());
		result.addObject("avgMaxMinMessagesReceivedByActor", administratorService.avgMaxMinMessagesReceivedByActor());
		result.addObject("avgMaxMinMarketsPublisheDaily", administratorService.avgMaxMinMarketsPublisheDaily());
		result.addObject("avgMaxMinMarkets", administratorService.avgMaxMinMarkets());
		result.addObject("ratioStaticMarkets", administratorService.ratioStaticMarkets());
		result.addObject("numberFisherman", administratorService.numberFisherman());
		result.addObject("numberBuyer", administratorService.numberBuyer());
		result.addObject("numberBussinesman", administratorService.numBussinesman());
		result.addObject("numberTransporter", administratorService.numTransporter());
		result.addObject("avgMaxMinOrders", administratorService.avgMaxMinOrders());
		result.addObject("avgMaxMinOrdersDaily", administratorService.avgMaxMinOrdersDaily());
		result.addObject("avgMaxMinPropertiesFish", administratorService.avgMaxMinPropertiesFish());

		return result;
	}
	
	@RequestMapping(value = "/administrator/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		result = new ModelAndView("administrator/edit");
		result.addObject("administrator", loginService.selectSelf());
		return result;
	}

	@RequestMapping(value="/administrator/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Administrator administrator, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(administrator, null);
		} else {
			try {
				administratorService.save(administrator);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable th) {
				result = createEditModelAndView(administrator, "administrator.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Administrator businessman, String message) {
		ModelAndView result = new ModelAndView("administrator/edit");

		result.addObject("administrator", businessman);
		result.addObject("message", message);

		return result;
	}

}
