package com.world.tbt.controller.admin;

import com.world.tbt.dto.RoleDTO;
import com.world.tbt.service.IPrivilegeService;
import com.world.tbt.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller(value = "roleControllerOfAdmin")
@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
public class RoleController 
{
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPrivilegeService privilegeService;
	@RequestMapping(value = {"quantri/role/danhsach","quantri/role"}, method = RequestMethod.GET)
	public ModelAndView roleListPage(@RequestParam(name = "page", required = false) Optional<Integer> page, 
	@RequestParam(name = "limit", required = false) Optional<Integer> limit,@RequestParam(name="sort", required = false) String sort)
	{
		ModelAndView mav = new ModelAndView("admin/Role/danhsach");
		return mav;
	}
	
	@RequestMapping(value = {"quantri/role/chinhsua"}, method = RequestMethod.GET)
	public ModelAndView showFormEdit(@RequestParam(name = "id", required = false) String id)
	{
		RoleDTO model = new RoleDTO();
		ModelAndView mav = new ModelAndView("admin/Role/edit");
		if(id!=null)
		{
			model= roleService.findById(Long.parseLong(id));
		}
		mav.addObject("model",model);
		mav.addObject("privileges", privilegeService.findAll());
		return mav;
	}
	@RequestMapping(value = {"quantri/role/users"}, method = RequestMethod.GET)
	public ModelAndView showUsersOfRole(@RequestParam(name = "roleid", required = true) String roleid)
	{
		ModelAndView mav = new ModelAndView("admin/User/danhsach");
		mav.addObject("roleid",roleid);
		return mav;
	}
}
