package util;

import javafx.scene.control.Alert;

import java.util.function.Supplier;

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
    public static void showEntityCreationsErrorDialog(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Pogreška pri kreiranju entiteta");
        alert.setHeaderText("Nije moguce kreirati zeljeni entitet");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void showEntityCreationSuccessDialog(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Zavsen upis u evidenciju");
        alert.setHeaderText("Uspesno ste kreirali novi entitet");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void showIOError(String message)
    {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Greska pri radu s datotekom");
        alert.setHeaderText("Greska pri citanju datoteke");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
