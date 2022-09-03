package main.java.de.emir.mysql;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

import java.sql.*;

public class MySQL {

    private static Statement stmt;
    private static Connection connection;
    String pw1 = "****";



    public void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamersucht?autoReconnect=true", "gamersucht", pw1);
                System.out.println("MySQl-Verbindung erfolgreich!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("MySQL-Verbindung fehlgeschlagen!");
            }
        }
    }

    public void close() {
        if (isConnected()) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Fehler beim Schließen der MySQL-Verbindung!");
            }
        }
    }

    public boolean isConnected() {
        return connection != null;
    }

    public static void onUpdate(String sql) {
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet onQuery(String sql) {

        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(String sql) {
        if (isConnected()) {
            try {
                connection.createStatement().executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Fehler beim Ausführen eines Updates in der MySQL-Datenbank!");
            }
        }
    }

    public ResultSet getResult(String sql) {
        if (isConnected()) {
            try {
                return connection.createStatement().executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Fehler beim Abfragen von Daten aus der MySQL-Datenbank!");
            }
        }
        return null;
    }

    public  String getValue(Guild guild, String type){
        try{
            if(connection.isClosed())
                connect();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM guild WHERE `serverid` = ?");
            ps.setString(1, guild.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return rs.getString(type);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
