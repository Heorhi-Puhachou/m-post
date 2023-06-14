package by.mltk.m_post;

import by.spelling.conversion.converter.lacink.TaraskLacinkConverter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private static final TaraskLacinkConverter converter = new TaraskLacinkConverter();
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Welcome");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

       // Text scenetitle = new Text("Aryhinalny tekst");
        TextArea originalTextArea = new TextArea();
       // scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(originalTextArea, 0, 0, 2, 1);


        Label userName = new Label("Telegram:");
        grid.add(userName, 0, 1);
        TextArea inputPost = new TextArea();
        grid.add(inputPost, 0, 2);
        TextArea outputPost = new TextArea();


        grid.add(outputPost, 1, 2);


        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                outputPost.setText(converter.convert(inputPost.getText()));
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Sign in button pressed");
            }
        });

        Scene scene = new Scene(grid, 1000, 775);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }

    public static void main(String[] args) {
        launch();
    }
}