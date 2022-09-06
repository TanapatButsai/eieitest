package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.application.models.UserList;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;

import java.io.IOException;

public class RegisterController {
    @FXML private ImageView bg;
    @FXML private ImageView logo01;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextFieldSignUp;
    @FXML private TextField passwordTextFieldSignUpCheck;
    @FXML private TextField nameTextField;
    @FXML private Label promptCheckIRetypePassword;
    @FXML private Label promptCheckName;

    private DataSource<UserList> dataSource;
    private UserList userList;
    private boolean usernameIsInUsers,usernameAlreadyCheck;


    @FXML private Label promptCheckID;
    @FXML public void initialize(){
        String url1 = getClass().getResource("/ku/cs/reg_images/bg002.jpg").toExternalForm();
        String url2 = getClass().getResource("/ku/cs/reg_images/logo001.jpg").toExternalForm();

        bg.setImage(new Image(url1));
        logo01.setImage(new Image(url2));

        dataSource = new UserListDataSource("data", "user.csv");
        userList = dataSource.readData();

        System.out.println(usernameAlreadyCheck);
    }

    @FXML public void handleCheckID() {
        usernameAlreadyCheck = true;
        String username = usernameTextField.getText();
        usernameIsInUsers = userList.checkUsernameIsExistedInUserList(username);
        if (username.isEmpty()) {
            promptCheckID.setText("Please enter ID");
        } else {
            if (usernameIsInUsers) {
                promptCheckID.setText("ID is already exists");
            } else {
                promptCheckID.setText("Can be used");
            }
        }

        System.out.println("ID is in user = " + usernameIsInUsers + " | Already click check =" + usernameAlreadyCheck);
    }

    @FXML public void handleSignUp(){
        String password = passwordTextFieldSignUp.getText();
        String passwordCheck = passwordTextFieldSignUpCheck.getText();

        boolean checkEqualPassword = password.equals(passwordCheck);

        if (usernameTextField.getText().isEmpty() && passwordTextFieldSignUp.getText().isEmpty()
                &&passwordTextFieldSignUpCheck.getText().isEmpty() && nameTextField.getText().isEmpty()){
            System.out.println("Please enter");
            promptCheckIRetypePassword.setText("Password is not match");
            promptCheckID.setText("hohonana");
            promptCheckName.setText("Please enter name");

        }else {
        }

//        if (!usernameAlreadyCheck && password.isEmpty() && passwordCheck.isEmpty()
//                && nameTextField.getText().isEmpty()){
//            System.out.println("Please enter");
//        }
//        else if (!usernameAlreadyCheck){
//            System.out.println("Please check username");
//        }
//        else if ( password.isEmpty() && passwordCheck.isEmpty() && nameTextField.getText().isEmpty()){
//            System.out.println("Please enter password or name");
//        } else if (checkEqualPassword) {
//            System.out.println("Password not match");
//        } else if ( !usernameIsInUsers ){
//            System.out.println("Can sign up");
//
//        }


    }
    @FXML public void handleBackButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }



    //other function
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
    public boolean isEmpty(String string){
        if (string.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
}
