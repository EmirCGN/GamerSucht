package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class RoleAddListener extends ListenerAdapter {

    Main main;
    public static boolean isMuting = false;


    public RoleAddListener(Main main) {
        this.main = main;
    }

    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        if(isMuting) return;
        for(Role r : event.getRoles()) {
            if(!main.getProfileManager().getRoles(event.getMember().getIdLong()).contains(r) && !r.getId().equalsIgnoreCase("622386116800151573")) {
                main.getProfileManager().addRole(event.getMember().getIdLong(), r.getIdLong());
            }
        }
    }
}