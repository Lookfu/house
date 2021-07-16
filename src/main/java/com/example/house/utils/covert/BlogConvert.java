package com.example.house.utils.covert;

import com.example.house.controller.viewobject.BlogVO;
import com.example.house.controller.viewobject.SimpleBlogVO;
import com.example.house.entity.Blog;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BlogConvert {
    //博客文章的摘要长度
    private static final int summaryLen=50;

    public static SimpleBlogVO simpleBlogVOCFBlog(Blog blog){
        if(blog==null){
            return null;
        }
        SimpleBlogVO simpleBlogVO=new SimpleBlogVO();
        BeanUtils.copyProperties(blog,simpleBlogVO);
        simpleBlogVO.setSummary(getSummary(blog.getContent()));
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Timestamp timestamp=new Timestamp(blog.getCreateTime().getTime()-28800000);
            String time = sdf.format(timestamp);
            simpleBlogVO.setCreateTime(time);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return simpleBlogVO;
    }

    public static BlogVO blogVOCFBlog(Blog blog){
        if(blog==null){
            return null;
        }
        BlogVO blogVO=new BlogVO();
        BeanUtils.copyProperties(blog,blogVO);
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Timestamp timestamp=new Timestamp(blog.getCreateTime().getTime()-28800000);
            String time = sdf.format(timestamp);
            blogVO.setCreateTime(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogVO;
    }

    private static String getSummary(String content){
        String res=null;
        if(content.length()<summaryLen){
            res=content;
        }else{
            res=content.substring(0,summaryLen);
            res+="...";
        }
        return res;
    }
}
