package controllers;
import models.Thread;
import models.Post;
import models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import services.ThreadService;
import services.PostService;
import utils.PostCell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class PostController {
    @FXML
    private Button attachButton;

    @FXML
    private TextField PostField;

    @FXML
    private ListView<Post> PostList;

    @FXML
    private Button sendButton;
    @FXML
    private Button emojiButton;
    @FXML
    private Label error;
    @FXML
    private ImageView imageView;
    @FXML
    private VBox vb;
    @FXML
    private Button delete;
    @FXML
    private Button update;

    public void setPostField(TextField PostField) {
        this.PostField = PostField;
    }

    public static int thrdId  ;
    public static String emojis = "";


    User user1 = new User(2,"dhirar");
    User user2 = new User(1,"dhirar");
    User userConnected = user1;
    PostService ms = new PostService();
    ThreadService ds = new ThreadService();


    public void initialize() throws SQLException {
        ObservableList<Post> Posts = FXCollections.observableList(ms.afficherByThreadId(thrdId));
        Thread d1 = ds.getById(thrdId);
        if(!userConnected.equals(d1.getCreatorThread())){
            delete.setVisible(false);
            update.setVisible(false);
        }
        vb.setStyle("-fx-background-color: " + d1.getColorThread() + ";");

        PostList.setItems(Posts);
        PostList.setCellFactory(param -> new PostCell());
        System.out.println(Posts);
        sendButton.setOnAction(e -> {
            try {
                sendPost(); // Call the sendPost() method to send the post
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        attachButton.setOnAction(e->{
            PostService.file = fileChooser.showOpenDialog(stage);
            if(PostService.file!=null){
                System.out.println(PostService.file);
                Image image = new Image(PostService.file.toURI().toString(),100,150,true,true);
                System.out.println(image);
                imageView.setImage(image);
                imageView.setFitWidth(100);
                imageView.setFitHeight(150);
                imageView.setPreserveRatio(true);

            }

        });
        imageView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                ContextMenu contextMenu = new ContextMenu();

                // Create menu items
                MenuItem deleteMenuItem = new MenuItem("Delete");
                deleteMenuItem.setOnAction(e->{
                    imageView.setImage(null);
                    imageView.setFitWidth(0);
                    imageView.setFitHeight(0);
                    PostService.file=null ;
                });
                contextMenu.getItems().addAll( deleteMenuItem);

                // Show context menu at the mouse location
                contextMenu.show(PostList, event.getScreenX(), event.getScreenY());

            }
        });
        attachContextMenuToListView(PostList);


    }
    public void deleteThread()  {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you ok with deleting the Thread?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                ds.delete(thrdId);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("success ");
                alert1.setContentText("Thread deleted successfully");
                alert1.showAndWait();
                changeScene("/ListThreads.fxml");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void retour() {
        changeScene("/ListThreads.fxml");
    }
    public void changeScene(String s) {
        try {
            // Chargez le fichier FXML pour la nouvelle scène
            Parent root = FXMLLoader.load(getClass().getResource(s));
            PostList.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendPost() throws SQLException {
        String mess = PostField.getText();
        if(mess.isEmpty() && PostService.file == null){
            error.setText("The Post is empty !");

        }else{
            // Get the text from the PostField
            error.setText("");

            String text = PostField.getText();
            Timestamp currentTimestamp = new Timestamp( System.currentTimeMillis());

            // Create a new Post object
            Post post = new Post(text,currentTimestamp,userConnected);
            // add Post to the database
            ms.add(post);
            imageView.setImage(null);
            imageView.setFitWidth(0);
            imageView.setFitHeight(0);
            PostService.file=null;
            // Add the Post to the PostList
            PostList.getItems().add(post);
            initialize();

            // Clear the PostField
            PostField.clear();
        }

    }
    public void updateTitle(){
        changeScene("/modifyTitle.fxml");
    }

    /*
        Stage newStage = new Stage();

        public void emojiPopup() throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/emojis.fxml"));
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();

            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    String currentText = PostField.getText();
                    PostField.setText(currentText +" "+ emojis);
                    emojis = "";
                }
            });
        }
    */
    private void attachContextMenuToListView(ListView<Post> listView) {
        listView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Right-click detected
                Post selectedItem = listView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int PostId = selectedItem.getIdPost();

                    // Create the context menu
                    ContextMenu contextMenu = new ContextMenu();

                    // Create menu items
                    MenuItem updateMenuItem = new MenuItem("Update");
                    MenuItem deleteMenuItem = new MenuItem("Delete");
                    System.out.println(userConnected.equals(selectedItem.getSender()));
                    System.out.println(userConnected);
                    System.out.println(selectedItem);

                    if(userConnected.equals(selectedItem.getSender())) {
                        updateMenuItem.setOnAction(updateEvent -> {
                            int idPost = selectedItem.getIdPost();
                            String contentpost = selectedItem.getContentPost();
                            PostField.setText(contentpost);
                            sendButton.setOnAction(actionEvent -> {
                                if (PostField.getText().isEmpty()) {
                                    error.setText("The Post is empty !");
                                } else {
                                    Post post = new Post(idPost,PostField.getText());
                                    try {
                                        ms.update(post);
                                        PostField.clear();
                                        initialize();
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                            System.out.println("Update Post with ID: " + idPost);
                        });

                        // Handle delete menu item
                        deleteMenuItem.setOnAction(deleteEvent -> {
                            try {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation");
                                alert.setHeaderText("Look, a Confirmation Dialog");
                                alert.setContentText("Are you ok with deleting the Post?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    ms.delete(PostId);
                                    listView.getItems().remove(selectedItem);
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Delete Post with ID: " + PostId);
                        });
                    }
                    // Add menu items to context menu
                    contextMenu.getItems().addAll(updateMenuItem, deleteMenuItem);

                    // Show context menu at the mouse location
                    contextMenu.show(listView, event.getScreenX(), event.getScreenY());
                }
            }
        });

    }



}
