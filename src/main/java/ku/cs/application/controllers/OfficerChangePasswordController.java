package ku.cs.application.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.application.models.*;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.OfficeListDataSource;
import ku.cs.application.services.UserListDataSource;

import java.io.IOException;

public class OfficerChangePasswordController {
    private Officer userOfficer;
    private OfficeList officeList;
    private UserList userList;
    @FXML private DataSource<OfficeList> dataSource;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;
    @FXML private TextField officerTextField;
    @FXML private TextField oldPasswordTextField;
    @FXML private TextField newPasswordTextField;
    @FXML private TextField newConfirmPasswordTextField;
    @FXML private ImageView logoku;
    @FXML private ImageView change_bg;
    private DataSource<UserList> userListDataSource = new UserListDataSource("data","user.csv");


    @FXML
    public void initialize(){
        userOfficer = (Officer)FXRouter.getData();
        String url1 = getClass().getResource("/ku/cs/officer_images/logoku.png").toExternalForm();
        logoku.setImage(new Image(url1));
        String url2 = getClass().getResource("/ku/cs/officer_images/changebg.jpg").toExternalForm();
        change_bg.setImage(new Image(url2));
        dataSource = new OfficeListDataSource("data", "officer.csv");
        userList = userListDataSource.readData();
        officeList = dataSource.readData();
        officerTextField.setText(userOfficer.getUsername());
        officerTextField.setEditable(false);
        errorLabel.setText("");
        successLabel.setText("");
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
    @FXML
    public void handleChangePassword(ActionEvent actionEvent){
        if (isChangePassword()){
            errorLabel.setText("");
            successLabel.setText("Success");
        }
    }
    private boolean isChangePassword() {

        String oldPassword = oldPasswordTextField.getText();
        String newPassword = newPasswordTextField.getText();
        String confirmNewPassword = newConfirmPasswordTextField.getText();
        Users user = new Users(userOfficer.getName(),userOfficer.getId(), userOfficer.getUsername()
                ,userOfficer.getPassword(),userOfficer.isAdmin(), userOfficer.getLastTimeLogin()
                ,userOfficer.getUserImage(),userOfficer.isBan(),true);
        if (oldPasswordTextField.getText().isEmpty() || newPasswordTextField.getText().isEmpty()
                || newConfirmPasswordTextField.getText().isEmpty()
                || !oldPasswordTextField.getText().equals(userOfficer.getPassword())
                || !newPassword.equals(confirmNewPassword)){
            oldPasswordTextField.clear();
            newConfirmPasswordTextField.clear();
            newPasswordTextField.clear();

            if (oldPasswordTextField.getText().isEmpty()){
                System.out.println("error1");
                errorLabel.setText("Please enter");
            }
            else if (!oldPasswordTextField.getText().equals(userOfficer.getPassword())) {
                System.out.println("error2");
                errorLabel.setText("Wrong password");
            }
            if (newPassword.equals(confirmNewPassword)){
                System.out.println("error3");
                errorLabel.setText("Please try again");
            }
            return false;
        }
        System.out.println("Can change password");
        userList.changePassword(user.getUsername(), confirmNewPassword);
        userListDataSource.writeData(userList);
        return true;
    }

}
