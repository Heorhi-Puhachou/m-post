package by.malatok.post.ui;

import by.malatok.post.util.SingleClipboard;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class PostBox extends VBox {

    private final TextArea post;
    private final Label label;
    private final Button copyButton;

    public PostBox(String labelText) {
        super();
        label = new Label(labelText);
        post = new TextArea();
        copyButton = new Button("U bufier");
        copyButton.setOnAction(event -> SingleClipboard.getInstance().putStringToClipboard(post.getText()));

        this.getChildren().add(label);
        this.getChildren().add(post);
        this.getChildren().add(copyButton);
    }

    public void setPostText(String postText) {
        post.setText(postText);
    }

    public void setLabelText(String labelText) {
        label.setText(labelText);
    }

    public void setupSizes() {
        setupSizes(post.getWidth());
    }

    public void setupSizes(double width) {
        copyButton.setMinWidth(width);
        copyButton.setMinHeight(60);
        post.setMinWidth(width);
        post.setMaxWidth(width);
        post.setMinHeight(250);
    }

    public double getElementWidth() {
        return post.getWidth();
    }
}
