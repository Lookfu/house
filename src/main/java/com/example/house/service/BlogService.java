package com.example.house.service;

import com.example.house.entity.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> listAll();

    Blog getDetail(int id);
}
