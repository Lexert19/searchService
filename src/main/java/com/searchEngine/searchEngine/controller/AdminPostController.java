package com.searchEngine.searchEngine.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.searchEngine.searchEngine.entity.Post;
import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.model.PostModel;
import com.searchEngine.searchEngine.repository.PostRepository;
import com.searchEngine.searchEngine.repository.UserRepository;
import com.searchEngine.searchEngine.service.PostService;



@Controller
@RequestMapping("/admin/post")
public class AdminPostController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    
    @GetMapping("/add")
    public String getAddPostForm(
        Model model
    ){
        return "admin/add-post";
    }

    @PostMapping("/add")
    public String addPost(
        @ModelAttribute PostModel postModel,
        @AuthenticationPrincipal UserDetails userDetails,
        Model model
    ){
        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        postService.addPost(postModel, user.get());

        return "admin/add-post";
    }

    @GetMapping("/edit/{id}")
    public String getEditPostForm(
        @PathVariable int id,
        Model model
    ) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        model.addAttribute("post", post.get());
        return "admin/edit-post";
    }

    @PostMapping("/edit")
    public String editPost(
        @ModelAttribute PostModel postModel,
        @AuthenticationPrincipal UserDetails userDetails,
        Model model
    ) {
        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        Post post = postService.editPost(postModel, user.get());

        model.addAttribute("post", post);
        return "admin/edit-post";
    }
    
}
