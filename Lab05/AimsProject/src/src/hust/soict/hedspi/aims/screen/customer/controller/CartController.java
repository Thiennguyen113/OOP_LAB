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
import java.util.Optional;

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

    private Cart cart;
    private Store store;
    private FilteredList<Media> filteredList;

    // Default no-arg constructor required for FXML loader
    public CartController() {}

    // Custom constructor for manual initialization (if needed)
    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    public void setStoreAndCart(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        if (filteredList != null) filteredList = null; // reset for new cart
        initializeTableAndListeners();
    }

    @FXML
    private void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media == null) {
            showAlert(Alert.AlertType.WARNING, "No selection", "Please select a media to play.");
            return;
        }
        if (media instanceof Playable) {
            try {
                String result = ((Playable) media).play(true);
                showAlert(Alert.AlertType.INFORMATION, "Playing Media", result);
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error playing media", e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Cannot play", "Selected media is not playable.");
        }
    }

    @FXML
    private void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media == null) {
            showAlert(Alert.AlertType.WARNING, "No selection", "Please select a media to remove.");
            return;
        }
        try {
            cart.removeMedia(media);
            showAlert(Alert.AlertType.INFORMATION, "Removed", "Media removed from cart.");
        } catch (MediaNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Remove error", e.getMessage());
        }
    }

    @FXML
    private void btnPlaceOrderPressed(ActionEvent event) {
        try {
            if (cart.getItemsOrdered().isEmpty()) {
                throw new IllegalStateException("Cart is empty");
            }
            Optional<ButtonType> result = showConfirmation("Confirm", "Are you sure you want to place order?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                cart.getItemsOrdered().clear();
                updateTotalCost();
                showAlert(Alert.AlertType.INFORMATION, "Successful", "Order placed successfully");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error order placed", e.getMessage());
        }
    }

    @FXML
    private void btnViewStorePressed(ActionEvent event) {
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));

            ViewStoreController viewStoreController = new ViewStoreController(store, cart);
            fxmlLoader.setController(viewStoreController);
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not load store view.");
            e.printStackTrace();
        }
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

    public void updateButtonBar(Media media) {
        if (media == null) {
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            btnPlay.setVisible(media instanceof Playable);
        }
    }

    private void updateTotalCost() {
        if (costLabel != null && cart != null)
            costLabel.setText(String.format("%.2f $", cart.totalCost()));
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Optional<ButtonType> showConfirmation(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert.showAndWait();
    }

    @FXML
    public void initialize() {
        initializeTableAndListeners();
    }

    private void initializeTableAndListeners() {
        if (colMediaId == null) return;
        colMediaId.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        if (cart == null) return;
        if(cart.getItemsOrdered().isEmpty()){
            tblMedia.setItems(cart.getItemsOrdered());
        }

        tblMedia.setItems(filteredList);

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
        btnPlay.setTooltip(new Tooltip("Play selected media"));
        btnRemove.setTooltip(new Tooltip("Remove selected media from cart"));
        btnPlaceOrder.setTooltip(new Tooltip("Place order for all items in cart"));

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Media> obs, Media oldMedia, Media newMedia) -> updateButtonBar(newMedia)
        );

        updateTotalCost();

        cart.getItemsOrdered().addListener((ListChangeListener<Media>) change -> updateTotalCost());

        filteredList = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredList);

        tfFilter.textProperty().addListener((obs, oldVal, newVal) -> showFilteredMedia(newVal));
        filterCategory.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> showFilteredMedia(tfFilter.getText()));
    }
}