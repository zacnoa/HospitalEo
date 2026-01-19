module org.noa.hospitaleo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jakarta.json.bind;
    requires jakarta.xml.bind;
    requires java.desktop;
    requires javafx.base;
    requires org.slf4j;
    requires java.sql;


    opens org.noa.hospitaleo to javafx.fxml;

    exports org.noa.hospitaleo.frontend.controllers;
    opens org.noa.hospitaleo.frontend.controllers to javafx.fxml;

    exports org.noa.hospitaleo.frontend.components;
    opens org.noa.hospitaleo.frontend.components to javafx.fxml;
    exports org.noa.hospitaleo;

}