package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.*;

public class LevelListener extends ListenerAdapter {

    Main main;
    HashMap<Long, Long> waiting = new HashMap<>();

    public LevelListener(Main main) {
        this.main = main;
    }




    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.isFromType(ChannelType.TEXT)) {
            if(event.getMember() == null) return;
            if(event.getMember().getUser().isBot()) return;
            long id = event.getMember().getIdLong();
            if(!main.getLevelManager().existsMember(id)) {
                main.getLevelManager().registerMember(id);
            }
            if(!waiting.containsKey(id)) {
                main.getLevelManager().addRandomXP(id);
                waiting.put(id, System.currentTimeMillis() + 60000);

                if(main.getLevelManager().testForLevelUp(id)) {
                    main.getLevelManager().levelUp(id);
                    event.getTextChannel().sendMessage(main.getMessage("Levelup!", Color.CYAN, "Herzlichen Glückwunsch, du bist nun Level " + main.getLevelManager().getLevel(id) + "!\n" +
                            "Mach weiter so :fire::ok_hand::muscle:").build()).queue();
                    main.getLevelManager().updateRoles(id, (int)main.getLevelManager().getLevel(id));
                }
            } else {
                if(waiting.get(id) <= System.currentTimeMillis()) {
                    waiting.remove(id);
                    main.getLevelManager().addRandomXP(id);
                    waiting.put(id, System.currentTimeMillis() + 60000);

                    if(main.getLevelManager().testForLevelUp(id)) {
                        main.getLevelManager().levelUp(id);
                        event.getTextChannel().sendMessage(main.getMessage("Levelup!", Color.CYAN, "Herzlichen Glückwunsch, du bist nun Level " + main.getLevelManager().getLevel(id) + "!\n" +
                                "Mach weiter so :fire::ok_hand::muscle:").build()).queue();
                        main.getLevelManager().updateRoles(id, (int)main.getLevelManager().getLevel(id));
                    }
                }
            }
        }
    }
}