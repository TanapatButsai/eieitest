package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import java.io.IOException;

import javafx.scene.control.Label;
import com.github.saacsos.FXRouter;
import ku.cs.application.models.*;
import ku.cs.application.services.*;

public class LoginController {
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
    private ImageView image_view_ku_logo; //imageViewKULogin
    private DataSource<UserList> dataSource;
    private UserList userList;
    private Users user;
    private DataSource<OfficerList> officerDataSource;
    private OfficerList officerIDList;

    private DataSource<ComplaintList> dataSource2 = new ComplaintListDataSource("data","complaint");
    private DataSource<BanList> banListDataSource;
    private BanList banList;
//    private ComplaintList complaintList;

    @FXML
    public void initialize() {
        //officer1 = new Officer("nenny","เรื่องร้องเรียงทั่วไป","teacher","study too much");
        image_view_login.setImage(new Image(url));
        image_view_ku_logo.setImage(new Image(url2));
        dataSource = new UserListDataSource("data","user.csv");
        officerDataSource = new OfficerListDataSource("data","officer.csv");
        banListDataSource = new BanListDataSource(false);
        userList = dataSource.readData();
        banList = banListDataSource.readData();
        System.out.println(banList);
//        complaintList = dataSource2.readData();

        if (userList == null){
            System.err.println("Cannot read file");
        } else {
            System.out.println("Can read file");
        }
        officerIDList = officerDataSource.readData();
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
        Officer officer = officerIDList.findOfficer(username);
        if (username.isEmpty() || password.isEmpty()){
            textError.setText("Enter username and password");
            System.err.println("TextField is empty");
        } else if (user == null || !user.getPassword().equals(password)
                || officer == null || !officer.getOfficerPassword().equals(password)) {
            if (officer == null){
                System.err.println("Wrong username or password");
                textError.setText("Wrong username or password");
            }else if (isOfficer(username,password, officer)){
                try {
                    FXRouter.goTo("officer", officer);
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า officer");
                    System.err.println("ให้ตรวจสอบการกำหนด route");
                    e.printStackTrace();
                }
            }
            if (user == null){
                System.err.println("Wrong username or password");
                textError.setText("Wrong username or password");
            } else if (isLogin(username,password,user)) {
                textError.setText("");
                if (user.isAdmin()){
                    try {
                        FXRouter.goTo("adminscene",user);
                    } catch (IOException e) {
                        System.err.println("ไปที่หน้า home");
                        System.err.println("ให้ตรวจสอบการกำหนด route");
                        e.printStackTrace();
                    }
                } else if(user.isBan()){
                    banAlert(user);
                }
                else {
                    try {
                        userList.recordTimeLogin(user);
                        dataSource.writeData(userList);
                        FXRouter.goTo("home",user);

                    } catch (IOException e) {
                        System.err.println("ไปที่หน้า home");
                        System.err.println("ให้ตรวจสอบการกำหนด route");
                        e.printStackTrace();
                    }
                }
            }

        }
        inputUsername.clear();// clear ช่อง TextField
        inputPassword.clear();
    }
    @FXML
    public void handleGoToHome(ActionEvent actionEvent){
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

    public boolean isLogin(String username, String password,Users user){
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }
    public boolean isOfficer(String username, String password, Officer officer){
        return username.equals(officer.getOfficerID()) && password.equals(officer.getOfficerPassword());
    }
    public void banAlert(Users user){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Ban ban = banList.findBanByUsername(user.getUsername());
        banList.tryLogin(user.getUsername());
        banListDataSource.writeData(banList);
        String reason = ban.getBannedReason();
        alert.setTitle("ระงับบัญชี");
        alert.setContentText(reason);
        alert.setHeaderText("บัญชีของคุณถูกระงับ");
        alert.showAndWait();
        inputUsername.clear();// clear ช่อง TextField
        inputPassword.clear();
        textError.setText("");
    }
}


