package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import org.noa.hospitaleo.entity.SearchbyNameEntity;
import org.noa.hospitaleo.interfaces.Searchable;


import java.util.List;
import java.util.Map;

public class RepositoryUtils {

    public static <T extends Searchable> ObservableList<SearchbyNameEntity> mapToObservableList(Map<?, T> map) {
        return FXCollections.observableArrayList(
                map.keySet().stream()
                        .map(map::get)
                        .map(d -> new SearchbyNameEntity(d.getName(), d.getId()))
                        .toList()
        );
    }

    public static <T extends Searchable> ObservableList<SearchbyNameEntity> listToObservableList(List<T> list) {
        return FXCollections.observableArrayList(list.stream()
                .map(d -> new SearchbyNameEntity(d.getName(), d.getId())).toList());
    }

    public static  StringConverter<SearchbyNameEntity> stringConverterFactory(ObservableList<SearchbyNameEntity> options)
    {
        return new StringConverter<SearchbyNameEntity>()
        {

            @Override
            public String toString(SearchbyNameEntity entity) {
                return entity == null ? "" : entity.getName();
            }

            @Override
            public SearchbyNameEntity fromString(String string) {
                return options.stream()
                        .filter(item -> item.getName().equalsIgnoreCase(string))
                        .findFirst()
                        .orElse(null);
            }
        };
    }


}
