package com.searchEngine.searchEngine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.searchEngine.searchEngine.entity.Post;
import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.model.PostModel;
import com.searchEngine.searchEngine.repository.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostModelService postModelService;
    
    @Transactional
    public Post addPost(PostModel postModel, User user){
        Post post = postModelService.createPost(postModel, user);
        
        this.postRepository.saveAndFlush(post);
        return post;
    }

    @Transactional
    public Post editPost(PostModel postModel, User user){
        Optional<Post> optionalPost = postRepository.findById(postModel.getId());
        if(optionalPost.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Post post = optionalPost.get();
        post = postModelService.editPost(postModel, post);
        postRepository.saveAndFlush(post);
        return post;
    }

    public List<Post> getLatestPosts(int limit){
        List<Post> posts = postRepository.findLatestPosts(limit);

        return posts;
        
    }
}
