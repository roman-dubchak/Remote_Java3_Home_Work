package Lesson2_data_base;

import java.sql.*;

public class SqlEx {
    private static Connection connection = null;
    private static Statement statement = null;

    synchronized static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:chat-server/chat.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized static void disconnect() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized static String getNickname(String login, String password) {
        String query = String.format("select nickname from users where login='%s' and password='%s'", login, password);
        try (ResultSet set = statement.executeQuery(query)) {
            if (set.next()){
                return set.getString("nickname");}
            else set.close(); // здесь не лишний клоуз?
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    synchronized static String addUsersInDB (String login, String password, String nickname) {
        String addUser = String.format("insert into users (login, password, nickname) value('%s', '%s', '%s')",
                login, password, nickname);
        try {
            statement.execute(addUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    synchronized static String changeNickname(String newLogin, String login, String password) {
        String updateNickname = String.format("update users set nickname='%s' where login='%s' and password='%s'",
                newLogin, login, password);
        try {
            statement.executeUpdate(updateNickname);
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return null;
    }
}

