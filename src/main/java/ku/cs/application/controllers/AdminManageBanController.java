package ku.cs.application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ku.cs.application.models.Ban;
import ku.cs.application.models.BanList;
import ku.cs.application.models.Complaint;
import ku.cs.application.models.Users;
import ku.cs.application.services.BanListDataSource;
import ku.cs.application.services.DataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class AdminManageBanController {
    @FXML private ListView<Ban> banListView;
    @FXML private Label nameLabel;
    @FXML private Label tryLogInLabel;
    @FXML private Label reasonLabel;
    @FXML private Label reqLabel;

    private DataSource<BanList> banListDataSource;
    private BanList banList;
    private Ban ban;
    private Users admin;

    @FXML
    public void initialize() {
        admin = (Users) FXRouter.getData();
        banListDataSource = new BanListDataSource(true);
        banList = banListDataSource.readData();
        System.out.println(banList);
        showListView();
        clearSelectedBan();
        handleSelectedListView();
    }

    private void showListView() {
        banListView.getItems().setAll(banList.getAllReqUnBan());
        banListView.refresh();
    }


    private void handleSelectedListView() {
        banListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Ban>() {
                    @Override
                    public void changed(ObservableValue<? extends Ban> observableValue, Ban ban, Ban t1) {
                        System.out.println(t1 + " is selected");
                        showSelectedBan(t1);
                    }
                });
    }
    private void showSelectedBan(Ban ban) {
        nameLabel.setText(ban.getUser());
        String tl = String.valueOf(ban.getTryLogin());
        tryLogInLabel.setText(tl);
        reasonLabel.setText(ban.getBannedReason());
        reqLabel.setText(ban.getRequest());
    }

    private void clearSelectedBan(){
        nameLabel.setText("");
        tryLogInLabel.setText("");
        reasonLabel.setText("");
        reqLabel.setText("");
    }
    @FXML
    private void handleUnBanButton(ActionEvent event) {
        banList.unban(ban.getBannedID());
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