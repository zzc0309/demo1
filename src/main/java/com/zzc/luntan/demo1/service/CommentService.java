package com.zzc.luntan.demo1.service;

import com.zzc.luntan.demo1.pojo.Comment;

import java.util.List;

public interface CommentService {
     void addComment(Comment comment);
     void deleteComment(int id);
     List<Comment> selectCommentByPostId(int id);
     String getAuthorById(int id);
}
