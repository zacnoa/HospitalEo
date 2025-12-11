module org.noa.hospitaleo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.noa.hospitaleo to javafx.fxml;
    exports org.noa.hospitaleo;
    exports controllers;
    opens controllers to javafx.fxml;
}