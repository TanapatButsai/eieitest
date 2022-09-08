package ku.cs.application.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
public class TeacherComplaintController {
    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    private ImageView KU_9non;
    @FXML
    public void initialize() {
        String url = getClass().getResource("/ku/cs/teachercomplaint_images/KU_9non.jpeg").toExternalForm();
        KU_9non.setImage(new Image(url));
    }
}
