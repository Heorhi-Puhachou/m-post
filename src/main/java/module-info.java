module by.malatok.post {
    requires javafx.controls;
    requires javafx.fxml;
    requires converter;


    opens by.malatok.post to javafx.fxml;
    exports by.malatok.post;
}