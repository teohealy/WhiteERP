package com.example.whiteerp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public interface Controllable {
    void showNewScene(String fxml);
    void openNewScene(Button button, String window);

}
