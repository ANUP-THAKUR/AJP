package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IDGenerator {
    public static int generateID(String tableName, String columnName) {
        String query = "SELECT MAX(" + columnName + ") AS max_id FROM " + tableName;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int maxId = resultSet.getInt("max_id");
                return maxId + 1; // Increment the highest ID by 1
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1; // Start from 1 if table is empty
    }
}

