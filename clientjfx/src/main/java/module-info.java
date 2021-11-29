module clientjfx {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.rmi;
    requires interfaces;
    opens clientjfx.vues to javafx.fxml;

    exports clientjfx.application;
}