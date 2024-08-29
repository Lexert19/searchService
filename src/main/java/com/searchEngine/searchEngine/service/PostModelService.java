package com.searchEngine.searchEngine.service;

import org.springframework.stereotype.Service;

import com.searchEngine.searchEngine.entity.Post;
import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.model.PostModel;

@Service
public class PostModelService {

    public Post createPost(PostModel postModel, User user){
        Post post = new Post();
        post.setContent(postModel.getContent());
        post.setLocale(postModel.getLocale());
        post.setTitle(postModel.getTitle());
        post.setUser(user);

        return post;
    }

    public Post editPost(PostModel postModel, Post post){
        post.setContent(postModel.getContent());
        post.setLocale(postModel.getLocale());
        post.setTitle(postModel.getTitle());

        return post;
    }
}
