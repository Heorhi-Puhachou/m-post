package by.malatok.post.ui;

import by.malatok.post.setup.Tags;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class RadioBox extends VBox {

    ToggleGroup toggleGroup = new ToggleGroup();

    public RadioBox() {
        super();

        for (Tags tags : Tags.values()) {
            RadioButton radioButton = new RadioButton(tags.name());
            radioButton.setToggleGroup(toggleGroup);
            radioButton.getProperties().put("name", tags.name());
            this.getChildren().add(radioButton);
        }
    }

    public ToggleGroup getToggleGroup() {
        return this.toggleGroup;
    }
}
