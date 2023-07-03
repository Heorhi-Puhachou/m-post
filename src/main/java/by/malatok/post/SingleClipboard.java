package by.malatok.post;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class SingleClipboard {

    private static SingleClipboard instance;
    private final Clipboard clipboard;
    private final ClipboardContent content;

    private SingleClipboard() {
        clipboard = Clipboard.getSystemClipboard();
        content = new ClipboardContent();
    }

    public static SingleClipboard getInstance() {
        if (instance == null) {
            instance = new SingleClipboard();
        }
        return instance;
    }

    public void putStringToClipboard(String string) {
        content.putString(string);
        clipboard.setContent(content);
    }

    public String getString() {
        return clipboard.getString();
    }
}
