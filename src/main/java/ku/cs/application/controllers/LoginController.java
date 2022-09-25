package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.control.Label;
import com.github.saacsos.FXRouter;
import ku.cs.application.models.*;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.OfficerIDListDataSource;
import ku.cs.application.services.UserListDataSource;

public class LoginController {
    //Admin ID PASSWORD
    String adminUsername = "admin";
    String adminPassword = "123456";
    //Officer ID PASSWORD
    String officerUser1 = "officer1";
    String officerPassword1 = "officer111";
    //private Officer officer1;


    @FXML private Label textError;
    @FXML private TextField inputUsername;
    @FXML private TextField inputPassword;
    String url
            = getClass().getResource("/ku/cs/login_images/ku_view.jpg").toExternalForm();
    String url2
            = getClass().getResource("/ku/cs/login_images/KU_SubLogo_Thai.png").toExternalForm();
    @FXML
    private ImageView image_view_login;
    @FXML
    private ImageView image_view_ku_logo;


    private DataSource<UserList> dataSource;
    private UserList userList;

    private DataSource<OfficerIDList> dataSource1;
    private OfficerIDList officerIDList;
    @FXML
    public void initialize() {
        //officer1 = new Officer("nenny","เรื่องร้องเรียงทั่วไป","teacher","study too much");
        image_view_login.setImage(new Image(url));
        image_view_ku_logo.setImage(new Image(url2));
        dataSource = new UserListDataSource("data","user.csv");
        dataSource1 = new OfficerIDListDataSource("data","officerID.csv");
        userList = dataSource.readData();
        if (userList == null){
            System.err.println("Cannot read file");
        } else {
            System.out.println("Can read file");
        }
        officerIDList = dataSource1.readData();
        if (officerIDList == null){
            System.err.println("Cannot read file");
        } else {
            System.out.println("Can read file");
        }
    }

    @FXML
    public void handleSignIn(ActionEvent actionEvent) {
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        Users user = userList.findUser(username);
        OfficerID officerID = officerIDList.findOfficer(username);


        if (username.isEmpty() || password.isEmpty()){
            textError.setText("Enter username and password");
            System.err.println("TextField is empty");
        } else if (Objects.equals(officerUser1, username) && Objects.equals(officerPassword1, password)) {
            try {
                FXRouter.goTo("officer");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า officer");
                System.err.println("ให้ตรวจสอบการกำหนด route");
                e.printStackTrace();
            }
        } else if (user == null || !user.getPassword().equals(password) || officerID == null) {
            if (officerID == null){
                System.err.println("Wrong username or password");
                textError.setText("Wrong username or password");
            }else if (isOfficer(username,password,officerID)){
                try {
                    FXRouter.goTo("officer",officerID);
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า home");
                    System.err.println("ให้ตรวจสอบการกำหนด route");
                    e.printStackTrace();
                }
            }
                System.err.println("Wrong username or password");
                textError.setText("Wrong username or password");

        }else if (isLogin(username,password,user)) {
            try {
                FXRouter.goTo("home",user);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า home");
                System.err.println("ให้ตรวจสอบการกำหนด route");
                e.printStackTrace();
            }
        }
//        else if (isOfficer(username,password,officerID)) {
//            try {
//                FXRouter.goTo("officer",officerID);
//            } catch (IOException e) {
//                System.err.println("ไปที่หน้า home");
//                System.err.println("ให้ตรวจสอบการกำหนด route");
//                e.printStackTrace();
//            }
//        }
        inputUsername.clear();// clear ช่อง TextField
        inputPassword.clear();
    }

    @FXML
    public void handleGoToHome(ActionEvent actionEvent){
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleGoToRegister(ActionEvent actionEvent){
        try {
            FXRouter.goTo("register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า register");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML

    public void handleGoToChangePassword(ActionEvent actionEvent){
        try {
            // เปลี่ยนการแสดงผลไปที่ route ที่ชื่อ member_card_detail
            // พร้อมส่ง reference instance john ไปด้วย
            FXRouter.goTo("changepassword");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า changepassword");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    public boolean isLogin(String username, String password,Users user){
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }
    public boolean isOfficer(String username, String password, OfficerID officerID){
        return username.equals(officerID.getOfficerID()) && password.equals(officerID.getOfficerPassword());
    }
}


