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

import domain.Comment;
import domain.Store;
import services.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;

	@RequestMapping(value = "/actor/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, @RequestParam Store q) {
		ModelAndView result;

		request.getSession().setAttribute("store_id", q.getId());
		result = createNewModelAndView(commentService.create(), null);

		return result;
	}

	@RequestMapping(value="/actor/save", method=RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(HttpServletRequest request, @Valid Comment comment, BindingResult binding) {
	ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(comment, null);
		} else {
			try {
				int store_id = (int) request.getSession().getAttribute("store_id");
				commentService.save(comment, store_id);
				
				result = new ModelAndView("redirect:/store/businessman/view.do?q=" + store_id);
				
				request.getSession().removeAttribute("store_id");
			} catch (Throwable th) {
				result = createNewModelAndView(comment, "comment.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(Comment comment, String message) {
		ModelAndView result;
		result = new ModelAndView("comment/create");
		result.addObject("comment", comment);
		result.addObject("message", message);
		return result;
	}

}
