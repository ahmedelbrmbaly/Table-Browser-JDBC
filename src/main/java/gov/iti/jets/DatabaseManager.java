package gov.iti.jets;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.InputStream;

public class DatabaseManager {


    private Connection connection = null;
    private static DatabaseManager instance;

    private DatabaseManager() {
        connectToDatabase();
    }

    private void connectToDatabase() {
        DataSource ds = DatabaseManager.getMySQLDataSource();

        try {
            connection = ds.getConnection();

            if (connection != null) 
            {
                System.out.println("Connected to the database successfully!");
            } 
            else 
            {
                System.out.println("Failed to connect to the database.");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
        
        
    }


   public ResultSet getAllRecords() {
        ResultSet rs = null;
        Statement stmt = null;
        try{
            stmt = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            rs = stmt.executeQuery("select ID, FirstName, MiddleName, LastName, Email, Phone from PersonDetails");
            /*
            while (rs.next()) {
                System.out.println(
                    "ID = " + rs.getInt("ID") +
                    ", FirstName = " + rs.getString("FirstName") +
                    ", MiddleName = " + rs.getString("MiddleName") +
                    ", LastName = " + rs.getString("LastName") +
                    ", Email = " + rs.getString("Email") +
                    ", Phone = " + rs.getString("Phone")
                );
                
            }
            */
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
              
        
  }

    public void insertRecord(String firstName, String middleName, String lastName, String email, String phone) {
        String insertQuery = "INSERT INTO PersonDetails (FirstName, MiddleName, LastName, Email, Phone) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) 
        {
       
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, middleName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phone);
    
    
            int rowsInserted = preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while inserting record: " + e.getMessage());
        }
        
        
    }

    public void updateRecord(String id, String firstName, String middleName, String lastName, String email, String phone) {
        String updateQuery = "UPDATE PersonDetails SET FirstName = ?, MiddleName = ?, LastName = ?, Email = ?, Phone = ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
       
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, middleName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phone);
    
    
            int rowsUpdated = preparedStatement.executeUpdate();

        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            System.out.println("Error while Updating record: " + e.getMessage());
        }
        

        
    }


    public void deleteRecord(String id) {
        String deleteQuery = "DELETE FROM PersonDetails WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
       
            preparedStatement.setString(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();

        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            System.out.println("Error while deleting record: " + e.getMessage());
        }


        
    }


     public static DataSource getMySQLDataSource() {
        Properties props = new Properties();
        MysqlDataSource mysqlDS = null;

        
        try (InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                System.out.println("Unable to find database.properties in the classpath.");
                return null;
            }
            props.load(input);
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(props.getProperty("db.url"));
            mysqlDS.setUser(props.getProperty("db.user"));
            mysqlDS.setPassword(props.getProperty("db.password"));
        } 
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading database.properties file: " + e.getMessage());
        }

        return mysqlDS;
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public boolean isConnected() {
        return connection != null;
    }

}

