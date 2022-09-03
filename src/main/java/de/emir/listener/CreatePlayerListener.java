package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CreatePlayerListener extends ListenerAdapter {

    Main main;

    public CreatePlayerListener(Main main) {
        this.main = main;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.isFromType(ChannelType.TEXT)) {
            main.applicationsChannel = event.getGuild().getTextChannelById("632922537256419349");
            main.guild = event.getGuild();
            if(event.getAuthor().isBot()) return;
            main.getProfileManager().createPlayer(event.getMember().getIdLong());
        }
    }
}