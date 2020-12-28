package com.zzc.luntan.demo1.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class Post {
    private int id;
    private String message;
    private String author;
    private String picture;
    private int praise;
    private int cai;
    private int cid;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    //private Date date;
    private String date;
}
