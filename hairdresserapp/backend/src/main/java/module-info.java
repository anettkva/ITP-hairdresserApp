module backend {
    requires com.fasterxml.jackson.databind;
    
    requires.spring.web;
    requires.spring.beans;
    requires.spring.boot;
    requires.spring.context;
    requires.spring.boot.autoconfigure;

    requires core;

    opens backend to spring.beans, spring.context, spring.web;
}
