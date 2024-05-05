package utils;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import models.Post;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import controllers.PostController;


public class PostCell extends ListCell<Post> {

    public PostCell() {}

    // Define the PostController instance
    private PostController postController;

    // Constructor to initialize the PostController
    public PostCell(PostController postController) {
        this.postController = postController;
    }

    @Override
    protected void updateItem(Post item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            VBox vbox = new VBox();
            Text text = new Text(item.getSender().getNom() + "\n" + item.getContentPost());
            Text text2 = new Text(" " + item.getTimeStamp_envoi());

            // Like button
            Button likeButton = new Button(item.getLikesCount() + " Likes");
            likeButton.setOnAction(e -> handleLike(item));

            // Dislike button
            Button dislikeButton = new Button(item.getDislikesCount() + " Dislikes");
            dislikeButton.setOnAction(e -> handleDislike(item));

            // HBox to contain like and dislike buttons
            HBox buttonsBox = new HBox(10); // Spacing between buttons
            buttonsBox.getChildren().addAll(likeButton, dislikeButton);


            // Image
            Image image = item.getImage();
            if (image != null) {
                ImageView imageView = new ImageView(image);
                vbox.getChildren().add(imageView);
            }

            // Add elements to the VBox
            vbox.getChildren().addAll(text, likeButton, dislikeButton, text2);

            // Set the graphic of the ListCell
            setGraphic(vbox);
        }
    }

    // Method to handle like event
    private void handleLike(Post post) {
        System.out.println("Like button clicked for post: " + post.getIdPost());
        if (postController != null) {
            postController.setCurrentPost(post); // Set the current post in the controller
            postController.handleLike(new ActionEvent()); // Call the handleLike method in the controller
        }
    }

    // Method to handle dislike event
    private void handleDislike(Post post) {
        System.out.println("Dislike button clicked for post: " + post.getIdPost());
        if (postController != null) {
            postController.setCurrentPost(post); // Set the current post in the controller
            postController.handleDislike(new ActionEvent()); // Call the handleDislike method in the controller
        }
    }

}
