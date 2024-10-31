package by.malatok.post.ui;

import by.malatok.post.setup.Media;
import javafx.scene.layout.VBox;

public class CheckColumn extends VBox {


    public CheckColumn() {
        super();

        for (Media media : Media.values()) {
            media.getCheckBox().getProperties().put("name", media.name());
            this.getChildren().add(media.getCheckBox());
        }
    }
}
