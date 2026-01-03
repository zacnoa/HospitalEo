package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import org.noa.hospitaleo.entity.IdentifiableEntity;
import org.noa.hospitaleo.interfaces.Identifiable;


import java.util.List;
import java.util.Map;

public class RepositoryUtils {

    public static <T extends Identifiable> ObservableList<IdentifiableEntity> mapToIdentifiableObservableList(Map<?, T> map) {
        return FXCollections.observableArrayList(
                map.keySet().stream()
                        .map(map::get)
                        .map(d -> new IdentifiableEntity(d.getName(), d.getId()))
                        .toList()
        );
    }

    public static <T extends Identifiable> ObservableList<IdentifiableEntity> listToIdentifiableObservableList(List<T> list) {
        return FXCollections.observableArrayList(list.stream()
                .map(d -> new IdentifiableEntity(d.getName(), d.getId())).toList());
    }

    public static   StringConverter<IdentifiableEntity> stringConverterFactory(ObservableList<IdentifiableEntity> options)
    {
        return new StringConverter<>()
        {

            @Override
            public String toString(IdentifiableEntity entity) {
                return entity == null ? "" : entity.getName();
            }

            @Override
            public IdentifiableEntity fromString(String string) {
                return options.stream()
                        .filter(item -> item.getName().equalsIgnoreCase(string))
                        .findFirst()
                        .orElse(null);
            }
        };
    }


}
