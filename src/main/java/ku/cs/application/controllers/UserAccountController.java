package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.github.saacsos.FXRouter;
import javafx.stage.FileChooser;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import javafx.scene.control.Button;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class UserAccountController {
    private Users user;
    @FXML
    private ImageView imageBackGroundUrl;
    @FXML
    private ImageView logoku;
    @FXML private ImageView userImage;
    @FXML
    private Label promptNewPassword;
    @FXML
    private Label promptPassword;
    @FXML
    private Label promptUsername;
    @FXML private Button updateInfoButton;

    @FXML private Label nameLabel;
    @FXML private Label idLabel;
    @FXML private Label usernameLabel;
    private UserList userList;

    private String imageUrl;
    DataSource<UserList> dataSource = new UserListDataSource("data","user.csv");
    @FXML private void initialize(){
        user = (Users)FXRouter.getData();
        String url = getClass().getResource("/ku/cs/user_account_scene_image/logoku.png").toExternalForm();
        String url1 = getClass().getResource("/ku/cs/user_account_scene_image/background.jpg").toExternalForm();
        imageBackGroundUrl.setImage(new Image(url1));
        logoku.setImage(new Image(url));
        userList = dataSource.readData();
        showStudentInfo();
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
    @FXML
    public void handleUpdateButton(ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add
                (new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));

        Node source = (Node) actionEvent.getSource();
        File file = fileChooser.showOpenDialog(source.getScene().getWindow());
        if (file != null){
            try {
                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("data/images/profile/");
                if (!destDir.exists()) destDir.mkdirs();
                // RENAME FILE
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_"+System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath()+System.getProperty("file.separator")+filename);
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );

                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                userImage.setImage(new Image(target.toUri().toString()));

                userList.setImageStudent(user.getUsername(),destDir + File.separator + filename);
                user.setUserImage(destDir + File.separator + filename);

                dataSource.writeData(userList);

                // SET NEW FILE PATH TO IMAGE
            } catch (IOException e) {
                e.printStackTrace();
            }
            showStudentImage();
        }
    }
    @FXML void showStudentInfo(){
        usernameLabel.setText(user.getUsername());
        idLabel.setText(user.getId());
        nameLabel.setText(user.getName());
        showStudentImage();
    }
    @FXML void showStudentImage(){

        if (user.getUserImage() != null){
            userImage.setImage(new Image("file:"+user.getUserImage()));
        }
    }
}


