package by.malatok.post.util;

import by.spelling.conversion.converter.lacink.TaraskLacinkConverter;

public class TextHandler {

    private final TaraskLacinkConverter converter;

    public TextHandler() {
        converter = new TaraskLacinkConverter();
    }

    public String getText(String originalText, String link, String[] tags, Integer belTime, Mode mode) {
        String result = buildTagString(tags) +
                "\n\n" +
                converter.convert(originalText) +
                "\n\n" +
                originalText +
                "\n\n" +
                link +
                "\n\n" +
                buildTime(belTime, mode);
        return result.trim();
    }

    private String buildTagString(String[] tags) {
        String tagRow = "";
        for (String tag : tags) {
            tagRow = tagRow + " #" + tag;
        }
        return tagRow.trim();
    }

    private String buildTime(Integer belTime, Mode mode) {
        int polTime = belTime - 1;
        int georTime = belTime + 1;

        return mode.getBelSymbol() + " " + belTime + ":00 pavodle biełaruskaha času\n" +
                "\uD83C\uDDF5\uD83C\uDDF1 " + polTime + ":00 pavodle polskaha\n" +
                "\uD83C\uDDEC\uD83C\uDDEA " + georTime + ":00 pavodle hruzinskaha";
    }
}
