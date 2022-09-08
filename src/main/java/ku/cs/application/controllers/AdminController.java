package ku.cs.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import ku.cs.application.services.UserListDataSource;

public class AdminController {
    @FXML private ListView<Users> userListView;

    private UserListDataSource ulds = new UserListDataSource();
    private UserList ul;

    @FXML
    public void initialize(){
        ul = ulds.getUserList();
        showListView();
    }

    private void showListView() {
        userListView.getItems().addAll(ul.getAllCards());
        userListView.refresh();
    }

}
