package by.malatok.post.ui;

import by.malatok.post.util.SingleClipboard;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class LinkBox extends HBox {

    private final TextArea linkTextArea;
    private final Button insertLinkButton;

    public LinkBox() {
        super();
        linkTextArea = new TextArea();
        insertLinkButton = new Button("Dadać YouTube spasyłku");
        insertLinkButton.setOnAction(event -> linkTextArea.setText(SingleClipboard.getInstance().getString()));
        this.getChildren().add(linkTextArea);
        this.getChildren().add(insertLinkButton);
    }

    public String getLink() {
        return linkTextArea.getText().trim();
    }

    public void setupSizes(double columnWidth, double columnHeight, double spacing) {
        this.setSpacing(spacing);

        linkTextArea.setMinWidth(columnWidth);
        linkTextArea.setMaxHeight(columnHeight);
        linkTextArea.setMinHeight(columnHeight);

        insertLinkButton.setMinWidth(columnWidth);
        insertLinkButton.setMaxHeight(columnHeight);
        insertLinkButton.setMinHeight(columnHeight);
    }
}
