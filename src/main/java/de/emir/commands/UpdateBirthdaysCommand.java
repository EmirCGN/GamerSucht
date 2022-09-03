package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class UpdateBirthdaysCommand implements Command {

    Main main;

    public UpdateBirthdaysCommand(Main main) {
        this.main = main;
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        if(!m.hasPermission(Permission.ADMINISTRATOR)) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Du hast keine Rechte für diesen Befehl " + m.getAsMention() + "!").build()).queue();
            return;
        }
        channel.sendMessage(main.getMessage("Updaten...", Color.CYAN, "Alle Geburtstage werden geupdated.").build()).queue();
        channel.getGuild().getTextChannelById("705900451010183269").editMessageById("960946472545845298", "**Januar**").queue();
        channel.getGuild().getTextChannelById("705900451010183269").editMessageById("960946563037921311", "**Februar**").queue();
        channel.getGuild().getTextChannelById("705900451010183269").editMessageById("960947609705512990", "**März**").queue();
        channel.getGuild().getTextChannelById("705900451010183269").editMessageById("960947666584481862", "**April**").queue();
        channel.getGuild().getTextChannelById("705900451010183269").editMessageById("960947689875439616", "**Mai**").queue();
        channel.getGuild().getTextChannelById("705900451010183269").editMessageById("960947729842962554", "**Juni**").queue();
        channel.getGuild().getTextChannelById("705900451010183269").editMessageById("960947761375768646", "**Juli**").queue();
        channel.getGuild().getTextChannelById("705900451010183269").editMessageById("960947787636310157", "**August**").queue();
        channel.getGuild().getTextChannelById("705900451010183269").editMessageById("960947831139618846", "**September**").queue();
        channel.getGuild().getTextChannelById("705900451010183269").editMessageById("960947866216566865", "**Oktober**").queue();
        channel.getGuild().getTextChannelById("705900451010183269").editMessageById("960947884252086313", "**November**").queue();
        channel.getGuild().getTextChannelById("705900451010183269").editMessageById("960947913465397258", "**Dezember**").queue();
        final int[] i = {1};
        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                if(i[0] != 0) {
                    i[0]--;
                    return;
                }

                ResultSet rs = main.getMySQL().getResult("SELECT * FROM Birthdays ORDER BY Birthday ASC");
                try {
                    while(rs.next()) {
                        if(main.guild.getMemberById(rs.getLong("ID")) == null) {
                            main.getBirthdayManager().removeBirthday(rs.getLong("ID"));
                            continue;
                        }
                        Member m = main.guild.getMemberById(rs.getLong("ID"));
                        String birthday = rs.getString("Birthday");
                        String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday)).complete().getContentRaw();
                        String newMessage = oldMessage + "\n<@" + m.getId() + "> *" + birthday.substring(0, 6) + "*";
                        main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday), newMessage).complete();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                this.cancel();
            }
        }, 0, 10000);
        final int[] i1 = {1};
        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                if(i1[0] != 0) {
                    i1[0]--;
                    return;
                }
                channel.sendMessage(main.getMessage("Updaten...", Color.CYAN, "Alle Geburtstage wurden geupdated!").build()).queue();
                this.cancel();
            }
        }, 0, 15000);
    }
}
