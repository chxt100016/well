package org.wella.entity;

import sun.text.resources.FormatData;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by xuqinghuo on 2017/7/25.
 */
public class Message {

    private Integer id;
    private String title;
    private String type;
    private String date;
    private String content;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {return type;}

    public void setType(String type) {
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
        this.date = dateF.format(date);
    }

}
