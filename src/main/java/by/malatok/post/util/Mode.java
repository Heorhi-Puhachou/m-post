package by.malatok.post.util;

public enum Mode {
    MASTADON(":by:"),
    TELEGRAM("❤️"),
    YOUTUBE(""),
    DISCORD("❤️");

    private final String belSymbol;

    private Mode(String belSymbol) {
        this.belSymbol = belSymbol;
    }

    public String getBelSymbol() {
        return belSymbol;
    }
}
