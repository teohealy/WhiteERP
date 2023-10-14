module com.example.whiteerp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.whiteerp to javafx.fxml;
    opens com.example.entities to javafx.base;
    exports com.example.whiteerp;


}