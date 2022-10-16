package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.github.saacsos.FXRouter;
public class ProjectOpeningController {
    private boolean xx = false;
//    @FXML
//    public void initialize(){
//        timee();
//        System.out.println("yaaaa");
//    }
//    public void timee(){
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            int cancelTime = 5;
//            @Override
//            public void run() {
//                cancelTime --;
//                if (cancelTime == 0) {
//                    timer.cancel();
//                }
//                System.out.println(cancelTime);
//            }
//        };
//        timer.scheduleAtFixedRate(task,1000,1000);
//        System.out.println("ay");
//    }
    @FXML
    void goToLogin(ActionEvent event) {
        try {
            FXRouter.goTo("login");
            FXRouter.setAnimationType("fade", 2000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//    public void go(){
//        try {
//            FXRouter.goTo("login");
//            FXRouter.setAnimationType("fade", 2000);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
