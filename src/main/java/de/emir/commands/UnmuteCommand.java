package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class UnmuteCommand implements Command {

    Main main;

    public UnmuteCommand(Main main) {
        this.main = main;
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        if(!m.hasPermission(Permission.MESSAGE_MANAGE)) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Du hast keine Rechte für diesen Befehl " + m.getAsMention() + "!").build()).queue();
            return;
        }
        String[] args = msg.getContentDisplay().split(" ");
        if(args.length < 3) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende -unmute <@User/ID> <Grund> " + m.getAsMention() + "!").build()).queue();
            return;
        }
        if(msg.getMentionedMembers().size() == 0) {
            long id;
            try {
                id = Long.parseLong(args[1]);
            } catch (NumberFormatException e) {
                channel.sendMessage(main.getMessage("Fehler", Color.RED, "Dies ist keine gültige ID " + m.getAsMention() + "!").build()).queue();
                id = 0;
                return;
            }
            StringBuilder reason = new StringBuilder();
            for(int i = 2; i < args.length; i++) {
                reason.append(args[i]);
            }
            main.getMuteManager().unmute(main.guild.getMemberById(id));
            channel.sendMessage(main.getMessage("Erfolg", Color.CYAN, "Du hast den User <@" + id + "> erfolgreich für `" + reason.toString() + "` unmuted " + m.getAsMention() + "!").build()).queue();
            //ID ANGEGEBEN
        } else {
            //GETAGGT
        }
    }
}
