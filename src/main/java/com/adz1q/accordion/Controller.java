package com.adz1q.accordion;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label lblNumber;

    @FXML
    private Label lblNumbers;

    @FXML
    private Spinner<Integer> spiHighRange;

    @FXML
    private Spinner<Integer> spiLowRange;

    @FXML
    private Button btnDraw;

    @FXML
    private Spinner<Integer> spiCount;

    @FXML
    private TextArea txtNumbers;

    @FXML
    private TextArea txtNumber;

    @FXML
    private Button btnDrawMany;

    private int low;
    private int high;
    private int count;

    @Override
    public void initialize(
            URL url,
            ResourceBundle resourceBundle) {
        var lowFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 1);
        var highFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(50, 100, 50);
        var countFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);

        spiLowRange.setValueFactory(lowFactory);
        spiHighRange.setValueFactory(highFactory);
        spiCount.setValueFactory(countFactory);

        txtNumber.setEditable(false);
        txtNumbers.setEditable(false);
        txtNumber.setWrapText(true);
        txtNumbers.setWrapText(true);

        btnDraw.setOnAction(actionEvent -> {
            if (spiLowRange.getValue() == null
                    || spiHighRange.getValue() == null) return;

            txtNumbers.clear();
            low = spiLowRange.getValue();
            high = spiHighRange.getValue();

            int draw = draw(low, high);
            txtNumber.setText(String.valueOf(draw));
        });

        btnDrawMany.setOnAction(actionEvent -> {
            if (spiLowRange.getValue() == null
                    || spiHighRange.getValue() == null
                    || spiCount.getValue() == null) return;

            txtNumber.clear();
            low = spiLowRange.getValue();
            high = spiHighRange.getValue();
            count = spiCount.getValue();

            for (int i = 0; i < count; i++) {
                txtNumbers.appendText(draw(low, high) + ", ");
            }
        });

        spiLowRange.valueProperty().addListener((observableValue, integer, newValue) -> {

        });

        spiHighRange.valueProperty().addListener((observableValue, integer, newValue) -> lblNumber.setText("Zakres losowania od " + spiHighRange.getValue() + " do " + newValue));

        spiCount.valueProperty().addListener((observableValue, integer, newValue) -> lblNumbers.setText(newValue + " losowych liczb"));
    }

    private int draw(int low, int high) {
        return (int) ((Math.random() * (high - low + 1)) + low);
    }
}
