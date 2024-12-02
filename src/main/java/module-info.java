module org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak to javafx.fxml;
    exports org.unibl.etf.tomic_andrej_1106_22_projektni_zadatak;
}