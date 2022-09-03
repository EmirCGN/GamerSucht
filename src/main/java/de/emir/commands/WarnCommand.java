package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;


public class WarnCommand implements Command {

    Main main;

    public WarnCommand(Main main){
        this.main = main;
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        if(!m.getRoles().contains(channel.getGuild().getRoleById("477893117908090881"))) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Du hast keine Rechte für diesen Befehl " + m.getAsMention() + "!").build()).queue();
            return;
        }
        String[] args = msg.getContentDisplay().split(" ");
        if(args.length < 3) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-warn <@User/ID> <Grund>` " + m.getAsMention() + "!").build()).queue();
            return;
        }
        if(msg.getMentionedMembers().size() == 1) {
            return;
        }
        try {
            long id = Long.parseLong(args[1]);
            if(channel.getGuild().getMemberById(id) == null) {
                channel.sendMessage(main.getMessage("Fehler", Color.RED, "Ich kann keinen User mit dieser ID finden " + m.getAsMention() + "!").build()).queue();
                return;
            }
            Member m1 = channel.getGuild().getMemberById(id);
            StringBuilder reasonBuilder = new StringBuilder();
            for(int i = 2; i < args.length; i++) {
                reasonBuilder.append(args[i]);
            }
            String reason = reasonBuilder.toString();
            main.getWarnManager().addWarn(m1.getIdLong(), reason, m.getIdLong());
            channel.sendMessage(main.getMessage("Fast geschafft", Color.CYAN, "Um den Warn abzuschließen, sende mir bitte einen Beweis-Screenshot per DM " + m.getAsMention() + "!").build()).queue();
        } catch (NumberFormatException e) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-warn <@User/ID> <Grund>` " + m.getAsMention() + "!").build()).queue();
            return;
        }
    }
}
