module gov.iti.jets {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static mysql.connector.j; 
    requires java.naming; 

    opens gov.iti.jets to javafx.fxml;
    exports gov.iti.jets;
}