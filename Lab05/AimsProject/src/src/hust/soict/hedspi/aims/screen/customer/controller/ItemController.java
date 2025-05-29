package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.exception.PlayerException;  // Make sure you have this
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;

public class ItemController {

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    @FXML
    private Label lbCost;

    @FXML
    private Label lbTitle;

    private Media media;
    private Cart cart;

    public ItemController(){}

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public ItemController(Cart cart) {
        this.cart = cart;
    }

    public void setData(Media media) {
        this.media = media;
        lbTitle.setText(media.getTitle());
        lbCost.setText(String.format("%.2f $", media.getCost()));
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60));
        }
    }

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        try {
            cart.addMedia(media);
            showAlert(Alert.AlertType.INFORMATION, "Add to Cart", "Added: " + media.getTitle());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Cart Error", e.getMessage());
        }
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        if (media instanceof Playable) {
            try {
                String details = ((Playable) media).play(true);
                showAlert(Alert.AlertType.INFORMATION, "Media Playing", details);
            } catch (PlayerException e) {
                showAlert(Alert.AlertType.ERROR, "Error Playing Media", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
