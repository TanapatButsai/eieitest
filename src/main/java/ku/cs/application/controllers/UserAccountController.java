package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.github.saacsos.FXRouter;
import ku.cs.application.models.Users;

import java.io.IOException;

public class UserAccountController {
    private Users user;
    @FXML
    private ImageView imageBackGroundUrl;

    @FXML
    private ImageView logoku;

    @FXML
    private Label promptNewPassword;

    @FXML
    private Label promptPassword;

    @FXML
    private Label promptUsername;
    @FXML private void initialize(){
        user = (Users)FXRouter.getData();
        String url = getClass().getResource("/ku/cs/pass_images/scku.jpeg").toExternalForm();
        String url1 = getClass().getResource("/ku/cs/pass_images/logoku.png").toExternalForm();
        imageBackGroundUrl.setImage(new Image(url));
        logoku.setImage(new Image(url1));
    }
    @FXML
    void handleGoToChangPassword(ActionEvent event) {
        try {
            FXRouter.goTo("changepassword",user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleGoToHome(ActionEvent event) {
        try {
            FXRouter.goTo("home",user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}