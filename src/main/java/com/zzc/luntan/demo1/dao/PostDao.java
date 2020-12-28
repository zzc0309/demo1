package com.zzc.luntan.demo1.dao;

import com.zzc.luntan.demo1.pojo.Post;
import javafx.geometry.Pos;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao {
     void insertPost(Post post);
     void deletePost(int id);
     void updatePost(Post post);
     Post selectPostById(int id);
     List<Post> displayAllPosts();
     String getAuthorById(int id);
}
