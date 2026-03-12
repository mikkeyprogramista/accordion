package com.adz1q.accordion;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Spinner<Integer> spiHighRange;

    @FXML
    private Spinner<Integer> spiLowRange;

    @FXML
    private Button btnDraw;

    @FXML
    private Spinner<Integer> spiCount;

    @FXML
    private Label lblNumbers;

    @FXML
    private TextArea txtNumbers;

    @FXML
    private TextArea txtNumber;

    @FXML
    private Label lblNumber;

    @FXML
    private Button btnDrawMany;

    @FXML
    private TextField txtH;

    @FXML
    private ScrollBar scrlH;

    @FXML
    private Pane pane;

    @FXML
    private Rectangle square;

    @FXML
    private TextField txtV;

    @FXML
    private ScrollBar scrlV;

    @FXML
    private CheckBox chxCock;

    private int low;
    private int high;
    private int count;

    @Override
    public void initialize(
            URL url,
            ResourceBundle resourceBundle) {
        // Spinner
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

        // Grid | Scroll
        scrlH.valueProperty().addListener((observableValue, number, newValue) -> {
            square.setLayoutX(newValue.doubleValue() * (pane.getWidth() - square.getWidth()) / 100);
            txtH.setText(String.valueOf(newValue.intValue()));

            if (chxCock.isSelected()) {
                square.setLayoutY(newValue.doubleValue() * (pane.getWidth() - square.getWidth()) / 100);
                txtH.setText(txtV.getText());
            }
        });

        scrlV.valueProperty().addListener((observableValue, number, newValue) -> {
            square.setLayoutY(newValue.doubleValue() * (pane.getHeight() - square.getHeight()) / 100);
            txtV.setText(String.valueOf(newValue.intValue()));

            if (chxCock.isSelected()) {
                square.setLayoutX(newValue.doubleValue() * (pane.getHeight() - square.getHeight()) / 100);
                txtH.setText(txtH.getText());
            }
        });

        txtH.setOnAction(actionEvent -> {
            square.setLayoutX(Integer.parseInt(txtH.getText()));
        });

        txtV.setOnAction(actionEvent -> {
            square.setLayoutY(Integer.parseInt(txtV.getText()));
        });
    }

    private int draw(int low, int high) {
        return (int) ((Math.random() * (high - low + 1)) + low);
    }
}
