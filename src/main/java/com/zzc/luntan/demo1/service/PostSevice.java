package com.zzc.luntan.demo1.service;

import com.zzc.luntan.demo1.pojo.Post;

import java.util.List;

public interface PostSevice {
     void insertPost(Post post);
     void deletePost(int id);
     void updatePost(Post post);
    Post selectPostById(int id);
    List<Post> displayAllPosts();
     String getAuthorById(int id);
}
