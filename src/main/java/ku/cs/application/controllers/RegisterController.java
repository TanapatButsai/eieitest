package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class RegisterController {
    @FXML private ImageView bg;
    @FXML private ImageView logo01;

    @FXML public void initialize(){
        String url1 = getClass().getResource("/ku/cs/reg_images/bg002.jpg").toExternalForm();
        String url2 = getClass().getResource("/ku/cs/reg_images/logo001.jpg").toExternalForm();

        bg.setImage(new Image(url1));
        logo01.setImage(new Image(url2));
    }

    @FXML public void handleBackButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
}
