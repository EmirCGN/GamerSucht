package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HeyListener extends ListenerAdapter {

    Main main;

    public HeyListener(Main main){
        this.main = main;
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().equalsIgnoreCase("hi") || e.getMessage().getContentRaw().equalsIgnoreCase("Hey") || e.getMessage().getContentRaw().equalsIgnoreCase("Hay") || e.getMessage().getContentRaw().equalsIgnoreCase("Moin") || e.getMessage().getContentRaw().equalsIgnoreCase("Moinsen") || e.getMessage().getContentRaw().equalsIgnoreCase("Moino") || e.getMessage().getContentRaw().equalsIgnoreCase("Hallo")) {
            e.getMessage().addReaction("👋").queue();
        }
    }
}
