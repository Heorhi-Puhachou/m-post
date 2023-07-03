package by.malatok.post.util;

public enum Mode {
    MASTADON(":by:"),
    TELEGRAM("❤\uFE0F"),
    TWITTER("❤"),
    FACEBOOK("❤\uFE0F");

    private final String belSymbol;

    private Mode(String belSymbol) {
        this.belSymbol = belSymbol;
    }

    public String getBelSymbol() {
        return belSymbol;
    }
}
