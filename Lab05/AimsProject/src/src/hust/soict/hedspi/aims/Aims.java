package hust.soict.hedspi.aims;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.MediaNotFoundException;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import java.util.Scanner;

public class Aims {

    private static Store store = new Store();
    private static Cart cart = new Cart();

    public static void main(String[] args) {
        store.addMedia(new DigitalVideoDisc("Star Wars", "Sci-Fi", "George Lucas", 120, 19.99f));
        store.addMedia(new DigitalVideoDisc("The Lord of the Rings", "Fantasy", "Peter Jackson", 180, 29.99f));

        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            showMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    viewStore(scanner);
                    break;
                case 2:
                    updateStore(scanner);
                    break;
                case 3:
                    viewCart();
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0);
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3: ");
    }

    public static void viewStore(Scanner scanner) {
        store.printStore();
        storeMenu(scanner);
    }

    public static void storeMenu(Scanner scanner) {
        int option;
        do {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. See a media's details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Clear buffer
            switch (option) {
                case 1:
                    viewMediaDetails(scanner);
                    break;
                case 2:
                    addMediaToCart(scanner);
                    break;
                case 3:
                    playMedia(scanner);
                    break;
                case 4:
                    viewCart();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0);
    }

    public static void viewMediaDetails(Scanner scanner) {
        System.out.print("Enter the title of the media: ");
        String title = scanner.nextLine();
        Media media = store.getMediaByTitle(title);
        if (media != null) {
            System.out.println("Media details: " + media);
            mediaDetailsMenu(scanner, media);
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void mediaDetailsMenu(Scanner scanner, Media media) {
        int option;
        do {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            System.out.println("2. Play");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    cart.addMedia(media);
                    break;
                case 2:
                    if (media instanceof Playable) {
                        try {
                            ((Playable) media).play();
                        } catch (PlayerException e) {
                            System.err.println("Error playing media: " + e.getMessage());
                        }
                    } else {
                        System.out.println("This media cannot be played.");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0);
    }

    public static void addMediaToCart(Scanner scanner) {
        System.out.print("Enter the title of the media: ");
        String title = scanner.nextLine();
        Media media = store.getMediaByTitle(title);
        if (media != null) {
            cart.addMedia(media);
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void playMedia(Scanner scanner) {
        System.out.print("Enter the title of the media to play: ");
        String title = scanner.nextLine();
        Media media = store.getMediaByTitle(title);
        if (media != null) {
            if (media instanceof Playable) {
                try {
                    ((Playable) media).play();
                } catch (PlayerException e) {
                    System.err.println("PlayerException caught:");
                    System.err.println("Message: " + e.getMessage());
                    e.printStackTrace();
                    System.out.println("Error playing media: " + e.getMessage());
                }
            } else {
                System.out.println("This media cannot be played.");
            }
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void updateStore(Scanner scanner) {
        System.out.println("Options: ");
        System.out.println("1. Add media to store");
        System.out.println("2. Remove media from store");
        System.out.println("Choose option: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                //Thêm media
                System.out.print("Enter the media title to add: ");
                String title = scanner.nextLine();
                Media newMedia = new DigitalVideoDisc(title, "Category", "Director", 120, 15.99f);
                store.addMedia(newMedia);
                break;
            case 2:
                // Xóa media
                System.out.print("Enter the title of the media to remove: ");
                String removeTitle = scanner.nextLine();
                Media removeMedia = store.getMediaByTitle(removeTitle);
                if (removeMedia != null) {
                    store.removeMedia(removeMedia);
                } else {
                    System.out.println("Media not found.");
                }
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
//Xem giỏ hàng
    public static void viewCart() {
        cart.print();
        cartMenu();
    }
    public static void cartMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do{
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter media in cart");
            System.out.println("2. Sort media in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4-5");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    filterMediaInCart();
                    break;
                case 2:
                    sortMediaInCart();
                    break;
                case 3:
                    System.out.print("Enter the title of media to remove: ");
                    String removeTitle = scanner.nextLine();
                    Media toRemove = cart.searchByTitle(removeTitle);
                    if (toRemove != null) {
                        try {
                            cart.removeMedia(toRemove);
                            System.out.println("Media removed from cart.");
                        } catch (MediaNotFoundException e) {
                            System.out.println(e.getMessage());
                        }

                    } else {
                        System.out.println("Media not found.");
                    }
                    break;
                case 4:
                    playaMedia();
                    break;
                case 5:
                    System.out.println("Order placed successfully");
                    cart.clearCart();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }while(option != 0);
    }

    public static void filterMediaInCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Filter by: 1-ID | 2-Title");
        int filterOption = scanner.nextInt();
        scanner.nextLine();
        if (filterOption == 1) {
            System.out.print("Enter media ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Media media = cart.searchByID(id);
            if (media != null) {
                System.out.println("Found: " + media.toString());
            } else {
                System.out.println("No media found with ID: " + id);
            }
        } else if (filterOption == 2) {
            System.out.print("Enter media title: ");
            String title = scanner.nextLine();
            Media media = cart.searchByTitle(title);
            if (media != null) {
                System.out.println("Found: " + media.toString());
            } else {
                System.out.println("No media found with title: " + title);
            }
        } else {
            System.out.println("Invalid filter option.");
        }
    }

    public static void sortMediaInCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Sort by : 1-Title | 2-Cost: ");
        int sortOption = scanner.nextInt();
        scanner.nextLine();
        if (sortOption == 1) {
            cart.sortByTitle();
        } else if (sortOption == 2) {
            cart.sortByCost();
        } else {
            System.out.println("Invalid sort option.");
        }
        cart.print();
    }

    public static void playaMedia() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the media to play: ");
        String playTitle = scanner.nextLine();

        Media media = cart.searchByTitle(playTitle);
        if (media!= null && media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                System.err.println("PlayerException caught:");
                System.err.println("Message: " + e.getMessage());
                System.err.println("Details: " + e.toString());
                e.printStackTrace();
                System.out.println("Error occurred while playing the media: " + e.getMessage());
            }
        } else if (media == null) {
            System.out.println("No media found with the title: " + playTitle);
        } else {
            System.out.println("This media cannot be played.");
        }
    }

}
