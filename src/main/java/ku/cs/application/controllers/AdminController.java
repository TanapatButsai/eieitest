package ku.cs.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import ku.cs.application.services.UserListDataSource;

public class AdminController {
    @FXML private ListView<Users> userListView;

    private UserListDataSource ulds = new UserListDataSource();
    private UserList userList;

    @FXML
    public void initialize(){
        userList = ulds.getUserList();
        showListView();
    }

    private void showListView() {
        userListView.getItems().addAll(userList.getAllCards());
        userListView.refresh();
    }

}
