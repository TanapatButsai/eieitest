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

    @FXML
    public void initialize() {

        image1.setImage(new Image
                (new File("src" + File.separator +
                        "main" + File.separator +
                        "resources" + File.separator +
                        "ku" + File.separator +
                        "cs" + File.separator +
                        "creditImages" + File.separator +
                        "creditmember1.jpg").toURI().toString()));
        image2.setImage(new Image
                (new File("src" + File.separator +
                        "main" + File.separator +
                        "resources" + File.separator +
                        "ku" + File.separator +
                        "cs" + File.separator +
                        "creditImages" + File.separator +
                        "creditmember2.jpg").toURI().toString()));
        image3.setImage(new Image
                (new File("src" + File.separator +
                        "main" + File.separator +
                        "resources" + File.separator +
                        "ku" + File.separator +
                        "cs" + File.separator +
                        "creditImages" + File.separator +
                        "creditmember3.jpg").toURI().toString()));
        image4.setImage(new Image
                (new File("src" + File.separator +
                        "main" + File.separator +
                        "resources" + File.separator +
                        "ku" + File.separator +
                        "cs" + File.separator +
                        "creditImages" + File.separator +
                        "creditmember4.png").toURI().toString()));
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