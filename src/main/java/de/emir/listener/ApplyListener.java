package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ApplyListener extends ListenerAdapter {

    Main main;

    public ApplyListener(Main main) {
        this.main = main;
    }

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        if (this.main.needToTellFunction.contains(event.getAuthor())) {
            if (event.getMessage().getContentDisplay().equalsIgnoreCase("Supporter")) {
                event.getChannel().sendMessage(this.main.getMessage("Bewerbung als Supporter", Color.CYAN, "Du möchtestDich also als Supporter bewerben. Sende mir nun Dein Bewerbungsschreiben zu! Bitte achte darauf, dass dein Bewerbungsschreiben in **eine Discord-Nachricht** passt, also dementsprechend **nicht mehr als 2.000** Zeichen hat.\n\nAlternativ kannst Du mir deine Bewerbung auch als `.txt`-Datei schicken. Sollten es mehr als **2.000** Wörter sein, kannst du deine Bewerbung auch als .txt Datei schicken!").build()).queue();
                this.main.functions.put(event.getAuthor(), "Supporter");
                this.main.needToTellFunction.remove(event.getAuthor());
                this.main.needToSendApplication.add(event.getAuthor());
            } else if (event.getMessage().getContentDisplay().equalsIgnoreCase("Designer")) {
                event.getChannel().sendMessage(this.main.getMessage("Bewerbung als Designer", Color.CYAN, "Du mmöchtest Dich also als Designer bewerben. Sende mir nun Dein Bewerbungsschreiben zu! Bitte achte darauf, dass dein Bewerbungsschreiben in **eine Discord-Nachricht** passt, also dementsprechend **nicht mehr als 2.000** Zeichen hat.\n\nAlternativ kannst Du mir deine Bewerbung auch als `.txt`-Datei schicken. Sollten es mehr als **2.000** Wörter sein, kannst du deine Bewerbung auch als .txt Datei schicken!").build()).queue();
                this.main.functions.put(event.getAuthor(), "Designer");
                this.main.needToTellFunction.remove(event.getAuthor());
                this.main.needToSendApplication.add(event.getAuthor());
            } else if (event.getMessage().getContentDisplay().equalsIgnoreCase("Content")) {
                event.getChannel().sendMessage(this.main.getMessage("Bewerbung als Content", Color.CYAN, "Du möchtestDich also als Content bewerben. Sende mir nun Dein Bewerbungsschreiben zu! Bitte achte darauf, dass dein Bewerbungsschreiben in **eine Discord-Nachricht** passt, also dementsprechend **nicht mehr als 2.000** Zeichen hat. Sollten es mehr als **2.000** Wörter sein, kannst du deine Bewerbung auch als .txt Datei schicken!").build()).queue();
                this.main.functions.put(event.getAuthor(), "Content");
                this.main.needToTellFunction.remove(event.getAuthor());
                this.main.needToSendApplication.add(event.getAuthor());
            } else {
                event.getChannel().sendMessage(this.main.getMessage("Fehler", Color.RED, "Bitte wähle einen gültigen Bereich!\nZur Auswahl stehen:\n- Supporter\n- Content\n- Designer").build()).queue();
                return;
            }
        } else if(main.needToSendApplication.contains(event.getAuthor())) {
            if(event.getMessage().getAttachments().isEmpty()) {
                String application = event.getMessage().getContentDisplay();
                String function = main.functions.get(event.getAuthor());
                main.applicationsChannel.sendMessage(main.getMessage("Bewerbung von " + event.getAuthor().getAsTag() + " als " + function, Color.CYAN, application).build()).complete();
                final int[] i = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i[0] == 0) {
                            long id = main.applicationsChannel.getLatestMessageIdLong();
                            main.applicationsChannel.addReactionById(id, "U+2705").queue();
                            main.applicationsChannel.addReactionById(id, "U+274C").queue();
                            main.getApplicationManager().addApplication(event.getAuthor(), id, application, function, event.getChannel().getIdLong());
                            this.cancel();
                        }
                        i[0]--;
                    }
                }, 0, 1000);

                event.getChannel().sendMessage(main.getMessage("Erfolg", Color.CYAN, "Deine Bewerbung wurde erfolgreich abgeschickt! Vielen Dank für Dein Interesse!").build()).queue();
                main.needToSendApplication.remove(event.getAuthor());
                main.functions.remove(event.getAuthor());
            } else {
                if(!event.getMessage().getAttachments().get(0).isImage() && !event.getMessage().getAttachments().get(0).isVideo()) {
                    if(!event.getMessage().getAttachments().get(0).getFileName().endsWith(".txt")) {
                        event.getChannel().sendMessage(main.getMessage("Fehler", Color.RED, "Dies ist keine `.txt`-Datei! Um Deine Bewerbung bearbeiten zu können, benötige ich eine `.txt`-Datei.").build()).queue();
                        return;
                    }
                    try {
                        File f = event.getMessage().getAttachments().get(0).downloadToFile("Applications//" + event.getAuthor().getId() + ".txt").get();
                        BufferedReader reader = new BufferedReader(new FileReader(f));
                        StringBuilder builder = new StringBuilder();
                        String currentLine = reader.readLine();
                        while (currentLine != null) {
                            builder.append(currentLine).append("\n");
                            currentLine = reader.readLine();
                        }
                        String application = builder.toString();
                        reader.close();
                        if(application.length() > 8000) {
                            event.getChannel().sendMessage(main.getMessage("Fehler", Color.RED, "Deine Bewerbung ist zu lang! Reduziere sie um mindestens " + (application.length() - 8000) + " Zeichen oder schicke es als .txt Datei!").build()).queue();
                            return;
                        }
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
                        String function = main.functions.get(event.getAuthor());
                        if(str1.split(" ").length != 0) {
                            main.applicationsChannel.sendMessage(main.getMessage("Bewerbung von " + event.getAuthor().getAsTag() + " als " + function, Color.CYAN, str1).build()).queue();
                        }
                        if(str2.split(" ").length != 0) {
                            main.applicationsChannel.sendMessage(main.getMessage(null, Color.CYAN, str2).build()).queue();
                        }
                        if(str3.split(" ").length != 0) {
                            main.applicationsChannel.sendMessage(main.getMessage(null, Color.CYAN, str3).build()).queue();
                        }
                        if(str4.split(" ").length != 0) {
                            main.applicationsChannel.sendMessage(main.getMessage(null, Color.CYAN, str4).build()).queue();
                        }
                        final int[] i = {1};
                        (new Timer()).schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if(i[0] == 0) {
                                    long id = main.applicationsChannel.getLatestMessageIdLong();
                                    main.applicationsChannel.addReactionById(id, "U+2705").queue();
                                    main.applicationsChannel.addReactionById(id, "U+274C").queue();
                                    main.getApplicationManager().addApplication(event.getAuthor(), id, application, function, event.getChannel().getIdLong());
                                    this.cancel();
                                }
                                i[0]--;
                            }
                        }, 0, 1000);

                        event.getChannel().sendMessage(main.getMessage("Erfolg", Color.CYAN, "Deine Bewerbung wurde erfolgreich abgeschickt! Vielen Dank für Dein Interesse!").build()).queue();
                        main.needToSendApplication.remove(event.getAuthor());
                        main.functions.remove(event.getAuthor());
                    } catch (InterruptedException | ExecutionException | IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    event.getChannel().sendMessage(main.getMessage("Fehler", Color.RED, "Dies ist keine `.txt`-Datei! Um Deine Bewerbung bearbeiten zu können, benötige ich eine `.txt`-Datei.").build()).queue();
                }
            }


        } else {
            if(event.getMessage().getContentDisplay().equalsIgnoreCase("-bewerben")) {
                if(!main.getApplicationManager().canApply(event.getAuthor())) {
                    long time = 1000;
                    long time2 = 60 * 60 * 24 * 30;
                    event.getChannel().sendMessage(main.getMessage("Fehler", Color.RED, "Du musst 30 Tage warten, bis du dich erneut bewerben kannst! Du kannst dich erneut bewerben am:\n" + new SimpleDateFormat("dd.MM.yyyy").format(main.getApplicationManager().getTimestamp(event.getAuthor()) + time * time2 + 7200000) + " um " + new SimpleDateFormat("HH:mm").format(main.getApplicationManager().getTimestamp(event.getAuthor()) + time * time2 + 7200000) + " Uhr").build()).queue();
                    return;
                }
                event.getChannel().sendMessage(main.getMessage("Bewerben", Color.CYAN, "Hey!\nDu möchtest Dich auf GamerSucht.net bewerben? Dann schreibe mir zunächst, für welchen Bereich Du Dich bewerben möchtest.\nZur Auswahl stehen:\n- Supporter\n- Content\n- Designer").build()).queue();
                main.needToTellFunction.add(event.getAuthor());
                final int[] i = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i[0] == 0) {
                            if(main.needToSendApplication.contains(event.getAuthor()) || main.needToTellFunction.contains(event.getAuthor())) {
                                event.getChannel().sendMessage(main.getMessage("Fehler", Color.RED, "Du hast zu lange gebraucht! Bitte starte den Bewerbungsvorgang erneut.").build()).queue();
                                main.needToSendApplication.remove(event.getAuthor());
                                main.needToTellFunction.remove(event.getAuthor());
                                main.functions.remove(event.getAuthor());
                            }
                        }
                        i[0]--;
                    }
                }, 0, 600000);
            }
        }

    }
}