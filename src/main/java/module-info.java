module org.noa.hospitaleo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jakarta.json.bind;
    requires jakarta.xml.bind;
    requires java.desktop;
    requires org.controlsfx.controls;
    requires javafx.base;


    opens org.noa.hospitaleo to javafx.fxml;
    exports org.noa.hospitaleo;

    exports org.noa.hospitaleo.controllers;
    opens org.noa.hospitaleo.controllers to javafx.fxml;

    exports org.noa.hospitaleo.components;
    opens org.noa.hospitaleo.components to javafx.fxml;

}