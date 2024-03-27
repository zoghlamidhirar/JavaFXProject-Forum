package test;


import models.User;

import controllers.addThread;
import controllers.listThreads;
import models.Thread;
import services.ThreadService;
//import services.MessageService;

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
        // Create a new user for testing
        User user = new User(1, "testUser");

        // Create an instance of ThreadService
        ThreadService threadService = new ThreadService();

        try {
            /*
// Test adding a new thread
            // Assuming Timestamp and User objects are properly instantiated
            Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
            Thread threadToAdd = new Thread( "Testing Title 222",timeStamp,user , "Test Description222","#FFF000" );
            threadService.add(threadToAdd);
            System.out.println("Thread added successfully.");

*/

            // Test getting all threads
            List<Thread> allThreads = threadService.getAll();
            System.out.println("All Threads:");
            for (Thread thread : allThreads) {
                System.out.println(thread);
            }


/*
            // Test getting a thread by ID
            int threadIdToGet = 9; // Change this ID to a valid ID from the database
            Thread retrievedThread = threadService.getById(threadIdToGet);
            if (retrievedThread != null) {
                System.out.println("Retrieved Thread:");
                System.out.println(retrievedThread);
            } else {
                System.out.println("Thread not found for ID: " + threadIdToGet);
            }






            // Test updating a thread
            if (retrievedThread != null) {
                // Update the fields as needed
                retrievedThread.setTitleThread("Updated Thread Title");
                retrievedThread.setDescriptionThread("Updated Description");
                retrievedThread.setColorThread("#000000");
                // Assuming you have methods to update these fields:
                // retrievedThread.setTimeStampCreation(new Timestamp(System.currentTimeMillis()));
                // retrievedThread.setCreator(new User(userId, userName));

                // Call the update method
                threadService.update(retrievedThread);
                System.out.println("Thread updated successfully.");
            }

            // Test deleting a thread
            int threadIdToDelete = 5; // Change this ID to a valid ID from the database
            threadService.delete(threadIdToDelete);
            System.out.println("Thread deleted successfully.");

*/

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error occurred: " + e.getMessage());
        }


    }
}
