package ku.cs.application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import ku.cs.application.models.Complaint;
import ku.cs.application.models.ComplaintList;
import ku.cs.application.models.ComplaintStatus;
import ku.cs.application.models.Users;
import ku.cs.application.services.ComplaintCategoryFilterer;
import ku.cs.application.services.ComplaintListDataSource;
import ku.cs.application.services.ComplaintStatusFilterer;
import ku.cs.application.services.Filterer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    private Users user;
    @FXML
    private ListView<Complaint> complaintListViewUnManage;
    @FXML
    private ListView<Complaint> complaintListViewInProgress;
    @FXML
    private ListView<Complaint> complaintListViewDone;
    @FXML
    private Label userLabel;
    private ComplaintListDataSource dataSource;
    private ComplaintList complaintList;
    private Complaint complaint;
    @FXML private MenuItem menuItemNew;
    @FXML private MenuItem menuItemOld;
    @FXML private MenuItem menuItemAscending;
    @FXML private MenuItem menuItemDescending;

    @FXML private MenuItem enrollMenuItem;
    @FXML private MenuItem placeMenuItem;
    @FXML private MenuItem teacherMenuItem;
    @FXML private MenuItem normalMenuItem;
    @FXML private MenuItem corruptMenuItem;
    Filterer<ComplaintList> filtererUnmanaged = new ComplaintStatusFilterer(ComplaintStatus.unmanaged);
    Filterer<ComplaintList> filtererInProgress = new ComplaintStatusFilterer(ComplaintStatus.inProgress);
    Filterer<ComplaintList> filtererDone = new ComplaintStatusFilterer(ComplaintStatus.done);
    private enum Sorter{timeNew,timeOld,ratingAscending, ratingDescending}
    private Sorter sorter;


    @FXML
    public void initialize() {
        user = (Users) FXRouter.getData();
        System.out.println(user);
        userLabel.setText(user.getName());
        dataSource = new ComplaintListDataSource("data", "complaint.csv");
        complaintList = dataSource.readData();
//        System.out.println(System.getProperty("file.separator"));
//        System.out.println(Arrays.toString(user.getUserImage().split("")));
        setMenuItemTime();
        showListView();
        handleSelectListView();
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleNormalComplaint(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("normalcomplaint", user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนทั่วไป ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleTeacherComplaint(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("teachercomplaint", user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนอาจารย์/บุคลากร ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void handlePlaceComplaint(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("placecomplaint", user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนอาคาร/สถานที่ ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleEnrollComplaint(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("enrollcomplaint", user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนการลงทะเบียนเรียน ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCorruptComplaint(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("corruptcomplaint", user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนการลงทะเบียนเรียน ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    void handleShowListView() {
        complaintList = dataSource.readData();
        showListView();
    }


//    @FXML
//    public void handleVoteButton(ActionEvent actionEvent) {
//        complaintList.vote(complaint);
//        dataSource.writeData(complaintList);
//    }

    private void showListView() {
        complaintListViewUnManage.getItems().setAll(complaintList.filter(filtererUnmanaged));
        complaintListViewDone.getItems().setAll(complaintList.filter(filtererDone));
        complaintListViewInProgress.getItems().setAll(complaintList.filter(filtererInProgress));
        complaintListViewUnManage.refresh();
        complaintListViewInProgress.refresh();
        complaintListViewDone.refresh();
    }

    @FXML
    public void handleGoToUserAccountScene(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("user_account", user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า changepassword");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    private void handleSelectListView() {
        complaintListViewUnManage.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Complaint>() {
            @Override
            public void changed(ObservableValue<? extends Complaint> observableValue, Complaint complaint, Complaint t1) {
                System.out.println(t1 + " is selected");
                selectComplaint(t1);
            }
        });
        complaintListViewDone.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Complaint>() {
            @Override
            public void changed(ObservableValue<? extends Complaint> observableValue, Complaint complaint, Complaint t1) {
                System.out.println(t1 + " is selected");
                selectComplaint(t1);
            }
        });
        complaintListViewInProgress.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Complaint>() {
            @Override
            public void changed(ObservableValue<? extends Complaint> observableValue, Complaint complaint, Complaint t1) {
                System.out.println(t1 + " is selected");
                selectComplaint(t1);
            }
        });
    }

    private void selectComplaint(Complaint complaint) {this.complaint = complaint;}
    public void setMenuItemTime() {

        menuItemNew.setOnAction(actionEvent -> {
            sorter = Sorter.timeNew;
            sortComplaintList();
            complaintListViewUnManage.getItems().setAll(complaintList.filter(filtererUnmanaged));
            complaintListViewInProgress.getItems().setAll(complaintList.filter(filtererInProgress));
            complaintListViewDone.getItems().setAll(complaintList.filter(filtererDone));
            refresh();
        });
        menuItemOld.setOnAction(actionEvent -> {
            sorter = Sorter.timeOld;
            sortComplaintList();
            complaintListViewUnManage.getItems().setAll(complaintList.filter(filtererUnmanaged));
            complaintListViewInProgress.getItems().setAll(complaintList.filter(filtererInProgress));
            complaintListViewDone.getItems().setAll(complaintList.filter(filtererDone));
            refresh();
        });
        menuItemAscending.setOnAction(actionEvent -> {
            sorter = Sorter.ratingAscending;
            sortComplaintList();
            complaintListViewUnManage.getItems().setAll(complaintList.filter(filtererUnmanaged));
            complaintListViewInProgress.getItems().setAll(complaintList.filter(filtererInProgress));
            complaintListViewDone.getItems().setAll(complaintList.filter(filtererDone));
            refresh();
        });
        menuItemDescending.setOnAction(actionEvent -> {
            sorter = Sorter.ratingDescending;
            sortComplaintList();
            complaintListViewUnManage.getItems().setAll(complaintList.filter(filtererUnmanaged));
            complaintListViewInProgress.getItems().setAll(complaintList.filter(filtererInProgress));
            complaintListViewDone.getItems().setAll(complaintList.filter(filtererDone));
            refresh();
        });

        normalMenuItem.setOnAction(actionEvent -> {
            complaintList = dataSource.readData();
            Filterer<ComplaintList> filterer = new ComplaintCategoryFilterer("normal");
            this.complaintList = complaintList.filterCategory(filterer);
            sortComplaintList();
            showListView();
            refresh();
        });
        enrollMenuItem.setOnAction(actionEvent -> {
            complaintList = dataSource.readData();
            Filterer<ComplaintList> filterer = new ComplaintCategoryFilterer("enroll");
            this.complaintList = complaintList.filterCategory(filterer);
            sortComplaintList();
            showListView();
            refresh();
        });
        placeMenuItem.setOnAction(actionEvent -> {
            complaintList = dataSource.readData();
            Filterer<ComplaintList> filterer = new ComplaintCategoryFilterer("place");
            this.complaintList = complaintList.filterCategory(filterer);
            sortComplaintList();
            showListView();
            refresh();
        });
        teacherMenuItem.setOnAction(actionEvent -> {
            complaintList = dataSource.readData();
            Filterer<ComplaintList> filterer = new ComplaintCategoryFilterer("teacher");
            this.complaintList = complaintList.filterCategory(filterer);
            sortComplaintList();
            showListView();
            refresh();
        });
        corruptMenuItem.setOnAction(actionEvent -> {
            complaintList = dataSource.readData();
            Filterer<ComplaintList> filterer = new ComplaintCategoryFilterer("corrupt");
            this.complaintList = complaintList.filterCategory(filterer);
            sortComplaintList();
            showListView();
            refresh();
        });
    }
    public void sortComplaintList(){
        if (sorter == Sorter.timeNew){
            this.complaintList.sortTimeNew();
        }else if (sorter == Sorter.timeOld){
            this.complaintList.sortTimeOld();
        }else if (sorter == Sorter.ratingAscending){
            this.complaintList.sortRatingOld();
        }else if (sorter == Sorter.ratingDescending){
            this.complaintList.sortRatingNew();
        }
    }
//    public void setMenuItemTime() {
//
//        menuItemNew.setOnAction(actionEvent -> {
//            complaintListViewUnManage.getItems().setAll(complaintList.sortTimeNew(complaintList.filter(filtererUnmanaged)));
//            complaintListViewInProgress.getItems().setAll(complaintList.sortTimeNew(complaintList.filter(filtererInProgress)));
//            complaintListViewDone.getItems().setAll(complaintList.sortTimeNew(complaintList.filter(filtererDone)));
//            refresh();
//        });
//        menuItemOld.setOnAction(actionEvent -> {
//            complaintListViewUnManage.getItems().setAll(complaintList.sortTimeOld(complaintList.filter(filtererUnmanaged)));
//            complaintListViewInProgress.getItems().setAll(complaintList.sortTimeOld(complaintList.filter(filtererInProgress)));
//            complaintListViewDone.getItems().setAll(complaintList.sortTimeOld(complaintList.filter(filtererDone)));
//            refresh();
//        });
//        menuItemAscending.setOnAction(actionEvent -> {
//            complaintListViewUnManage.getItems().setAll(complaintList.sortRatingOld(complaintList.filter(filtererUnmanaged)));
//            complaintListViewInProgress.getItems().setAll(complaintList.sortRatingOld(complaintList.filter(filtererInProgress)));
//            complaintListViewDone.getItems().setAll(complaintList.sortRatingNew(complaintList.filter(filtererDone)));
//            refresh();
//        });
//        menuItemDescending.setOnAction(actionEvent -> {
//            complaintListViewUnManage.getItems().setAll(complaintList.sortRatingNew(complaintList.filter(filtererUnmanaged)));
//            complaintListViewInProgress.getItems().setAll(complaintList.sortRatingNew(complaintList.filter(filtererInProgress)));
//            complaintListViewDone.getItems().setAll(complaintList.sortRatingNew(complaintList.filter(filtererDone)));
//            refresh();
//        });
//        normalMenuItem.setOnAction(actionEvent -> {
//            Filterer<ComplaintList> filterer = new ComplaintCategoryFilterer("normal");
//            this.complaintList = complaintList.filterCategory(filterer);
//            complaintList.sortTimeNew();
//            refresh();
//        });
//        enrollMenuItem.setOnAction(actionEvent -> {
//            refresh();
//        });
//        placeMenuItem.setOnAction(actionEvent -> {
//            refresh();
//        });
//        teacherMenuItem.setOnAction(actionEvent -> {
//            refresh();
//        });
//        corruptMenuItem.setOnAction(actionEvent -> {
//            refresh();
//        });
//    }
    @FXML
    void handleGoToSelectedComplaintDetail(ActionEvent event) {
        try {
            if (!(complaint == null)){
                List<Object> userAndComplaint = new ArrayList<>();
                userAndComplaint.add(user);
                userAndComplaint.add(complaint);
                FXRouter.goTo("selected_complaint_detail",userAndComplaint);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void refresh(){
        complaintListViewDone.refresh();
        complaintListViewUnManage.refresh();
        complaintListViewInProgress.refresh();
    }
}
