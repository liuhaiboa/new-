package com.haibo.haibo.util;


import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/19/019.
 */
public class FreemarkerUtils {
    private static Configuration cfg;

    private static Configuration builtConfiguration(){
        cfg=new Configuration(Configuration.VERSION_2_3_23);

        String path=FreemarkerUtils.class.getResource("/").getPath();
        File file=new File(path+File.separator+"templates");

        cfg.setDefaultEncoding("UTF-8");
        try {
            cfg.setDirectoryForTemplateLoading(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


return cfg;
    }

    private static Template getTemplate(String ftlName){
        Template temp=null;
        try {
            temp=builtConfiguration().getTemplate(ftlName+".ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }


//后端向前端传值
 public static void forward(String ftlName, Map map, HttpServletResponse response) {

     try {
         response.setCharacterEncoding("utf-8");
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();

         getTemplate(ftlName).process(map, out);//zhaodaomoban
     } catch (Exception e) {
         e.printStackTrace();
     }

 }
}
