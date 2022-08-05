module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;


    opens ku.cs to javafx.fxml;
    exports ku.cs;
    exports ku.cs.application.controllers;
    opens ku.cs.application.controllers to javafx.fxml;

}