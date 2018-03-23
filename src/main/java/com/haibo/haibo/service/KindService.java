package com.haibo.haibo.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/9/009.
 */
@Service
public class KindService {
    private Map<String,String> types=new HashMap<String,String>();
    public KindService(){
        //允许上传的文件类型
        types.put("image/jpeg", ".jpg");
        types.put("image/gif", ".gif");
        types.put("image/x-ms-bmp", ".bmp");
        types.put("image/png", ".png");

    }
    public String fileupload(MultipartHttpServletRequest request, CommonsMultipartResolver commonsMultipartResolver){
        commonsMultipartResolver.setDefaultEncoding("utf-8");//上传头像的文件名可以是中文
        commonsMultipartResolver.setResolveLazily(true);
        commonsMultipartResolver.setMaxInMemorySize(1024*1024*4);//设置缓冲
        commonsMultipartResolver.setMaxUploadSizePerFile(1024*1024);//设置每个文件大小
        commonsMultipartResolver.setMaxUploadSize(1024*1024*2);//设置最多能上传文件的大小
        MultipartFile multipartFile=request.getFile("imgFile");//取得头像的文件域
        String fileType=types.get(multipartFile.getContentType());
        UUID uuid=UUID.randomUUID();
        String path=KindService.class.getClassLoader().getResource("").getPath();
        String dir=request.getParameter("dir");
        String filePath=path+"static/editor/upload/"+dir+"/"+uuid.toString()+fileType;
        //String tpath=request.getRemoteAddr();
        File file=new File(filePath);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("error",0);
        jsonObject.put("url","/static/editor/upload/"+dir+"/"+uuid.toString()+fileType);
        System.out.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();


    }
}