module com.example.whiteerp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.whiteerp to javafx.fxml;
    exports com.example.whiteerp;
}