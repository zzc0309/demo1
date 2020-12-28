package com.zzc.luntan.demo1.pojo;

import lombok.Data;

@Data
public class Comment {
    private int id;
    private int postid;
    private String author;
    private String comment;
}
