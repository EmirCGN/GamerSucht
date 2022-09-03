package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LevelsCommand implements Command {

    Main main;

    public LevelsCommand(Main main) {
        this.main = main;
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        if(msg.getContentDisplay().split(" ").length != 1) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-levels` " + m.getAsMention() + "!").build()).queue();
            return;
        }
        ResultSet rs = main.getMySQL().getResult("SELECT * FROM Levels ORDER BY TotalXP DESC LIMIT 10");
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int i = 0;
            while(rs.next()) {
                i++;
                stringBuilder.append("**").append(i).append(". ").append("<@").append(rs.getLong("ID")).append(">").append("** (Lvl. ").append(rs.getInt("Level")).append(", ").append(rs.getLong("TotalXP")).append(" XP)\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        channel.sendMessage(main.getMessage("Top 10", Color.CYAN, stringBuilder.toString()).build()).queue();
    }
}
