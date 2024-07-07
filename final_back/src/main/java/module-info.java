module org.example.final_back {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.final_back to javafx.fxml;
    exports org.example.final_back;
}