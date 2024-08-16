module Taskpplication {
    requires java.sql;
    requires transitive javafx.base;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;

    opens Taskpplication to javafx.fxml;

    exports Taskpplication;
}