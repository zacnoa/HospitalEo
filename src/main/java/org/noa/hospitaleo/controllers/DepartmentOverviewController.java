package org.noa.hospitaleo.controllers;


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
import org.noa.hospitaleo.entity.Department;
import org.noa.hospitaleo.repository.MockEntitiyRepository;

public class DepartmentOverviewController {

    @FXML
    private ListView<Department> listView;
    @FXML
    private ObservableList<Department> departments;

    @FXML
    public void initialize()
    {
        departments = FXCollections.observableList(MockEntitiyRepository.getDepartmentStorage());
        listView.setItems(departments);


        listView.setCellFactory(lv -> new ListCell<>() {

            private  Label nameLabel = new Label();
            private  Label patientsLabel = new Label();
            private  Label doctorsLabel = new Label();
            private  Label roomsLabel = new Label();

            private  Region spacer = new Region();
            private  HBox statsRow = new HBox(12, patientsLabel, doctorsLabel, roomsLabel, spacer);
            private  VBox root = new VBox(4, nameLabel, statsRow);

            {
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

                int brojPacijenata = dept.getPatients() == null ? 0 : dept.getPatients().size();
                int brojDoktora = dept.getDoctors() == null ? 0 : dept.getDoctors().size();
                int brojSoba = dept.getRooms() == null ? 0 : dept.getRooms().size();

                nameLabel.setText(dept.getName());
                patientsLabel.setText("Pacijenti: " + brojPacijenata);
                doctorsLabel.setText("Doktori: " + brojDoktora);
                roomsLabel.setText("Sobe: " + brojSoba);

                setText(null);
                setGraphic(root);
            }
        });
    }
}
