package com.zzc.luntan.demo1.service.impl;

import com.zzc.luntan.demo1.dao.PostDao;
import com.zzc.luntan.demo1.pojo.Post;
import com.zzc.luntan.demo1.service.PostSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostSevice {
    @Autowired
    PostDao postDao;


    @Override
    public void insertPost(Post post) {
        postDao.insertPost(post);
    }

    @Override
    public void deletePost(int id) {
        postDao.deletePost(id);
    }

    @Override
    public void updatePost(Post post) {
        postDao.updatePost(post);
    }

    @Override
    public Post selectPostById(int id) {
        return postDao.selectPostById(id);
    }

    @Override
    public List<Post> displayAllPosts() {
        return postDao.displayAllPosts();
    }

    @Override
    public String getAuthorById(int id) {
        return postDao.getAuthorById(id);
    }
}
