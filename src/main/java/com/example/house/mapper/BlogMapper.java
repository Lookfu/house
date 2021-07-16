package com.example.house.mapper;

import com.example.house.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {
    /**
     * 返回所有值
     * @return
     */
    List<Blog> listAll();

    Blog getDetail(int id);
}
