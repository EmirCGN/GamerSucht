package main.java.de.emir.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;

import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

class userstatusListener extends ListenerAdapter{

    public void onUserUpdateOnlineStatusEvent(UserUpdateOnlineStatusEvent event) {
        OnlineStatus on = event.getGuild().getMember(event.getUser()).getOnlineStatus();
        if (event.getGuild().getOwner().getUser().getId().equals("421044883114164227")){
            event.getGuild().getTextChannelsByName("╠-gs-log", true).get(0).sendMessage(
                    new EmbedBuilder()
                            .setColor(Color.cyan)
                            .setDescription("User **" + event.getUser().getAsMention() + "** is now **" + on + "**!")
                            .build()
            ).queue();

        }else return;

    }
}