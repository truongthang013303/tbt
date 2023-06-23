package com.world.tbt.controller.admin;

import com.world.tbt.dto.CategoryDTO;
import com.world.tbt.service.ICategoryService;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller(value = "categoryControllerOfAdmin")
@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
public class CategortyController 
{
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = {"quantri/theloai/danhsach","quantri/theloai"}, method = RequestMethod.GET)
	public ModelAndView categoryListPage(@RequestParam(name = "page", required = false) Optional<Integer> page, 
	@RequestParam(name = "limit", required = false) Optional<Integer> limit,@RequestParam(name="sort", required = false) String sort, HttpServletRequest request) 
	{
		ModelAndView mav = new ModelAndView("admin/Category/danhsach");
		return mav;
	}
	
	@RequestMapping(value = {"quantri/theloai/chinhsua"}, method = RequestMethod.GET)
	public ModelAndView showFormEdit(@RequestParam(name = "id", required = false) String id, HttpServletRequest request) 
	{
		CategoryDTO model = new CategoryDTO();
		ModelAndView mav = new ModelAndView("admin/Category/edit");
		if(id!=null)
		{
			model = categoryService.findById(Long.parseLong(id));
		}
		mav.addObject("model", model);
		return mav;
	}
	@RequestMapping(value = {"quantri/theloai/news"}, method = RequestMethod.GET)
	public ModelAndView showFormEdit(@RequestParam(name = "categoryid", required = true) String categoryid)
	{
		ModelAndView mav = new ModelAndView("admin/New/danhsach");
		mav.addObject("categoryid",categoryid);
		return mav;
	}
}
