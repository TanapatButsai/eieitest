package ku.cs.application.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.application.models.*;
import ku.cs.application.services.ComplaintListDataSource;
import ku.cs.application.services.DataSource;
import com.github.saacsos.FXRouter;
import ku.cs.application.services.VoteListDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class NormalComplaintController {
    @FXML private TextArea bodyTextArea;
    @FXML private TextArea bodyTextArea1;
    @FXML private TextField headTextField;
    private DataSource<ComplaintList> dataSource;
    private DataSource<VoteList> voteListDataSource;
    private ComplaintList complaintList;
    private Users user;
    @FXML
    private ImageView kasetsartImage;
    private ArrayList<Object> objects;
    private String category;
    private String complaintImageUrl;
    Path target;
    File file;
    private boolean isAddFile = false;
    private VoteList voteList;
    @FXML
    public void initialize() {
        dataSource = new ComplaintListDataSource("data","complaint.csv");
        voteListDataSource = new VoteListDataSource("data","vote.csv");
        voteList = voteListDataSource.readData();
        complaintList = dataSource.readData();
        objects = (ArrayList<Object>) FXRouter.getData();
        user = (Users) objects.get(0);
        category = (String) objects.get(1);
        String imagePath = (String) objects.get(2);
        String url = getClass().getResource(imagePath).toExternalForm();
        kasetsartImage.setImage(new Image(url));
    }
    public void handleBackHomeButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void handlePushComplaint(ActionEvent actionEvent){
        String headComplaint = headTextField.getText();
        String bodyComplaint = bodyTextArea.getText();
        String bodyComplaint1 = bodyTextArea1.getText();
        Complaint complaint = new Complaint(headComplaint,bodyComplaint,bodyComplaint1,category,user.getUsername());
        if (complaintImageUrl == null){complaintImageUrl = "data\\images\\complaint\\default_complaint.png";}
        complaint.setImageUrl(complaintImageUrl);
        complaint.recordTime();
        complaint.setSolution("no");
        complaintList.add(complaint);
        dataSource.writeData(complaintList);
        Vote vote = new Vote(complaint.getHeadComplaint()+":"+complaint.getNameWriter()
                            +":"+complaint.getTime());
        voteList.add(vote);
        voteListDataSource.writeData(voteList);
        if (!(bodyTextArea.getText().equals("") || bodyTextArea1.getText().equals("")
                || headTextField.getText().equals(""))){
            if (isAddFile){saveFile();}
            try {
                com.github.saacsos.FXRouter.goTo("home",user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML public void handleAddPicture(ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(new File(System.getProperty("complaint.dir")));
        fileChooser.getExtensionFilters().add
                (new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        Node source = (Node) actionEvent.getSource();
        file = fileChooser.showOpenDialog(source.getScene().getWindow());
        if (file != null){
            // CREATE FOLDER IF NOT EXIST
            File destDir = new File("data/images/complaint/");

            if (!destDir.exists()) destDir.mkdirs();
            // RENAME FILE
            String[] fileSplit = file.getName().split("\\.");

            String filename = LocalDate.now() + "_"+System.currentTimeMillis() + "."
                    + fileSplit[fileSplit.length - 1];

            target = FileSystems.getDefault().getPath(
                    destDir.getAbsolutePath()+System.getProperty("file.separator")+filename);
            // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
            complaintImageUrl = (destDir + File.separator + filename).replace(File.separator,"\\");
                    isAddFile = true;
            // SET NEW FILE PATH TO IMAGE
        }
        System.out.println(complaintImageUrl);
    }
    private void saveFile(){
        try {

            Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
