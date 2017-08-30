package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Store;
import services.StoreService;

@Controller
@RequestMapping("/store")
public class StoreController {

	@Autowired
	StoreService storeService;

	@RequestMapping(value = "/businessman/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(storeService.create(), null);

		return result;
	}

	@RequestMapping(value="/businessman/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Store store, BindingResult binding) {
	ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(store, null);
		} else {
			try {
				storeService.save(store);
				result = new ModelAndView("redirect:/store/businessman/view.do");
			} catch (Throwable th) {
				result = createNewModelAndView(store, "store.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(Store store, String message) {
		ModelAndView result;
		result = new ModelAndView("store/create");
		result.addObject("store", store);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/businessman/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam(required = false) Store q) {
		ModelAndView result;

		result = new ModelAndView("store/view");
		result.addObject("store", q == null ? storeService.selectSelf() : q);

		return result;
	}
	
	@RequestMapping(value = "/businessman/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("store/list");
		result.addObject("store", storeService.findAll());

		return result;
	}
	
	@RequestMapping(value = "/businessman/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Store store) {
		ModelAndView result;
		result = new ModelAndView("store/edit");
		result.addObject("store", store);
		return result;
	}

	@RequestMapping(value = "/businessman/edit", method = RequestMethod.POST,params = "delete")
	public ModelAndView deleteEdit(@Valid Store store) {
		ModelAndView result;

		try {
			storeService.delete(store);
			result = new ModelAndView("redirect:/store/list.do");
		} catch (Throwable th) {
			result = createEditModelAndView(store, "store.commit.error");
		}

		return result;
	}

	@RequestMapping(value="/businessman/edit", method=RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Store store, BindingResult binding) {
	ModelAndView result;
	if (binding.hasErrors()) {
		result = createEditModelAndView(store, null);
	} else {
		try {
			storeService.save(store);
				result = new ModelAndView("redirect:/store/list.do");
			} catch (Throwable th) {
				result = createEditModelAndView(store, "store.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(Store store, String message) {
		ModelAndView result = new ModelAndView("store/edit");

		result.addObject("store", store);
		result.addObject("message", message);

		return result;
	}

}
