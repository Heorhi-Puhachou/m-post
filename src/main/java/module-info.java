module by.mltk.m_post {
    requires javafx.controls;
    requires javafx.fxml;
    requires converter;


    opens by.mltk.m_post to javafx.fxml;
    exports by.mltk.m_post;
}