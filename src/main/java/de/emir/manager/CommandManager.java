package main.java.de.emir.manager;

import main.java.de.emir.commands.*;
import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

    Main main;
    public ConcurrentHashMap<String, Command> commands;

    public CommandManager(Main main) {
        this.main = main;
        this.commands = new ConcurrentHashMap<>();
        this.commands.put("say", new SayCommand(main));
        this.commands.put("profile", new ProfileCommand(main));
        this.commands.put("credits", new CreditsCommand(main));
        this.commands.put("updateall", new UpdateallCommand(main));
        this.commands.put("test", new TestCommand(main));
        this.commands.put("updatebirthdays", new UpdateBirthdaysCommand(main));
        this.commands.put("mute", new MuteCommand(main));
        this.commands.put("unmute", new UnmuteCommand(main));
        this.commands.put("registerall", new RegisterAllCommand(main));
        this.commands.put("registerallroles", new RegisterAllRolesCommand(main));
        this.commands.put("roles", new RolesCommand(main));
        this.commands.put("rank", new RankCommand(main));
        this.commands.put("sendprivatemessage", new SendPrivateMessageCommand(main));
        this.commands.put("levels", new LevelsCommand(main));
        this.commands.put("warn", new WarnCommand(main));
        this.commands.put("reactrole", new ReactRolesCommand(main));
        this.commands.put("clear", new ClearCommand(main));
        this.commands.put("ping", new PingCommand(main));
    }

    public boolean perform(String cmd, Member m, TextChannel channel, Message msg) {

        Command command;
        if((command = this.commands.get(cmd.toLowerCase())) != null) {
            command.performCommand((net.dv8tion.jda.api.entities.Member) m, channel, msg);
            return true;
        }
        else return false;
    }
}