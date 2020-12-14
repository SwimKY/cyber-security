package com.yky.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.StringJoiner;

/**
 * @Author: yky
 * @CreateTime: 2020-10-17
 * @Description: 今日头条数据类
 */
public class TodayHeadlines {

    private static final long serialVersionUID = 1L;

    //@TableId(value = "id", type = IdType.AUTO)
    //private int id;
    private String title;
    private String imgIlink;
    private String link;
    private String time;
    private String content;

    @ApiModelProperty(value = "爬取信息的时间")
    @TableField(fill = FieldFill.INSERT)
    private Date greTime;


    public TodayHeadlines() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgIlink() {
        return imgIlink;
    }

    public void setImgIlink(String imgIlink) {
        this.imgIlink = imgIlink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getGreTime() {
        return greTime;
    }

    public void setGreTime(Date greTime) {
        this.greTime = greTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TodayHeadlines.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("imgIlink='" + imgIlink + "'")
                .add("link='" + link + "'")
                .add("time='" + time + "'")
                .add("content='" + content + "'")
                .add("greTime='" + greTime + "'")
                .toString();
    }
}
