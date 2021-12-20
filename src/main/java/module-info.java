module com.kodilla.battleships.battleships {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kodilla.battleships to javafx.fxml;
    exports com.kodilla.battleships;
}