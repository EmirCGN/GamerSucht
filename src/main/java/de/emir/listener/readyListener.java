package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Date;

public class readyListener extends ListenerAdapter {

    Main main;

    public readyListener(Main main) {
        this.main = main;
    }

    @Override
    public void onReady(ReadyEvent event) {
        String out = "\n This bot is running on the following servers: \n";

        for (Guild g : event.getJDA().getGuilds()) {
            out += g.getName() + " (" + g.getId() + ")  \n";
        }

        System.out.println(out);

        STATICS.lastRestart = new Date();


    }
}
