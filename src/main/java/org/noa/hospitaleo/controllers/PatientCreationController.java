package org.noa.hospitaleo.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.noa.hospitaleo.entity.SearchbyNameEntity;
import org.noa.hospitaleo.repository.MockEntityRepository;
import java.util.List;


public class PatientCreationController {


    @FXML
    private GridPane gridPane;
    @FXML
    private TextField patientName;
    @FXML
    private TextField patientOIB;
    @FXML
    private TextArea patientDiagnosis;
    @FXML
    private CheckBox underageFlag;
    @FXML
    private String selectedDepartmentId;

    @FXML
    private ComboBox<SearchbyNameEntity> departmentComboBox;
    @FXML
    private ComboBox<SearchbyNameEntity> doctorComboBox;

    @FXML
    private void initialize() {

        ObservableList<SearchbyNameEntity> options = FXCollections.observableArrayList(
                MockEntityRepository.getDepartmentMap().keySet().stream()
                        .map(MockEntityRepository::getDepartment)
                        .map(d -> new SearchbyNameEntity(d.getName(), d.getId()))
                        .toList()
        );

        departmentComboBox.setItems(options);

        departmentComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(SearchbyNameEntity entity) {
                return entity == null ? "" : entity.getName();
            }

            @Override
            public SearchbyNameEntity fromString(String string) {
                // traži po imenu, vraća null ako nema
                return options.stream()
                        .filter(item -> item.getName().equalsIgnoreCase(string))
                        .findFirst()
                        .orElse(null);
            }
        });
        departmentComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedDepartmentId = newVal.getId();
            }
        });
    }

}

