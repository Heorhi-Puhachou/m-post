package by.malatok.post.ui;

import by.malatok.post.setup.Tags;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class RadioColumn extends VBox {

    ToggleGroup toggleGroup = new ToggleGroup();

    public RadioColumn() {
        super();

        for (Tags tags : Tags.values()) {
            tags.getRadioButton().setToggleGroup(toggleGroup);
            tags.getRadioButton().getProperties().put("name", tags.name());
            this.getChildren().add(tags.getRadioButton());
        }
    }

    public ToggleGroup getToggleGroup() {
        return this.toggleGroup;
    }
}
