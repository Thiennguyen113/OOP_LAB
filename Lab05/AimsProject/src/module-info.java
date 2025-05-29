module com.example.aimsproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens hust.soict.hedspi.aims to javafx.fxml;
    opens hust.soict.hedspi.aims.screen.customer.controller to javafx.fxml;

    exports hust.soict.hedspi.aims;
    exports hust.soict.hedspi.aims.store;
    exports hust.soict.hedspi.aims.cart;
    exports hust.soict.hedspi.aims.media;
    exports hust.soict.hedspi.aims.screen.customer;
    exports hust.soict.hedspi.aims.screen.customer.controller;
}
