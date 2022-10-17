package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;

import java.io.IOException;

public class AdminController {
    @FXML private ListView<Users> userListView;
    @FXML private Label fnLabel;
    @FXML private Label usernameLabel;
    @FXML private Label intuitionLabel;
    @FXML private Label lastLoginLabel;

    private DataSource<UserList> ulds;
    private UserList userList;


    @FXML
    public void initialize() {
        ulds = new UserListDataSource("data","user.csv");
        userList = ulds.readData();
        System.out.println(userList);
        showListView();
        clearSelectedUser();
        handleSelectedListView();
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
        intuitionLabel.setText(user.getId());
        usernameLabel.setText(user.getUsername());
        String[] timeArr = user.getLastTimeLogin().split("-");
        String time = timeArr[0]+":"+timeArr[1]+":"+timeArr[2]+" "+timeArr[3]+"-"+timeArr[4]+"-"+timeArr[5];
        lastLoginLabel.setText(time);
    }

    private void clearSelectedUser(){
        fnLabel.setText("");
        intuitionLabel.setText("");
        usernameLabel.setText("");
        lastLoginLabel.setText("");
    }

    @FXML
    public void handleAdminComplaint(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("admincomplaint");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า complaint ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleManageBan(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("adminmanageban");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า manageban ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
