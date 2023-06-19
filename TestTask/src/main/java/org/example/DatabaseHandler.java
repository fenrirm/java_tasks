package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test_task";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }

    public static void insertEquation(String equation) {
        String sql = "INSERT INTO equation (equation) VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement psInsert = connection.prepareStatement(sql)) {
            psInsert.setString(1, equation);
            psInsert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static int getEquationId(Equation equation) {
        int equationId = -1;

        try (Connection connection = getConnection()) {
            String query = "SELECT equation_id FROM equation WHERE equation = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, equation.getEquation());
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    equationId = resultSet.getInt("equation_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equationId;
    }

    public static void insertRoot(int equationId, double root) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO root (equation_id, root) VALUES (?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, equationId);
                statement.setDouble(2, root);
                statement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static List<Equation> getEquationsByRoot(double root) {
        List<Equation> equations = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
            String query = "SELECT equation FROM equation e " +
                    "INNER JOIN root r ON e.equation_id = r.equation_id " +
                    "WHERE r.root = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDouble(1, root);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String received_equation = resultSet.getString("equation");
                    Equation equation = new Equation(received_equation);
                    equations.add(equation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return equations;
    }

}
