module by.malatok.post {
    requires javafx.controls;
    requires javafx.fxml;
    requires converter;


    opens by.malatok.post to javafx.fxml;
    exports by.malatok.post;
    exports by.malatok.post.ui;
    opens by.malatok.post.ui to javafx.fxml;
    exports by.malatok.post.util;
    opens by.malatok.post.util to javafx.fxml;
}