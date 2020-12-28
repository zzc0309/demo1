package com.zzc.luntan.demo1.service.impl;

import com.zzc.luntan.demo1.dao.CommentDao;
import com.zzc.luntan.demo1.pojo.Comment;
import com.zzc.luntan.demo1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;
    @Override
    public void addComment(Comment comment) {
        commentDao.addComment(comment);
    }

    @Override
    public void deleteComment(int id) {
        commentDao.deleteComment(id);
    }

    @Override
    public List<Comment> selectCommentByPostId(int id) {
        return commentDao.selectCommentByPostId(id);
    }

    @Override
    public String getAuthorById(int id) {
        return commentDao.getAuthorById(id);
    }
}
