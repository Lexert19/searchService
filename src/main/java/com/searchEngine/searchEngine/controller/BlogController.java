package com.searchEngine.searchEngine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.searchEngine.searchEngine.entity.Post;
import com.searchEngine.searchEngine.service.PostService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private PostService postService;
    
    
    @GetMapping("/")
    public String getLatestPosts(
        @RequestParam(defaultValue = "10") int limit,
        Model model
    ) {
        List<Post> posts = postService.getLatestPosts(limit);
        model.addAttribute("posts", posts);
        return "blog/blog";
    }
    
}
