package by.malatok.post;

import by.malatok.post.ui.MainView;
import by.malatok.post.util.SingletonConstantStorage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PostApplication extends Application {

    private static final double SCENE_WIDTH = 900;
    private static final double SCENE_HEIGHT = 950;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        SingletonConstantStorage singletonConstantStorage = SingletonConstantStorage.getInstance();

        //------------------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------------------
        singletonConstantStorage.setBEL_TIME_HOURS(21);
        singletonConstantStorage.setBEL_POL_HOUR_SHIFT(2);
        singletonConstantStorage.setBEL_TIME_MINUTES(30);
        singletonConstantStorage.setPostTags(new String[]{"strym", "disciples"});
        singletonConstantStorage.setYoutubeTags(new String[]{"Małatok", "strym", "disciples", "pabiełarusku"});
        //------------------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------------------

        MainView mainView = new MainView();
        Scene scene = new Scene(mainView.getGridPane(), SCENE_WIDTH, SCENE_HEIGHT);

        primaryStage.setTitle("M-2334");
        primaryStage.setScene(scene);
        primaryStage.show();

        mainView.setupSizes();
    }
}
