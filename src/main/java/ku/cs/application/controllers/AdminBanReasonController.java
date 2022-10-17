package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ku.cs.application.models.*;
import com.github.saacsos.FXRouter;
import ku.cs.application.services.BanListDataSource;
import ku.cs.application.services.ComplaintListDataSource;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;

import java.io.IOException;

public class AdminBanReasonController {

    @FXML
    private TextArea reasonTextArea;

    @FXML
    private Label userLabel;
    private UserList userList;
    private Complaint complaint;
    private BanList banList;
    private DataSource<UserList> dataSource;
    private DataSource<BanList> banListDataSource;
    private Users user;

    @FXML private void initialize(){
        dataSource = new UserListDataSource("data","user.csv");
        banListDataSource = new BanListDataSource(true);
        userList = dataSource.readData();
        banList = banListDataSource.readData();
        complaint = (Complaint) FXRouter.getData();
        user = userList.findUser(complaint.getNameWriter());
        System.out.println(banList);
        System.out.println(banList);
    }

    @FXML
    void handleBack(ActionEvent event) {
        try {
            FXRouter.goTo("admincomplaint");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleBan(ActionEvent event) {
        if (!(user == null) && !reasonTextArea.getText().equals("")) ban();
    }
    public void ban(){
        userList.setBan(complaint.getNameWriter());
        dataSource.writeData(userList);
        createBanUser();
    }
    public void createBanUser(){
        String username = complaint.getNameWriter();
        String reason = reasonTextArea.getText();
        String bannedID = Integer.toString(banList.getSize()+1);
        String objectID = complaint.getHeadComplaint()+":"+complaint.getNameWriter()+":"+complaint.getTime();
        Ban ban =new Ban(bannedID,username,reason,objectID,true);
        ban.setTime();
        banList.addBan(ban);
        banListDataSource.writeData(banList);
    }


}

