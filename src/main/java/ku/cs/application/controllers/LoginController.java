package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Optional;

import javafx.scene.control.Label;
import com.github.saacsos.FXRouter;
import ku.cs.application.models.*;
import ku.cs.application.services.*;

public class LoginController {
    @FXML private Label textError;
    @FXML private TextField inputUsername;
    @FXML private TextField inputPassword;
    private String url
            = getClass().getResource("/ku/cs/login_images/ku_view.jpg").toExternalForm();
    private String url2
            = getClass().getResource("/ku/cs/login_images/KU_SubLogo_Thai.png").toExternalForm();
    @FXML
    private ImageView imageViewLogin;
    @FXML
    private ImageView imageViewKULogo; //imageViewKULogin
    private DataSource<UserList> dataSource;
    private UserList userList;
    private DataSource<OfficeList> officerDataSource;
    private OfficeList officerIDList;

    private DataSource<ComplaintList> dataSource2 = new ComplaintListDataSource("data","complaint");
    private DataSource<BanList> banListDataSource;
    private BanList banList;

    @FXML
    public void initialize() {
        //officer1 = new Officer("nenny","เรื่องร้องเรียงทั่วไป","teacher","study too much");
        imageViewLogin.setImage(new Image(url));
        imageViewKULogo.setImage(new Image(url2));
        dataSource = new UserListDataSource("data","user.csv");
        officerDataSource = new OfficeListDataSource("data","officer.csv");
        banListDataSource = new BanListDataSource(false);
        userList = dataSource.readData();
        banList = banListDataSource.readData();
        officerIDList = officerDataSource.readData();
        System.out.println(officerIDList);

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
        if (username.isEmpty() || password.isEmpty()){
            textError.setText("Enter username and password");
            System.err.println("TextField is empty");
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
                }
                else if (user.isOfficer()){
                    userList.recordTimeLogin(user);
                    dataSource.writeData(userList);
                    String role = officerIDList.findOfficerRole(user.getUsername());
                    user = new Officer(user.getName(),user.getId()
                            ,user.getUsername(),user.getPassword(),user.getLastTimeLogin(),user.getUserImage()
                            ,role);
                    try {
                        FXRouter.goTo("officer",user);
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
        inputUsername.clear();// clear ช่อง TextField
        inputPassword.clear();
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

    public void banAlert(Users user){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Ban ban = banList.findBanByUsername(user.getUsername());
        banList.tryLogin(user.getUsername());
        banListDataSource.writeData(banList);
        String reason = ban.getBannedReason();
        alert.setTitle("บัญชีของคุณถูกระงับ");
        alert.setContentText("ต้องการยื่นคำร้องขอคืนสิทธิ์การถูกระงับหรือไม่");
        alert.setHeaderText(reason);

        Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                try {
                    FXRouter.goTo("user_request_unban",user);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else if (result.get() == ButtonType.CANCEL){
                System.out.println("no");
            }
        inputUsername.clear();// clear ช่อง TextField
        inputPassword.clear();
        textError.setText("");
    }
    @FXML
    public void handleCreditButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("credit");

        } catch (IOException e) {
            System.err.println("ไปที่หน้า credit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}


