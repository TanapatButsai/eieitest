package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import ku.cs.application.services.UserListDataSource;

import java.io.IOException;

public class AdminController {
    @FXML private ListView<Users> userListView;

//    private UserListDataSource ulds = new UserListDataSource();
//    private UserList userList;


    @FXML
    public void initialize() {
//        userList = ulds.getUserList();
//        showListView();
//    }
    }
//
//    private void showListView() {
//        userListView.getItems().addAll(userList.getAllCards());
//        userListView.refresh();
//    }

    @FXML
    public void handleBackToLogin(){
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }



}
