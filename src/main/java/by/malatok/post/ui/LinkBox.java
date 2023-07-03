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
        linkTextArea.setMaxHeight(30);

        insertLinkButton = new Button("Ustavic spasylku");
        insertLinkButton.setMinHeight(30);
        insertLinkButton.setOnAction(event -> linkTextArea.setText(SingleClipboard.getInstance().getString()));

        this.getChildren().add(linkTextArea);
        this.getChildren().add(insertLinkButton);
    }

    public String getLink() {
        return linkTextArea.getText().trim();
    }

    public void setupSizes(double columnWidth) {
        this.setSpacing(10);
        linkTextArea.setMinWidth(columnWidth);
        insertLinkButton.setMinWidth(columnWidth);

        linkTextArea.setMaxHeight(30);
        linkTextArea.setMinHeight(30);
        insertLinkButton.setMaxHeight(30);
        insertLinkButton.setMinHeight(30);
    }
}
