package com.zzc.luntan.demo1.controller;

import com.zzc.luntan.demo1.pojo.Comment;
import com.zzc.luntan.demo1.pojo.Result;
import com.zzc.luntan.demo1.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/03")
public class CommentController {
    @Autowired
    CommentServiceImpl commentService;

    @PostMapping("/comments")
    public Result addComment(@RequestBody Comment comment, HttpServletRequest req){
        HttpSession session = req.getSession();
        if(session.getAttribute(session.getId())==null){
            return new Result(400,"评论失败,还未登陆!");
        }

        commentService.addComment(comment);
        return new Result(200,"评论成功!");
    }

    @DeleteMapping("/comments/{id}")
    public Result deleteComment(@PathVariable int id,HttpServletRequest req){
        HttpSession session = req.getSession();
        if(session.getAttribute(session.getId())==null||!session.getAttribute(session.getId()).equals(commentService.getAuthorById(id))){
            return new Result(400,"你不是该评论作者,无法删除!");
        }
        commentService.deleteComment(id);
        return new Result(200,"删除成功");
    }

    @GetMapping("/comments/{id}")
    public List<Comment> selectCommentByPostId(@PathVariable int id){
        return commentService.selectCommentByPostId(id);
    }
}
