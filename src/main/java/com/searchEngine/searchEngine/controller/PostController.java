package com.searchEngine.searchEngine.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.searchEngine.searchEngine.entity.Post;
import com.searchEngine.searchEngine.repository.PostRepository;


@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/{title}")
    public String getPostByTitle(
        @PathVariable String title,
        Model model
    ) {
        Optional<Post> post = postRepository.findByTitle(title);
        if(post.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        model.addAttribute("post", post.get());
        return "blog/post";
    }
    
}
