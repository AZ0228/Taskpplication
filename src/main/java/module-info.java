module Taskpplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.web;
    requires javafx.media;
    requires javafx.swing;
    requires java.sql;

    // If you have any packages that need to be exported, add them here
    exports Taskpplication;

    // If you are using FXML controllers, you need to open the package containing the controllers to javafx.fxml:
     opens Taskpplication to javafx.fxml;
}