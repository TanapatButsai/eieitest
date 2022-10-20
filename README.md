# CSS Project<br>

## รายชื่อสมาชิก<br>
      6410451059 ธนาภัทร บุตรใส (HEAD) 
      6410451113 นภกมล ปินตาพรหม
      6410451229 พีรพัฒน์ ภักดีพงษ์ 
      6410451024 ธนภัทร ตันเจริญ 
      
## การส่งงานเพื่อนําเสนอความก้าวหน้า
# ความก้าวหน้าของระบบครั้งที่ 1 ( 12 ส.ค. 17:00 น.)
      -ทำหน้า login, home template, ขึ้นโครง User (ธนาภัทร บุตรใส)
      -ทำหน้า Register และวางโครงไว้คร่าวๆ (ธนภัทร ตันเจริญ)
      -ทำหน้า Forgot password (นภกมล ปินตาพรหม)
      -ทำหน้า Credit (พีรพัฒน์ ภักดีพงษ์)
      
# ความก้าวหน้าของระบบครั้งที่ 2 ( 9 ก.ย. 17:00 น.)
      -ทำส่วน model user, userList, DataSource,ทำระบบ login,sign up, changepassword (ธนาภัทร บุตรใส)
      -ทำหน้าส่วน officer แก้ไขส่วน sign upเล็กน้อย (ธนภัทร ตันเจริญ)
      -ทำส่วนuser (หน้าร้องเรียน), ขึ้นโครง model complaint (นภกมล ปินตาพรหม)
      -ทำส่วน admin, แก้ไขcredit (พีรพัฒน์ ภักดีพงษ์)
      
# ความก้าวหน้าของระบบครั้งที่ 3 ( 30 ก.ย. 17:00 น.)
      -ทำส่วน Model user, user account, ฟังชั่น login time, user upload profile, ทำให้complaint-user-adminทำงานร่วมกัน, ดูภาพรวมของโปรแกรม (ธนาภัทร บุตรใส)
      -ทำให้ officer รับข้อมูลที่ตรงกับหน้าที่ของตัวเองได้โดยแสดงในlistview,สร้างoffficerIDเป็นUserของofficerและแบ่งหน้าที่ของofficer(ธนภัทร ตันเจริญ)
      -ทำให้Userเพิ่มคำร้องเรียนในหน้า Normal/ Enroll/Place/ TeacherComplaintและบันทึกข้อมูล,ComplaintListDataSource,สร้างshowlistview ให้userเห็นคำร้องเรียนทั้งหมด (นภกมล ปินตาพรหม)
      -ทำส่วน Admin ต่อจากครั้งก่อน, ทำviewสำหรับทำหน้าอื่นๆต่อ,ทำหน้าให้Adminดูเวลาผู้ใช้ทั้งหมดได้และเรียงตามเวลา (พีรพัฒน์ ภักดีพงษ์)

## การวงโครงสร้างไฟล์
- data
  - images
    - complaint
    - profile
  - bans.csv
  - complaint.csv
  - officer.csv
  - report.csv
  - user.csv
  - vote.csv
- src
  - main
    - java
      - ku.cs
        - application
          - controllers
            - AdminAccountController.java
            - AdminBanReasonController.java
            - AdminChangePassword.java
            - AdminComplaintController.java
            - AdminController.java
            - AdminManageBanController.java
            - AdminOfficerController.java
            - AdminSelectedReportComplaintController.java
            - ChangePasswprdController.java
            - CreditController.java
            - HomeController.java
            - LoginController.java
            - NormalComplaintController.java
            - OfficerChangePasswordController.java
            - ProjectOpeningController.java
            - RegisterController.java
            - ReportComplaintController.java
            - SelectedComplaintDetailController.java
            - StudentListController.java
            - UserAccountController.java
            - UserComplaintListController.java
            - UserRequestUnbanController.java
          - models
            - Ban.java
            - BanList.java
            - Complaint.java
            - ComplaintList.java
            - ComplaintStatus.java
            - Office.java
            - OfficeList.java
            - Officer.java
            - Report.java
            - ReportList.java
            - UserList.java
            - UserOfficer.java
            - Users.java
            - Vote.java
            - VoteList.java
          - services
            - BanListDataSource.java
            - ComplaintCategoryFilterer.java
            - ComplaintListDataSource.java
            - ComplaintStatusFilterer.java
            - DataSource.java
            - Filterer.java
            - OfficerListDataSource.java
            - VoteListDataSource.java
        - com.github.saacsos.fxrouter-1.0.0
        - Main.java
        - ProjectApplication.java
      - module-info.java
    - resources
      - ku.cs
        - admin_scene_images
        - button_css
        - corruptcomplaint_images
        - credit_Image
        - enrollcomplaint_images
        - home_images
        - login_images
        - normalcomplaint_images
        - officer_images
        - pass_images
        - placecomplaint_images
        - reg_images
        - teachercomplaint_images
        - user_account_scene_image
          - admin_account.fxml
          - admin_ban_reason.fxml
          - admin_change_password.fxml
          - admin_complaint_scene.fxml
          - admin_manage_ban.fxml
          - admin_selected_report_complaint.fxml
          - admin_signup_officer.fxml
          - adminscene.fxml
          - changepassword.fxml
          - common_button_reactive.css
          - corruptcomplaint.fxml
          - credit.fxml
          - enrollcomplaint.fxml
          - home.fxml
          - login.fxml
          - normalcomplaint.fxml
          - officer,fxml
          - officer_change_password.fxml
          - officer_list_member.fxml
          - placecomplaint.fxml
          - project_opening_scene.fxml
          - register.fxml
          - report_complaint.fxml
          - selected_complaint_detail.fxml
          - teachercomplaint.fxml
          - user_account.fxml
          - user_complaint_list.fxml
          - user_request_unban.fxml
      - home.css
      - home2.css
