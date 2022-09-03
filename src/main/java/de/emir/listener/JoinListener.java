package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinListener extends ListenerAdapter {

    Main main;

    public JoinListener(Main main){
        this.main = main;
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if(event.getUser().isBot() || event.getUser().isFake()) return;
        main.getProfileManager().createPlayer(event.getMember().getIdLong());
    }
}