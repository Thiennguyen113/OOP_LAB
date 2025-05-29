package hust.soict.hedspi.aims.screen.customer;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.IllegalItemException;
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

    public static void main(String[] args) throws IllegalItemException {
        store = new Store();
        cart = new Cart();
        ArrayList<Media> List = new ArrayList<>();
        // Book
        List.add(new Book("Attack on Titan", "Action", 29.99f, Collections.singletonList("Hajime Isayama")));
        List.add(new Book("One Piece", "Adventure", 24.50f, Collections.singletonList("Eiichiro Oda")));
        List.add(new Book("Naruto", "Action", 22.75f, Collections.singletonList("Masashi Kishimoto")));
        List.add(new Book("My Hero Academia", "Superhero", 20.99f, Collections.singletonList("Kohei Horikoshi")));
        List.add(new Book("Tokyo Ghoul", "Horror", 26.25f, Collections.singletonList("Sui Ishida")));
        List.add(new Book("Demon Slayer", "Action", 28.50f, Collections.singletonList("Koyoharu Gotouge")));
        List.add(new Book("Chainsaw Man", "Dark Fantasy", 23.99f, Collections.singletonList("Tatsuki Fujimoto")));
        List.add(new Book("Jujutsu Kaisen", "Supernatural", 25.75f, Collections.singletonList("Gege Akutami")));

    // DVD
        List.add(new DigitalVideoDisc("Your Name", "Romance", "Makoto Shinkai", 106, 27.99f));
        List.add(new DigitalVideoDisc("Spirited Away", "Fantasy", "Hayao Miyazaki", 125, 30.50f));
        List.add(new DigitalVideoDisc("Princess Mononoke", "Adventure", "Hayao Miyazaki", 134, 29.99f));
        List.add(new DigitalVideoDisc("Grave of the Fireflies", "Drama", "Isao Takahata", 89, 22.99f));
        List.add(new DigitalVideoDisc("Neon Genesis Evangelion", "Sci-Fi", "Hideaki Anno", 87, 24.99f));
        List.add(new DigitalVideoDisc("Cowboy Bebop: The Movie", "Sci-Fi", "Shinichiro Watanabe", 115, 26.50f));
        List.add(new DigitalVideoDisc("Ghost in the Shell", "Cyberpunk", "Mamoru Oshii", 83, 23.99f));
        List.add(new DigitalVideoDisc("Perfect Blue", "Psychological", "Satoshi Kon", 81, 21.99f));

    // CD
        CompactDisc attackOnTitanCD = new CompactDisc("Attack on Titan OST", "Anime OST", "Hiroyuki Sawano", 556, 18.99f, "Hiroyuki Sawano");
        attackOnTitanCD.addTrack(new Track("Guren no Yumiya", 5));
        attackOnTitanCD.addTrack(new Track("Shinzou wo Sasageyo!", 4));
        List.add(attackOnTitanCD);

        CompactDisc narutoCD = new CompactDisc("Naruto OST", "Anime OST", "Toshiro Masuda", 173, 15.99f, "Toshiro Masuda");
        narutoCD.addTrack(new Track("Sadness and Sorrow", 3));
        narutoCD.addTrack(new Track("Fighting Spirit", 4));
        List.add(narutoCD);

        CompactDisc onePieceCD = new CompactDisc("One Piece OST", "Anime OST", "Kohei Tanaka", 0, 17.50f, "Kohei Tanaka");
        onePieceCD.addTrack(new Track("We Are!", 4));
        onePieceCD.addTrack(new Track("Overtaken", 3));
        List.add(onePieceCD);

        CompactDisc jujutsuKaisenCD = new CompactDisc("Jujutsu Kaisen OST", "Anime OST", "Hiroaki Tsutsumi", 0, 19.99f, "Hiroaki Tsutsumi");
        jujutsuKaisenCD.addTrack(new Track("Kaikai Kitan", 4));
        jujutsuKaisenCD.addTrack(new Track("Battle Theme", 5));
        List.add(jujutsuKaisenCD);

        CompactDisc tokyoGhoulCD = new CompactDisc("Tokyo Ghoul OST", "Anime OST", "Yutaka Yamada", 0, 16.75f, "Yutaka Yamada");
        tokyoGhoulCD.addTrack(new Track("Unravel", 4));
        tokyoGhoulCD.addTrack(new Track("Glassy Sky", 5));
        List.add(tokyoGhoulCD);

        for (Media media : List) {
            store.addMedia(media);
        }
        launch();
    }
}
