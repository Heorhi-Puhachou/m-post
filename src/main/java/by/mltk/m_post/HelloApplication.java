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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static javafx.geometry.Pos.CENTER;

public class HelloApplication extends Application {

    private static final TaraskLacinkConverter converter = new TaraskLacinkConverter();

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

        Button btn = new Button("Padrychtavac");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(CENTER);
        hbBtn.getChildren().add(btn);

        grid.add(hbBtn, 0, ++row, 2, 1);


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




        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                mastadonPost.setText(converter.convert(originalTextArea.getText()));
                telegramPost.setText(originalTextArea.getText() + "\n\n" + converter.convert(originalTextArea.getText()));
            }
        });

        Scene scene = new Scene(grid, 1000, 775);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}