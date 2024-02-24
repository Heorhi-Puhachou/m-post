package by.malatok.post.setup;

import java.util.Arrays;
import java.util.List;

public enum Tags {

    MIN(Arrays.asList("Małatok", "biełstrym")),
    HEROI(Arrays.asList("Małatok", "biełstrym", "mightandmagic", "homm3")),
    WACHADZIEŁAK(Arrays.asList("pacaklaca", "Orril", "Małatok", "biełstrym", "warhammer", "ageofsigmar", "tabletopsimulator")),
    PROKADA(Arrays.asList("Małatok", "biełstrym", "prokada", "dota2")),
    RANDOMNICA(Arrays.asList("Małatok", "biełstrym", "randomnica"));

    private final List<String> tags;

    // private enum constructor
    private Tags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
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
