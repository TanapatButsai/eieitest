package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class TutorialStudent {
    private int x;
    private String url;
    @FXML private ImageView image;

    @FXML public void initialize(){
        x = 1;
        show();
    }
    @FXML
    void next(ActionEvent event) {
        x = x+1;
        show();
    }
    public void show(){
        if (x == 1){
            url = getClass().getResource("/ku/cs/tutorial_image/1.JPG").toExternalForm();
        }if (x == 2){
            url = getClass().getResource("/ku/cs/tutorial_image/2.JPG").toExternalForm();
        }if (x == 3){
            url = getClass().getResource("/ku/cs/tutorial_image/3.JPG").toExternalForm();
        }if (x == 4){
            url = getClass().getResource("/ku/cs/tutorial_image/4.JPG").toExternalForm();
        }if (x == 5){
            url = getClass().getResource("/ku/cs/tutorial_image/5.JPG").toExternalForm();
        }if (x == 6){
            url = getClass().getResource("/ku/cs/tutorial_image/6.JPG").toExternalForm();
        }if (x == 7){
            url = getClass().getResource("/ku/cs/tutorial_image/7.JPG").toExternalForm();
        }if (x == 8){
            url = getClass().getResource("/ku/cs/tutorial_image/8.JPG").toExternalForm();
        }if (x == 10){
            url = getClass().getResource("/ku/cs/tutorial_image/10.JPG").toExternalForm();
        }if (x == 11) {
            url = getClass().getResource("/ku/cs/tutorial_image/11.JPG").toExternalForm();
        }if (x == 12) {
            url = getClass().getResource("/ku/cs/tutorial_image/12.JPG").toExternalForm();
        }
        image.setImage(new Image(url));
    }
    @FXML
    void handleGoToHome(ActionEvent event) {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

