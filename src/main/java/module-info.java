module org.noa.hospitaleo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.noa.hospitaleo to javafx.fxml;
    exports org.noa.hospitaleo;
}