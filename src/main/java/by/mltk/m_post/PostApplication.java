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

import static javafx.geometry.Pos.CENTER;

public class PostApplication extends Application {

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


        originalTextArea.setOnKeyTyped(event -> {
            mastadonPost.setText(getText(originalTextArea.getText(), tags, BEL_TIME, Mode.MASTADON));
            telegramPost.setText(getText(originalTextArea.getText(), tags, BEL_TIME, Mode.TELEGRAM));

            String twitterText = getText(originalTextArea.getText(), tags, BEL_TIME, Mode.TWITTER).trim();
            twitterLabel.setText("Twitter(" + twitterText.trim().length() + "):");
            twitterPost.setText(twitterText);

            facebookPost.setText(getText(originalTextArea.getText(), tags, BEL_TIME, Mode.FACEBOOK));
        });

        Scene scene = new Scene(grid, 1000, 775);
        primaryStage.setScene(scene);
        primaryStage.show();

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
            case TELEGRAM:
                belSymbol = "❤\uFE0F";
                break;
            default:
                belSymbol = "❤";
                break;
        }

        int polTime = belTime - 1;
        int georTime = belTime + 1;

        return belSymbol + " " + belTime + ":00 pavodle biełaruskaha času\n" +
                "\uD83C\uDDF5\uD83C\uDDF1 " + polTime + ":00 pavodle polskaha\n" +
                "\uD83C\uDDEC\uD83C\uDDEA " + georTime + ":00 pavodle hruzinskaha";
    }

    private String getText(String originalText, String[] tags, Integer belTime, Mode mode) {
        StringBuilder result = new StringBuilder();
        if (mode != Mode.TWITTER) {
            result.append(buildTagString(tags));
            result.append("\n\n");
            result.append(originalText);
            result.append("\n\n");
        }
        result.append(converter.convert(originalText));
        result.append("\n\n");
        result.append(buildTime(belTime, mode));

        return result.toString();
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
