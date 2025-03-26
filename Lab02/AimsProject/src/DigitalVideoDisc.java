public class DigitalVideoDisc {
    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;

    private static int nbDigitalVideoDiscs = 0;
    private int id;

    public DigitalVideoDisc(String title) {
        this(title, "Unknown", "Unknown", 0, 0.0f);
    }
    public DigitalVideoDisc(String title, String category, float cost) {
        this(title, category, "Unknown", 0, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    // Getter
    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public float getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DigitalVideoDisc that = (DigitalVideoDisc) obj;
        return this.id == that.id; // So sánh theo id duy nhất
    }
}
