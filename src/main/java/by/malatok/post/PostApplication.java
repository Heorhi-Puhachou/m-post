package by.malatok.post;

import by.malatok.post.setup.Media;
import by.malatok.post.setup.Tags;
import by.malatok.post.ui.MainView;
import by.malatok.post.util.SingletonConstantStorage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Calendar;

public class PostApplication extends Application {

    private static final double SCENE_WIDTH = 1600;
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
        singletonConstantStorage.setBEL_TIME_MINUTES(00);

        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek){
            case Calendar.MONDAY:
                singletonConstantStorage.setPostTags(Tags.DEADLOCK);
                break;
            case Calendar.WEDNESDAY:
                singletonConstantStorage.setPostTags(Tags.DOTA2);
                break;
            case Calendar.FRIDAY:
                singletonConstantStorage.setPostTags(Tags.RANDOMNICA);
                break;
            default:
                singletonConstantStorage.setPostTags(Tags.MIN);
        }

        Media.findByAbbr("YOUTUBE").getCheckBox().setSelected(true);
        Media.findByAbbr("TWITCH").getCheckBox().setSelected(true);
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
