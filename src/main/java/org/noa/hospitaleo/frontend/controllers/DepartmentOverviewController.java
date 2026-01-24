package org.noa.hospitaleo.frontend.controllers;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.backend.DatabaseAPI;
import org.noa.hospitaleo.frontend.entity.Department;
import org.noa.hospitaleo.frontend.util.DialogUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DepartmentOverviewController {

    @FXML
    private ListView<Department> listView;

    @FXML
    public void initialize()
    {
        ObservableList<Department> departments = FXCollections.observableArrayList();
        try {
            departments.setAll(HospitalEoApplication.getApi().getAllDepartments());
        }catch(SQLException e)
        {
            DialogUtils.showDatabaseErrorDialog("Greska pri dohvacanju departments");
            HospitalEoApplication.logger.error(e.getMessage(),e);
        }
        listView.setItems(departments);
        listView.setCellFactory(_ -> new DepartmentListCell());
    }

    private static class DepartmentListCell extends ListCell<Department> {

        private final Label nameLabel = new Label();
        private final Label patientsLabel = new Label();
        private final Label doctorsLabel = new Label();
        private final Label roomsLabel = new Label();

        private final Region spacer = new Region();
        private final HBox statsRow = new HBox(12, patientsLabel, doctorsLabel, roomsLabel, spacer);
        private final VBox root = new VBox(4, nameLabel, statsRow);

        private DepartmentListCell() {
            nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            statsRow.setStyle("-fx-text-fill: -fx-text-inner-color; -fx-opacity: 0.9;");

            HBox.setHgrow(spacer, Priority.ALWAYS);
            root.setPadding(new Insets(8, 10, 8, 10));
        }

        @Override
        protected void updateItem(Department dept, boolean empty) {
            super.updateItem(dept, empty);

            if (empty || dept == null) {
                setText(null);
                setGraphic(null);
                return;
            }

            nameLabel.setText(dept.getName());
            Thread.startVirtualThread(()->
            {
                Map<String,Integer> stats = new HashMap<>();
                try {
                    stats = HospitalEoApplication.getApi().getDepartmentStatistics(dept);
                } catch (SQLException e) {
                    HospitalEoApplication.logger.error(e.getMessage(),e);
                    Platform.runLater(()->
                            DialogUtils.showDatabaseErrorDialog("Doslo je do greske kod skupljanja podataka za " + dept.getName() + " odjel")
                    );
                }
                Map<String, Integer> finalStats = stats;
                Platform.runLater(()->
                {
                    patientsLabel.setText("Pacijenti: " + finalStats.get("patients"));
                    doctorsLabel.setText("Doktori: " + finalStats.get("doctors"));
                    roomsLabel.setText("Sobe: " + finalStats.get("rooms"));
                });
            });

            setText(null);
            setGraphic(root);
        }
    }
}
