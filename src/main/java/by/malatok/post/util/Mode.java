package by.malatok.post.util;

public enum Mode {
    MASTADON(":by:"),
    TELEGRAM("❤️"),
    YOUTUBE(""),
    FACEBOOK("❤️");

    private final String belSymbol;

    private Mode(String belSymbol) {
        this.belSymbol = belSymbol;
    }

    public String getBelSymbol() {
        return belSymbol;
    }
}
