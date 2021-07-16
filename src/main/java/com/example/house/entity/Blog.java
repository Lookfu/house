package com.example.house.entity;

import java.sql.Timestamp;

public class Blog {
  private Integer id;
  private String  tags;
  private String  content;
  private String  title;
  private Timestamp createTime;
  private Integer  cat;
  private String img;
  
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getTags() {
    return tags;
  }
  public void setTags(String tags) {
    this.tags = tags;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public Integer getCat() {
    return cat;
  }

  public void setCat(Integer cat) {
    this.cat = cat;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }
}
