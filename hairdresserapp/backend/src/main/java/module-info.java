module backend {
    requires transitive core;
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.data.jpa;
    requires transitive spring.core;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires spring.jdbc;

    exports backend;

    opens backend to spring.core, spring.context, spring.boot, spring.beans;
}
