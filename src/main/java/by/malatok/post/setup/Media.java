package by.malatok.post.setup;

import javafx.scene.control.CheckBox;

import java.util.Arrays;
import java.util.List;

public enum Media {

    YOUTUBE(new CheckBox("YOUTUBE")),
    TWITCH(new CheckBox("TWITCH")),
    TELEGRAM(new CheckBox("TELEGRAM")),
    FACEBOOK(new CheckBox("FACEBOOK")),
    TIKTOK(new CheckBox("TIKTOK"));

    private final CheckBox checkBox;

    // private enum constructor
    private Media(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public static Media findByAbbr(String abbr) {
        for (Media v : values()) {
            if (v.name().equals(abbr)) {
                return v;
            }
        }
        return null;
    }
}
