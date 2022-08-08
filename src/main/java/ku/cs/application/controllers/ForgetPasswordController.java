package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class ForgetPasswordController {

//    @FXML
//    public void handleAddEmailButton(ActionEvent actionEvent) {
//
//        // รับข้อมูลจาก TextField ข้อมูลที่รับเป็น String เสมอ
//        String addEmail = addEmailTextField.getText();
//
//        addEmailTextField.clear();
//    }


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