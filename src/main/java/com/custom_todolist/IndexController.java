package com.custom_todolist;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class IndexController {
    @FXML
    private Label indexText;

    @FXML
    protected void onHelloButtonClick() {
        indexText.setText("Welcome to JavaFX Application!");
    }
}