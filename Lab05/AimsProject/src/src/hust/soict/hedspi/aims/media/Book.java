package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<>();

    public Book(String title, String category, float cost, List<String> authors) throws IllegalArgumentException {
        super(title, category, cost);
        this.authors.addAll(authors);
    }

    public String getAuthors() {
        if(authors.isEmpty()) return "";
        StringBuilder authorsString = new StringBuilder();
        for (String author : authors) {
            authorsString.append(author).append(", ");
        }
        authorsString.setLength(authorsString.length() - 2);
        return authorsString.toString();
    }

    public void addAuthor(String author) {
        if (!authors.contains(author)) {
            authors.add(author);
            System.out.println("Author " + author + " added.");
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
                + " - Authors: " + (authors.isEmpty() ? "N/A" : String.join(", ", authors));
    }

    public String printBook() {
        StringBuilder book = new StringBuilder("Book details:\n");
        book.append("Title: ").append(getTitle()).append("\n");
        book.append("Category: ").append(getCategory()).append("\n");
        book.append("Cost: ").append(getCost()).append("\n");
        book.append("Authors: ").append(authors.isEmpty() ? "N/A" : String.join(", ", authors)).append("\n");
        return book.toString();
    }
}