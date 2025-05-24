module $MODULE_NAME$ {
    requires java.desktop;
    requires javafx.fxml;
    requires javafx.controls;

    opens hust.soict.hedspi.javafx to javafx.fxml;
}