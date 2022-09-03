package main.java.de.emir.manager;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class BirthdayTimer {

    Main main;

    public BirthdayTimer(Main main) {
        this.main = main;
    }

    Timer t = new Timer();

    public void start() {
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if(main.guild == null) return;
                String time = new SimpleDateFormat("HH.mm").format(System.currentTimeMillis());
                if(time.equalsIgnoreCase("00.01")) {
                    main.getBirthdayManager().removeWrongBirthdayRoles();
                    return;
                }
                if(!time.equalsIgnoreCase("00.00")) return;
                String completeTime = new SimpleDateFormat("dd.MM.").format(System.currentTimeMillis());
                if(!main.getBirthdayManager().existsBirthday(completeTime)) return;
                for(int i = 0; i < main.getBirthdayManager().todayBirthdays(completeTime).size(); i++) {
                    long id = main.getBirthdayManager().todayBirthdays(completeTime).get(i);
                    User user = main.guild.getMemberById(id).getUser();
                    if(main.guild.getMember(user) == null) {
                        main.getBirthdayManager().removeBirthday(id);
                        return;
                    }
                    int birthday = main.getBirthdayManager().getAge(id);
                    user.getJDA().openPrivateChannelById(id)
                            .flatMap(channel -> channel.sendMessage(main.getMessage("Happy Birthday!", Color.CYAN, "Hey!\n\nWir wünschen Dir alles Gute zu Deinem " + birthday + ". Geburtstag!\nZu diesem besonderen Tag erhälst du eine Spezial-Rolle auf unserem Discord-Server!\n\nLiebe Grüße,\ndie GamerSucht-Leitung").build()))
                            .queue();
                    main.guild.addRoleToMember(main.guild.getMember(user), main.guild.getRoleById("576168145522393088")).queue();
                    main.guild.getTextChannelById("477893246949916682").sendMessage(main.getMessage("Geburtstag!", Color.CYAN, "<@" + id + "> wird heute " + birthday + " Jahre alt! Herzlichen Glückwunsch!").build()).queue();
                }
            }
        }, 0, 60000);
    }
}