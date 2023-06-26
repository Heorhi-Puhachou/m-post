package by.mltk.m_post;

import by.spelling.conversion.converter.lacink.TaraskLacinkConverter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static javafx.geometry.Pos.CENTER;

public class HelloApplication extends Application {

    private static final String[] tags = {"strym", "rimworld"};

    private static final TaraskLacinkConverter converter = new TaraskLacinkConverter();

    public static void main(String[] args) {
        launch();
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
        grid.add(mastadonLabel, 0, ++row);
        TextArea mastadonPost = new TextArea();
        grid.add(mastadonPost, 0, ++row);

        row--;
        row--;

        Label telegramLabel = new Label("Telegram:");
        grid.add(telegramLabel, 1, ++row);
        TextArea telegramPost = new TextArea();
        grid.add(telegramPost, 1, ++row);

        Label twitterLabel = new Label("Twitter:");
        grid.add(twitterLabel, 0, ++row);
        TextArea twitterPost = new TextArea();
        grid.add(twitterPost, 0, ++row);

        row--;
        row--;

        Label facebookLabel = new Label("Facebook:");
        grid.add(facebookLabel, 1, ++row);
        TextArea facebookPost = new TextArea();
        grid.add(facebookPost, 1, ++row);

        originalTextArea.setOnKeyTyped(event -> {
            mastadonPost.setText(getMastadonText(originalTextArea.getText(), tags, 19));
            telegramPost.setText(originalTextArea.getText() + "\n\n" + converter.convert(originalTextArea.getText()));

        });

        Scene scene = new Scene(grid, 1000, 775);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String buildTagString(String[] tags) {
        String tagRow = "";
        for (String tag : tags) {
            tagRow = tagRow + " #" + tag;
        }
        return tagRow.trim();
    }

    private String buildMastadonTime(String[] tags) {
        String tagRow = "";
        for (String tag : tags) {
            tagRow = tagRow + " #" + tag;
        }
        return tagRow.trim();
    }

    private String getMastadonText(String originalText, String[] tags, Integer belTime) {
        StringBuilder result = new StringBuilder();
        result.append(buildTagString(tags));
        result.append("\n\n");
        result.append(originalText);
        result.append("\n\n");
        result.append(converter.convert(originalText));
        result.append("\n\n");
        result.append("  -" + belTime + "-  ");

        return result.toString();
    }

    private String getTelegramText(String originalText, String[] tags, Integer belTime) {
        StringBuilder result = new StringBuilder();
        result.append(buildTagString(tags));
        result.append("\n\n");
        result.append(originalText);
        result.append("\n\n");
        result.append(converter.convert(originalText));
        result.append("\n\n");
        result.append("  -" + belTime + "-  ");

        return result.toString();
    }
}