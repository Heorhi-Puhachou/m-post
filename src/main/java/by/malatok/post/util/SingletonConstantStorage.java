package by.malatok.post.util;

import by.malatok.post.setup.Tags;

import java.util.ArrayList;
import java.util.List;

public class SingletonConstantStorage {


    private Tags tags;
    private final List<String> customTags = new ArrayList<>();
    private String[] youtubeTags;
    private Integer BEL_TIME_HOURS;

    private Integer BEL_POL_HOUR_SHIFT;
    private Integer BEL_TIME_MINUTES;

    private static SingletonConstantStorage instance;

    private SingletonConstantStorage() {
    }

    public static SingletonConstantStorage getInstance() {
        if (instance == null) {
            instance = new SingletonConstantStorage();
        }
        return instance;
    }

    public void rewriteCustomTags(List<String> newCustomTags) {
        customTags.clear();
        customTags.addAll(newCustomTags);
    }


    public List<String> getPostTags() {
        List<String> result = new ArrayList<>(customTags);
        if (tags == null) {
            result.addAll(Tags.MIN.getTags());
        } else {
            result.addAll(tags.getTags());
        }
        return result;
    }

    public void setPostTags(Tags tags) {
        this.tags = tags;
        this.tags.getRadioButton().setSelected(true);
    }

    public String[] getYoutubeTags() {
        return youtubeTags;
    }

    public void setYoutubeTags(String[] youtubeTags) {
        this.youtubeTags = youtubeTags;
    }

    public Integer getBEL_TIME_HOURS() {
        return BEL_TIME_HOURS;
    }

    public void setBEL_TIME_HOURS(Integer BEL_TIME_HOURS) {
        this.BEL_TIME_HOURS = BEL_TIME_HOURS;
    }

    public Integer getBEL_POL_HOUR_SHIFT() {
        return BEL_POL_HOUR_SHIFT;
    }

    public void setBEL_POL_HOUR_SHIFT(Integer BEL_POL_HOUR_SHIFT) {
        this.BEL_POL_HOUR_SHIFT = BEL_POL_HOUR_SHIFT;
    }

    public Integer getBEL_TIME_MINUTES() {
        return BEL_TIME_MINUTES;
    }

    public void setBEL_TIME_MINUTES(Integer BEL_TIME_MINUTES) {
        this.BEL_TIME_MINUTES = BEL_TIME_MINUTES;
    }
}
