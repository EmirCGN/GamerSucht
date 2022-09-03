package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import main.java.de.emir.mysql.MySQL;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.List;

public class ReactRolesCommand implements Command {
    Main main;

    public ReactRolesCommand(Main main) {
        this.main = main;
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        if(!m.getRoles().contains(channel.getGuild().getRoleById("605815851974656051"))) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Du hast keine Rechte für diesen Befehl " + m.getAsMention() + "!").build()).queue();
            return;
        }

        String[] args = msg.getContentDisplay().split(" ");

        List<TextChannel> channels = msg.getMentionedChannels();
        List<Role> roles = msg.getMentionedRoles();
        List<Emote> emotes = msg.getEmotes();

        if (!channels.isEmpty() && !roles.isEmpty()) {
            TextChannel tc = channels.get(0);
            Role role = roles.get(0);
            String messageIDString = args[2];

            try {
                long messageID = Long.parseLong(messageIDString);

                if(!emotes.isEmpty()) {
                    Emote emote = emotes.get(0);
                    tc.addReactionById(messageID, emote).queue();
                    MySQL.onUpdate("INSERT INTO reactroles(guildid, channelid, messageid, emote, rollenid) VALUES(" +
                            channel.getGuild().getIdLong() + ", " + tc.getIdLong() + ", " + messageID + ", '" + emote.getId() + "', " + role.getIdLong() + ")");
                }
                else {
                    String emote = args[3];
                    tc.addReactionById(messageID, emote).queue();
                    MySQL.onUpdate("INSERT INTO reactroles(guildid, channelid, messageid, emote, rollenid) VALUES(" +
                            channel.getGuild().getIdLong() + ", " + tc.getIdLong() + ", " + messageID + ", '" + emote + "', " + role.getIdLong() + ")");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        else {
            channel.sendMessage("Bitte benutze `-reactrole #channel messageID :emote: @Rolle`").queue();
        }
    }
}
