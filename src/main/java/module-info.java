module com.customtodolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.swing;
    requires java.base;


    opens com.customtodolist to javafx.fxml;
    opens com.customtodolist.GUI to javafx.swing;
    exports com.customtodolist;
}