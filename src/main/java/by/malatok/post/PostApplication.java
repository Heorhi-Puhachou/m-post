package by.malatok.post;

import by.malatok.post.ui.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PostApplication extends Application {

    private static final double SCENE_WIDTH = 800;
    private static final double SCENE_HEIGHT = 850;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView.getGridPane(), SCENE_WIDTH, SCENE_HEIGHT);

        primaryStage.setTitle("Malatok2334");
        primaryStage.setScene(scene);
        primaryStage.show();

        mainView.setupSizes();
    }
}
