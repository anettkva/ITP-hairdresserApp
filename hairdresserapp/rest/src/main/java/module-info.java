module rest {
    
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires core;

    opens rest to spring.beans, spring.context, spring.web;

}