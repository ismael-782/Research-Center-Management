package com.example;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;

public class logInController implements Initializable {
    private static String userEmail="";
    public static String id;



    @FXML
    private PasswordField Password;

    @FXML
    private Button Sign_In_Button;

    @FXML
    private TextField Username;

    @FXML
    private Label Wrong_Log_In;

    @FXML
    private Label showPass;

    @FXML
    private ToggleButton showPassButton;

    @FXML
    void CheckLogIn(ActionEvent event) throws IOException {
        Authenticate();
    }

    @FXML
    void PasswordFieldKeyTyped(KeyEvent event) {
        showPass.textProperty().bind(Bindings.concat(Password.getText()));

    }

    @FXML
    void showPass(ActionEvent event) {
        if (showPassButton.isSelected()) {
            showPass.setVisible(true);
            showPass.textProperty().bind(Bindings.concat(Password.getText()));
            showPassButton.setText("Hide Password");
        } else {
            showPass.setVisible(false);
            showPassButton.setText("Show Password");
        }
    }

    public static String getUserEmail() {
        return userEmail;
    }

    private void Authenticate() throws IOException {

        User x = new User(Username.getText().toString(), Password.getText().toString());
        if (Username.getText().isEmpty() || Password.getText().isEmpty()) {
            Wrong_Log_In.setText("Fill All Fields");
        }
        else {
            if (x.authenticate_user(Username.getText().toString(), Password.getText().toString())) {
                if (x.searchInAdminFile(Username.getText().substring(1, 9), Password.getText())) {
                    Wrong_Log_In.setText("Success Admin");
                    AdminMainPageController.transfare_admin_data(Username.getText().substring(1, 9),Password.getText());
                    App.setRoot("AdminMainPage");


                } else {
                    String id1= Username.getText().toString().substring(1,9);
                    id=id1;
                    Wrong_Log_In.setText("Success Member");
                    Admin.SetAll();
                    App.setRoot("hello-view");
                    userEmail=Username.getText().toString();
//                    App.setRoot("hello-view");
                }
            } else {
                Wrong_Log_In.setText("Wrong username or password!");
            }
            }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        showPass.setVisible(false);
        ///admin.read

    }

}
