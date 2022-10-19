package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class AdminChangePassword {
    @FXML private TextField oldPasswordTextField;
    @FXML private TextField newPasswordTextField;
    @FXML private TextField confirmPasswordTextField;
    @FXML private DataSource<UserList> dataSource;
    private Users user;
    private UserList userList;
    @FXML private Label promptOldPassword;
    @FXML private Label promptNewPassword;


    @FXML
    public void initialize() {
        dataSource = new UserListDataSource("data","user.csv");
        userList = dataSource.readData();
        if (userList == null) {
            System.err.println("AdminChangePasswordController : Cannot read file");
        } else {
            System.out.println("AdminChangePasswordController : Can read file");
        }
        user = (Users)FXRouter.getData();
        System.out.println(user);
    }

    @FXML
    public void handleChangePassword(ActionEvent actionEvent){
        if (isChangePassword()){
            try {
                FXRouter.goTo("user_account");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isChangePassword() {

        String oldPassword = oldPasswordTextField.getText();
        String newPassword = newPasswordTextField.getText();
        String confirmNewPassword = confirmPasswordTextField.getText();
        if (oldPasswordTextField.getText().isEmpty() || newPasswordTextField.getText().isEmpty()
                || confirmPasswordTextField.getText().isEmpty()
                || !oldPasswordTextField.getText().equals(user.getPassword())
                || !newPassword.equals(confirmNewPassword)){
            oldPasswordTextField.clear();
            confirmPasswordTextField.clear();
            newPasswordTextField.clear();

            if (oldPasswordTextField.getText().isEmpty()){
                promptOldPassword.setText("Please enter");
            } else if (!oldPasswordTextField.getText().equals(user.getPassword())) {
                promptOldPassword.setText("Wrong password");
            }
            if (newPassword.equals(confirmNewPassword)){
                promptNewPassword.setText("Please try again");
            }
            return false;
        }
        System.out.println("Can change password");
        userList.changePassword(user.getUsername(), confirmNewPassword);
        dataSource.writeData(userList);
        return true;
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("admin_account",user);

        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_account ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
