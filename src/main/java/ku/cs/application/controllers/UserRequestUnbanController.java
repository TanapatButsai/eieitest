package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import com.github.saacsos.FXRouter;
import ku.cs.application.models.Ban;
import ku.cs.application.models.BanList;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import ku.cs.application.services.BanListDataSource;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;

import java.io.IOException;

public class UserRequestUnbanController {

    @FXML
    private ImageView image_view_login;

    @FXML
    private TextArea inputReason;

    @FXML
    private TextField inputUsername;
    @FXML
    private TextArea requestTextField;
    @FXML
    private AnchorPane loginAnchorPane;



    @FXML
    private Label textError;
    private Users user;
    private BanList banList;
    private Ban ban;
    @FXML private DataSource<UserList> dataSource = new UserListDataSource("data","user.csv");
    @FXML private DataSource<BanList> banListDataSource = new BanListDataSource(false);
    @FXML public void initialize(){
        user = (Users) FXRouter.getData();
        banList = banListDataSource.readData();
        ban = banList.findBanByUsername(user.getUsername());
        inputUsername.setText(user.getName());
        inputUsername.setEditable(false);
        inputReason.setText(ban.getBannedReason());
        inputReason.setEditable(false);

    }
    @FXML
    void handleBack(ActionEvent event) {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleSentRequest(ActionEvent event) {
        if (!requestTextField.getText().equals("")){
            banList.setRequestUnban(user.getUsername(),requestTextField.getText());
            banListDataSource.writeData(banList);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("ส่งข้อมูลเพื่อพิจารณาเรียบร้อย");
            alert.setContentText("");
            alert.showAndWait();
            try {
                FXRouter.goTo("login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            textError.setText("กรุณาใส่ข้อความ");
        }
    }
}

