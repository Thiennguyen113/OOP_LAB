package hust.soict.hedspi.aims.store;
import hust.soict.hedspi.aims.cart.Cart; // Nhớ import
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class TestViewStoreScreen extends Application {
    private static Store store;
    private static Cart cart;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        ViewStoreController viewStoreController = new ViewStoreController(store, cart);
        fxmlLoader.setController(viewStoreController);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        store = new Store();
        ArrayList<Media> List = new ArrayList<>();
        // Book
        List.add(new Book( "Sherlock Holmes", "Detective", 22.50f, Collections.singletonList("Arthur Conan Doyle")));
        List.add(new Book("Harry Potter and the Sorcerer's Stone", "Fantasy", 28.99f, Collections.singletonList("J.K. Rowling")));
        List.add(new Book("To Kill a Mockingbird", "Classic", 18.99f, Collections.singletonList("Harper Lee")));

        // CD
        CompactDisc thrillerCD = new CompactDisc("Thriller", "Pop", "Michael Jackson", 0, 15.99f, "Michael Jackson");
        thrillerCD.addTrack(new Track("Thriller", 6));
        thrillerCD.addTrack(new Track("Beat It", 4));
        List.add(thrillerCD);

        CompactDisc imagineCD = new CompactDisc("Imagine", "Rock", "John Lennon", 0, 14.59f, "John Lennon");
        imagineCD.addTrack(new Track("Imagine", 3));
        imagineCD.addTrack(new Track("Jealous Guy", 4));
        List.add(imagineCD);

        for (Media media : List) {
            store.addMedia(media);
        }
        launch();
    }
}
