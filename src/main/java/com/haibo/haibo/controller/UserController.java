package com.haibo.haibo.controller;

import com.haibo.haibo.po.User;
import com.haibo.haibo.service.UserService;
import com.haibo.haibo.util.FreemarkerUtils;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/25/025.
 */
@Controller
@RequestMapping(value = {"/user"})
//@SessionAttributes(value = {"user"})
public class UserController {
    @Autowired
    private UserService service;



    @RequestMapping("/reg")
    public void reg(HttpServletResponse response, MultipartHttpServletRequest request){
        CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(request.getServletContext());
       User user=null;
        if(commonsMultipartResolver.isMultipart(request)){

          //  user=service.uplodPic(request,commonsMultipartResolver);
           // if(ServletFileUpload.isMultipartContent(request)){
            user=service.uplodPic(request,commonsMultipartResolver);
            service.register(user);

        }
      //  FreemarkerUtils.forward("show",null,response);
         RequestDispatcher dispatcher=request.getRequestDispatcher("/news/queryz/1");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

    @RequestMapping(value="/logout")
    public void loginout(HttpServletResponse response,HttpServletRequest request){

       RequestDispatcher dispatcher=request.getRequestDispatcher("/news/queryz/1");
       request.getSession().removeAttribute("username");
      request.getSession().removeAttribute("userid");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       // FreemarkerUtils.forward("show",null,response);

    }
    @RequestMapping(value = {"/pic/{userid}"})
    public void pic(@PathVariable String userid,HttpServletResponse response){
        User user=service.pic(userid);
        try {
            try (
                    OutputStream outputStream = response.getOutputStream()) {
                outputStream.write(user.getPic());
//                outputStream.write(user.getPic());
                outputStream.flush();
                outputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @RequestMapping(value = {"/login"})
    public void login(User user,
            HttpServletResponse response, HttpServletRequest request, ModelMap model) {
        //Map map=new HashMap();
        User user1 = service.login(user);
        if (user1 != null) {//登录成功

//           map.put("username",user1.getUsername());
//           map.put("userid",user1.getUserid());
            //处理Cookie
//            request.setAttribute("username", user1.getUsername());
          //request.setAttribute("pagenum", user1.getPagenum());
//            request.setAttribute("userid", user1.getUserid());

            request.getSession().setAttribute("username",user1.getUsername());
            request.getSession().setAttribute("userid",user1.getUserid());
            request.getSession().setAttribute("pagenum", user1.getPagenum());

            //处理Session
            //   model.put("user",user);
            if (user.getSun().equals("on")) {
                Cookie cookie = new Cookie("papaoku", user.getUsername());
                cookie.setMaxAge(3600 * 24 * 7);
                Cookie cookie1 = new Cookie("papaokp", user.getPassword());
                cookie1.setMaxAge(3600 * 24 * 7);
                response.addCookie(cookie);//发到前端去
                response.addCookie(cookie1);

            } else {//登录失败
                // map.put("username",null);
            }

            // FreemarkerUtils.forward("show",map,response);
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
}