package ku.cs.application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ku.cs.application.models.*;
import ku.cs.application.services.*;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class AdminManageBanController {
    @FXML private ListView<Ban> banListView;
    @FXML private Label nameLabel;
    @FXML private Label tryLogInLabel;
    @FXML private Label reasonLabel;
    @FXML private Label reqLabel;
    @FXML
    private MenuItem handleShowAllBan;
    @FXML
    private Label promptLabel;
    @FXML
    private MenuItem handleShowAllRequestUnban;

    private DataSource<BanList> banListDataSource;
    private BanList banList;
    private Ban ban;
    private Users admin;

    private DataSource<UserList> userListDataSource;
    private UserList userList;

    @FXML
    public void initialize() {
        admin = (Users) FXRouter.getData();
        banListDataSource = new BanListDataSource(false);
        userListDataSource = new UserListDataSource("data","user.csv");
        userList = userListDataSource.readData();
        banList = banListDataSource.readData();
        System.out.println(banList);
        showListView();
        clearSelectedBan();
        handleSelectedListView();
        setMenuItemTime();
    }

    private void showListView() {
        banListView.getItems().setAll(banList.getBanList());
        clearSelectedBan();
        banListView.refresh();
    }
    public void showRequestUnbanListView(){
        banListView.getItems().setAll(banList.getAllReqUnBan());
        clearSelectedBan();
        banListView.refresh();
    }


    private void handleSelectedListView() {
        banListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Ban>() {
                    @Override
                    public void changed(ObservableValue<? extends Ban> observableValue, Ban ban, Ban t1) {
                        System.out.println(t1 + " is selected");
                        thisIsBan(t1);
                    }
                });
    }
    public void setMenuItemTime() {

        handleShowAllBan.setOnAction(actionEvent -> {
            clearSelectedBan();
            showListView();
        });
        handleShowAllRequestUnban.setOnAction(actionEvent -> {
            clearSelectedBan();
            showRequestUnbanListView();

        });

    }
    private void showSelectedBan(Ban ban) {

        if (!(ban == null)){
            nameLabel.setText(ban.getUser());
            String tl = String.valueOf(ban.getTryLogin());
            tryLogInLabel.setText(tl);
            reasonLabel.setText(ban.getBannedReason());
            reqLabel.setText(ban.getRequest());
            if (ban.getRequest().equals("none")){
                promptLabel.setText("คำร้องขอคืนสิทธิ์");
                reqLabel.setText(ban.getRequest());
            }
        }
    }
    public void thisIsBan(Ban ban){
        this.ban = ban;
        showSelectedBan(ban);
    }

    private void clearSelectedBan(){
        nameLabel.setText("");
        tryLogInLabel.setText("");
        reasonLabel.setText("");
        reqLabel.setText("");
        promptLabel.setText("");
        reqLabel.setText("");
    }
    @FXML
    private void handleUnBanButton(ActionEvent event) {

        if (!(ban == null)){
            if (!ban.getRequest().equals("none")){
                banList.unban(ban.getBannedID());
                userList.unban(ban.getUser());
                userListDataSource.writeData(userList);
                banListDataSource.writeData(banList);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("แบนแล้วเรียบร้อย");
                alert.showAndWait();
                clearSelectedBan();
                showListView();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("ยังไม่มีการส่งคำร้องของผู้ถูกแบน");
                alert.showAndWait();
            }
        }
//กูมั่ว
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