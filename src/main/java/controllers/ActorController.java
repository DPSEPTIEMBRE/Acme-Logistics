package controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.AdministratorService;
import services.BusinessmanService;
import services.BuyerService;
import services.FishermanService;
import services.TransporterService;
import domain.Actor;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController{


	//Services

	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private BusinessmanService businessmanService;
	
	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private FishermanService fishermanService;
	
	@Autowired
	private TransporterService transporterService;
	
	@Autowired
	private LoginService loginService;

	// Constructors -----------------------------------------------------------
	public ActorController(){
		super();
	}

	//Actions

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		
		result = new ModelAndView("actor/list"); 
		
		Actor me = loginService.selectSelf();
		
		List<Actor> actores = new ArrayList<Actor>();
		actores.addAll(administratorService.findAll());
		actores.addAll(businessmanService.findAll());
		actores.addAll(buyerService.findAll());
		actores.addAll(fishermanService.findAll());
		actores.addAll(transporterService.findAll());

		result.addObject("actores", actores);
		result.addObject("me", me);

		return result;
	}
	
	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public ModelAndView follow(@RequestParam Actor q) {
		ModelAndView result;

		Actor actor = loginService.selectSelf();
		
		if ((!actor.getFollowed().contains(q)) && actor != q) {
			administratorService.follow(q);
		}
		
		result = new ModelAndView("redirect:/actor/myListFolloweds.do");
		return result;
	}
	
	@RequestMapping("/myListFolloweds")
	public ModelAndView listActorFolloweds() {
		ModelAndView result;

		Actor actor = loginService.selectSelf();
		
		result = new ModelAndView("actor/myListFolloweds");
		result.addObject("actors", actor.getFollowed());
		
		return result;
	}
	
	@RequestMapping("/myListFollowers")
	public ModelAndView listActorFollowers() {
		ModelAndView result;

		Actor actor = loginService.selectSelf();
		
		result = new ModelAndView("actor/myListFollowers");
		result.addObject("actors", actor.getFollower());
		
		return result;
	}

}
