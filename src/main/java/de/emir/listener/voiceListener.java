package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class voiceListener extends ListenerAdapter {

    Main main;
    public voiceListener(Main main) {
        this.main = main;
    }

    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        event.getGuild().getTextChannelsByName("╠-gs-log", true).get(0).sendMessage(main.getMessage(
                "ModLog", Color.CYAN, "Der User **" + event.getVoiceState().getMember().getUser().getName() + "** ist nun im **" + event.getChannelJoined().getName() + "** Channel").build()).queue();

    }

}
