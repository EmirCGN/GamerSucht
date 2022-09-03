package main.java.de.emir.manager;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileManager {

    Main main;

    public ProfileManager(Main main) {
        this.main = main;
    }

    public void createPlayer(long id) {
        if(!existsPlayer(id)) {
            main.getMySQL().update("INSERT INTO User(ID, isTeamMember, isTeamMemberSince, TeamStrikes, Strikes, Notes, isTempbanned, isTempbannedFrom, isTempbannedReason, " +
                    "getsUnbannedWhen, isBanned, isBannedFrom, isBannedReason, isTempmuted, isTempmutedFrom, isTempmutedReason, getsUnmutedWhen, isMuted, isMutedFrom, isMutedReason, " +
                    "Roles) VALUES ('" + id + "', '0', '0', '0', '0', '0', '0', '0', '', '0', '0', '0', '', '0', '0', '', '0', '0', '0', '', '')");
        }
    }

    public List<Long> getRoles(long id) {
        List<Long> toreturn = new ArrayList<>();
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM User WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                String rolesRaw = rs.getString("Roles");
                String[] roles = rolesRaw.split(";");
                for(String role : roles) {
                    toreturn.add(Long.parseLong(role));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toreturn;
    }

    public String getRolesRaw(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM User WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                return rs.getString("Roles");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addRole(long memberID, long roleID) {
        String roles = getRolesRaw(memberID);
        roles = roles + roleID + ";";
        main.getMySQL().update("UPDATE User SET Roles='" + roles + "' WHERE ID='" + memberID + "'");
    }

    public void removeRole(long memberID, long roleID) {
        String roles = getRolesRaw(memberID);
        roles = roles.replace(roleID + ";", "");
        main.getMySQL().update("UPDATE User SET Roles='" + roles + "' WHERE ID='" + memberID + "'");
    }

    public boolean existsPlayer(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM User WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                return rs.getString("ID") != null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setTeamSince(long id, long timestamp) {
        main.getMySQL().update("UPDATE User SET isTeamMemberSince='" + timestamp + "' WHERE ID='" + id + "'");
    }

    public boolean isBanned(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM User WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                return rs.getInt("isBanned") != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isMuted(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM User WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                return rs.getInt("isMuted") != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isTeamMember(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM User WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                return rs.getInt("isTeamMember") != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getTeamRank(Member m) {
        for(Role r : m.getRoles()) {
            if(r.getId().equalsIgnoreCase("477893096756215829")) return "\uD83D\uDD12 Owner \uD83D\uDD12";
            if(r.getId().equalsIgnoreCase("477893104335323146")) return "●\uD83C\uDFC6 Administrator \uD83C\uDFC6●";
            if(r.getId().equalsIgnoreCase("870045319612735550")) return "●✔ SrContent ✔●";
            if(r.getId().equalsIgnoreCase("581481942726344705")) return "•❄ SrModerator/in ❄•";
            if(r.getId().equalsIgnoreCase("870046872264388669")) return "✔● Content ✔●";
            if(r.getId().equalsIgnoreCase("477893113113870346")) return "●Ⓜ Moderator/in Ⓜ●";
            if(r.getId().equalsIgnoreCase("574972907378311168")) return "⭕ Supporter/in ⭕";
            if(r.getId().equalsIgnoreCase("758348227048243200")) return "⭕ Test-Supporter ⭕";
        }
        return null;
    }

    public String inTeamSince(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM User WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                long l = rs.getLong("isTeamMemberSince");
                return new SimpleDateFormat("dd.MM.yyyy").format(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getTeamStrikes(long id) {
        List<String> toreturn = new ArrayList<>();
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM User WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                String strikesRaw = rs.getString("TeamStrikes");
                String[] strikes = strikesRaw.split(";");
                toreturn.addAll(Arrays.asList(strikes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toreturn;
    }

    public int getTeamStrikesNumber(long id) {
        List<String> toreturn = new ArrayList<>();
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM User WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                String strikesRaw = rs.getString("TeamStrikes");
                if(strikesRaw.equalsIgnoreCase("")) return 0;
                String[] strikes = strikesRaw.split(";");
                toreturn.addAll(Arrays.asList(strikes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toreturn.size();
    }

    public List<String> getStrikes(long id) {
        List<String> toreturn = new ArrayList<>();
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM User WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                String strikesRaw = rs.getString("Strikes");
                String[] strikes = strikesRaw.split(";");
                toreturn.addAll(Arrays.asList(strikes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toreturn;
    }

    public int getStrikesNumber(long id) {
        List<String> toreturn = new ArrayList<>();
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM User WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                String strikesRaw = rs.getString("Strikes");
                if(strikesRaw.equalsIgnoreCase("")) return 0;
                String[] strikes = strikesRaw.split(";");
                toreturn.addAll(Arrays.asList(strikes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toreturn.size();
    }

    public List<String> getNotes(long id) {
        List<String> toreturn = new ArrayList<>();
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM User WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                String strikesRaw = rs.getString("Notes");
                String[] strikes = strikesRaw.split(";");
                toreturn.addAll(Arrays.asList(strikes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toreturn;
    }



}