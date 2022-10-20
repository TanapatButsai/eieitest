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
        FXRouter.setAnimationType("fade", 3000);
        FXRouter.goTo("project_opening_scene");


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
        FXRouter.when("officer_change_password",packageStr + "officer_change_password.fxml");
        FXRouter.when("officer_list_member",packageStr + "officer_list_member.fxml");
        FXRouter.when("admincomplaint",packageStr + "admin_complaint_scene.fxml");
        FXRouter.when("user_account",packageStr + "user_account.fxml");
        FXRouter.when("admin_manage_ban",packageStr + "admin_manage_ban.fxml");
        FXRouter.when("user_complaint_list",packageStr + "user_complaint_list.fxml");
        FXRouter.when("selected_complaint_detail",packageStr+"selected_complaint_detail.fxml");
        FXRouter.when("report_complaint",packageStr+"report_complaint.fxml");
        FXRouter.when("admin_selected_report_complaint",packageStr+"admin_selected_report_complaint.fxml");
        FXRouter.when("admin_ban_reason",packageStr + "admin_ban_reason.fxml");
        FXRouter.when("project_opening_scene",packageStr+"project_opening_scene.fxml");
        FXRouter.when("admin_account",packageStr+"admin_account.fxml");
        FXRouter.when("admin_change_password",packageStr+"admin_change_password.fxml");
        FXRouter.when("admin_signup_officer",packageStr+"admin_signup_officer.fxml");
        FXRouter.when("user_request_unban",packageStr+"user_request_unban.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}
