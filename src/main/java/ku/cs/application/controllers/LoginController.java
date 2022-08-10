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

public class LoginController {
    //Admin ID PASSWORD
    String adminUsername = "admin";
    String adminPassword = "123";

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



    @FXML
    public void initialize() {
        image_view_login.setImage(new Image(url));
        image_view_ku_logo.setImage(new Image(url2));


    }
    @FXML
    public void handleSignIn(ActionEvent actionEvent) {

        if (Objects.equals(adminUsername, inputUsername.getText()) && Objects.equals(adminPassword, inputPassword.getText())){
            try {
                FXRouter.goTo("home");


            } catch (IOException e) {
                System.err.println("ไปที่หน้า home");
                System.err.println("ให้ตรวจสอบการกำหนด route");
                e.printStackTrace();
            }
        }else {
            textError.setText("wrong username or password");
        }
        inputUsername.clear() ;// clear ช่อง TextField
        inputPassword.clear();
    }
    @FXML
    public void handleGoToHome(ActionEvent actionEvent){
        try {
            // เปลี่ยนการแสดงผลไปที่ route ที่ชื่อ member_card_detail
            // พร้อมส่ง reference instance john ไปด้วย
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    public void handleGoToRegister(ActionEvent actionEvent){
        try {
            // เปลี่ยนการแสดงผลไปที่ route ที่ชื่อ member_card_detail
            // พร้อมส่ง reference instance john ไปด้วย
            FXRouter.goTo("register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า register");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    public void handleGoToForgetPassword(ActionEvent actionEvent){
        try {
            // เปลี่ยนการแสดงผลไปที่ route ที่ชื่อ member_card_detail
            // พร้อมส่ง reference instance john ไปด้วย
            FXRouter.goTo("forgetpassword");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า forgetpassword");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
}


