package com.world.tbt.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
    @RequestMapping(value = {"/home","/"}, method = RequestMethod.GET)
    public String homeUser()
    {
        return "web/Home/WorldHome";
    }
    @RequestMapping(value = {"","default"}, method = RequestMethod.GET)
    public String defaultPage()
    {
        return "web/Home/WorldHome";
    }
    @RequestMapping(value = {"baiviet"}, method = RequestMethod.GET)
    public ModelAndView baiviet()
    {
        return new ModelAndView("web/Home/single-blog");
    }
}
