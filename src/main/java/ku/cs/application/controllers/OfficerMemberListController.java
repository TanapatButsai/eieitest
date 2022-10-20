package ku.cs.application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.application.models.*;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.OfficeListDataSource;
import ku.cs.application.services.UserListDataSource;

import java.io.IOException;

public class OfficerMemberListController {
    private Officer userOfficer;
    @FXML private Label officerUsernameLabel;
    @FXML private Label officerNameLabel;
    @FXML private Label officerLastLoginLabel;
    @FXML private ListView<String> officerMemberListView;
    @FXML private ImageView OfficerImageView;
    private DataSource<UserList> ulds;
    private UserList userList;
    private OfficeList officerIDList;
    private DataSource<OfficeList> officerDataSource;
    @FXML
    public void initialize(){
        userOfficer = (Officer) com.github.saacsos.FXRouter.getData();
        ulds = new UserListDataSource("data","user.csv");
        userList = ulds.readData();
        officerDataSource = new OfficeListDataSource("data","officer.csv");
        officerIDList = officerDataSource.readData();
        showListView();
        handleSelectedListView();
        officerNameLabel.setText("");
        officerUsernameLabel.setText("");
        officerLastLoginLabel.setText("");
    }
    private void showListView() {
        officerMemberListView.getItems().setAll(userList.getAllRoleOfficers(userOfficer.getRole(), officerIDList));
//        officerMemberListView.getItems().setAll(userList);
        officerMemberListView.refresh();
    }
    private void handleSelectedListView() {
        officerMemberListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                showSelectedUser(newValue);
            }
        });
    }

    private void showSelectedUser(String newValue) {
        Users data = userList.find(newValue);
        officerUsernameLabel.setText(data.getUsername());
        officerNameLabel.setText(data.getName());
        officerLastLoginLabel.setText(data.getLastTimeLogin());
        OfficerImageView.setImage(new Image("file:"+data.getUserImage()));
    }


    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("officer", userOfficer);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
