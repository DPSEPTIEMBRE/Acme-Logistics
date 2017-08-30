package controllers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import domain.OfferMarket;
import services.OfferMarketService;

@Controller
@RequestMapping("/offermarket")
public class OfferMarketController {

	@Autowired
	OfferMarketService offermarketService;
	@Autowired
	ViewResolver viewResolver;
	
	@RequestMapping(value = "/actor/favourite", method = RequestMethod.GET)
	public ModelAndView favourite(@RequestParam OfferMarket q) {
		ModelAndView result;

		offermarketService.favourite(q);
		result = new ModelAndView("redirect:/wall/actor/view.do");

		return result;
	}
	
	@RequestMapping(value = "/actor/view-offer", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam OfferMarket q) {
		ModelAndView result;

		result = new ModelAndView("offermarket/view");
		result.addObject("offermarket", q);

		return result;
	}
	
	@RequestMapping(value = "/buyer/buy", method = RequestMethod.GET)
	public ModelAndView buy(@RequestParam OfferMarket q) {
		ModelAndView result;

		offermarketService.buy(q);
		result = new ModelAndView("redirect:/ticker/buyer/list.do");

		return result;
	}

	@RequestMapping(value = "/fisherman/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(offermarketService.create(), null);

		return result;
	}
	
	@ResponseBody
	@RequestMapping("/actor/view/async")
	public String view_async(HttpServletRequest request, Locale locale, @RequestParam OfferMarket q) {
		JSONObject jobj = new JSONObject();
		jobj.put("status", q == null ? 400 : 200);
		
		if(q != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("offermarket", q);
			
			MockHttpServletResponse resp = new MockHttpServletResponse();
			
			try {
				View v = viewResolver.resolveViewName("offermarket/view_async", locale);
				v.render(map, request, resp);
				
				jobj.put("data", resp.getContentAsString());
			} catch (Exception e) {
				jobj.put("status", 400);
			}
		}
		
		return jobj.toString();
	}
	
	@ResponseBody
	@RequestMapping("/actor/search/async")
	public String view_async(HttpServletRequest request, Locale locale, @RequestParam(required = false) String q) {
		JSONObject jobj = new JSONObject();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offermarket", offermarketService.search(q));
		
		MockHttpServletResponse resp = new MockHttpServletResponse();
		
		try {
			View v = viewResolver.resolveViewName("offermarket/search_async", locale);
			v.render(map, request, resp);
			
			jobj.put("status", 200);
			jobj.put("data", resp.getContentAsString());
		} catch (Exception e) {
			jobj.put("status", 400);
		}
		
		return jobj.toString();
	}
	
	@RequestMapping(value = "/actor/search", method = RequestMethod.GET)
	public ModelAndView search() {
		ModelAndView result;
		
		result = new ModelAndView("offermarket/search");
		
		return result;
	}

	@RequestMapping(value="/fisherman/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid OfferMarket offermarket, BindingResult binding) {
	ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(offermarket, null);
		} else {
			try {
				offermarketService.save(offermarket);
				result = new ModelAndView("redirect:/offermarket/list.do");
			} catch (Throwable th) {
				result = createEditModelAndView(offermarket, "offermarket.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(OfferMarket offermarket, String message) {
		ModelAndView result;
		result = new ModelAndView("offermarket/create");
		result.addObject("offermarket", offermarket);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/fisherman/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("offermarket/list");
		result.addObject("offermarket", offermarketService.selectSelfOffers());

		return result;
	}

	@RequestMapping(value = "/fisherman/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam OfferMarket offermarket) {
		ModelAndView result;
		result = new ModelAndView("offermarket/edit");
		result.addObject("offermarket", offermarket);
		return result;
	}

	@RequestMapping(value = "/fisherman/edit", method = RequestMethod.POST,params = "delete")
	public ModelAndView deleteEdit(@Valid OfferMarket offermarket) {
		ModelAndView result;

		try {
			offermarketService.delete(offermarket);
			result = new ModelAndView("redirect:/offermarket/list.do");
		} catch (Throwable th) {
			result = createEditModelAndView(offermarket, "offermarket.commit.error");
		}

		return result;
	}

	@RequestMapping(value="/fisherman/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid OfferMarket offermarket, BindingResult binding) {
	ModelAndView result;
	if (binding.hasErrors()) {
		result = createEditModelAndView(offermarket, null);
	} else {
		try {
			offermarketService.save(offermarket);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable th) {
				result = createEditModelAndView(offermarket, "offermarket.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(OfferMarket offermarket, String message) {
		ModelAndView result = new ModelAndView("offermarket/edit");

		result.addObject("offermarket", offermarket);
		result.addObject("message", message);

		return result;
	}

}
