package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class RankCommand implements Command {

    Main main;

    public RankCommand(Main main) {
        this.main = main;
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        long level;
        long xp;
        long neededXpForLvlUp;
        if(msg.getContentDisplay().split(" ").length == 1) {
            if (!main.getLevelManager().existsMember(m.getIdLong())) {
                level = 0;
                xp = 0;
                neededXpForLvlUp = 100;
            } else {
                level = main.getLevelManager().getLevel(m.getIdLong());
                xp = main.getLevelManager().getXp(m.getIdLong());
                neededXpForLvlUp = main.getLevelManager().getXpForLvlUp(m.getIdLong());
            }
            channel.sendMessage(main.getMessage("Level-Info von " + m.getUser().getAsTag(), Color.CYAN, "**Platz:** " + main.getLevelManager().getRank(m.getIdLong()) + "\n\n**Level:** " + level + "\n\n**XP:** " + xp + "/" + neededXpForLvlUp, m.getUser().getAvatarUrl()).build()).queue();
        } else {
            if(msg.getContentDisplay().split(" ").length == 2) {
                try {
                    long id = Long.parseLong(msg.getContentDisplay().split(" ")[1]);
                    if(channel.getGuild().getMemberById(id) == null) {
                        channel.sendMessage(main.getMessage("Fehler", Color.RED, "Ich konnte keinen User mit dieser ID finden!").build()).queue();
                        return;
                    }
                    if (!main.getLevelManager().existsMember(id)) {
                        level = 0;
                        xp = 0;
                        neededXpForLvlUp = 100;
                    } else {
                        level = main.getLevelManager().getLevel(id);
                        xp = main.getLevelManager().getXp(id);
                        neededXpForLvlUp = main.getLevelManager().getXpForLvlUp(id);
                    }
                    channel.sendMessage(main.getMessage("Level-Info von " + channel.getGuild().getMemberById(id).getUser().getAsTag(), Color.CYAN, "**Platz:** " + main.getLevelManager().getRank(id) + "\n\n**Level:** " + level + "\n\n**XP:** " + xp + "/" + neededXpForLvlUp, channel.getGuild().getMemberById(id).getUser().getAvatarUrl()).build()).queue();
                } catch (NumberFormatException e) {
                    if(msg.getMentionedMembers().size() != 1) {
                        channel.sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-rank <@User/ID>` " + m.getAsMention() + "!").build()).queue();
                        return;
                    }
                    Member m1 = msg.getMentionedMembers().get(0);
                    if (!main.getLevelManager().existsMember(m1.getIdLong())) {
                        level = 0;
                        xp = 0;
                        neededXpForLvlUp = 100;
                    } else {
                        level = main.getLevelManager().getLevel(m1.getIdLong());
                        xp = main.getLevelManager().getXp(m1.getIdLong());
                        neededXpForLvlUp = main.getLevelManager().getXpForLvlUp(m1.getIdLong());
                    }
                    channel.sendMessage(main.getMessage("Level-Info von " + m1.getUser().getAsTag(), Color.CYAN, "**Platz:** " + main.getLevelManager().getRank(m1.getIdLong()) + "\n\n**Level:** " + level + "\n\n**XP:** " + xp + "/" + neededXpForLvlUp, m1.getUser().getAvatarUrl()).build()).queue();
                }
            } else {
                if(msg.getMentionedMembers().size() != 1) {
                    channel.sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-rank <@User/ID>` " + m.getAsMention() + "!").build()).queue();
                    return;
                }
                Member m1 = msg.getMentionedMembers().get(0);
                if (!main.getLevelManager().existsMember(m1.getIdLong())) {
                    level = 0;
                    xp = 0;
                    neededXpForLvlUp = 100;
                } else {
                    level = main.getLevelManager().getLevel(m1.getIdLong());
                    xp = main.getLevelManager().getXp(m1.getIdLong());
                    neededXpForLvlUp = main.getLevelManager().getXpForLvlUp(m1.getIdLong());
                }
                channel.sendMessage(main.getMessage("Level-Info von " + m1.getUser().getAsTag(), Color.CYAN, "**Platz:** " + main.getLevelManager().getRank(m1.getIdLong()) + "\n\n**Level:** " + level + "\n\n**XP:** " + xp + "/" + neededXpForLvlUp, m1.getUser().getAvatarUrl()).build()).queue();
            }
        }
    }
}
