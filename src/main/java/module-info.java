module com.userinfo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.userinfo to javafx.fxml;
    exports com.userinfo;
}