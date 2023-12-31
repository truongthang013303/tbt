package com.world.tbt.controller.web;

import com.world.tbt.dto.AppUser;
import com.world.tbt.dto.NewDTO;
import com.world.tbt.service.ICategoryService;
import com.world.tbt.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
    @Autowired
    private INewService newService;
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "403", method = RequestMethod.GET)
    public String pageNotFound()
    {
        return "403";
    }

    @RequestMapping(value = {"category/{code}","/category"}, method = RequestMethod.GET)
    public ModelAndView newsSortByCate(@PathVariable(name = "code", required = false) String cateCode)
    {
        ModelAndView mav = new ModelAndView("web/Home/category");
        mav.addObject("categoryCodeFromUser",cateCode);
        mav.addObject("categories",categoryService.findAll());
        return mav;
    }

    @RequestMapping(value ="baiviet/{id}", method = RequestMethod.GET)
    public ModelAndView newDetailWorld(@PathVariable(name = "id", required = true) long id, HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    {
        ModelAndView mav = new ModelAndView("web/Home/single-blog");
        //status=1[da duyet]
        NewDTO model =  newService.findByIdAndStatus(id, 1);
        if(model==null){
            mav.setViewName("error/404");
            return mav;
        }
        if(authentication==null)
        {
            model.setRatings(null);
        }else{
            AppUser appUser = (AppUser)authentication.getPrincipal();
            model.setRatings(model.getRatings().stream().filter(rating->rating.getUser().getId()==appUser.getId()).collect(Collectors.toSet()));
        }
        mav.addObject("model",model);
        mav.addObject("appUser", SecurityContextHolder.getContext().getAuthentication().getName());
        if(request.getUserPrincipal()==null){
            Cookie cookieUsername = new Cookie("username","anony");
            cookieUsername.setPath("/");
            cookieUsername.setMaxAge(0);
            response.addCookie(cookieUsername);

            Cookie cookieJSESSIONID = new Cookie("JSESSIONID","anony");
            cookieJSESSIONID.setPath("/");
            cookieJSESSIONID.setMaxAge(0);
            response.addCookie(cookieJSESSIONID);
        }
        return mav;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(name = "keyword") @NotBlank Optional<String> keyword, Model model)
    {
        ModelAndView mav = new ModelAndView("web/Home/search");
        if(!keyword.isPresent()){
            return mav;
        }
        //Status always is 1 =[DADUYET]
        mav.addObject("news",newService.findByTitleContainingAndStatus(keyword.get()));
        return mav;
    }

    @RequestMapping(value = "accessdenied", method = RequestMethod.GET)
    public String accessdinied()
    {
        return "accessdenied";
    }

    @RequestMapping(value = {"/login", "login"}, method = RequestMethod.GET)
    @ResponseBody
    //Trong config spring security khi login failed se chay vao duong dan /login?error=true
    public String loginPage(@RequestParam(name = "error", required = false) String error)
    {
        if(error!=null&&error.equalsIgnoreCase("true"))
        {
            return "failed";
        }
        return "successed";
    }
    @RequestMapping(value = {"home","/"}, method = RequestMethod.GET)
    public ModelAndView world(HttpServletResponse response, Principal principal, Authentication authentication)
    {
        ModelAndView mav = new ModelAndView("web/Home/WorldHome");
        mav.addObject("categories",categoryService.findAll());
        //status=1 => da duyet
        Page<NewDTO> news = newService.findByPage(1, PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "createdDate")));
        mav.addObject("news", news);
        mav.addObject("isLast", news.isLast());
        if(authentication!=null){
            if(!authentication.getName().equals("anonymousUser")) {
                AppUser appUser = (AppUser)authentication.getPrincipal();
                if(appUser.getInterest()!=null) {
                    mav.addObject("forYou", newService.findAllByCategoryCodePage(appUser.getInterest().getCode(), 1, PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "createdDate"))));
                }
            }
        }
        return mav;
    }
}
