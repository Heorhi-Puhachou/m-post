package by.mltk.m_post;

import by.spelling.conversion.converter.lacink.TaraskLacinkConverter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.function.Function;

import static javafx.geometry.Pos.CENTER;

public class PostApplication extends Application {

    private static final double SCENE_WIDTH = 800;
    private static final double SCENE_HEIGHT = 800;
    private static final String[] tags = {"strym", "lahiendyajzenvalda"};
    private static final Integer BEL_TIME = 20;
    private static final TaraskLacinkConverter converter = new TaraskLacinkConverter();

    private final Clipboard clipboard = Clipboard.getSystemClipboard();
    private final ClipboardContent content = new ClipboardContent();

    public static void main(String[] args) {
        launch();
    }

    private void stringToClipboard(String string) {
        content.putString(string);
        clipboard.setContent(content);
    }

    @Override
    public void start(Stage primaryStage) {
        int row = 0;
        int column = 0;

        primaryStage.setTitle("Malatok2334");

        GridPane grid = new GridPane();
        grid.setAlignment(CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Original text
        TextArea originalTextArea = new TextArea();
        grid.add(originalTextArea, column, row, 2, 1);

        row++;

        //link
        TextArea linkTextArea = new TextArea();
        linkTextArea.setMaxHeight(30);
        grid.add(linkTextArea, column, row);
        Button insertLinkButton = new Button("Ustavic spasylku");
        insertLinkButton.setMinHeight(30);
        grid.add(insertLinkButton, 1, row);
        insertLinkButton.setOnAction(event -> linkTextArea.setText(clipboard.getString()));

        row++;
        row++;
        row++;
        row++;

        Label mastadonLabel = new Label("Mastadon:");
        Button mastadonButton = new Button("kapiravac");
        TextArea mastadonPost = addPostElements(row, 0, grid, mastadonLabel, mastadonButton);

        Label telegramLabel = new Label("Telegram:");
        Button telegramButton = new Button("kapiravac");
        TextArea telegramPost = addPostElements(row, 1, grid, telegramLabel, telegramButton);

        row++;
        row++;
        row++;

        Label twitterLabel = new Label("Twitter:");
        Button twitterButton = new Button("kapiravac");
        TextArea twitterPost = addPostElements(row, 0, grid, twitterLabel, twitterButton);

        Label facebookLabel = new Label("Facebook:");
        Button facebookButton = new Button("kapiravac");
        TextArea facebookPost = addPostElements(row, 1, grid, facebookLabel, facebookButton);

        final Function<Mode, String> prepareText = mode -> getText(
                originalTextArea.getText(),
                linkTextArea.getText().trim(),
                tags,
                BEL_TIME,
                mode);

        originalTextArea.setOnKeyTyped(event -> {
            mastadonPost.setText(prepareText.apply(Mode.MASTADON));
            telegramPost.setText(prepareText.apply(Mode.TELEGRAM));

            String twitterText = prepareText.apply(Mode.TWITTER);
            twitterLabel.setText("Twitter(" + twitterText.length() + "):");
            twitterPost.setText(twitterText);

            facebookPost.setText(prepareText.apply(Mode.FACEBOOK));
        });

        Scene scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        insertLinkButton.setMinWidth(facebookPost.getWidth());
        setStandardButtonSize(facebookButton, facebookPost.getWidth());
        setStandardButtonSize(twitterButton, facebookPost.getWidth());
        setStandardButtonSize(telegramButton, facebookPost.getWidth());
        setStandardButtonSize(mastadonButton, facebookPost.getWidth());
    }

    private void setStandardButtonSize(Button button, double width) {
        button.setMinWidth(width);
        button.setMinHeight(70);
    }

    private String buildTagString(String[] tags) {
        String tagRow = "";
        for (String tag : tags) {
            tagRow = tagRow + " #" + tag;
        }
        return tagRow.trim();
    }

    private String buildTime(Integer belTime, Mode mode) {
        String belSymbol;
        switch (mode) {
            case MASTADON:
                belSymbol = ":by:";
                break;
            case TWITTER:
                belSymbol = "❤";
                break;
            default:
                belSymbol = "❤\uFE0F";
                break;
        }

        int polTime = belTime - 1;
        int georTime = belTime + 1;

        return belSymbol + " " + belTime + ":00 pavodle biełaruskaha času\n" +
                "\uD83C\uDDF5\uD83C\uDDF1 " + polTime + ":00 pavodle polskaha\n" +
                "\uD83C\uDDEC\uD83C\uDDEA " + georTime + ":00 pavodle hruzinskaha";
    }

    private String getText(String originalText, String link, String[] tags, Integer belTime, Mode mode) {
        StringBuilder result = new StringBuilder();
        result.append(buildTagString(tags));
        result.append("\n\n");
        result.append(converter.convert(originalText));
        result.append("\n\n");
        result.append(originalText);
        result.append("\n\n");
        result.append(link);
        result.append("\n\n");
        result.append(buildTime(belTime, mode));

        return result.toString().trim();
    }

    private TextArea addPostElements(Integer row,
                                     Integer column,
                                     GridPane grid,
                                     Label label,
                                     Button button) {
        grid.add(label, column, ++row);
        TextArea post = new TextArea();
        grid.add(post, column, ++row);
        grid.add(button, column, ++row);

        button.setOnAction(event -> stringToClipboard(post.getText()));
        return post;
    }
}
