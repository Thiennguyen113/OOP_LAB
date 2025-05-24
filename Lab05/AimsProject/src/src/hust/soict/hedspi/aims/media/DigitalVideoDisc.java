package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.IllegalItemException;
import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Media implements Playable {
    private String director;
    private int length;

    private static int nbDigitalVideoDiscs = 0;

    // Constructor chính
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost); // Tự động tăng ID
        this.director = director;
        this.length = length;
    }

    public DigitalVideoDisc(String title) {
        this(title, "Unknown", "Unknown", 0, 0.0f);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        this(title, category, "Unknown", 0, cost);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DigitalVideoDisc)) return false;
        DigitalVideoDisc that = (DigitalVideoDisc) obj;
        return this.length == that.length &&
                this.getTitle().equals(that.getTitle()) &&
                this.getCategory().equals(that.getCategory()) &&
                ((this.director == null && that.director == null) ||
                        (this.director != null && this.director.equals(that.director)));
    }

    @Override
    public void play() throws PlayerException {
        if (this.length <= 0) {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
        System.out.println("Playing DVD: " + getTitle());
        System.out.println("DVD length: " + length + " minutes");
    }

    @Override
    public String play(boolean returnDetails) throws PlayerException {
        play();
        return String.format(
                "Playing DVD: %s\nDirector: %s\nLength: %d minutes",
                getTitle(), getDirector(), getLength()
        );
    }

    @Override
    public String toString() {
        return String.format("DVD - %s - %s - %s - %d - $%.2f",
                getTitle(), getCategory(), director, length, getCost());
    }
}
