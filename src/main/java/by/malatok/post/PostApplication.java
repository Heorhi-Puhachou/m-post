package by.malatok.post;

import by.spelling.conversion.converter.lacink.TaraskLacinkConverter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        PostBox mastadonBox = new PostBox("Mastadon:");
        PostBox telegramBox = new PostBox("Telegram:");
        PostBox twitterBox = new PostBox("Twitter:");
        PostBox facebookBox = new PostBox("Facebook:");

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
        insertLinkButton.setOnAction(event -> linkTextArea.setText(SingleClipboard.getInstance().getString()));

        row++;

        grid.add(mastadonBox, 0, row);
        grid.add(telegramBox, 1, row);

        row++;

        grid.add(twitterBox, 0, row);
        grid.add(facebookBox, 1, row);

        final Function<Mode, String> prepareText = mode -> getText(
                originalTextArea.getText(),
                linkTextArea.getText().trim(),
                tags,
                BEL_TIME,
                mode);

        originalTextArea.setOnKeyTyped(event -> {
            mastadonBox.setPostText(prepareText.apply(Mode.MASTADON));
            telegramBox.setPostText(prepareText.apply(Mode.TELEGRAM));

            String twitterText = prepareText.apply(Mode.TWITTER);
            twitterBox.setLabelText("Twitter(" + twitterText.length() + "):");
            twitterBox.setPostText(twitterText);

            facebookBox.setPostText(prepareText.apply(Mode.FACEBOOK));
        });

        Scene scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        insertLinkButton.setMinWidth(linkTextArea.getWidth());
        //setStandardButtonSize(facebookButton, mastadonPost.getWidth());
        //setStandardButtonSize(twitterButton, mastadonPost.getWidth());
        //setStandardButtonSize(telegramButton, mastadonPost.getWidth());
        //setStandardButtonSize(mastadonButton, mastadonPost.getWidth());
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
}
