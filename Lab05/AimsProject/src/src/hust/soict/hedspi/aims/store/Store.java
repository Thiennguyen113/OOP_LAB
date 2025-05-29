package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media media) {
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("The media " + media.getTitle() + " has been added to the store.");
        } else {
            System.out.println("The media is already in the store.");
        }
    }

    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println("The media " + media.getTitle() + " has been removed from the store.");
        } else {
            System.out.println("The media was not found in the store.");
        }
    }

    public Media getMediaByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public void searchByTitle(String title) {
        Media foundMedia = getMediaByTitle(title);
        if (foundMedia != null) {
            System.out.println("Found Media: " + foundMedia.toString());
        } else {
            System.out.println("No media found with title: " + title);
        }
    }

    public void printStore() {
        if (itemsInStore.isEmpty()) {
            System.out.println("The store is empty.");
        } else {
            System.out.println("********** STORE ITEMS **********");
            int index = 1;
            for (Media media : itemsInStore) {
                System.out.println(index + ". " + media.toString());
                index++;
            }
            System.out.println("*********************************");
        }
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}
