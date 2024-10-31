package by.malatok.post.ui;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class TagBox extends HBox {

    private final TextArea tagTextArea;
    private final Label customTagsLabel;

    public TagBox() {
        super();
        customTagsLabel = new Label("Dadatkovyja tehi:");
        tagTextArea = new TextArea();
        this.getChildren().add(customTagsLabel);
        this.getChildren().add(tagTextArea);

    }

    public TextArea getTagTextArea() {
        return tagTextArea;
    }

    public void setupSizes(double columnWidth, double columnHeight, double spacing) {
        this.setSpacing(spacing);

        tagTextArea.setMinWidth(columnWidth);
        tagTextArea.setMaxHeight(columnHeight);
        tagTextArea.setMinHeight(columnHeight);

        customTagsLabel.setMinWidth(columnWidth);
        customTagsLabel.setMaxHeight(columnHeight);
        customTagsLabel.setMinHeight(columnHeight);
    }
}
