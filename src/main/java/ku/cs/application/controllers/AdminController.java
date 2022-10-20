package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class AdminController {
    @FXML private ListView<Users> userListView;
    @FXML private Label fnLabel;
    @FXML private Label usernameLabel;
    @FXML private Label intuitionLabel;
    @FXML private Label lastLoginLabel;
    @FXML private ImageView userImage;
    @FXML private ImageView rainbow;

    private DataSource<UserList> dataSource;
    private UserList userList;
    private Users user ;
    private Users admin;



    @FXML
    public void initialize() {
        user = (Users) FXRouter.getData();
        admin = (Users) FXRouter.getData();
        dataSource = new UserListDataSource("data","user.csv");
        userList = dataSource.readData();
        System.out.println(userList);
        showListView();
        clearSelectedUser();
        handleSelectedListView();
        String url2 = getClass().getResource("/ku/cs/admin_scence_images/rainbow.png").toExternalForm();
        rainbow.setImage(new Image(url2));
    }


    private void showListView() {
        userListView.getItems().setAll(userList.getAllUsers());
        userListView.refresh();
    }

    @FXML
    public void handleBack(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }



    private void handleSelectedListView() {
        userListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Users>() {
                    @Override
                    public void changed(ObservableValue<? extends Users> observableValue, Users users, Users t1) {
                        System.out.println(t1 + " is selected");
                        showSelectedUser(t1);
                    }
                });
    }
    private void showSelectedUser(Users user) {
        fnLabel.setText(user.getName());
        usernameLabel.setText(user.getUsername());
        intuitionLabel.setText(user.getId());
        String[] timeArr = user.getLastTimeLogin().split("-");
        String time = timeArr[0]+":"+timeArr[1]+":"+timeArr[2]+" "+timeArr[3]+"-"+timeArr[4]+"-"+timeArr[5];
        lastLoginLabel.setText(time);
        if (!user.getUserImage().equals("/ku/cs/student_image/default.png")){
            userImage.setImage(new Image("file:"+user.getUserImage()));
        }else {
            userImage.setImage(new Image(getClass().getResource("/ku/cs/student_image/default.png").toExternalForm()));
        }
    }


    private void clearSelectedUser(){
        fnLabel.setText("");
        intuitionLabel.setText("");
        usernameLabel.setText("");
        lastLoginLabel.setText("");
        userImage.setImage(null);

    }

    @FXML
    public void handleAdminComplaint(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admincomplaint",admin);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า complaint ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleManageBan(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin_manage_ban", admin);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_manage_ban ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleAdminAccount(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admin_account", admin);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_account ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleGoToOfficerSignUp(ActionEvent actionEvent){
        try {
            FXRouter.goTo("admin_signup_officer", admin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
