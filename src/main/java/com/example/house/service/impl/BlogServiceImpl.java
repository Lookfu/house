package com.example.house.service.impl;

import com.example.house.entity.Blog;
import com.example.house.mapper.BlogMapper;
import com.example.house.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired(required = false)
    BlogMapper blogMapper;
    @Override
    public List<Blog> listAll() {
        return blogMapper.listAll();
    }

    @Override
    public Blog getDetail(int id) {
        return blogMapper.getDetail(id);
    }
}
