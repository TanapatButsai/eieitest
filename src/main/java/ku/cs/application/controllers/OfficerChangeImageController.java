package ku.cs.application.controllers;

import javafx.fxml.FXML;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.github.saacsos.FXRouter;
import javafx.stage.FileChooser;
import ku.cs.application.models.Officer;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class OfficerChangeImageController {
        @FXML
        private Label idLabel;

        @FXML
        private ImageView imageBackGroundUrl;

        @FXML
        private ImageView logoKU;

        @FXML
        private Label nameLabel;

        @FXML
        private Label promptPassword;

        @FXML
        private Button updateInfoButton;

        @FXML
        private ImageView userImage;

        @FXML
        private Label usernameLabel;
        private DataSource<UserList> dataSource = new UserListDataSource("data","user.csv");
        private UserList userList;
        private Users officer;
        @FXML public void initialize(){
            officer = (Officer)  FXRouter.getData();
            userList = dataSource.readData();
            idLabel.setText(officer.getId());
            usernameLabel.setText(officer.getUsername());
            nameLabel.setText(officer.getName());
            showInfo();
            String url = getClass().getResource("/ku/cs/user_account_scene_image/bird.png").toExternalForm();
            String url1 = getClass().getResource("/ku/cs/user_account_scene_image/greenposter.png").toExternalForm();
            imageBackGroundUrl.setImage(new Image(url1));
            logoKU.setImage(new Image(url));
        }
        @FXML
        void handleGoToHome(ActionEvent event) {
            try {
                FXRouter.goTo("officer",officer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @FXML
        void handleUpdateButton(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            fileChooser.getExtensionFilters().add
                    (new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
            Node source = (Node) event.getSource();
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
                    //Delete previous picture
                    if (!officer.getUserImage().equals("/ku/cs/student_image/default.png")){
                        File deleteDestDir = new File("");
                        Path pathDelete = FileSystems.getDefault().getPath(deleteDestDir.getAbsolutePath()
                                +System.getProperty("file.separator")+officer.getUserImage());
                        Files.delete(pathDelete);
                    }
                    // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                    userImage.setImage(new Image(target.toUri().toString()));
                    userList.setImageStudent(officer.getUsername(),destDir + File.separator + filename);
                    officer.setUserImage(destDir + File.separator + filename);
                    dataSource.writeData(userList);
                    // SET NEW FILE PATH TO IMAGE
                } catch (IOException e) {
                    e.printStackTrace();
                }
                showImage();
            }
        }
    @FXML void showInfo(){
        usernameLabel.setText(officer.getUsername());
        idLabel.setText(officer.getId());
        nameLabel.setText(officer.getName());
        showImage();
    }

    @FXML void showImage(){
        if (officer.getUserImage() != null) {
            if (officer.getUserImage().equals("/ku/cs/student_image/default.png")) {
                String im = (getClass().getResource("/ku/cs/student_image/default.png").toExternalForm());
                userImage.setImage(new Image(getClass().getResource("/ku/cs/student_image/default.png").toExternalForm()));

            }else {
                String im = ("file:"+officer.getUserImage());
                userImage.setImage(new Image(im));
            }
        }
    }
}