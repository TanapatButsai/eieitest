package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;

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
        FXRouter.when("home",packageStr + "home.fxml");
        FXRouter.when("forgetpassword",packageStr + "forgetpassword.fxml");

    }

    public static void main(String[] args) {
        launch();
    }
}
