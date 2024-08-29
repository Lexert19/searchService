package com.searchEngine.searchEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searchEngine.searchEngine.entity.Post;
import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.model.PostModel;
import com.searchEngine.searchEngine.model.UserModel;
import com.searchEngine.searchEngine.repository.UserRepository;
import com.searchEngine.searchEngine.service.PostService;
import com.searchEngine.searchEngine.service.RegisterService;
import com.searchEngine.searchEngine.staticClass.RandomUtil;

@Component
public class SetupContext {
    @Autowired
    private PostService postService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegisterService registerService;

    public User setupUser() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setBirthDate("2002-12-12");
        String email = RandomUtil.generateRandomString(6) + "@wp.pl";
        userModel.setEmail(email);
        userModel.setFirstName("firstname");
        userModel.setLastName("lastname");
        userModel.setPassword("test");
        userModel.setUsername("username");

        return registerService.registerUser(userModel);
    }

    public User setupAdminUser() throws Exception{
        UserModel userModel = new UserModel();
        userModel.setBirthDate("2002-12-12");
        String email = RandomUtil.generateRandomString(6) + "@wp.pl";
        userModel.setEmail(email);
        userModel.setFirstName("firstname");
        userModel.setLastName("lastname");
        userModel.setPassword("test");
        userModel.setUsername("username");

        User user = registerService.registerUser(userModel);
        user.getRoles().add("ROLE_ADMIN"); 
        userRepository.saveAndFlush(user);
        return user;
    }

    public Post setupPost(String title) {
        User user = userRepository.findFirstByOrderByIdAsc();

        PostModel postModel = new PostModel();
        postModel.setContent("content");
        postModel.setLocale("en");
        postModel.setTitle(title);

        Post post = postService.addPost(postModel, user);
        return post;
    }

    public void setupPosts() {
        User user = userRepository.findFirstByOrderByIdAsc();

        for (int i = 0; i < 10; i++) {
            PostModel postModel = new PostModel();
            postModel.setContent("content");
            postModel.setLocale("en");
            postModel.setTitle(RandomUtil.generateRandomString(6));

            postService.addPost(postModel, user);
        }

    }
}
