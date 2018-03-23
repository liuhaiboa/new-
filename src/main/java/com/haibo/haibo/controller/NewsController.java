package com.haibo.haibo.controller;


import com.haibo.haibo.po.News;
import com.haibo.haibo.po.PageBean;
import com.haibo.haibo.po.User;
import com.haibo.haibo.service.NewsService;
import com.haibo.haibo.service.UserService;
import com.haibo.haibo.util.FreemarkerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by Administrator on 2017/12/27/027.
 */
@Controller
@RequestMapping(value={"/news"})
public class NewsController {
    @Autowired
    private NewsService service;
    @Autowired
    private UserService uservice;


    @RequestMapping(value = "/delz/{id}")
    public void delZ(@PathVariable int id ,HttpServletRequest request,HttpServletResponse response){
        service.delz(id);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/news/queryz/1");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @RequestMapping(value = "/savez")
    public void saveZ(@ModelAttribute News news, HttpServletRequest request, HttpServletResponse response) {
        System.out.print(news);
        news.setDatetime(new Date(System.currentTimeMillis()));
        news.setRootid(0);//是主贴
        if (service.save(news) != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/news/queryz/1");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    @RequestMapping(value = {"/queryz/{cupage}"})
    public void queryZT(HttpServletResponse response,
                        @PathVariable String cupage,
                        HttpServletRequest request){
        int currentPage=Integer.parseInt(cupage);//当前第几页
        int size=0;//设置长量
        if(request.getSession().getAttribute("username")==null){//判断是不游客
            size=4;//登录失败或者游客，默认size就是2个
        }else{
            // size=Integer.parseInt(request.getSession().getAttribute("pegenum").toString());
            int userid=Integer.parseInt(request.getSession().getAttribute("userid").toString());
            size= uservice.getPagenum(userid);//取出数据库中当前用户的pagenum每页行数

        }
        Sort sort=new Sort(Sort.Direction.DESC,"id");//排序
        Pageable pageable=new PageRequest(currentPage-1,size,sort);
       Page<News> page=service.queryZ(pageable);
        Map<String,Object> map=new HashMap<>();

        PageBean pb=new PageBean();
        pb.setMaxPage(page.getTotalPages());
        pb.setCurrentPage(currentPage);
        pb.setMaxRow(page.getTotalElements());
        pb.setRowPerPage(page.getNumberOfElements());
        pb.setData(page.getContent());


        map.put("username",request.getSession().getAttribute("username"));
        map.put("userid",request.getSession().getAttribute("userid"));
        map.put("pb",pb);

        FreemarkerUtils.forward("show",map,response);
    }





}