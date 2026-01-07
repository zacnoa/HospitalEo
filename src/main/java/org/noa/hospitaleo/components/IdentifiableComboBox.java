package org.noa.hospitaleo.components;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.noa.hospitaleo.entity.IdentifiableEntity;

import util.RepositoryUtils;

public class IdentifiableComboBox extends ComboBox<IdentifiableEntity> {

    public IdentifiableComboBox(){}// Prazno radi inicijalizacije

    public void  setUp(ObservableList<IdentifiableEntity> items, ObjectProperty<String> selectedIdProperty, String prompt)
    {
        promptTextProperty().set(prompt);
        setItems(items);
        setConverter(RepositoryUtils.stringConverterFactory(items));

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
    public void setUp(ObjectProperty<String> selectedIdProperty,String prompt)
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
        setConverter(RepositoryUtils.stringConverterFactory(options));
    }

}
