package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class RegisterAllCommand implements Command {

    Main main;

    public RegisterAllCommand(Main main) {
        this.main = main;
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        if(!m.hasPermission(Permission.ADMINISTRATOR)) return;
        for(Member m1 : main.guild.getMembers()) {
            if(!main.getProfileManager().existsPlayer(m1.getIdLong())) {
                main.getProfileManager().createPlayer(m1.getIdLong());
            }
        }
    }
}