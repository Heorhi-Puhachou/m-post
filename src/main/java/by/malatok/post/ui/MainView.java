package by.malatok.post.ui;

import by.malatok.post.util.Mode;
import by.malatok.post.util.TextHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.util.function.Function;

import static javafx.geometry.Pos.CENTER;

public class MainView {

    private static final String[] tags = {"seryjał", "žachi", "silenthill"};
    private static final Integer BEL_TIME_HOURS = 21;
    private static final Integer BEL_TIME_MINUTES = 30;
    private static final Integer SPACING = 10;

    private final LinkBox linkBox;
    private final TextBox textBox;

    private final TextArea originalTextArea;
    private final PostBox mastadonBox;
    private final PostBox telegramBox;
    private final PostBox twitterBox;
    private final PostBox facebookBox;
    private final GridPane gridPane;

    public MainView() {
        linkBox = new LinkBox();
        textBox = new TextBox();
        originalTextArea = new TextArea();
        mastadonBox = new PostBox("Mastadon:");
        telegramBox = new PostBox("Telegram:");
        twitterBox = new PostBox("Twitter:");
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
        row++;
        grid.add(textBox, column, row, 2, 1);
        row++;
        grid.add(mastadonBox, 0, row);
        grid.add(telegramBox, 1, row);
        row++;
        grid.add(twitterBox, 0, row);
        grid.add(facebookBox, 1, row);

        return grid;
    }

    private void setupTextHandler() {
        TextHandler textHandler = new TextHandler();

        final Function<Mode, String> prepareText = mode -> textHandler.getText(
                originalTextArea.getText(),
                linkBox.getLink(),
                tags,
                BEL_TIME_HOURS,
                BEL_TIME_MINUTES,
                mode);

        originalTextArea.setOnKeyTyped(event -> {
            mastadonBox.setPostText(prepareText.apply(Mode.MASTADON));
            telegramBox.setPostText(prepareText.apply(Mode.TELEGRAM));
            facebookBox.setPostText(prepareText.apply(Mode.FACEBOOK));
            textBox.setText(prepareText.apply(Mode.TEXT));

            //Pakazac kolkasc symbalau dla Twitter
            String twitterText = prepareText.apply(Mode.TWITTER);
            twitterBox.setLabelText("Twitter(" + twitterText.length() + "):");
            twitterBox.setPostText(twitterText);
        });
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setupSizes() {
        mastadonBox.setupSizes();
        telegramBox.setupSizes();
        twitterBox.setupSizes();
        facebookBox.setupSizes();

        double elementWidth = mastadonBox.getElementWidth();
        linkBox.setupSizes(elementWidth, 30, SPACING);
    }
}
