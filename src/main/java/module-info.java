module townmap.assignment6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires java.desktop;


    opens townmap.assignment6 to javafx.fxml;
    exports townmap.assignment6;
}