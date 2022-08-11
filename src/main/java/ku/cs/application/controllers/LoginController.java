package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class LoginController {
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
    public void handleGoToHome(ActionEvent actionEvent){
        try {
            // เปลี่ยนการแสดงผลไปที่ route ที่ชื่อ member_card_detail
            // พร้อมส่ง reference instance john ไปด้วย
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    public void handleGoToRegister(ActionEvent actionEvent){
        try {

            com.github.saacsos.FXRouter.goTo("register");
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
            com.github.saacsos.FXRouter.goTo("forgetpassword");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า forgetpassword");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
}


