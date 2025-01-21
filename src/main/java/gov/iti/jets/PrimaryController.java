package gov.iti.jets;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrimaryController {

    ResultSet rs = null;
    DatabaseManager dbManager = null;

    @FXML
    private TextField id;

    @FXML
    private TextField fName;

    @FXML
    private TextField mName;

    @FXML
    private TextField lName;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private Button newBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button firstBtn;

    @FXML
    private Button previousBtn;

    @FXML
    private Button nextBtn;

    @FXML
    private Button lastBtn;

    @FXML
    private Button clearBtn;

    @FXML
    public void initialize() {
       
        dbManager = DatabaseManager.getInstance();
        if (dbManager.isConnected()) {
            System.out.println("Database connection is active.");
        } else {
            System.err.println("Database connection failed.");
        }
        setStart();

        // Button actions
        newBtn.setOnAction(event -> handleNew());
        updateBtn.setOnAction(event -> handleUpdate());
        deleteBtn.setOnAction(event -> handleDelete());
        firstBtn.setOnAction(event -> handleFirst());
        previousBtn.setOnAction(event -> handlePrevious());
        nextBtn.setOnAction(event -> handleNext());
        lastBtn.setOnAction(event -> handleLast());
        clearBtn.setOnAction(event -> handleClear());
    }

    private void handleNew() {
        System.out.println("New button clicked");

        String firstName = fName.getText();
        String middleName = mName.getText();
        String lastName = lName.getText();
        String emailValue = email.getText();
        String phoneValue = phone.getText();

     
        dbManager.insertRecord(firstName, middleName, lastName, emailValue, phoneValue);
        setStart();  
    }

    private void handleUpdate() {
        System.out.println("Update button clicked");

        String idValue = id.getText();
        String firstName = fName.getText();
        String middleName = mName.getText();
        String lastName = lName.getText();
        String emailValue = email.getText();
        String phoneValue = phone.getText();

        
        dbManager.updateRecord(idValue, firstName, middleName, lastName, emailValue, phoneValue);
        setStart();  
    }

    private void handleDelete() {
        System.out.println("Delete button clicked");

        String idValue = id.getText();

       
        dbManager.deleteRecord(idValue);
        setStart(); 
    }

    private void handleFirst() {
        System.out.println("First button clicked");
        try {
            if (rs != null) {
                rs.first();
                setTextFields();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while accessing the database: " + e.getMessage());
        }
    }

    private void handlePrevious() {
        System.out.println("Previous button clicked");
        try {
            if (rs != null && !rs.isFirst()) {
                rs.previous();
                setTextFields();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while accessing the database: " + e.getMessage());
        }
    }

    private void handleNext() {
        System.out.println("Next button clicked");
        try {
            if (rs != null && !rs.isLast()) {
                rs.next();
                setTextFields();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while accessing the database: " + e.getMessage());
        }
    }

    private void handleLast() {
        System.out.println("Last button clicked");
        try {
            if (rs != null) {
                rs.last();
                setTextFields();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while accessing the database: " + e.getMessage());
        }
    }

    private void handleClear() {
        
        
        id.clear();
        fName.clear();
        mName.clear();
        lName.clear();
        email.clear();
        phone.clear();
        System.out.println("Clear button clicked - All fields cleared");
    }

    private void setStart() {
        try {
            rs = dbManager.getAllRecords();
            if (rs != null && rs.next()) {
                setTextFields();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while accessing the database: " + e.getMessage());
        }
    }

    private void setTextFields() {
        try {
            id.setText(rs.getString("ID"));
            fName.setText(rs.getString("FirstName"));
            mName.setText(rs.getString("MiddleName"));
            lName.setText(rs.getString("LastName"));
            email.setText(rs.getString("Email"));
            phone.setText(rs.getString("Phone"));
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while setting text fields: " + e.getMessage());
        }
    }
}
