package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class ReactionListener extends ListenerAdapter {

    Main main;

    public ReactionListener(Main main) {
        this.main = main;
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if(event.getUser() == null) return;
        if(event.getUser().isBot()) return;
        if(main.getApplicationManager().isApplicationReaction(event.getMessageIdLong())) {
            if(event.getReactionEmote().getAsCodepoints().equalsIgnoreCase("U+2705")) {
                long id = main.getApplicationManager().getUserIDByMessageID(event.getMessageIdLong());
                User user = main.jda.getUserById(id);
                main.jda.getPrivateChannelById(main.getApplicationManager().getPrivateChannelID(id)).sendMessage(main.getMessage("Bewerbung angenommen!",
                        Color.CYAN, "Hey lieber Bewerber,\n\nvielen Dank für Dein Interesse an unserem Discord-Server." +
                                "\n\nDeine Bewerbung hat uns von Dir überzeugt und wir würden Dich gerne zu einem Gespräch einladen, " +
                                "bitte schreib <@" + event.getMember().getId() + "> dazu privat an, er wird alles weitere mit Dir klären.\n" +
                                "\n\nStatus Deiner Bewerbung ➤ Gespräch\n\nDu erhälst bei einer weiteren Statusänderung eine weitere Nachricht, in der dein Status aktualisiert wird." +
                                "\n\nLiebe Grüße und viel Spaß Dir weiterhin auf GamerSucht.net" +
                                "\nDie GamerSucht-Leitung").build()).queue();
                TextChannel archive = event.getGuild().getTextChannelById("632249332652834836");
                String function = main.getApplicationManager().getFunction(user.getIdLong());
                String application = main.getApplicationManager().getApplication(user.getIdLong());
                if(application.length() > 2000) {
                    EmbedBuilder msg1;
                    EmbedBuilder msg2;
                    EmbedBuilder msg3;
                    EmbedBuilder msg4;
                    String str1 = "";
                    String str2 = "";
                    String str3 = "";
                    String str4 = "";
                    String[] applicationSplit = application.split(" ");
                    int i1 = 0;
                    while(str1.length() < 2000 && i1 < applicationSplit.length) {
                        str1 = str1 + applicationSplit[i1] + " ";
                        applicationSplit[i1] = "";
                        i1++;
                    }
                    String application2 = Arrays.toString(applicationSplit);
                    String[] applicationSplit2 = application2.split(" ");
                    int i2 = 0;
                    while (str2.length() < 2000 && i2 < applicationSplit2.length) {
                        str2 = str2 + applicationSplit2[i2] + " ";
                        applicationSplit2[i2] = "";
                        i2++;
                    }
                    String application3 = Arrays.toString(applicationSplit2);
                    String[] applicationSplit3 = application3.split(" ");
                    int i3 = 0;
                    while (str3.length() < 2000 && i3 < applicationSplit3.length) {
                        str3 = str3 + applicationSplit3[i3] + " ";
                        applicationSplit3[i3] = "";
                        i3++;
                    }
                    String application4 = Arrays.toString(applicationSplit3);
                    String[] applicationSplit4 = application4.split(" ");
                    int i4 = 0;
                    while (str4.length() < 2000 && i4 < applicationSplit4.length) {
                        str4 = str4 + applicationSplit4[i4] + " ";
                        applicationSplit4[i4] = "";
                        i4++;
                    }
                    str1 = str1.replace("[", "").replace("]", "").replace(",", "");
                    str2 = str2.replace("[", "").replace("]", "").replace(",", "");
                    str3 = str3.replace("[", "").replace("]", "").replace(",", "");
                    str4 = str4.replace("[", "").replace("]", "").replace(",", "");
                    if(!str1.equalsIgnoreCase("")) {
                        archive.sendMessage(main.getMessage("Bewerbung von " + user.getAsTag() + " als " + function, Color.CYAN, str1).build()).queue();
                    }
                    if(!str2.equalsIgnoreCase("")) {
                        archive.sendMessage(main.getMessage(null, Color.CYAN, str2).build()).queue();
                    }
                    if(!str3.equalsIgnoreCase("")) {
                        archive.sendMessage(main.getMessage(null, Color.CYAN, str3).build()).queue();
                    }
                    if(!str4.equalsIgnoreCase("")) {
                        archive.sendMessage(main.getMessage(null, Color.CYAN, str4).build()).queue();
                    }
                } else {
                    archive.sendMessage(main.getMessage("Bewerbung von " + user.getAsTag() + " als " + function, Color.CYAN, application).build()).queue();
                }
                event.getTextChannel().deleteMessageById(event.getMessageId()).queue();
                event.getTextChannel().sendMessage(main.getMessage("Bewerbung angenommen", Color.CYAN, "Du hast die Bewerbung von " + user.getAsTag() + " erfolgreich angenommen " + event.getMember().getAsMention() + "!").build()).queue();
                main.getApplicationManager().removeApplication(user);
            } else if(event.getReactionEmote().getAsCodepoints().equalsIgnoreCase("U+274C")) {
                long id = main.getApplicationManager().getUserIDByMessageID(event.getMessageIdLong());
                User user = main.jda.getUserById(id);
                main.jda.getPrivateChannelById(main.getApplicationManager().getPrivateChannelID(id)).sendMessage(main.getMessage("Bewerbung abgelehnt!",
                        Color.CYAN, "Heyho Bewerber,\n\nvielen Dank für Dein Interesse, unser Serverteam zu unterstützen." +
                                "\n\nNachdem wir uns sorgfältig Gedanken über Deine Bewerbung gemacht haben, müssen wir Dir leider mitteilen, dass wir uns dazu entschieden haben, " +
                                "Dich nicht in unser Serverteam aufzunehmen.\nEine Absage ist nicht erfreulich, aber lasse Dich deshalb nicht entmutigen.\nDu kannst Dich jedoch in 30 Tagen erneut bewerben und mit etwas Glück doch noch ein Teil unseres Teams werden!\n" +
                                "\nWir wünschen Dir für Deine Zukunft alles Gute und viel Erfolg.\n\nViel Spaß noch auf GamerSucht.net\nDie GamerSucht-Leitung").build()).queue();
                event.getTextChannel().deleteMessageById(event.getMessageId()).queue();
                event.getTextChannel().sendMessage(main.getMessage("Bewerbung abgelehnt", Color.CYAN, "Du hast die Bewerbung von " + user.getAsTag() + " erfolgreich abgelehnt " + event.getMember().getAsMention() + "!").build()).queue();
            }
        }
    }
    //Heyho Bewerber,
    //
    //vielen Dank für Dein Interesse, unser Serverteam zu unterstützen.
    //
    //Nachdem wir uns sorgfältig Gedanken über Deine Bewerbung gemacht haben, müssen wir Dir leider mitteilen, dass wir uns dazu entschieden haben, Dich nicht in unser Serverteam aufzunehmen.
    //Eine Absage ist nicht erfreulich, aber lasse Dich deshalb nicht entmutigen.
    //Du kannst Dich jedoch in 30 Tagen erneut bewerben und mit etwas Glück doch noch ein Teil unseres Teams werden!
    //
    //Wir wünschen Dir für Deine Zukunft alles Gute und viel Erfolg.
    //
    //Viel Spaß noch auf GamerSucht
    //Die GamerSucht - Leitung
}
