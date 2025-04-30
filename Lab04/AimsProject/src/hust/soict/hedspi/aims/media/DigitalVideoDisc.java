package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Media {
    private String director;
    private int length;

    private static int nbDigitalVideoDiscs = 0;

    // Constructor with only title (default category, cost, director, and length)
    public DigitalVideoDisc(String title) {
        this(title, "Unknown", "Unknown", 0, 0.0f);
    }

    // Constructor with title, category, and cost
    public DigitalVideoDisc(String title, String category, float cost) {
        this(title, category, "Unknown", 0, cost);
    }

    // Full constructor
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(nbDigitalVideoDiscs + 1, title, category, cost);  // Call the superclass constructor with the correct parameters
        this.director = director;
        this.length = length;

        nbDigitalVideoDiscs++;  // Increment the count of DVDs
        this.setId(nbDigitalVideoDiscs);  // Set the ID
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    // Override equals method to compare DVDs based on title, category, director, and length
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DigitalVideoDisc that = (DigitalVideoDisc) obj;
        return this.getTitle().equals(that.getTitle()) &&
                this.getCategory().equals(that.getCategory()) &&
                this.director.equals(that.director) &&
                this.length == that.length;
    }

    // Play the DVD
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }

    // Override toString method to display DVD details
    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + director + " - " + length + " - " + getCost();
    }
}
