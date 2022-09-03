package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class SendPrivateMessageCommand implements Command {

    Main main;

    public SendPrivateMessageCommand(Main main) {
        this.main = main;
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        if(!m.hasPermission(Permission.ADMINISTRATOR)) return;
        String[] args = msg.getContentDisplay().split(" ");
        if(args.length < 5) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-sendprivatemessage <ID> <Überschrift | Nachricht>` " + m.getAsMention() + "!").build()).queue();
            return;
        }
        long id;
        try {
            id = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-sendprivatemessage <ID> <Überschrift | Nachricht>` " + m.getAsMention() + "!").build()).queue();
            return;
        }
        if(channel.getGuild().getMemberById(id) == null) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Auf dem Server gibt es keinen User mit dieser ID " + m.getAsMention() + "!").build()).queue();
            return;
        }
        StringBuilder title = new StringBuilder();
        StringBuilder message = new StringBuilder();
        int i1 = 0;
        for(int i = 2; i < args.length; i++) {
            if(!args[i].equalsIgnoreCase("|")) title.append(args[i]).append(" ");
            else break;
            if(i == args.length - 1) {
                channel.sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-sendprivatemessage <ID> <Überschrift | Nachricht>` " + m.getAsMention() + "!").build()).queue();
                return;
            }
            else i1 = i;
        }
        for(int i = i1 + 2; i < args.length; i++) {
            message.append(args[i]).append(" ");
        }
        channel.sendMessage(String.valueOf(id)).queue();
        main.jda.getUserById(id).openPrivateChannel()
                .flatMap(channel1 -> channel1.sendMessage(main.getMessage(title.toString(), Color.CYAN, message.toString()).build())).queue();

    }
}
