package by.malatok.post.setup;

import javafx.scene.control.RadioButton;

import java.util.Arrays;
import java.util.List;

public enum Tags {

    MIN(Arrays.asList("Małatok", "biełstrym"), new RadioButton("MIN")),
    DOTA2(Arrays.asList("Małatok", "biełstrym", "dota2"), new RadioButton("DOTA2")),
    DEADLOCK(Arrays.asList("Małatok", "biełstrym", "deadlock"), new RadioButton("DEADLOCK")),
    RANDOMNICA(Arrays.asList("Małatok", "biełstrym", "randomnica"), new RadioButton("RANDOMNICA"));

    private final List<String> tags;
    private final RadioButton radioButton;

    // private enum constructor
    private Tags(List<String> tags, RadioButton radioButton) {
        this.tags = tags;
        this.radioButton = radioButton;
    }

    public List<String> getTags() {
        return tags;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }

    public static Tags findByAbbr(String abbr) {
        for (Tags v : values()) {
            if (v.name().equals(abbr)) {
                return v;
            }
        }
        return null;
    }
}
