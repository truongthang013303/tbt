package com.world.tbt.service.impl;
import com.world.tbt.service.ICategoryService;
import com.world.tbt.service.ICommonService;
import com.world.tbt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Arrays;

@Service
public class CommonService implements ICommonService {
    @Autowired
    private NewService newService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IUserService userService;
    @Override
    public ModelMap dashBoard_CommonService()
    {
        ModelMap mm = new ModelMap();
        ModelMap mmNews = new ModelMap();
        mmNews.addAttribute("totalNews", new ModelMap().addAttribute("name","Tổng số bài viết").addAttribute("value",newService.getTotalItem()));
        mmNews.addAttribute("choDuyet",new ModelMap().addAttribute("name","Bài viết chờ duyệt").addAttribute("value",newService.countByStatus(0)));
        mmNews.addAttribute("daDuyet",new ModelMap().addAttribute("name","Bài viết đã duyệt").addAttribute("value",newService.countByStatus(1)));
        mmNews.addAttribute("tuChoi",new ModelMap().addAttribute("name","Bài viết bị từ chối").addAttribute("value",newService.countByStatus(2)));
        //Co mot so truong hop status cua bai viet=null se gay loi he thong
       //mmNews.addAttribute("default",new ModelMap().addAttribute("name","Bài viết default").addAttribute("value",newService.countByStatus(null)));
        mmNews.addAttribute("disable",new ModelMap().addAttribute("name","Bài viết disable").addAttribute("value",newService.countByStatus(3)));
        mm.addAttribute("news",mmNews);
        ModelMap mmCategory = new ModelMap();
        mmCategory.addAttribute("categories",categoryService.findAll());
        mm.addAttribute("category",mmCategory);
        ModelMap mmUser = new ModelMap();
        mmUser.addAttribute("totalUser",new ModelMap().addAttribute("name","Tổng số người dùng").addAttribute("value", userService.getTotalItem()));
        mm.addAttribute("user", mmUser);
        mm.addAttribute("easyPieChart",
                Arrays.asList(
                        new ModelMap().addAttribute("name","Tổng bài viết").addAttribute("value",newService.getTotalItem() ),
                        new ModelMap().addAttribute("name","Bài viết chờ duyệt").addAttribute("value", newService.countByStatus(0)).addAttribute("percent",(float)newService.countByStatus(0)/(float)newService.getTotalItem()*100),
                        new ModelMap().addAttribute("name","Tổng user").addAttribute("value", userService.getTotalItem() )

                )
        );
        return mm;
    }
}
