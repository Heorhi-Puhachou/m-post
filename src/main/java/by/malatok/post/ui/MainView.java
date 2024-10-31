package by.malatok.post.ui;

import by.malatok.post.setup.Media;
import by.malatok.post.setup.Tags;
import by.malatok.post.util.Mode;
import by.malatok.post.util.Procedure;
import by.malatok.post.util.SingletonConstantStorage;
import by.malatok.post.util.TextHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.function.Function;

import static javafx.geometry.Pos.CENTER;

public class MainView {

    private static final Integer SPACING = 10;

    private final LinkBox linkBox;
    private final TagBox tagBox;
    private final TextArea originalTextArea;
    private final PostBox mastadonBox;
    private final PostBox telegramBox;
    private final PostBox youtubeBox;
    private final PostBox discordBox;
    private final RadioColumn tags;
    private final CheckColumn media;
    private final GridPane gridPane;

    public MainView() {
        linkBox = new LinkBox();
        tagBox = new TagBox();
        originalTextArea = new TextArea();

        tags = new RadioColumn();
        media = new CheckColumn();

        mastadonBox = new PostBox("Mastadon:");
        telegramBox = new PostBox("Telegram:");
        youtubeBox = new PostBox("YouTube:");
        discordBox = new PostBox("Discord:");
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
        grid.add(tagBox, column + 2, row, 2, 1);
        row++;

        grid.add(originalTextArea, column, row, 2, 1);
        grid.add(tags, column + 2, row);
        grid.add(media, column + 3, row);
        row++;

        grid.add(youtubeBox, 0, row);
        grid.add(mastadonBox, 1, row);

        grid.add(telegramBox, 2, row);
        grid.add(discordBox, 3, row);

        return grid;
    }

    private void setupTextHandler() {
        TextHandler textHandler = new TextHandler();

        final Function<Mode, String> prepareText = mode -> textHandler.getText(
                originalTextArea.getText(),
                linkBox.getLink(),
                mode,
                Media.findByAbbr("YOUTUBE").getCheckBox().isSelected(),
                Media.findByAbbr("TWITCH").getCheckBox().isSelected(),
                Media.findByAbbr("TELEGRAM").getCheckBox().isSelected(),
                Media.findByAbbr("FACEBOOK").getCheckBox().isSelected(),
                Media.findByAbbr("TIKTOK").getCheckBox().isSelected());

        final Procedure refreshText = () -> {
            mastadonBox.setPostText(prepareText.apply(Mode.MASTADON));
            telegramBox.setPostText(prepareText.apply(Mode.TELEGRAM));
            discordBox.setPostText(prepareText.apply(Mode.DISCORD));
            youtubeBox.setPostText(prepareText.apply(Mode.YOUTUBE));
        };

        EventHandler<ActionEvent> eh = event -> {
            if (event.getSource() instanceof CheckBox) {
                refreshText.invoke();
            }
        };

        for (Media media : Media.values()) {
            media.getCheckBox().setOnAction(eh);
        }

        tags.getToggleGroup().selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            SingletonConstantStorage.getInstance().setPostTags(Tags.findByAbbr(newVal.getProperties().get("name").toString()));
            refreshText.invoke();
        });

        tagBox.getTagTextArea().setOnKeyTyped(event -> {
            List<String> newCustomTags = List.of(tagBox.getTagTextArea().getText().split(" "));
            SingletonConstantStorage.getInstance().rewriteCustomTags(newCustomTags);
            refreshText.invoke();
        });

        originalTextArea.setOnKeyTyped(event -> {
            refreshText.invoke();
        });


    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setupSizes() {
        mastadonBox.setupSizes();
        telegramBox.setupSizes();
        youtubeBox.setupSizes();
        discordBox.setupSizes();

        double elementWidth = mastadonBox.getElementWidth();
        linkBox.setupSizes(elementWidth, 30, SPACING);
        tagBox.setupSizes(elementWidth, 30, SPACING);
    }
}
