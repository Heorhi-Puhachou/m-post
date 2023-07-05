package by.malatok.post.util;

public enum Mode {
    MASTADON(":by:", TextSize.USUAL),
    TELEGRAM("❤\uFE0F", TextSize.USUAL),
    TWITTER("❤", TextSize.SMALL),
    FACEBOOK("❤\uFE0F", TextSize.USUAL);

    private final String belSymbol;
    private final TextSize textSize;

    private Mode(String belSymbol, TextSize textSize) {
        this.belSymbol = belSymbol;
        this.textSize = textSize;
    }

    public String getBelSymbol() {
        return belSymbol;
    }

    public TextSize geTextSize() {
        return textSize;
    }
}
