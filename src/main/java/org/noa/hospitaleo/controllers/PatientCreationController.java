package org.noa.hospitaleo.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import org.noa.hospitaleo.entity.Department;
import org.noa.hospitaleo.entity.SearchbyNameEntity;
import org.noa.hospitaleo.repository.EntityRepository;
import org.noa.hospitaleo.repository.MockEntityRepository;



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
    private ComboBox<SearchbyNameEntity> departmentComboBox;

    @FXML
    private void initialize() {

        // 1. Učitaj departmente u listu
        ObservableList<SearchbyNameEntity> options =
                FXCollections.observableList(
                        MockEntityRepository.getDepartmentMap().keySet().stream()
                                .map(MockEntityRepository::getDepartment)
                                .map(d -> new SearchbyNameEntity(d.getName(), d.getId()))
                                .toList()
                );

        // 2. FilteredList – u početku prikazuje sve
        FilteredList<SearchbyNameEntity> filteredList =
                new FilteredList<>(options, item -> true);

        // 3. Postavi items u ComboBox
        departmentComboBox.setItems(filteredList);

        // 4. StringConverter (OBAVEZNO za custom objekte)
        departmentComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(SearchbyNameEntity entity) {
                return entity == null ? "" : entity.getName();
            }

            @Override
            public SearchbyNameEntity fromString(String string) {
                return departmentComboBox.getItems().stream()
                        .filter(item -> item.getName().equalsIgnoreCase(string))
                        .findFirst()
                        .orElse(null);
            }
        });

        // 5. Filtriranje dok korisnik tipka
        departmentComboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {

            String filter = newValue == null ? "" : newValue.toLowerCase().trim();

            filteredList.setPredicate(item ->
                    filter.isEmpty() ||
                            item.getName().toLowerCase().contains(filter)
            );

            if (!departmentComboBox.isShowing()) {
                departmentComboBox.show();
            }
        });

    }


}

