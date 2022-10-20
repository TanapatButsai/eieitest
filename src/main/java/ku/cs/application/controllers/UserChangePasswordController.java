package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;
import com.github.saacsos.FXRouter;
import java.io.IOException;


public class UserChangePasswordController {
    @FXML private ImageView scku;
    @FXML private ImageView logoku;
    @FXML private TextField newConfirmPasswordTextField;
    @FXML private TextField newPasswordTextField;
    @FXML private TextField oldPasswordTextField;
    @FXML private Label promptOldPassword;
    @FXML private Label promptNewPassword;
    @FXML private DataSource<UserList> dataSource;
    private UserList userList;
    private Users user;


    @FXML
    public void initialize() {
        String url = getClass().getResource("/ku/cs/pass_images/scku.jpeg").toExternalForm();
        scku.setImage(new Image(url));
        String url1 = getClass().getResource("/ku/cs/pass_images/logoku.png").toExternalForm();
        logoku.setImage(new Image(url1));
        dataSource = new UserListDataSource("data", "user.csv");
        userList = dataSource.readData();
        if (userList == null) {
            System.err.println("ChangePasswordController : Cannot read file");
        } else {
            System.out.println("ChangePasswordController : Can read file");
        }

        user = (Users)FXRouter.getData();
        System.out.println(user);
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("user_account",user);

        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
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
            String confirmNewPassword = newConfirmPasswordTextField.getText();
            if (oldPasswordTextField.getText().isEmpty() || newPasswordTextField.getText().isEmpty()
                    || newConfirmPasswordTextField.getText().isEmpty()
                    || !oldPasswordTextField.getText().equals(user.getPassword())
                    || !newPassword.equals(confirmNewPassword)){
                oldPasswordTextField.clear();
                newConfirmPasswordTextField.clear();
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
    }
