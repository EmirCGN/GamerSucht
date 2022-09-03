package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class PingCommand implements Command {

    Main main;

    public PingCommand(Main main){
        this.main = main;
    }


    public String getTrigger() {
        return "ping";
    }

    public String[] getAliases() {
        return new String[]{};
    }

    public String getDescription() {
        return "Check the bots response time";
    }

    public String[] getUsage() {
        return new String[0];
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        long startTime = System.currentTimeMillis();
        channel.sendTyping().complete();
        long stopTime = System.currentTimeMillis();
        long ms = stopTime - startTime;
        channel.sendMessage(new EmbedBuilder()
                .setDescription("**Pong.**\nTime: " + String.valueOf(ms) + "ms")
                .setColor(Color.green).build()).queue();

    }
}

