package by.mltk.m_post;

import by.spelling.conversion.converter.lacink.TaraskLacinkConverter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class HelloApplication extends Application {

    private static final String[] tags = {"strym", "lahiendyajzenvalda"};

    private static final Integer BEL_TIME = 20;

    private static final TaraskLacinkConverter converter = new TaraskLacinkConverter();

    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();

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
        grid.add(mastadonLabel, 0, ++row);
        TextArea mastadonPost = new TextArea();
        grid.add(mastadonPost, 0, ++row);
        Button mastadonButton = new Button("kapiravac");
        grid.add(mastadonButton, 0, ++row);

        mastadonButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                stringToClipboard(mastadonPost.getText());
            }
        });

        row--;
        row--;
        row--;

        Label telegramLabel = new Label("Telegram:");
        grid.add(telegramLabel, 1, ++row);
        TextArea telegramPost = new TextArea();
        grid.add(telegramPost, 1, ++row);
        Button telegramButton = new Button("kapiravac");
        grid.add(telegramButton, 1, ++row);

        telegramButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                stringToClipboard(telegramPost.getText());
            }
        });

        row++;
        row++;
        row++;

        Label twitterLabel = new Label("Twitter:");
        grid.add(twitterLabel, 0, ++row);
        TextArea twitterPost = new TextArea();
        grid.add(twitterPost, 0, ++row);
        Button twitterButton = new Button("kapiravac");
        grid.add(twitterButton, 0, ++row);

        twitterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                stringToClipboard(twitterPost.getText());
            }
        });

        row--;
        row--;
        row--;

        Label facebookLabel = new Label("Facebook:");
        grid.add(facebookLabel, 1, ++row);
        TextArea facebookPost = new TextArea();
        grid.add(facebookPost, 1, ++row);
        Button facebookButton = new Button("kapiravac");
        facebookButton.setMinWidth(facebookPost.getMaxWidth());
        grid.add(facebookButton, 1, ++row);
        facebookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                stringToClipboard(facebookPost.getText());
            }
        });

        originalTextArea.setOnKeyTyped(event -> {
            mastadonPost.setText(getMastadonText(originalTextArea.getText(), tags, BEL_TIME));
            telegramPost.setText(getTelegramText(originalTextArea.getText(), tags, BEL_TIME));

            String twitterText = getTwitterText(originalTextArea.getText(), tags, BEL_TIME).trim();
            twitterLabel.setText("Twitter(" + twitterText.trim().length() + "):");
            twitterPost.setText(twitterText);

            facebookPost.setText(getFacebookText(originalTextArea.getText(), tags, BEL_TIME));
        });

        Scene scene = new Scene(grid, 1000, 775);
        primaryStage.setScene(scene);
        primaryStage.show();

        setStandardButtonSize(facebookButton, facebookPost.getWidth());
        setStandardButtonSize(twitterButton, facebookPost.getWidth());
        setStandardButtonSize(telegramButton, facebookPost.getWidth());
        setStandardButtonSize(mastadonButton, facebookPost.getWidth());
    }

    private void setStandardButtonSize(Button button, double width){
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

    private String getMastadonText(String originalText, String[] tags, Integer belTime) {
        StringBuilder result = new StringBuilder();
        result.append(buildTagString(tags));
        result.append("\n\n");
        result.append(originalText);
        result.append("\n\n");
        result.append(converter.convert(originalText));
        result.append("\n\n");
        result.append(buildTime(belTime, Mode.MASTADON));

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
        result.append(buildTime(belTime, Mode.TELEGRAM));

        return result.toString();
    }

    private String getTwitterText(String originalText, String[] tags, Integer belTime) {
        StringBuilder result = new StringBuilder();
        result.append(converter.convert(originalText));
        result.append("\n\n");
        result.append(buildTime(belTime, Mode.TWITTER));
        return result.toString();
    }

    private String getFacebookText(String originalText, String[] tags, Integer belTime) {
        StringBuilder result = new StringBuilder();
        result.append(buildTagString(tags));
        result.append("\n\n");
        result.append(originalText);
        result.append("\n\n");
        result.append(converter.convert(originalText));
        result.append("\n\n");
        result.append(buildTime(belTime, Mode.FACEBOOK));

        return result.toString();
    }
}
