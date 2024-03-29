package by.malatok.post.ui;

import by.malatok.post.setup.Tags;
import by.malatok.post.util.Mode;
import by.malatok.post.util.SingletonConstantStorage;
import by.malatok.post.util.TextHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.util.function.Function;

import static javafx.geometry.Pos.CENTER;

public class MainView {

    private static final Integer SPACING = 10;

    private final LinkBox linkBox;
    private final TextArea originalTextArea;
    private final PostBox mastadonBox;
    private final PostBox telegramBox;
    private final PostBox youtubeBox;
    private final PostBox facebookBox;
    private final RadioBox tags;
    private final GridPane gridPane;

    public MainView() {
        linkBox = new LinkBox();
        originalTextArea = new TextArea();

        tags = new RadioBox();

        mastadonBox = new PostBox("Mastadon:");
        telegramBox = new PostBox("Telegram:");
        youtubeBox = new PostBox("YouTube:");
        facebookBox = new PostBox("Facebook:");
        gridPane = addElementsToView();
        setupTextHandler();
    }

    private GridPane addElementsToView() {
        int row = 0;
        int column = 0;

        GridPane grid = new GridPane();
        grid.setAlignment(CENTER);
        grid.setHgap(SPACING);
        grid.setVgap(SPACING);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(linkBox, column, row, 2, 1);
        row++;

        grid.add(originalTextArea, column, row, 2, 1);
        grid.add(tags, column + 2, row);
        row++;

        grid.add(youtubeBox, 0, row);
        grid.add(mastadonBox, 1, row);

        grid.add(telegramBox, 2, row);
        grid.add(facebookBox, 3, row);

        return grid;
    }

    private void setupTextHandler() {
        TextHandler textHandler = new TextHandler();


        final Function<Mode, String> prepareText = mode -> textHandler.getText(
                originalTextArea.getText(),
                linkBox.getLink(),
                mode);

        tags.getToggleGroup().selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            SingletonConstantStorage.getInstance().setPostTags(Tags.findByAbbr(newVal.getProperties().get("name").toString()));
            mastadonBox.setPostText(prepareText.apply(Mode.MASTADON));
            telegramBox.setPostText(prepareText.apply(Mode.TELEGRAM));
            facebookBox.setPostText(prepareText.apply(Mode.FACEBOOK));
            youtubeBox.setPostText(prepareText.apply(Mode.YOUTUBE));
        });


        originalTextArea.setOnKeyTyped(event -> {
            mastadonBox.setPostText(prepareText.apply(Mode.MASTADON));
            telegramBox.setPostText(prepareText.apply(Mode.TELEGRAM));
            facebookBox.setPostText(prepareText.apply(Mode.FACEBOOK));
            youtubeBox.setPostText(prepareText.apply(Mode.YOUTUBE));
        });


    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setupSizes() {
        mastadonBox.setupSizes();
        telegramBox.setupSizes();
        youtubeBox.setupSizes();
        facebookBox.setupSizes();

        double elementWidth = mastadonBox.getElementWidth();
        linkBox.setupSizes(elementWidth, 30, SPACING);
    }
}
