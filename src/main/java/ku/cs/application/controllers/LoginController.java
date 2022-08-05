package ku.cs.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController {
    String url = getClass().getResource("/ku/cs/login_images/ku_view.jpg").toExternalForm();
    @FXML
    private ImageView image_view_login;
    @FXML
    public void initialize() {
        image_view_login.setImage(new Image(url));
    }



}
