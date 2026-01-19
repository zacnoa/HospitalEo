package org.noa.hospitaleo.frontend.components;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.noa.hospitaleo.frontend.entity.IdentifiableEntity;

import org.noa.hospitaleo.frontend.util.Utils;

import java.util.UUID;

public class IdentifiableComboBox extends ComboBox<IdentifiableEntity> {

    @SuppressWarnings("java:S1186") // Prazno radi inicijalizacije
    public IdentifiableComboBox(){}

    public void  setUp(ObservableList<IdentifiableEntity> items, ObjectProperty<UUID> selectedIdProperty, String prompt)
    {
        promptTextProperty().set(prompt);
        setItems(items);
        setConverter(Utils.stringConverterFactory(items));

        getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue!=null)
            {
                selectedIdProperty.set(newValue.id());
            }
            else
            {
                selectedIdProperty.set(null);
            }
        });
    }
    public void setUp(ObjectProperty<UUID> selectedIdProperty,String prompt)
    {
        promptTextProperty().set(prompt);
        getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue!=null)
            {
                selectedIdProperty.set(newValue.id());
            }
            else
            {
                selectedIdProperty.set(null);
            }
        });
    }
    public void updateItems(ObservableList<IdentifiableEntity> options)
    {
        setItems(options);
        setConverter(Utils.stringConverterFactory(options));
    }

}
