package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.github.saacsos.FXRouter;

import java.io.File;
import java.io.IOException;

public class CreditController {
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;

        String url
                = getClass().getResource("/ku/cs/creditImages/creditmember1.jpg").toExternalForm();
        String url2
                = getClass().getResource("/ku/cs/creditImages/creditmember2.jpg").toExternalForm();
        String url3
                = getClass().getResource("/ku/cs/creditImages/creditmember3.jpg").toExternalForm();
        String url4
                = getClass().getResource("/ku/cs/creditImages/creditmember4.png").toExternalForm();
    @FXML
    public void initialize() {
        image1.setImage(new Image(url));
        image2.setImage(new Image(url2));
        image3.setImage(new Image(url3));
        image4.setImage(new Image(url4));
    }


    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
}