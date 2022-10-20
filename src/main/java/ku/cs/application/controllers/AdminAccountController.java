package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;
import com.github.saacsos.FXRouter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class AdminAccountController {
    @FXML private ImageView userImage;
    @FXML private Label usernameLabel;
    @FXML private Label nameLabel;
    @FXML private Label studentIDLabel;
    private Users user;
    private Users admin;
    private UserList userList;
    DataSource<UserList> dataSource = new UserListDataSource("data","user.csv");


    @FXML private void initialize(){
        admin = (Users) FXRouter.getData();
        user = (Users)FXRouter.getData();
        userList = dataSource.readData();
        showAdminInfo();
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
                //Delete previous picture
                if (!user.getUserImage().equals("/ku/cs/student_image/default.png")){
                    File deleteDestDir = new File("");
                    Path pathDelete = FileSystems.getDefault().getPath(deleteDestDir.getAbsolutePath()
                            +System.getProperty("file.separator")+user.getUserImage());
                    Files.delete(pathDelete);
                }
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                userImage.setImage(new Image(target.toUri().toString()));
                userList.setImageStudent(user.getUsername(),destDir + File.separator + filename);
                user.setUserImage(destDir + File.separator + filename);
                dataSource.writeData(userList);
                // SET NEW FILE PATH TO IMAGE
            } catch (IOException e) {
                e.printStackTrace();
            }

//            showStudentImage();
        }
    }

    @FXML void showAdminInfo() {
        usernameLabel.setText(user.getUsername());
        nameLabel.setText(user.getName());
        studentIDLabel.setText(user.getId());
        showAdminImage();
    }


    @FXML void showAdminImage(){
        if (user.getUserImage() != null){
            userImage.setImage(new Image("file:"+user.getUserImage()));
        }
    }

    @FXML
    public void handleGoToAdminChangePassword(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin_change_password",admin);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_change_password ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleBack(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("adminscene",admin);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_scene ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }






}
