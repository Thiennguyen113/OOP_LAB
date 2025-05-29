package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.exception.IllegalItemException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StoreManagerScreen extends JFrame {
    private Store store;

    public JPanel createNorth(){
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north,BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }
    public JMenuBar createMenuBar(){
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(e -> {
            new StoreManagerScreen(this.store);
            this.dispose();
        });
        menu.add(viewStoreItem);

        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(e -> {
            new AddBookToStoreScreen(store);
            this.dispose();
        });

        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(e -> {
            new AddCompactDiscToStoreScreen(store);
            this.dispose();
        });

        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(e -> {
            new AddDigitalVideoDiscToStoreScreen(store);
            this.dispose();
        });

        smUpdateStore.add(addBook);
        smUpdateStore.add(addCD);
        smUpdateStore.add(addDVD);
        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    public JPanel createHeader(){
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.GRAY);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }
    private JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i = 0; i < mediaInStore.size(); i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i));
            center.add(cell);
        }
        return center;
    }

    public StoreManagerScreen(Store store) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void initializeStore(Store store) throws IllegalItemException {
        ArrayList<Media> List = new ArrayList<>();
        // Book
        List.add(new Book( "Sherlock Holmes", "Detective", 22.50f, Collections.singletonList("Arthur Conan Doyle")));
        List.add(new Book("Harry Potter and the Sorcerer's Stone", "Fantasy", 28.99f, Collections.singletonList("J.K. Rowling")));
        List.add(new Book("To Kill a Mockingbird", "Classic", 18.99f, Collections.singletonList("Harper Lee")));

        // DVD
        List.add(new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 24.99f));
        List.add(new DigitalVideoDisc("The Godfather", "Crime", "Francis Ford Coppola", 175, 21.99f));

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
    }

    public static void main(String[] args) {
        Store store = new Store();
        try{
            initializeStore(store);
        }catch(IllegalItemException e){
            e.printStackTrace();
        }
        new StoreManagerScreen(store);
    }
}
