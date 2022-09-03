package main.java.de.emir.listener;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.de.emir.mysql.MySQL;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionRoleListener {

    public void onMessageReactionAdd(MessageReactionAddEvent event) {

        if(event.getChannelType() == ChannelType.TEXT) {
            if(!event.getUser().isBot()) {
                long guildid = event.getGuild().getIdLong();
                long channelid = event.getChannel().getIdLong();
                long messageid = event.getMessageIdLong();
                String emote = "";

                if(event.getReactionEmote().isEmoji()) {
                    emote = event.getReactionEmote().getEmoji();
                }
                else {
                    emote = event.getReactionEmote().getId();
                }

                ResultSet set = MySQL.onQuery("SELECT rollenid FROM reactroles WHERE guildid = " + guildid + " AND channelid = " + channelid + " AND messageid = " + messageid + " AND emote = '" + emote + "'");

                try {
                    if(set.next()) {
                        long rollenid = set.getLong("rollenid");

                        Guild guild = event.getGuild();

                        guild.addRoleToMember(event.getMember(), guild.getRoleById(rollenid)).queue();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {

        if(event.getChannelType() == ChannelType.TEXT) {
            if(!event.getUser().isBot()) {
                long guildid = event.getGuild().getIdLong();
                long channelid = event.getChannel().getIdLong();
                long messageid = event.getMessageIdLong();
                String emote = "";

                if(event.getReactionEmote().isEmoji()) {
                    emote = event.getReactionEmote().getEmoji();
                }
                else {
                    emote = event.getReactionEmote().getId();
                }

                ResultSet set = MySQL.onQuery("SELECT rollenid FROM reactroles WHERE guildid = " + guildid + " AND channelid = " + channelid + " AND messageid = " + messageid + " AND emote = '" + emote + "'");

                try {
                    if(set.next()) {
                        long rollenid = set.getLong("rollenid");

                        Guild guild = event.getGuild();

                        guild.removeRoleFromMember(event.getMember(), guild.getRoleById(rollenid)).queue();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
