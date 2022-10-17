package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.github.saacsos.FXRouter;
public class ProjectOpeningController {
    @FXML
    void goToLogin(ActionEvent event) {
        try {
            FXRouter.setAnimationType("fade", 2000);
            FXRouter.goTo("login");
            FXRouter.setAnimationType("fade", 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
