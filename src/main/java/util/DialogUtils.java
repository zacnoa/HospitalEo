package util;

import javafx.scene.control.Alert;

public class DialogUtils {

    public static void showDisplayScreenErrorDialog(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Pogreška pri prikazu ekrana");
        alert.setHeaderText("Nije moguce prikazati odabrani ekran");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showSearchScreenErrorDialog(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Pogreška pri pretrazivanju");
        alert.setHeaderText("Nije moguce pronaci rezultat prema trazenim parametrima");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
