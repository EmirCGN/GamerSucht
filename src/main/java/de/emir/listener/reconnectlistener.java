package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.events.ReconnectedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class reconnectlistener extends ListenerAdapter {

    Main main;

    public reconnectlistener(Main main) {
        this.main = main;
    }


    @Override
    public void onReconnect(ReconnectedEvent event) {

        System.out.println("[INFO] RECONNECT");

        STATICS.reconnectCount++;

    }
}