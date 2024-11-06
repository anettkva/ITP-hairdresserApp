module core {
    exports core;
    exports json;
    exports json.internal;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;


    opens core to com.fasterxml.jackson.databind;
}
