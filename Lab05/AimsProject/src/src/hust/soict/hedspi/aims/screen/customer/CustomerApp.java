package hust.soict.hedspi.aims.screen.customer;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CustomerApp extends Application {

    public static Store store = new Store();
    public static Cart cart = new Cart();

    @Override
    public void start(Stage stage) throws IOException {
        final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        ViewStoreController viewStoreController = new ViewStoreController(store, cart);
        fxmlLoader.setController(viewStoreController);

        Parent root = fxmlLoader.load();
        stage.setTitle("AIMS Media Store");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        ArrayList<Media> mediaList = new ArrayList<>();

        // Book
        Book book1 = new Book(1, "Clean Code", "Programming", 29.99f);
        book1.addAuthor("Robert C. Martin");
        mediaList.add(book1);

        Book book2 = new Book(2, "Effective Java", "Programming", 35.50f);
        book2.addAuthor("Joshua Bloch");
        mediaList.add(book2);

        // DVD
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 19.99f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Interstellar", "Sci-Fi", "Christopher Nolan", 0, 24.99f); // Will cause exception

        mediaList.add(dvd1);
        mediaList.add(dvd2);

        // CD
        CompactDisc cd1 = new CompactDisc(3, "The Best of Mozart", "Classical", "Various", 0, 14.99f, "Mozart");
        cd1.addTrack(new Track("Symphony No.40", 6));
        cd1.addTrack(new Track("Eine kleine Nachtmusik", 5));
        mediaList.add(cd1);

        CompactDisc cd2 = new CompactDisc(4, "Broken CD", "Error", "None", 0, 9.99f, "Unknown");
        mediaList.add(cd2);

        for (Media media : mediaList) {
            store.addMedia(media);
        }

        for (Media media : mediaList) {
            if (media instanceof Playable) {
                try {
                    ((Playable) media).play(true);
                } catch (PlayerException e) {
                    System.err.println("Exception caught while playing media:");
                    System.err.println(e.getMessage());
                    e.printStackTrace();

                    JOptionPane.showMessageDialog(null,
                            "An error occurred while playing media:\n" + e.getMessage(),
                            "Player Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        launch();
    }
}
