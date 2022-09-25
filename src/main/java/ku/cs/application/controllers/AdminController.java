package ku.cs.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;

import java.io.IOException;

public class AdminController {
    @FXML private ListView<Users> userListView;

    private DataSource<UserList> ulds;
    private UserList userList;

    @FXML
    public void initialize() {
        ulds = new UserListDataSource("data","user.csv");
        userList = ulds.readData();
        showListView();
    }

//
    private void showListView() {
        userListView.getItems().addAll(userList.getAllCards());
        userListView.refresh();
    }

    @FXML
    public void handleBackToLogin(){
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
