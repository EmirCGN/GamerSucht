package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class RoleRemoveListener extends ListenerAdapter {

    Main main;
    public static boolean isMuting = false;

    public RoleRemoveListener(Main main) {
        this.main = main;
    }

    @Override
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
        if(isMuting) return;
        for(Role r : event.getRoles()) {
            main.getProfileManager().removeRole(event.getMember().getIdLong(), r.getIdLong());
        }
    }
}
