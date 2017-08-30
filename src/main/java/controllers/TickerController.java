package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Ticker;
import services.TickerService;

@Controller
@RequestMapping("/ticker")
public class TickerController {

	@Autowired
	TickerService tickerService;

	@RequestMapping(value = "/buyer/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("ticker/list");
		result.addObject("ticker", tickerService.selectSelf());

		return result;
	}
	
	@RequestMapping(value = "/buyer/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam Ticker q) {
		ModelAndView result;

		result = new ModelAndView("ticker/view");
		result.addObject("ticker", q);

		return result;
	}

}
