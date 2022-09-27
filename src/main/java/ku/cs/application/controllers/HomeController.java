package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Label;
import ku.cs.application.models.Users;

import java.io.IOException;

public class HomeController {
    private Users user;
    @FXML private Label userLabel;
    @FXML
    public void initialize(){
        user = (Users)FXRouter.getData();
        userLabel.setText(user.getFullName());
        System.out.println(user.toString());
    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleNormalComplaint(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("normalcomplaint",user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนทั่วไป ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleTeacherComplaint(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("teachercomplaint",user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนอาจารย์/บุคลากร ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handlePlaceComplaint(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("placecomplaint",user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนอาคาร/สถานที่ ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleEnrollComplaint(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("enrollcomplaint",user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนการลงทะเบียนเรียน ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleGoToUserAccountScene(ActionEvent actionEvent){
        try {
            FXRouter.goTo("user_account",user);

        } catch (IOException e) {
            System.err.println("ไปที่หน้า changepassword");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
}
