package main.java.de.emir.manager;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class LevelManager {

    Main main;

    public LevelManager(Main main) {
        this.main = main;
    }


    public void addRandomXP(long id) {
        Random r = new Random();
        int i = r.nextInt(11);
        long xp = 15 + i;
        xp = xp + getXp(id);
        long totalXp = getTotalXp(id) + 15 + i;
        main.getMySQL().update("UPDATE Levels SET XP='" + xp + "' WHERE ID='" + id + "'");
        main.getMySQL().update("UPDATE Levels SET TotalXP='" + totalXp + "' WHERE ID='" + id + "'");
    }

    public void updateRoles(long id, int level) {
        if(level == 1) {
            Role neuling = main.guild.getRoleById("477893135662317578");
            main.guild.addRoleToMember(id, neuling).queue();
        } else if(level == 5) {
            Role lehrling = main.guild.getRoleById("477893135960244242");
            Role neuling = main.guild.getRoleById("477893135662317578");
            main.guild.addRoleToMember(id, lehrling).queue();
            main.guild.removeRoleFromMember(id, neuling).queue();
        } else if(level == 10) {
            Role gamer = main.guild.getRoleById("477893137067409430");
            Role lehrling = main.guild.getRoleById("477893135960244242");
            main.guild.addRoleToMember(id, gamer).queue();
            main.guild.removeRoleFromMember(id, lehrling).queue();
        } else if(level == 15) {
            Role megaGamer = main.guild.getRoleById("477893136832790552");
            Role gamer = main.guild.getRoleById("477893137067409430");
            main.guild.addRoleToMember(id, megaGamer).queue();
            main.guild.removeRoleFromMember(id, gamer).queue();
        } else if(level == 20) {
            Role suchti = main.guild.getRoleById("477893138107858951");
            Role megaGamer = main.guild.getRoleById("477893136832790552");
            main.guild.addRoleToMember(id, suchti).queue();
            main.guild.removeRoleFromMember(id, megaGamer).queue();
        } else if(level == 30) {
            Role megaSuchti = main.guild.getRoleById("477893138497798146");
            Role suchti = main.guild.getRoleById("477893138107858951");
            main.guild.addRoleToMember(id, megaSuchti).queue();
            main.guild.removeRoleFromMember(id, suchti).queue();
        } else if(level == 40) {
            Role zockerGott = main.guild.getRoleById("477893139000983574");
            Role megaSuchti = main.guild.getRoleById("477893138497798146");
            main.guild.addRoleToMember(id, zockerGott).queue();
            main.guild.removeRoleFromMember(id, megaSuchti).queue();
        }
    }

    public int getRank(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Levels ORDER BY TotalXP DESC");
        try {
            int i = 1;
            while (rs.next()) {
                if(rs.getLong("ID") != id) {
                    i++;
                } else {
                    break;
                }
            }
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean testForLevelUp(long id) {
        long xp = getXp(id);
        if(xp >= (5 * (Math.pow(getLevel(id), 2)) + 50 * getLevel(id) + 100)) {
            return true;
        }
        return false;
    }

    public void levelUp(long id) {
        main.getMySQL().update("UPDATE Levels SET Level='" + (getLevel(id) + 1) + "' WHERE ID='" + id + "'");
        main.getMySQL().update("UPDATE Levels SET XP='0' WHERE ID='" + id + "'");
    }

    public long getXpForLvlUp(long id) {
        return (long)(5 * (Math.pow(getLevel(id), 2)) + 50 * getLevel(id) + 100);
    }

    public long getXp(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Levels WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                return rs.getLong("XP");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long getTotalXp(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Levels WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                return rs.getLong("TotalXP");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long getLevel(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Levels WHERE ID='" + id + "'");
        try {
            if(rs.next()) {
                return rs.getLong("Level");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean existsMember(long id) {
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Levels WHERE ID='" + id + "'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void registerMember(long id) {
        main.getMySQL().update("INSERT INTO Levels(ID, TotalXP, XP, Level) VALUES ('" + id + "', '0', '0', '0')");
    }
}
