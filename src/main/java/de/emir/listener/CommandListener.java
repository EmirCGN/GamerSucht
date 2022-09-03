package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;



public class CommandListener extends ListenerAdapter {

    Main main;

    public CommandListener(Main main) {
        this.main = main;
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.isFromType(ChannelType.TEXT) && event.getMessage().getContentDisplay().startsWith("-")) {
            String[] args = event.getMessage().getContentDisplay().substring(1).split(" ");
            if (args.length > 0) {
                main.getCommandManager().perform(args[0], (Member) event.getMember(), event.getTextChannel(), event.getMessage());
            }
        }
    }
}
