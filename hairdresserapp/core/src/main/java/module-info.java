module core {
    exports core;
    exports json;
    exports json.internal;
    requires com.fasterxml.jackson.databind;

     opens core to com.fasterxml.jackson.databind;
}
