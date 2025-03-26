public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            itemsOrdered[qtyOrdered] = disc;
            qtyOrdered++;
            System.out.println("The disc " + disc.getTitle() + " has been added.");
        } else {
            System.out.println("The cart is full. Cannot add more.");
        }
    }

    public void addDigitalVideoDisc(DigitalVideoDisc... DVDs) {
        for (DigitalVideoDisc disc : DVDs) {
            if (qtyOrdered < MAX_NUMBERS_ORDERED) {
                addDigitalVideoDisc(disc);
            } else {
                System.out.println("The Cart is full. Cannot add " + disc.getTitle());
                break;
            }
        }
    }


    public void addDigitalVideoDisc(DigitalVideoDisc DVD1, DigitalVideoDisc DVD2) {
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            addDigitalVideoDisc(DVD1);
        } else {
            System.out.println("The Cart is full. Cannot add " + DVD1.getTitle());
        }

        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            addDigitalVideoDisc(DVD2);
        } else {
            System.out.println("The Cart is full. Cannot add " + DVD2.getTitle());
        }
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].equals(disc)) {
                found = true;
                for (int j = i; j < qtyOrdered - 1; j++) { // Tránh truy cập ngoài mảng
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                itemsOrdered[qtyOrdered - 1] = null; // Xóa phần tử cuối
                qtyOrdered--;
                System.out.println("The disc " + disc.getTitle() + " has been removed.");
                break;
            }
        }
        if (!found) {
            System.out.println("The disc is not found.");
        }
    }


    public float totalCost() {
        float sum = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            sum += itemsOrdered[i].getCost();
        }
        return sum;
    }

    public void displayCart() {
        System.out.println("********* CART *********");
        if (qtyOrdered == 0) {
            System.out.println("The cart is empty.");
        } else {
            for (int i = 0; i < qtyOrdered; i++) {
                System.out.println((i + 1) + ". " + itemsOrdered[i].getTitle() + " - $" + itemsOrdered[i].getCost());
            }
        }
        System.out.println("Total cost: $" + totalCost());
        System.out.println("************************");
    }
}
