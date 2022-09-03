package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class SayCommand implements Command {
    Main main;

    //<#4836684>  // ++
    //<@645754>   // --
    //<&4385643908609>  // !!


    public SayCommand(Main main) {
        this.main = main;
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        if(!m.getRoles().contains(channel.getGuild().getRoleById("477893117908090881"))) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Du hast keine Rechte für diesen Befehl " + m.getAsMention() + "!").build()).queue();
            return;
        }
        String[] args = msg.getContentDisplay().split(" ");
        if(args.length < 3) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-say <Channel> <Überschrift | Nachricht>`, um eine neue Nachricht zu senden " + m.getAsMention() + "!").build()).queue();
            return;
        }
        String channelname = args[1].replace("#", "");
        if(channel.getGuild().getTextChannelsByName(channelname, true).size() == 0) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Der Channel `#" + channelname + "` existiert nicht " + m.getAsMention() + "!").build()).queue();
            return;
        }
        String message = "";
        for(int i = 2; i < args.length; i++) {
            message = message + (args[i].replace("++", "#").replace("--", "@").replace("!!", "&"));
            message = message + (" ");
        }
        if(!message.contains("|")) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Bitte trenne die Überschrift und die Nachricht mit einem `|` " + m.getAsMention() + "!").build()).queue();
            return;
        }
        String[] messageSplit = message.split("\\|");
        channel.getGuild().getTextChannelsByName(channelname, true).get(0).sendMessage(main.getMessage(messageSplit[0], Color.CYAN, messageSplit[1]).build()).queue();
        channel.sendMessage(main.getMessage("Erfolg", Color.GREEN, "Die Nachricht wurde erfolgreich gesendet " + m.getAsMention() + "!").build()).queue();
    }


}