module ru.gb.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ru.gb.javafxapp to javafx.fxml;
    exports ru.gb.javafxapp;
}