package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.MediaNotFoundException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CartController {

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private Label costLabel;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TextField tfFilter;

    private final Cart cart;
    private final Store store;
    private FilteredList<Media> filteredList;

    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    private void showFilteredMedia(String filter) {
        if (filteredList == null) return;

        if (filter == null || filter.isEmpty()) {
            filteredList.setPredicate(media -> true);
        } else {
            Toggle selectedToggle = filterCategory.getSelectedToggle();
            if (selectedToggle == null) {
                filteredList.setPredicate(media -> true);
                return;
            }

            String lowerFilter = filter.toLowerCase();

            if (selectedToggle == radioBtnFilterId) {
                filteredList.setPredicate(media -> Integer.toString(media.getId()).contains(lowerFilter));
            } else if (selectedToggle == radioBtnFilterTitle) {
                filteredList.setPredicate(media -> media.getTitle().toLowerCase().contains(lowerFilter));
            } else {
                filteredList.setPredicate(media -> true);
            }
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Cart Empty", "Your cart is currently empty.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Place Order");
        confirm.setContentText("Do you want to place this order?");
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                cart.clearCart();
                showAlert(Alert.AlertType.INFORMATION, "Order Placed", "Your order has been placed successfully!");
            }
        });
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            try {
                String result = ((Playable) media).play(true);
                showAlert(Alert.AlertType.INFORMATION, "Playing Media", result);
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
            }
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        try {
            Media media = tblMedia.getSelectionModel().getSelectedItem();
            cart.removeMedia(media);
        } catch (MediaNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Remove error", e.getMessage());
        }
    }


    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
            ViewStoreController controller = new ViewStoreController(store, cart);
            fxmlLoader.setController(controller);

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateButtonBar(Media media) {
        if (media == null) {
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            if (media instanceof Playable) {
                btnPlay.setVisible(true);
            } else {
                btnPlay.setVisible(false);
            }
        }
    }

    private void updateTotalCost() {
        costLabel.setText(String.format("%.2f $", cart.totalCost()));
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(
                new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(
                new PropertyValueFactory<>("cost"));

        filteredList = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredList);

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Media> obs, Media oldMedia, Media newMedia) -> updateButtonBar(newMedia)
        );

        cart.getItemsOrdered().addListener((ListChangeListener<Media>) change -> updateTotalCost());

        tfFilter.textProperty().addListener((obs, oldVal, newVal) -> showFilteredMedia(newVal));
        filterCategory.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> showFilteredMedia(tfFilter.getText()));

        updateTotalCost();
    }
}
