package com.example.lab9exe;

import javafx.animation.*;
import javafx.beans.binding.When;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Lab9Controller implements Initializable {
    @FXML
    private Text text;
    @FXML
    private Text invalidationStatusText;
    @FXML
    private Text changeStatusText;
    @FXML
    private TextField input1;
    @FXML
    private TextField input2;
    private Timeline timeline;
    private ScaleTransition scaleTransition;

    private Timeline makeTimeline() {
        Timeline t = new Timeline();
        Duration stepDuration = Duration.seconds(2.0);
        Duration totalDuration = Duration.ZERO;

        for(Color color : List.of(Color.RED, Color.ORANGE, Color.YELLOW, Color.LIGHTGREEN, Color.CYAN, Color.VIOLET)) {
            KeyValue val = new KeyValue(this.text.fillProperty(), color);
            KeyFrame frame = new KeyFrame(totalDuration, val);
            t.getKeyFrames().add(frame);
            totalDuration = totalDuration.add(stepDuration);
        }

        t.setAutoReverse(true);
        t.setCycleCount(-1);
        return t;
    }

    private ScaleTransition makeScaleTransition() {
        ScaleTransition t = new ScaleTransition(Duration.seconds(0.1), this.text);
        t.setAutoReverse(true);
        t.setCycleCount(2);
        t.setToX(1.2);
        t.setToY(1.2);
        return t;
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.timeline = this.makeTimeline();
        this.scaleTransition = this.makeScaleTransition();
        this.timeline.statusProperty().addListener((_) -> this.invalidationStatusText.setText("Animation status: " + this.timeline.getStatus()));
        this.timeline.statusProperty().addListener((_, oldStatus, newStatus) -> {
            Text var10000 = this.changeStatusText;
            String var10001 = String.valueOf(oldStatus);
            var10000.setText("Was " + var10001 + ", now " + newStatus);
        });
        this.invalidationStatusText.fillProperty().bind(this.text.fillProperty());
        this.changeStatusText.fillProperty().bind((new When(this.timeline.statusProperty().isEqualTo(Animation.Status.RUNNING))).then(Color.GREEN).otherwise(Color.RED));
        this.input1.textProperty().bindBidirectional(this.input2.textProperty());
    }

    @FXML
    private void handleClick() {
        if (this.timeline.getStatus() == Animation.Status.RUNNING) {
            this.timeline.stop();
        } else {
            this.timeline.play();
        }

    }

    @FXML
    private void handleMouseEntered() {
        this.scaleTransition.playFromStart();
    }
}
