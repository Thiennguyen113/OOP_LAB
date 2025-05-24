package hust.soict.hedspi.aims.media;

import java.util.Comparator;
import java.util.Objects;

public class Media implements Comparable<Media> {
    private int id;
    private String title;
    private String category;
    private float cost;

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Title: " + title + " | Category: " + category + " | Cost: $" + cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Media)) return false;

        Media media = (Media) obj;

        // Avoid NullPointerException and compare both title and cost
        return title != null && title.equalsIgnoreCase(media.title)
                && Float.compare(cost, media.cost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title != null ? title.toLowerCase() : null, cost);
    }

    @Override
    public int compareTo(Media other) {
        if (other == null) throw new NullPointerException("Cannot compare to null");

        int titleCompare = this.title.compareToIgnoreCase(other.title);
        if (titleCompare != 0) return titleCompare;

        return Float.compare(this.cost, other.cost);
    }

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
}
