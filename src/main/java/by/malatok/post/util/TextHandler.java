package by.malatok.post.util;

import by.spelling.conversion.converter.l.KLKanvertar;

import static by.malatok.post.util.TextSize.SMALL;

public class TextHandler {

    private final KLKanvertar kanvertar;

    public TextHandler() {
        kanvertar = new KLKanvertar();
    }

    public String getText(String originalText, String link, String[] tags, Integer belTimeHours, Integer belTimeMinutes, Mode mode) {

        StringBuilder result = new StringBuilder();

        if (mode == Mode.TEXT) {
            result.append(kanvertar.kanvertavać(originalText));
            result.append("\n\n");
            result.append("***");
            result.append("\n\n");
            result.append(originalText);
            return result.toString().trim();
        }


        if (mode.geTextSize() != SMALL) {
            result.append(buildTagString(tags));
            result.append("\n\n");
        }
        result.append(kanvertar.kanvertavać(originalText));
        result.append("\n\n");
        if (mode.geTextSize() != SMALL) {
            result.append(originalText);
            result.append("\n\n");
        }
        result.append(link);
        result.append("\n\n");
        result.append(buildTime(belTimeHours, belTimeMinutes, mode));
        return result.toString().trim();
    }

    private String buildTagString(String[] tags) {
        String tagRow = "";
        for (String tag : tags) {
            tagRow = tagRow + " #" + tag;
        }
        return tagRow.trim();
    }

    private String buildTime(Integer belTimeHours, Integer belTimeMinutes, Mode mode) {
        int polTime = belTimeHours - 1;
        int georTime = belTimeHours + 1;

        return mode.getBelSymbol() + " " + belTimeHours + ":" + belTimeMinutes + " pavodle biełaruskaha času\n" +
                "\uD83C\uDDF5\uD83C\uDDF1 " + polTime + ":" + belTimeMinutes + " pavodle polskaha\n" +
                "\uD83C\uDDEC\uD83C\uDDEA " + georTime + ":" + belTimeMinutes + " pavodle hruzinskaha";
    }
}
