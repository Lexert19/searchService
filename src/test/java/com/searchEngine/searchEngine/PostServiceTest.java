package com.searchEngine.searchEngine;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.searchEngine.searchEngine.entity.Post;
import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.model.PostModel;
import com.searchEngine.searchEngine.service.PostService;
import com.searchEngine.searchEngine.staticClass.RandomUtil;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostService postService;
    @Autowired
    private SetupContext setupContect;
    private User user;

    @BeforeEach
    public void setup() throws Exception {
        user = setupContect.setupUser();
        setupContect.setupPosts();
    }

    @Test
    public void getLatestPosts() {
        List<Post> posts = postService.getLatestPosts(10);
        assertTrue(posts.size() == 10);
    }

    @Test
    public void addPost() {
        String title = RandomUtil.generateRandomString(6);
        PostModel postModel = new PostModel();
        postModel.setContent("content");
        postModel.setLocale("en");
        postModel.setTitle(title);

        Post post = postService.addPost(postModel, user);
        assertTrue(post.getTitle() == title);
    }

    @Test
    public void editPost() {
        String title = RandomUtil.generateRandomString(6);
        String newTitle = RandomUtil.generateRandomString(6);

        Post post = setupContect.setupPost(title);

        PostModel postModel = new PostModel();
        postModel.setContent("editedContent");
        postModel.setId(post.getId());
        postModel.setLocale("pl");
        postModel.setTitle(newTitle);
        post = postService.editPost(postModel, user);

        assertTrue(post.getContent().equals("editedContent"));
        assertTrue(post.getLocale().equals("pl"));
        assertTrue(post.getTitle().equals(newTitle));
    }

}
