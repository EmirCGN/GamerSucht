package main.java.de.emir.manager;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationManager {

    Main main;

    public ApplicationManager(Main main) {
        this.main = main;
    }

    public void addApplication(User user, long messageID, String application, String function, long privateChannelID) {
        main.getMySQL().update("INSERT INTO Applications(ID, AFunction, Application, MessageID, Timestamp, PrivateChannelID) VALUES ('" + user.getId() + "', '" + function + "', '" + application +
                "', '" + messageID + "', '" + System.currentTimeMillis() + "', '" + privateChannelID + "')");
    }

    public boolean existsApplication(User user) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Applications WHERE ID='" + user.getId() + "'");
        try {
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public long getUserIDByMessageID(long messageID) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Applications WHERE MessageID='" + messageID + "'");
        try {
            if(rs.next()) {
                return rs.getLong("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getFunction(long userID) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Applications WHERE ID='" + userID + "'");
        try {
            if(rs.next()) {
                return rs.getString("Function");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getApplication(long userID) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Applications WHERE ID='" + userID + "'");
        try {
            if(rs.next()) {
                return rs.getString("Application");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getPrivateChannelID(long userID) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Applications WHERE ID='" + userID + "'");
        try {
            if(rs.next()) {
                return rs.getLong("PrivateChannelID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isApplicationReaction(long messageID) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Applications WHERE MessageID='" + messageID + "'");
        try {
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean canApply(User user) {
        if(!existsApplication(user)) return true;
        long time = 1000;
        long time2 = 60 * 60 * 24 * 30;
        if((getTimestamp(user) + time * time2) < System.currentTimeMillis()) {
            removeApplication(user);
            return true;
        }
        else return false;
    }

    public void removeApplication(User user) {
        main.getMySQL().update("DELETE FROM Applications WHERE ID='" + user.getId() + "'");
    }

    public long getTimestamp(User user) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Applications WHERE ID='" + user.getId() + "'");
        try {
            if(rs.next()) {
                return rs.getLong("Timestamp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
