package by.malatok.post.util;

import by.spelling.conversion.converter.l.KLKanvertar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TextHandler {

    private final KLKanvertar kanvertar;

    private static final String TWITCH_LINK = "https://twitch.tv/malatok2334";
    private static final String X_LINK = "https://x.com/Malatok2334";
    private static final String FACEBOOK_LINK = "https://www.facebook.com/groups/malatok2334";
    private static final String TIKTOK_LINK = "https://www.tiktok.com/@bielaruski.malatok/";
    private static final String TELEGRAM_LINK = "https://t.me/malatok2334";
    private final SingletonConstantStorage singletonConstantStorage;

    public TextHandler() {
        kanvertar = new KLKanvertar();
        singletonConstantStorage = SingletonConstantStorage.getInstance();
    }

    public String getText(String originalText,
                          String link,
                          Mode mode,
                          Boolean youtube,
                          Boolean twitch,
                          Boolean telegram,
                          Boolean facebook,
                          Boolean tiktok) {
        final List<String> tags = singletonConstantStorage.getPostTags();

        StringBuilder result = new StringBuilder();

        if (mode == Mode.MASTADON) {
            result.append(kanvertar.kanvertavać(originalText));
            result.append("\n\n");
            result.append("***");
            result.append("\n\n");
            result.append(originalText);
            result.append("\n\n");
            if (youtube) {
                result.append("YouTube - " + link);
                result.append("\n");
            }
            if (twitch) {
                result.append("Twitch - " + TWITCH_LINK);
                result.append("\n");
            }
            if (telegram) {
                result.append("Telegram - " + TELEGRAM_LINK);
                result.append("\n");
            }
            if (facebook) {
                result.append("Facebook - " + FACEBOOK_LINK);
                result.append("\n");
            }
            if (tiktok) {
                result.append("TikTok - " + TIKTOK_LINK);
                result.append("\n");
            }
            result.append("\n");
            result.append(buildTime(mode));
            result.append("\n\n");
            result.append(buildTagString(tags));
            return result.toString().trim();
        }

        if (mode == Mode.YOUTUBE) {
            result.append(kanvertar.kanvertavać(originalText));
            result.append("\n\n");
            result.append("***");
            result.append("\n\n");
            result.append(originalText);
            result.append("\n\n");
            result.append(buildTagString(tags));
            return result.toString().trim();
        }

        if (mode == Mode.TELEGRAM) {
            result.append(buildTagString(tags));
            result.append("\n\n");
            result.append(kanvertar.kanvertavać(originalText));
            result.append("\n\n");
            result.append("***");
            result.append("\n\n");
            result.append(originalText);

            result.append("\n\n");
            if (youtube) {
                result.append("YouTube - " + link);
                result.append("\n");
            }
            if (twitch) {
                result.append("Twitch - " + TWITCH_LINK);
                result.append("\n");
            }
            if (telegram) {
                result.append("Telegram - " + TELEGRAM_LINK);
                result.append("\n");
            }
            if (facebook) {
                result.append("Facebook - " + FACEBOOK_LINK);
                result.append("\n");
            }
            if (tiktok) {
                result.append("TikTok - " + TIKTOK_LINK);
                result.append("\n");
            }
            result.append("\n");

            result.append(buildTime(mode));
        }

        if (mode == Mode.DISCORD) {
            result.append(kanvertar.kanvertavać(originalText));
            result.append("\n\n");
            result.append("***");
            result.append("\n\n");
            result.append(originalText);

            result.append("\n\n");
            if (youtube) {
                result.append("YouTube - " + link);
                result.append("\n");
            }
            if (twitch) {
                result.append("Twitch - " + TWITCH_LINK);
                result.append("\n");
            }
            if (telegram) {
                result.append("Telegram - " + TELEGRAM_LINK);
                result.append("\n");
            }
            if (facebook) {
                result.append("Facebook - " + FACEBOOK_LINK);
                result.append("\n");
            }
            if (tiktok) {
                result.append("TikTok - " + TIKTOK_LINK);
                result.append("\n");
            }
            result.append("\n");

            result.append(buildTime(mode));
        }
        return result.toString().trim();
    }

    private String buildTagString(List<String> tags) {
        StringBuilder tagRow = new StringBuilder();
        for (String tag : tags) {
            tagRow.append(" #").append(tag);
        }
        return tagRow.toString().trim();
    }

    private String buildTime(Mode mode) {
        Integer belTimeHours = singletonConstantStorage.getBEL_TIME_HOURS();
        String belTimeMinutes = singletonConstantStorage.getBEL_TIME_MINUTES() > 9
                ?
                "" + singletonConstantStorage.getBEL_TIME_MINUTES()
                :
                "0" + singletonConstantStorage.getBEL_TIME_MINUTES();
        int polTime = belTimeHours - singletonConstantStorage.getBEL_POL_HOUR_SHIFT();
        int georTime = belTimeHours - singletonConstantStorage.getBEL_POL_HOUR_SHIFT() - 1;

        String DATE_FORMAT_NOW = "yyyy.MM.dd";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

        return sdf.format(cal.getTime())
                + "\n\n"
                + mode.getBelSymbol() + " " + belTimeHours + ":" + belTimeMinutes + " pavodle biełaruskaha času\n" +
                "\uD83C\uDDF5\uD83C\uDDF1 " + polTime + ":" + belTimeMinutes + " pavodle polskaha\n" +
                "\uD83C\uDF0D " + georTime + ":" + belTimeMinutes + " pavodle UTC";
    }
}
