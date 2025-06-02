package com.example.websocket_demo.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class App{
    private static String username;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                getUsername();

                while(username == null || username.isEmpty() || username.length() > 16){
                    JOptionPane.showMessageDialog(null,
                            "Invalid Username",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    getUsername();
                }

                try {
                    Platform.startup(() -> {
                        Stage stage = new Stage();
                        try {
                            mainGUI mainGUI = new mainGUI();
                            mainGUI.setUsername(username);
                            mainGUI.start(stage);
                        } catch (IOException | ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void getUsername() {
        username = JOptionPane.showInputDialog(null,
                "Enter Username (Max: 16 Characters): ",
                "Chat Application",
                JOptionPane.QUESTION_MESSAGE);
    }

}
