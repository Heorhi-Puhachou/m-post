package by.malatok.post;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class PostBox extends VBox {

    private final TextArea post;
    private final Label label;

    public PostBox(String labelText) {
        super();
        label = new Label(labelText);
        post = new TextArea();
        Button copyButton = new Button("kapiravac");
        copyButton.setOnAction(event -> SingleClipboard.getInstance().putStringToClipboard(post.getText()));

        this.getChildren().add(label);
        this.getChildren().add(post);
        this.getChildren().add(copyButton);
    }

    public void setPostText(String postText) {
        post.setText(postText);
    }

    public String getPostText() {
        return post.getText();
    }

    public void setLabelText(String labelText) {
        label.setText(labelText);
    }
}
