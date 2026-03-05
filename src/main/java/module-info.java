module com.adz1q.accordion {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.adz1q.accordion to javafx.fxml;
    exports com.adz1q.accordion;
}