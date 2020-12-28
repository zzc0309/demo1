package com.zzc.luntan.demo1.dao;

import com.zzc.luntan.demo1.pojo.Comment;
import com.zzc.luntan.demo1.pojo.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentDao {
     void addComment(Comment comment);
     void deleteComment(int id);
     List<Comment> selectCommentByPostId(int id);
     String getAuthorById(int id);
}
