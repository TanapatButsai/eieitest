//package ku.cs.application.controllers;
//
//import javafx.fxml.FXML;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import ku.cs.application.models.Users;
//import com.github.saacsos.FXRouter;
//
//import java.io.IOException;
//
//public class UserAccountSceneController {
//    private Users user;
//    @FXML private ImageView imageBackGroundUrl;
//
//    @FXML
//    public void initialize(){
//        String url = getClass().getResource("/ku/cs/pass_images/scku.jpeg").toExternalForm();
//        imageBackGroundUrl.setImage(new Image(url));
//        user = (Users)FXRouter.getData();
//        System.out.println(user.toString());
//    }
//
//    @FXML
//    public void handleGoToHome(){
//        try {
//            FXRouter.goTo("home",user);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @FXML
//    public void handleGoToChangPassword(){
//        try {
//            FXRouter.goTo("changepassword",user);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
