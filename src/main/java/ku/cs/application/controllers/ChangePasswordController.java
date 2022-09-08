package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;

public class ChangePasswordController {

//    @FXML
//    public void handleAddEmailButton(ActionEvent actionEvent) {
//
//        // รับข้อมูลจาก TextField ข้อมูลที่รับเป็น String เสมอ
//        String addEmail = addEmailTextField.getText();
//
//        addEmailTextField.clear();
//    }

    @FXML
    private ImageView scku;
    @FXML
    private ImageView logoku;

    @FXML
    public void initialize() {
        String url = getClass().getResource("/ku/cs/pass_images/scku.jpeg").toExternalForm();
        scku.setImage(new Image(url));
        String url1 = getClass().getResource("/ku/cs/pass_images/logoku.png").toExternalForm();
        logoku.setImage(new Image(url1));
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
}