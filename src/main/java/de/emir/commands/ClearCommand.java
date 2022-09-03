package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClearCommand implements Command {

    Main main;
    public ClearCommand(Main main) {
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {

        if (m.hasPermission(channel, Permission.MESSAGE_MANAGE)){
            msg.delete().queue();;
            String[] args = msg.getContentDisplay().split(" ");

            if (args.length == 2){
                try {
                    int amount = Integer.parseInt(args[1]);
                    channel.purgeMessages(get(channel, amount));
                    channel.sendMessage(amount + " Nachrichten gelöscht.").complete().delete().queueAfter(3, TimeUnit.SECONDS);
                    return;
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Message> get(MessageChannel channel, int amount){
        List<Message> messages = new ArrayList<>();
        int i = amount + 1;

        for (Message msg : channel.getIterableHistory().cache(false)){
            if (!msg.isPinned()){
                messages.add(msg);
            }
            if (--i <= 0) break;
        }

        return messages;
    }
}
