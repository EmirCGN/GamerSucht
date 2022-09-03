package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class WarnListener extends ListenerAdapter {

    Main main;

    public WarnListener(Main main) {
        this.main = main;
    }

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent e) {
        if(main.confirming.contains(e.getAuthor()) && e.getMessage().getAttachments().size() != 0) {
            List<Message.Attachment> attachments = new ArrayList<>();

        }
    }
}
