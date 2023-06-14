package by.mltk.m_post;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import by.spelling.conversion.converter.lacink.TaraskLacinkConverter;

public class HelloController {
    @FXML
    private Label welcomeText;

    private static final TaraskLacinkConverter converter = new TaraskLacinkConverter();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(converter.convert("Лягенда Зэльды"));
    }
}