package hust.soict.hedspi.javafx;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;
    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }
    void drawingAreaDragged(MouseEvent event) {
        Circle newCircle = new Circle(event.getX(), event.getY(), 4, Color.BLACK);
        drawingAreaPane.getChildren().add(newCircle);
    }
}
