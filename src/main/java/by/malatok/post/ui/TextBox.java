package by.malatok.post.ui;

import by.malatok.post.util.SingleClipboard;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class TextBox extends HBox {

    private final TextArea textArea;
    private final Button copyToBufer;

    public TextBox() {
        super();
        textArea = new TextArea();
        copyToBufer = new Button("U Bufier");
        copyToBufer.setOnAction(event -> SingleClipboard.getInstance().putStringToClipboard(textArea.getText()));
        this.getChildren().add(textArea);
        this.getChildren().add(copyToBufer);
    }

    public void setText(String text) {
        textArea.setText(text.trim());
    }

    public void setupSizes(double columnWidth, double columnHeight, double spacing) {
        this.setSpacing(spacing);

        textArea.setMinWidth(columnWidth);
        textArea.setMaxHeight(columnHeight);
        textArea.setMinHeight(columnHeight);

        copyToBufer.setMinWidth(columnWidth);
        copyToBufer.setMaxHeight(columnHeight);
        copyToBufer.setMinHeight(columnHeight);
    }
}
