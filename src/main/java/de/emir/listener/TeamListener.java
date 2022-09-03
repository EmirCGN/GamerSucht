package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class TeamListener extends ListenerAdapter {

    Main main;

    public TeamListener(Main main) {
        this.main = main;
    }

    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        for(Role r : event.getRoles()) {
            if(r.getId().equalsIgnoreCase("477893181833216000")) {
                main.getProfileManager().setTeamSince(event.getMember().getIdLong(), System.currentTimeMillis());
            }
        }
    }
}
