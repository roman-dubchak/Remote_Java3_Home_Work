package ru.gb.chat.server.core;

import java.sql.*;

public class SqlClient {
    private static Connection connection = null;
    private static Statement statement = null;

    synchronized static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:chat-server/chat_j3.db");
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
        String query = String.format("select nickname from users where login='%s' and password='%s'", login.trim(), password);
//        trim удаляет пробелы в начале и в конце String
        try (ResultSet set = statement.executeQuery(query)) { // не неужен close ResultSet, тк Try с русерсами
            if (set.next()){
                return set.getString("nickname");}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    synchronized static String addUsersInDB (String login, String password, String nickname) {
        String addUser = String.format("insert into users (login, password, nickname) values('%s', '%s', '%s')",
                                login, password, nickname);
        try {
            statement.execute(addUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    synchronized static String changeNickname(String login, String password, String newLogin) {
        String updateNickname = String.format("update users set nickname='%s' where login='%s' and password='%s'",
                                 login, password, newLogin);
        try {
            statement.executeUpdate(updateNickname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
