module com.example.desktopapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires com.google.gson;





    opens com.example.desktopapp to javafx.fxml;
    exports com.example.desktopapp;
}