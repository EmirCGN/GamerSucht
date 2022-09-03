package main.java.de.emir.manager;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BirthdayManager {

    Main main;

    public BirthdayManager(Main main) {
        this.main = main;
    }

    public void addBirthday(long id, String birthday) {
        String date = birthday.substring(0, 6);
        String year = birthday.substring(6, 10);
        main.getMySQL().update("INSERT INTO Birthdays(ID, Birthday, Year) VALUES ('" + id + "', '" + date + "', '" + year + "')");
    }

    public void removeBirthday(long id) {
        main.getMySQL().update("DELETE FROM Birthdays WHERE ID='" + id + "'");
    }

    public boolean hasToldBirthday(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Birthdays WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getBirthday(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Birthdays WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                return rs.getString("Birthday");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getBirthdayYear(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Birthdays WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                return rs.getString("Year");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean existsBirthday(String date) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Birthdays WHERE Birthday='" + date + "'");
        try {
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void removeWrongBirthdayRoles() {
        Role r = main.guild.getRoleById("576168145522393088");
        for(Member m : main.guild.getMembers()) {
            if(!m.getRoles().contains(r)) continue;
            String date = new SimpleDateFormat("dd.MM.").format(System.currentTimeMillis() + 7200000);
            if(!getBirthday(m.getIdLong()).equalsIgnoreCase(date)) {
                main.guild.removeRoleFromMember(m, r).queue();
            }
        }
    }

    public List<Long> todayBirthdays(String date) {
        List<Long> toreturn = new ArrayList<>();
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Birthdays WHERE Birthday='" + date + "'");
        try {
            while(rs.next()) {
                toreturn.add(rs.getLong("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toreturn;
    }

    public int getAge(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Birthdays WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                String currentYear = new SimpleDateFormat("yyyy").format(System.currentTimeMillis() + 7200000);
                return Integer.parseInt(currentYear) - rs.getInt("Year");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long getMessageIDByDate(String date) {
        if(date.substring(3, 5).equalsIgnoreCase("01")) return getMessageIDByMonth("Januar");
        if(date.substring(3, 5).equalsIgnoreCase("02")) return getMessageIDByMonth("Februar");
        if(date.substring(3, 5).equalsIgnoreCase("03")) return getMessageIDByMonth("März");
        if(date.substring(3, 5).equalsIgnoreCase("04")) return getMessageIDByMonth("April");
        if(date.substring(3, 5).equalsIgnoreCase("05")) return getMessageIDByMonth("Mai");
        if(date.substring(3, 5).equalsIgnoreCase("06")) return getMessageIDByMonth("Juni");
        if(date.substring(3, 5).equalsIgnoreCase("07")) return getMessageIDByMonth("Juli");
        if(date.substring(3, 5).equalsIgnoreCase("08")) return getMessageIDByMonth("August");
        if(date.substring(3, 5).equalsIgnoreCase("09")) return getMessageIDByMonth("September");
        if(date.substring(3, 5).equalsIgnoreCase("10")) return getMessageIDByMonth("Oktober");
        if(date.substring(3, 5).equalsIgnoreCase("11")) return getMessageIDByMonth("November");
        if(date.substring(3, 5).equalsIgnoreCase("12")) return getMessageIDByMonth("Dezember");
        return 0;
    }

    public HashMap<Member, String> getDates() {
        HashMap<Member, String> toreturn = new HashMap<>();
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Birthdays ORDER BY Birthday ASC");
        try {
            while(rs.next()) {
                if(main.guild.getMemberById(rs.getLong("ID")) == null) {
                    removeBirthday(rs.getLong("ID"));
                    continue;
                }
                toreturn.put(main.guild.getMemberById(rs.getLong("ID")), rs.getString("Birthday"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toreturn;
    }

    public long getMessageIDByMonth(String month) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM BirthdayMessages WHERE Month='" + month + "'");
        try {
            if(rs.next()) {
                return rs.getLong("MessageID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}