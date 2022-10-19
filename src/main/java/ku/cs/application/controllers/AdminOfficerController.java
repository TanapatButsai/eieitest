package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import ku.cs.application.models.*;
import com.github.saacsos.FXRouter;
import ku.cs.application.services.*;

import java.io.IOException;

public class AdminOfficerController {
    @FXML
    private TextField InstitutionTextField;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Circle userImageCircleView;

    @FXML
    private Label userLabel;

    @FXML
    private TextField usernameTextField;

    private Users admin;
    private boolean usernameIsInUsers;
    private String username;
    private DataSource<UserList> dataSource;
    private DataSource<OfficeList> officeListDataSource;
    private OfficeList officeList;
    private UserList userList;
    private boolean usernameHasComma;
    private boolean usernameCanBeUse;
    private boolean usernameAlreadyCheck;
    private String officerRole;
    @FXML private MenuItem corrupt;
    @FXML private MenuItem enroll;
    @FXML private MenuItem normal;
    @FXML private MenuItem place;
    @FXML private MenuItem teacher;
    @FXML
    private MenuButton roleMenuBar;
    private String imageUrl;
    @FXML
    private ImageView imageBackGround;
    @FXML
    private ImageView imageBackGround2;
    @FXML
    private ImageView minimal;
    @FXML
    public void initialize(){
        admin = (Users) FXRouter.getData();
        dataSource = new UserListDataSource("data", "user.csv");
        userList = dataSource.readData();

        officeListDataSource = new OfficerListDataSource("data","officer.csv");
        officeList = officeListDataSource.readData();
        System.out.println(userList);
        String url = getClass().getResource("/ku/cs/user_account_scene_image/minimal.png").toExternalForm();
        String url1 = getClass().getResource("/ku/cs/user_account_scene_image/announce.png").toExternalForm();
        imageBackGround.setImage(new Image(url));
        imageBackGround2.setImage(new Image(url1));

        setMenuItemTime();
    }

    @FXML
    void handleBackButton(ActionEvent event) {
        try {
            FXRouter.goTo("adminscene",admin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void handleSignUp(ActionEvent actionEvent){
//        promptCheckID.setText("");
//        promptCheckIRetypePassword.setText("");
//        promptCheckName.setText("");
//        promptCheckUsername.setText("");

        try {
            if (isSignUp()){
                FXRouter.goTo("adminscene",admin);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isSignUp() {
        String newUserUsername = usernameTextField.getText();
        String newUserPasswordUsername = passwordTextField.getText();
        String newUserID = InstitutionTextField.getText();
        String newUserFullName = nameTextField.getText();
        if (newUserFullName.isEmpty() || newUserUsername.isEmpty()|| newUserID.isEmpty()
                || newUserPasswordUsername.isEmpty() || confirmPasswordTextField.getText().isEmpty()
                || !usernameAlreadyCheck
                || !newUserPasswordUsername.equals(passwordTextField.getText())
                || !isInteger(newUserID) || newUserID.length() != 10
                || !usernameCanBeUse || usernameIsInUsers || usernameHasComma) {
            if (!usernameAlreadyCheck || !usernameCanBeUse) {
//                promptCheckUsername.setText("Please check username");
            }
            if (newUserID.isEmpty()) {
//                promptCheckID.setText("Enter your Student ID");
                InstitutionTextField.clear();
            }
            if (newUserFullName.isEmpty()) {
//                promptCheckName.setText("Enter your name");
                nameTextField.clear();
            }

            if (newUserPasswordUsername.isEmpty() || confirmPasswordTextField.getText().isEmpty()) {
//                promptCheckIRetypePassword.setText("Enter password");
                passwordTextField.clear();
                confirmPasswordTextField.clear();
            } else if (!newUserPasswordUsername.equals(confirmPasswordTextField.getText())) {
//                promptCheckIRetypePassword.setText("Passwords do not match");
                passwordTextField.clear();
                confirmPasswordTextField.clear();
            }
            if (newUserUsername.isEmpty()) {
//                promptCheckUsername.setText("Enter username");
                usernameTextField.clear();
            }
            if (!usernameAlreadyCheck) {
//                promptCheckUsername.setText("Please check username");
            }
            if (!isInteger(newUserID) || newUserID.length() != 10){
//                promptCheckID.setText("Student ID must be a number(10 digits)");
            }
            usernameCanBeUse = false;
            usernameAlreadyCheck = false;
            return false;
        }
        Users newUser = new Users(newUserFullName,newUserID,newUserUsername,newUserPasswordUsername
                ,false,"0-0-0-0-0-0","data\\images\\profile\\default.png",false
                ,true);
        officeList.addOfficerINOffice(newUser.getUsername(),officerRole);
        officeListDataSource.writeData(officeList);
        System.out.println(officeList);
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
    @FXML public void handleCheckUsername(ActionEvent actionEvent) {
        username = usernameTextField.getText();
        usernameIsInUsers = userList.checkUsernameIsExistedInUserList(username);
        usernameAlreadyCheck = true;
        if (username.equals("")) {
//            promptCheckUsername.setText("Please enter ID");
        } else {
            if (usernameIsInUsers) {
//                promptCheckUsername.setText("ID is already exists");
            } else if (username.contains(",")) {
//                promptCheckUsername.setText("Can not use \",\"");
                usernameHasComma = true;
            } else {
//                promptCheckUsername.setText("Can be used");
                usernameCanBeUse = true;
                usernameHasComma = false;
            }
        }
        System.out.println("ID is in user = " + usernameIsInUsers + " | Already click check =" + usernameAlreadyCheck);
    }
    public void setMenuItemTime() {
        normal.setOnAction(actionEvent -> {
            this.officerRole = "normal";
            roleMenuBar.setText("งานบริหารงานทั่วไป");
        });
        teacher.setOnAction(actionEvent -> {
            this.officerRole = "teacher";
            roleMenuBar.setText("กองบริหารงานบุคคล");
        });
        place.setOnAction(actionEvent -> {
            this.officerRole = "place";
            roleMenuBar.setText("งานอาคารและสถานที่");
        });
        enroll.setOnAction(actionEvent -> {
            this.officerRole = "enroll";
            roleMenuBar.setText("สำนักงานการทะเบียน");
        });

        corrupt.setOnAction(actionEvent -> {
            this.officerRole = "corrupt";
            roleMenuBar.setText("หน่วยงานตรวจสอบการทุจริต");
        });
    }
}