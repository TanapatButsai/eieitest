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

public class RegisterController {
    @FXML private ImageView bg;
    @FXML private ImageView logo01;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextFieldSignUp;
    @FXML private TextField passwordTextFieldSignUpCheck;
    @FXML private TextField nameTextField;
    @FXML private TextField idTextField;
    @FXML private Label promptCheckIRetypePassword;
    @FXML private Label promptCheckName;
    @FXML private Label promptCheckUsername;
    @FXML private Label promptCheckID;
    private DataSource<UserList> dataSource;
    private UserList userList;
    private Users newUser;
    private boolean usernameIsInUsers = false;
    private boolean usernameAlreadyCheck = false;
    private boolean usernameCanBeUse = false;
    private boolean usernameHasComma = false;

    @FXML public void initialize(){
        String url1 = getClass().getResource("/ku/cs/reg_images/bg002.jpg").toExternalForm();
        String url2 = getClass().getResource("/ku/cs/reg_images/logo001.jpg").toExternalForm();

        bg.setImage(new Image(url1));
        logo01.setImage(new Image(url2));

        dataSource = new UserListDataSource("data", "user.csv");
        userList = dataSource.readData();
    }

    @FXML public void handleSignUp(ActionEvent actionEvent){
        promptCheckID.setText("");
        promptCheckIRetypePassword.setText("");
        promptCheckName.setText("");
        promptCheckUsername.setText("");

        try {
            if (isSignUp()){
                FXRouter.goTo("login");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML public void handleCheckUsername(ActionEvent actionEvent) {
        promptCheckUsername.setText("");
        usernameAlreadyCheck = true;
        String username = usernameTextField.getText();
        usernameIsInUsers = userList.checkUsernameIsExistedInUserList(username);
        if (username.isEmpty()) {
            promptCheckUsername.setText("Please enter ID");
        } else {
            if (usernameIsInUsers) {
                promptCheckUsername.setText("ID is already exists");
            } else if (username.contains(",")) {
                promptCheckUsername.setText("Can not use \",\"");
                usernameHasComma = true;
            } else {
                promptCheckUsername.setText("Can be used");
                usernameCanBeUse = true;
                usernameHasComma = false;
            }
        }
        System.out.println("ID is in user = " + usernameIsInUsers + " | Already click check =" + usernameAlreadyCheck);
    }

    @FXML public void handleBackButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    //other function
    public boolean isSignUp() {
        String newUserUsername = usernameTextField.getText();
        String newUserPasswordUsername = passwordTextFieldSignUp.getText();
        String newUserID = idTextField.getText();
        String newUserFullName = nameTextField.getText();
        if (newUserFullName.isEmpty() || newUserUsername.isEmpty()|| newUserID.isEmpty()
                    || newUserPasswordUsername.isEmpty() || !usernameAlreadyCheck
                    || !newUserPasswordUsername.equals(passwordTextFieldSignUpCheck.getText())
                    || !isInteger(newUserID) || newUserID.length() != 10
                    || !usernameCanBeUse || usernameIsInUsers || usernameHasComma) {
            if (!usernameAlreadyCheck || !usernameCanBeUse) {
                promptCheckUsername.setText("Please check username");
            }
            if (newUserID.isEmpty()) {
                promptCheckID.setText("Enter your Student ID");
                idTextField.clear();
            }
            if (newUserFullName.isEmpty()) {
                promptCheckName.setText("Enter your name");
                nameTextField.clear();
            }

            if (newUserPasswordUsername.isEmpty() || passwordTextFieldSignUpCheck.getText().isEmpty()) {
                promptCheckIRetypePassword.setText("Enter password");
                passwordTextFieldSignUp.clear();
                passwordTextFieldSignUpCheck.clear();
            } else if (!newUserPasswordUsername.equals(passwordTextFieldSignUpCheck.getText())) {
                promptCheckIRetypePassword.setText("Passwords do not match");
                passwordTextFieldSignUp.clear();
                passwordTextFieldSignUpCheck.clear();
            }
            if (newUserUsername.isEmpty()) {
                promptCheckUsername.setText("Enter username");
                usernameTextField.clear();
            }
            if (!usernameAlreadyCheck) {
                promptCheckUsername.setText("Please check username");
            }
            if (!isInteger(newUserID) || newUserID.length() != 10){
                promptCheckID.setText("Student ID must be a number(10 digits)");
            }
            usernameCanBeUse = false;
            usernameAlreadyCheck = false;
            return false;
        }
            Users newUser = new Users(newUserFullName,newUserID,newUserUsername,newUserPasswordUsername,false,"0-0-0-0-0-0","data\\images\\profile\\default.png");
            userList.addUser(newUser);
            dataSource.writeData(userList);
            usernameCanBeUse = false;
            usernameAlreadyCheck = false;
            System.out.println("Already save a new user");
            return true;
        }
    
    
    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }
    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
