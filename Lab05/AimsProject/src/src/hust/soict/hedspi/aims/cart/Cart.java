package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.exception.MediaNotFoundException;
import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }
    public void addMedia(Media media) {
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is full. Cannot add more.");
        } else if (itemsOrdered.contains(media)) {
            System.out.println("This media is already in the cart.");
        } else {
            itemsOrdered.add(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been added.");
        }
    }

    public void addMedia(Media... mediaItems) {
        for (Media media : mediaItems) {
            if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
                addMedia(media);
            } else {
                System.out.println("The cart is full. Cannot add \"" + media.getTitle() + "\".");
                break;
            }
        }
    }

    public void removeMedia(Media media) throws MediaNotFoundException {
        if (!itemsOrdered.contains(media)) {
            throw new MediaNotFoundException("Media not found in cart.");
        }
        itemsOrdered.remove(media);
    }


    public float totalCost() {
        float sum = 0;
        for (Media media : itemsOrdered) {
            sum += media.getCost();
        }
        return sum;
    }

    public void displayCart() {
        System.out.println("********* CART *********");
        if (itemsOrdered.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            int index = 1;
            for (Media media : itemsOrdered) {
                System.out.println(index++ + ". " + media.toString());
            }
        }
        System.out.println("Total cost: $" + totalCost());
        System.out.println("************************");
    }

    public void print() {
        System.out.println("***********************CART***********************");
        if (itemsOrdered.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            System.out.println("Ordered Items:");
            int index = 1;
            for (Media media : itemsOrdered) {
                System.out.println(index++ + ". " + media.toString());
            }
        }
        System.out.printf("Total cost: %.2f$\n", totalCost());
        System.out.println("***************************************************");
    }

    public Media searchByID(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public class MediaComparatorByTitleCost implements Comparator<Media> {
        @Override
        public int compare(Media m1, Media m2) {
            int titleCompare = m1.getTitle().compareToIgnoreCase(m2.getTitle());
            if (titleCompare != 0) return titleCompare;
            return Float.compare(m1.getCost(), m2.getCost());
        }
    }

    public class MediaComparatorByCostTitle implements Comparator<Media> {
        @Override
        public int compare(Media m1, Media m2) {
            int costCompare = Float.compare(m2.getCost(), m1.getCost()); // giảm dần
            if (costCompare != 0) return costCompare;
            return m1.getTitle().compareToIgnoreCase(m2.getTitle());
        }
    }

    public void sortByTitle() {
        itemsOrdered.sort(new MediaComparatorByTitleCost());
        System.out.println("Media sorted by title and cost.");
    }

    public void sortByCost() {
        itemsOrdered.sort(new MediaComparatorByCostTitle());
        System.out.println("Media sorted by cost and title.");
    }

    public void clearCart(){
        itemsOrdered.clear();
        System.out.println("The cart has been emptied.");
    }
}