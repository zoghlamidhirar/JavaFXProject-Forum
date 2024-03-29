package test;


import models.User;

import controllers.addThread;
import controllers.listThreads;
import models.Thread;
import models.Post;
import services.ThreadService;
import services.PostService;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import models.Test;

import services.TestService;

import java.sql.SQLException;



public class Main extends Object {

    public static void main(String[] args) {


        /*
        User User1 = new User(1,"dhirar1");
        User User2 = new User(2,"dhirar2");
        // Instantiate PostService
        PostService postService = new PostService();

        try {
            // Test adding a new post
            testAddPost(postService);

            // Test getting all posts
            testGetAllPosts(postService);

            // You can add more tests as needed
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testAddPost(PostService postService) throws SQLException {
        // Create a test post
        String content = "This is a test post";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User sender = new User(2,"dhirar2");
        Post post = new Post(content, timestamp, sender);

        // Add the post
        postService.add(post);

        // Verify that the post has been added by retrieving all posts
        testGetAllPosts(postService);
    }

    private static void testGetAllPosts(PostService postService) throws SQLException {
        // Retrieve all posts
        List<Post> allPosts = postService.getAll();

        // Print or process the retrieved posts
        for (Post post : allPosts) {
            System.out.println(post);
        }

         */
    }
}
