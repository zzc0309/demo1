package com.zzc.luntan.demo1.controller;

import com.zzc.luntan.demo1.pojo.Post;
import com.zzc.luntan.demo1.pojo.Result;
import com.zzc.luntan.demo1.pojo.User;
import com.zzc.luntan.demo1.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/02")
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @PostMapping("/posts")
    public Result insertPost(@RequestBody Post post, HttpServletRequest req){
        HttpSession session = req.getSession();

        if(session.getAttribute(session.getId())==null){
            return new Result(400,"发布失败,还未登陆!");
        }

        postService.insertPost(post);
        return new Result(200,"发布成功");
    }

    @DeleteMapping("/posts/{id}")
    public Result deletePost(@PathVariable int id,HttpServletRequest req){
        HttpSession session = req.getSession();
        if(!session.getAttribute(session.getId()).equals(postService.getAuthorById(id))){
            return new Result(400,"你不是该帖作者,无法删除!");
        }
        postService.deletePost(id);
        return new Result(200,"删除成功");
    }

    @PutMapping("/posts")
    public Result updatePost(@RequestBody Post post,HttpServletRequest req){
        HttpSession session = req.getSession();
        if(!session.getAttribute(session.getId()).equals(postService.getAuthorById(post.getId()))){
            return new Result(400,"你不是该帖作者,无法修改!");
        }
        postService.updatePost(post);
        return new Result(200,"修改成功");
    }

    @GetMapping("/posts/{id}")
    public Post selectPostById(@PathVariable int id){
        return postService.selectPostById(id);
    }


    //首页
    @GetMapping("/posts")
    public List<Post> displayAllPosts(){
        return postService.displayAllPosts();
    }
}
