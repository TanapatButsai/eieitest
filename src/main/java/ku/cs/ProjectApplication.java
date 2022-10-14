package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;
import ku.cs.application.services.Filterer;

import java.io.IOException;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "DingTwoProject", 800, 600);
        configRoute();
        FXRouter.goTo("login");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("project", packageStr + "project.fxml");
        FXRouter.when("login", packageStr + "login.fxml");
        FXRouter.when("register",packageStr + "register.fxml");
        FXRouter.when("credit", packageStr + "credit.fxml");
        FXRouter.when("home",packageStr + "home.fxml");
        FXRouter.when("changepassword",packageStr + "changepassword.fxml");
        FXRouter.when("normalcomplaint", packageStr + "normalcomplaint.fxml");
        FXRouter.when("teachercomplaint", packageStr + "teachercomplaint.fxml");
        FXRouter.when("placecomplaint", packageStr + "placecomplaint.fxml");
        FXRouter.when("enrollcomplaint", packageStr + "enrollcomplaint.fxml");
        FXRouter.when("corruptcomplaint", packageStr + "corruptcomplaint.fxml");
        FXRouter.when("adminscene",packageStr + "adminscene.fxml");
        FXRouter.when("officer",packageStr + "officer.fxml");
        FXRouter.when("admincomplaint",packageStr + "admin_complaint_scene.fxml");
        FXRouter.when("user_account",packageStr + "user_account.fxml");
        FXRouter.when("adminmanageban", packageStr + "admin_manage_ban.fxml");
        FXRouter.when("user_complaint_list",packageStr + "user_complaint_list.fxml");
        FXRouter.when("selected_complaint_detail",packageStr+"selected_complaint_detail.fxml");
        FXRouter.when("report_complaint",packageStr+"report_complaint.fxml");
        FXRouter.when("admin_selected_report_complaint",packageStr+"admin_selected_report_complaint.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}
