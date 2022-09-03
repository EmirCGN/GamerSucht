package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import oshi.SystemInfo;

import java.awt.*;
import java.text.SimpleDateFormat;

public class CreditsCommand implements Command {

    Main main;

    public CreditsCommand(Main main) {
        this.main = main;
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        String[] args = msg.getContentDisplay().split(" ");
        channel.sendMessage(main.getMessage("Credits", Color.CYAN,"Informationen über mich:\n\nIch wurde von EmirCGN erschaffen und arbeite für den GamerSucht-Server.\nAktuelle Version: 1.0.0 \nUptime: " + new SystemInfo().getOperatingSystem().getSystemUptime()).build()).queue();
    }
}

