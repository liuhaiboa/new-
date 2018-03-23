package com.haibo.haibo.service;

import com.haibo.haibo.dao.IUserDao;
import com.haibo.haibo.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/25/025.
 */
@Service
public class UserService {
    @Autowired private IUserDao dao;


    private Map<String,String> types=new HashMap<String,String>();
    public UserService(){
        //允许上传的文件类型
        types.put("image/jpeg", ".jpg");
        types.put("image/gif", ".gif");
        types.put("image/x-ms-bmp", ".bmp");
        types.put("image/png", ".png");

    }

    public User login(User user){
        return dao.login(user.getUsername(),user.getPassword());

    }

    public void register(User user){
        dao.save(user);//注册
   }
    public User pic(String userid){
        return dao.pic(Integer.parseInt(userid));//注册
    }

    //上传组件
    public User uplodPic(MultipartHttpServletRequest request,//springmvc自带的传二进制流的request
                         CommonsMultipartResolver commonsMultipartResolver){

        commonsMultipartResolver.setDefaultEncoding("utf-8");//上传头像的文件名可以是中文
        commonsMultipartResolver.setResolveLazily(true);
        commonsMultipartResolver.setMaxInMemorySize(1024*1024*4);//设置缓冲
        commonsMultipartResolver.setMaxUploadSizePerFile(1024*1024);//设置每个文件大小
        commonsMultipartResolver.setMaxUploadSize(1024*1024*2);//设置最多能上传文件的大小

        //MultipartHttpServletRequest req=commonsMultipartResolver.resolveMultipart(request);
        MultipartFile multipartFile=request.getFile("file0");//取得头像的文件域
        String fileType=types.get(multipartFile.getContentType());//取出头像的文件类型houzui

        // String fileName=file.getOriginalFilename();
        //文件名字
        UUID uuid= UUID.randomUUID();
        File file=new File("d:/N19/upload/"+uuid+fileType);//新头像文件对象
        User user=new User();
//        try {
        try {
            multipartFile.transferTo(file);//传输头像

            user.setUsername( request.getParameter("reusername"));
            user.setPassword( request.getParameter("repassword"));
            user.setPagenum(2);//默认分页每页2个
            //blob存储
            FileInputStream fis=new FileInputStream(file);
            byte[] buffer=new byte[fis.available()];
            //开辟该文件大小的缓冲区
            fis.read(buffer);//把头像读到缓冲区内
           user.setPic(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;

    }




    public int setPagenum( Integer userid, Integer pagenum){
        return dao.setPagenum(userid,pagenum);

    }
    public int getPagenum( Integer userid){
        return dao.getPagenum(userid);

    }


}
