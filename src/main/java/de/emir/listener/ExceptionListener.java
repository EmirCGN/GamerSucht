package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class ExceptionListener extends ListenerAdapter {

    Main main;

    public ExceptionListener(Main main) {
        this.main = main;
    }

    @Override
    public void onException(ExceptionEvent event) {
        main.jda.getUserById("421044883114164227").openPrivateChannel().flatMap(channel -> channel.sendMessage(main
                                                                                                                       .getMessage(
                                                                                                                               "Exception",
                                                                                                                               Color.RED,
                                                                                                                               "Es wurde eine Exception geworfen!\n\n**Grund:** "
                                                                                                                                       + event
                                                                                                                                       .getCause()
                                                                                                                                       .toString()
                                                                                                                                       + "\n\n**Beschreibung:** "
                                                                                                                                       + event
                                                                                                                                       .getCause()
                                                                                                                                       .getMessage())
                                                                                                                       .build()))
                .queue();
    }
}
