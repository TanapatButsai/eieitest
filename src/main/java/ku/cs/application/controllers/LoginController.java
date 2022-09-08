package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.control.Label;
import com.github.saacsos.FXRouter;
import ku.cs.application.models.UserList;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;
import ku.cs.application.models.Users;

import javax.net.ssl.SSLContext;

public class LoginController {
    //Admin ID PASSWORD
    String adminUsername = "admin";
    String adminPassword = "123456";
    @FXML private Label textError;
    @FXML private TextField inputUsername;
    @FXML private TextField inputPassword;
    String url
            = getClass().getResource("/ku/cs/login_images/ku_view.jpg").toExternalForm();
    String url2
            = getClass().getResource("/ku/cs/login_images/KU_SubLogo_Thai.png").toExternalForm();
    @FXML
    private ImageView image_view_login;
    @FXML
    private ImageView image_view_ku_logo;


    private DataSource<UserList> dataSource;
    private UserList userList;
    @FXML
    public void initialize() {
        image_view_login.setImage(new Image(url));
        image_view_ku_logo.setImage(new Image(url2));
        dataSource = new UserListDataSource("data","user.csv");
        userList = dataSource.readData();
        if (userList == null){
            System.err.println("Cannot read file");
        } else {
            System.out.println("Can read file");
        }
    }

    @FXML
    public void handleSignIn(ActionEvent actionEvent) {
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        Users user = userList.findUser(username);

        if (username.isEmpty() || password.isEmpty()){
            textError.setText("Enter username and password");
            System.err.println("TextField is empty");
        } else if (Objects.equals(adminUsername, username) && Objects.equals(adminPassword, password)){
            try {
                FXRouter.goTo("home");

            } catch (IOException e) {
                System.err.println("ไปที่หน้า home");
                System.err.println("ให้ตรวจสอบการกำหนด route");
                e.printStackTrace();
            }
        } else if (user == null || !user.getPassword().equals(password)) {
            System.err.println("Wrong username or password");
            textError.setText("Wrong username or password");
        } else if (isLogin(username,password,user)) {
            try {
                FXRouter.goTo("home",user);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า home");
                System.err.println("ให้ตรวจสอบการกำหนด route");
                e.printStackTrace();
            }
        }
        inputUsername.clear();// clear ช่อง TextField
        inputPassword.clear();
    }

    @FXML
    public void handleGoToHome(ActionEvent actionEvent){
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleGoToRegister(ActionEvent actionEvent){
        try {
            FXRouter.goTo("register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า register");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleGoToForgetPassword(ActionEvent actionEvent){
        try {
            FXRouter.goTo("forgetpassword");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า forgetpassword");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    public boolean isLogin(String username, String password,Users user){
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }
}


