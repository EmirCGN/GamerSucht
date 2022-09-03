package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

public class RegisterAllRolesCommand implements Command {

    Main main;

    public RegisterAllRolesCommand(Main main) {
        this.main = main;
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        if(!m.hasPermission(Permission.ADMINISTRATOR)) return;
        for(Member m1 : main.guild.getMembers()) {
            for(Role r : m1.getRoles()) {
                if(r.getId().equalsIgnoreCase("421044883114164227")) continue;
                main.getProfileManager().addRole(m1.getIdLong(), r.getIdLong());
            }
        }
    }
}