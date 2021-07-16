package com.example.house.controller;

import com.example.house.controller.viewobject.BlogVO;
import com.example.house.controller.viewobject.SimpleBlogVO;
import com.example.house.entity.Blog;
import com.example.house.response.CommonReturnType;
import com.example.house.service.BlogService;
import com.example.house.utils.covert.BlogConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/listAll")
    public CommonReturnType listAll(){
        List<Blog> list=blogService.listAll();
        List<SimpleBlogVO> res=new ArrayList<>(list.size());
        for(Blog e:list){
            res.add(BlogConvert.simpleBlogVOCFBlog(e));
        }
        return CommonReturnType.create(res);
    }

    @GetMapping("/detail")
    public CommonReturnType getDetail(int id){
        Blog blog=blogService.getDetail(id);
        BlogVO blogVO=BlogConvert.blogVOCFBlog(blog);
        return CommonReturnType.create(blogVO);
    }
}
