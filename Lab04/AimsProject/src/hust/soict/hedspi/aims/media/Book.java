package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<String>();

    public Book(int id, String title, String category, float cost) {
        super(id,title, category, cost);
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void addAuthor(String author) {
        if (!authors.contains(author)) {
            authors.add(author);
            System.out.println("Author " + author+ " added.");
        } else {
            System.out.println("Author " + author + " is already in the list.");
        }
    }

    public void removeAuthor(String author) {
        if (authors.contains(author)) {
            authors.remove(author);
            System.out.println("Author " + author + " removed.");
        } else {
            System.out.println("Author " + author + " not found in the list.");
        }
    }

    @Override
    public String toString() {
        return "Book - " + getTitle() + " - " + getCategory() + " - $" + getCost()
                + " - Authors: " + String.join(", ", authors);
    }
}
