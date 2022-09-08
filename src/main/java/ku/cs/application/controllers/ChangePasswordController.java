package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;
import com.github.saacsos.FXRouter;
import java.io.IOException;


public class ChangePasswordController {
    @FXML private ImageView scku;
    @FXML private ImageView logoku;
    @FXML private TextField usernameTextField;
    @FXML private TextField oldPasswordTextField;
    @FXML private TextField newPasswordTextField;
    @FXML private DataSource<UserList> dataSource;
    @FXML private Label promptUsername;
    @FXML private Label promptPassword;
    @FXML private Label promptNewPassword;
    private UserList userList;


    @FXML
    public void initialize() {
        String url = getClass().getResource("/ku/cs/pass_images/scku.jpeg").toExternalForm();
        scku.setImage(new Image(url));
        String url1 = getClass().getResource("/ku/cs/pass_images/logoku.png").toExternalForm();
        logoku.setImage(new Image(url1));
        dataSource = new UserListDataSource("data", "user.csv");
        userList = dataSource.readData();
        if (userList == null) {
            System.err.println("ChangePasswordController : Cannot read file");
        } else {
            System.out.println("ChangePasswordController : Can read file");
        }
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("login");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleChangePassword(ActionEvent actionEvent){
        promptUsername.setText("");
        promptNewPassword.setText("");
        promptPassword.setText("");
        try {
            Users user = userList.findUser(usernameTextField.getText());
            if (usernameTextField.getText().isEmpty()){
                System.err.println("username TextField is empty");
                promptUsername.setText("Enter username");

            } else if (user == null) {
                System.err.println("Username is not exist");
                promptUsername.setText("Wrong username");
            } else if (isChangePassword()){
                FXRouter.goTo("login");
            }
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    private boolean isChangePassword() {
        Users user = userList.findUser(usernameTextField.getText());
        String usernameUser = user.getUsername();
        String passwordUser = user.getPassword();
        if (usernameTextField.getText().isEmpty() || oldPasswordTextField.getText().isEmpty()
                || newPasswordTextField.getText().isEmpty() || newPasswordTextField.getText().contains(",")
                || !oldPasswordTextField.getText().equals(passwordUser)) {
            if (newPasswordTextField.getText().contains(",")){
                System.err.println("cannot use \" , \"");
                promptNewPassword.setText("cannot use \" , \"");
            }
            if (usernameTextField.getText().isEmpty()){
                System.err.println("Username TextField is empty");
                promptUsername.setText("Enter username");
            }
            if (oldPasswordTextField.getText().isEmpty()){
                System.err.println("Old password TextField is empty");
                promptPassword.setText("Enter password");
            } else if (!oldPasswordTextField.getText().equals(passwordUser)) {
                System.err.println("Old password TextField is not match");
                promptPassword.setText("Wrong password");
            }
            if (newPasswordTextField.getText().isEmpty()){
                System.err.println("New  username TextField is empty");
                promptNewPassword.setText("Enter new password");
            }
            if (!usernameTextField.getText().equals(usernameUser)) {
                System.err.println("Wrong username : input username is not exist");
            }
            return false;
        } else {
            System.out.println("Can change password");
            userList.removeUser(user);
            user.setPassword(newPasswordTextField.getText());
            userList.addUser(user);
            dataSource.writeData(userList);
            return true;


        }
    }

}