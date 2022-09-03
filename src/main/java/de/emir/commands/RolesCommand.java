package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class RolesCommand implements Command {

    Main main;

    public RolesCommand(Main main) {
        this.main = main;
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        for(long l : main.getProfileManager().getRoles(m.getIdLong())) {
            channel.sendMessage(main.guild.getRoleById(l).getName()).queue();
        }
    }
}
