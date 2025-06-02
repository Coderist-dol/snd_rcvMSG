package com.example.websocket_demo.client;

import com.example.websocket_demo.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.catalina.User;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public final class mainGUI implements MessageListener{
    @FXML
    HBox userTabs;
    @FXML
    VBox userTabsContainer;

    private MyStompClient myStompClient;
    private String username;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean FXMLLoaded = true;
    private ArrayList<String> users = new ArrayList<>();


    public mainGUI() {}

    public void setUsername(String username) throws ExecutionException, InterruptedException {
        this.username = username;
        myStompClient = new MyStompClient(this, username);

    }

    public void start(Stage primaryStage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/websocket_demo/client/interface.fxml"));
            root = loader.load();

            this.stage = primaryStage;
            scene = new Scene(root, 1006,702);
            this.stage.setScene(scene);
            this.stage.setTitle("Client Application");
            this.stage.setResizable(false);

            primaryStage.setOnCloseRequest(event -> {
                int option = JOptionPane.showConfirmDialog(null, "Do you really want to leave?",
                        "Exit", JOptionPane.YES_NO_OPTION);

                if(option == JOptionPane.YES_OPTION){
                    myStompClient.disconnectUser(username);
                    primaryStage.close();
                }else {
                    event.consume();
                }
            });

            primaryStage.show();

        } catch (IOException e) {
            System.out.println("Cause of Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void addConnectedUsersComponents(ArrayList<User> users) {

    }

    @Override
    public void onMessageRecieve(Message message) {

    }

    @Override
    public void onActiveUsersUpdated(ArrayList<String> users) {

    }


    public void hoverEffect(MouseDragEvent mouseDragEvent) {
        System.out.println("hoverEffect");
    }
}
