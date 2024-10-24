module backend {
    requires core;
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.data.jpa;
    requires spring.core;

    opens backend to spring.beans, spring.context, spring.web;
}
