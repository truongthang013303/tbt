package com.world.tbt.controller.admin;

import com.world.tbt.dto.UserDTO;
import com.world.tbt.service.IRoleService;
import com.world.tbt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

;

@Controller(value = "userControllerOfAdmin")
@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
public class UserController 
{
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value = {"quantri/nguoidung/danhsach","quantri/nguoidung"}, method = RequestMethod.GET)
	public ModelAndView userListPage(@RequestParam(name = "page", required = false) Optional<Integer> page, 
	@RequestParam(name = "limit", required = false) Optional<Integer> limit,@RequestParam(name="sort", required = false) String sort, HttpServletRequest request) 
	{
		ModelAndView mav = new ModelAndView("admin/User/danhsach");
		return mav;
	}
	
	@RequestMapping(value = {"quantri/nguoidung/chinhsua"}, method = RequestMethod.GET)
	public ModelAndView showFormEdit(@RequestParam(name = "id", required = false) String id, HttpServletRequest request) 
	{
		UserDTO model = new UserDTO();
		ModelAndView mav = new ModelAndView("admin/User/edit");
		if(id!=null)
		{
			model= userService.findById(Long.parseLong(id));
		}
		mav.addObject("model", model);
		mav.addObject("roles", roleService.findAll());
		return mav;
	}
	@RequestMapping(value = {"rui"}, method = RequestMethod.GET)
	public String retrieveUserInformation(){
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return "";
	}
}
