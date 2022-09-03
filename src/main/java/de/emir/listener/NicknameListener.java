package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class NicknameListener extends ListenerAdapter {

    Main main;

    Role bot;
    Role owner;
    Role coOwner;
    Role admin;
    Role technic;
    Role srMod;
    Role srCon;
    Role mod;
    Role con;
    Role sup;
    Role tsup;
    Role designer;
    Role tcteam;
    Role booster;
    Role partner;
    Role yt;
    Role twitch;
    Role miniyt;
    Role friend;
    Role teamfriend;
    Role tipico;
    Role hero;
    Role ultra;
    Role muted;

    boolean done = false;

    public NicknameListener(Main main) {
        this.main = main;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.isFromType(ChannelType.TEXT)) {

            if(event.getMessage().getContentDisplay().equalsIgnoreCase("Selam Aleyküm")) {
                event.getTextChannel().sendMessage("Aleyküm Selam").queue();
            }
            if(event.getMessage().getContentDisplay().equalsIgnoreCase("Selam Aleykum")) {
                event.getTextChannel().sendMessage("Aleykum Selam").queue();
            }
            if (event.getMessage().getContentDisplay().equalsIgnoreCase("Welche Gang ist die krasseste Gang?")){
                event.getTextChannel().sendMessage("EELYP ist die krasseste Gang amk").queue();
            }
            if (event.getMessage().getContentDisplay().equalsIgnoreCase("Wer ist der krasseste Gangster?")){
                event.getTextChannel().sendMessage("Oximbillah, es ist Pusat. Der ist sogar Augsburg Nummer 1!").queue();
            }
            if (event.getMessage().getContentDisplay().equalsIgnoreCase("Wie mache ich schnell Geld?")){
                event.getTextChannel().sendMessage("Geh husteln und mach deine Träume war bruder").queue();
            }
            if (event.getMessage().getContentDisplay().equalsIgnoreCase("Wer hat den GamerSucht programmiert?")){
                event.getTextChannel().sendMessage("Mich hat EmirCGN entwickelt :D").queue();
            }
            if (event.getMessage().getContentDisplay().equalsIgnoreCase("Könnte ich Entmutet werden?")){
                event.getTextChannel().sendMessage("Du kannst hier in den Chat einen Ausführlichen EA/Entbannungsantrag schreiben weshalb wir dir dein mute aufheben/verkürzen sollten.").queue();
            }
            if (event.getMessage().getContentDisplay().equalsIgnoreCase("Wieso kommt keiner in den Support, ich warte schon seit 10 Minuten?")){
                event.getTextChannel().sendMessage("Wenn ein Supporter oder Moderator Zeit hat, wird er dich Supporten, falls dich niemand Supportet hat, kannst du gerne ein Ticket erstellen in #support-chat.").queue();
            }
            if (event.getMessage().getContentDisplay().equalsIgnoreCase("kann ich mich als Owner bewerben?")){
                event.getTextChannel().sendMessage("Nein, du kannst dich momentan nur als Supporter bewerben und musst dich hocharbeiten. Du findest in #bewerbungs-system weitere Informationen.").queue();
            }
            if (event.getMessage().getContentDisplay().equalsIgnoreCase("Wie kann man Ränge bekommen?")){
                event.getTextChannel().sendMessage("Du kannst Ränge bekommen, in dem du dir in #tipico die Ränge kaufst, oder nimmst an den Gewinnspielen Teil.").queue();
            }
            if (event.getMessage().getContentDisplay().equalsIgnoreCase("Wie lange dauert es das man eine Antwort erhält wegen seiner Bewerbung?")){
                event.getTextChannel().sendMessage("Es kommt drauf an wie viel Zeit die Leitung hat, aber die sollten dir innerhalb 1 Woche antworten per Discord.").queue();
            }
            if (event.getMessage().getContentDisplay().equalsIgnoreCase("Wie funktioniert das Casino-System?")){
                event.getTextChannel().sendMessage("Du kannst ganz einfach in #⎾\uD83C\uDFB0⏌-befehle-tipico alles nachlesen. Falls du fragen hast wie man etwas kauft etc., kannst du die **!shop** eingeben da wird alles gezeigt was du dir Kaufen kannst.").queue();
            }
            if(event.getMessage().getContentDisplay().equalsIgnoreCase("Wie kann ich mich bewerben?")) {
                event.getTextChannel().sendMessage("Du kannst dich nur während einer Bewerbungsphase bewerben, in dem Channel #╠-bewerbungs-system wird dann auch nochmal alles erklärt").queue();
            }
            if(event.getMessage().getContentDisplay().equalsIgnoreCase("Wer ist der süßeste?")){
                event.getTextChannel().sendMessage("Sag was du willst, aber es ist einfach Yigit der süße").queue();
            }
            if(event.getMessage().getContentDisplay().equalsIgnoreCase("sucuk mit ei")){
                event.getTextChannel().sendMessage("https://tenor.com/view/cook-breakfast-eggs-meat-gif-7205343").queue();
            }
            if(event.getMessage().getContentDisplay().equalsIgnoreCase("GamerSucht")){
                event.getTextChannel().sendMessage("https://discord.gg/XRrFkdfma7").queue();
            }



            if(main.guild != null && !done) {
                bot = event.getGuild().getRoleById("477893098014375936");
                owner = event.getGuild().getRoleById("477893096756215829");
                admin = event.getGuild().getRoleById("477893104335323146");
                srCon = event.getGuild().getRoleById("870045319612735550");
                srMod = event.getGuild().getRoleById("581481942726344705");
                con = event.getGuild().getRoleById("870046872264388669");
                mod = event.getGuild().getRoleById("477893113113870346");
                sup = event.getGuild().getRoleById("574972907378311168");
                tsup = event.getGuild().getRoleById("758348227048243200");
                designer = event.getGuild().getRoleById("477893116204941312");
                tcteam = event.getGuild().getRoleById("868249341054304287");
                booster = event.getGuild().getRoleById("622386116800151573");
                partner = event.getGuild().getRoleById("477893124644012034");
                yt = event.getGuild().getRoleById("477893122634940431");
                twitch = event.getGuild().getRoleById("477893126485180427");
                miniyt = event.getGuild().getRoleById("477893123343908873");
                friend = event.getGuild().getRoleById("776589854703878144");
                tipico = event.getGuild().getRoleById("749221161870557255");
                hero = event.getGuild().getRoleById("477893128401977366");
                ultra = event.getGuild().getRoleById("477893129777840148");
                muted = event.getGuild().getRoleById("640149433325060116");
                done = true;
            }
            Member m = event.getMember();
            if(m == null) return;
            if(m.getNickname() != null) {
                if (m.getRoles().contains(bot)) {
                    if(!m.getNickname().startsWith("Bot | ")) {
                        modifyNickname(event.getGuild(), m, "Bot | ");
                    }
                    return;
                }
                if (m.getRoles().contains(owner)) {
                    if(!m.getNickname().startsWith("Owner | ")) {
                        modifyNickname(event.getGuild(), m, "Owner | ");
                    }
                    return;
                }
                if (m.getRoles().contains(admin)){
                    if(!m.getNickname().startsWith("Admin | ")) {
                        modifyNickname(event.getGuild(), m, "Admin | ");
                    }
                    return;
                }
                if (m.getRoles().contains(srCon)) {
                    if(!m.getNickname().startsWith("SrContent | ")) {
                        modifyNickname(event.getGuild(), m, "SrContent | ");
                    }
                    return;
                }
                if (m.getRoles().contains(srMod)){
                    if(!m.getNickname().startsWith("SrMod | ")) {
                        modifyNickname(event.getGuild(), m, "SrMod | ");
                    }
                    return;
                }
                if (m.getRoles().contains(con)){
                    if(!m.getNickname().startsWith("Content | ")) {
                        modifyNickname(event.getGuild(), m, "Content | ");
                    }
                    return;
                }
                if (m.getRoles().contains(mod)){
                    if(!m.getNickname().startsWith("Mod | ")) {
                        modifyNickname(event.getGuild(), m, "Mod | ");
                    }
                    return;
                }
                if (m.getRoles().contains(sup)){
                    if(!m.getNickname().startsWith("Sup | ")) {
                        modifyNickname(event.getGuild(), m, "Sup | ");
                    }
                    return;
                }
                if (m.getRoles().contains(tsup)){
                    if(!m.getNickname().startsWith("T-Sup | ")) {
                        modifyNickname(event.getGuild(), m, "T-Sup | ");
                    }
                    return;
                }
                if (m.getRoles().contains(designer)){
                    if(!m.getNickname().startsWith("Designer | ")) {
                        modifyNickname(event.getGuild(), m, "Designer | ");
                    }
                    return;
                }
                if (m.getRoles().contains(tcteam)){
                    if(!m.getNickname().startsWith("Tipico-Team | ")) {
                        modifyNickname(event.getGuild(), m, "Tipico-Team | ");
                    }
                    return;
                }
                if (m.getRoles().contains(booster)){
                    if (!m.getNickname().startsWith("Booster | ")){
                        modifyNickname(event.getGuild(), m, "Booster | ");
                    }
                }
                if (m.getRoles().contains(partner)){
                    if(!m.getNickname().startsWith("Partner | ")) {
                        modifyNickname(event.getGuild(), m, "Partner | ");
                    }
                    return;
                }
                if (m.getRoles().contains(yt)){
                    if(!m.getNickname().startsWith("YouTube | ")) {
                        modifyNickname(event.getGuild(), m, "YouTube | ");
                    }
                    return;
                }
                if (m.getRoles().contains(twitch)){
                    if(!m.getNickname().startsWith("Twitch | ")) {
                        modifyNickname(event.getGuild(), m, "Twitch | ");
                    }
                    return;
                }
                if (m.getRoles().contains(miniyt)){
                    if(!m.getNickname().startsWith("Mini-YT | ")) {
                        modifyNickname(event.getGuild(), m, "Mini-YT | ");
                    }
                    return;
                }
                if (m.getRoles().contains(friend)){
                    if(!m.getNickname().startsWith("Freund | ")) {
                        modifyNickname(event.getGuild(), m, "Freund | ");
                    }
                    return;
                }
                if (m.getRoles().contains(tipico)){
                    if(!m.getNickname().startsWith("Tipico | ")) {
                        modifyNickname(event.getGuild(), m, "Tipico | ");
                    }
                    return;
                }
                if (m.getRoles().contains(hero)) {
                    if(!m.getNickname().startsWith("Hero | ")) {
                        modifyNickname(event.getGuild(), m, "Hero | ");
                    }
                    return;
                }
                if (m.getRoles().contains(ultra)) {
                    if(!m.getNickname().startsWith("Ultra | ")) {
                        modifyNickname(event.getGuild(), m, "Ultra | ");
                    }
                    return;
                }
                if (m.getRoles().contains(muted)){
                    if(!m.getNickname().startsWith("Muted | ")) {
                        modifyNickname(event.getGuild(), m, "Muted | ");
                    }
                    return;
                }
                modifyNickname(event.getGuild(), m, "Mitglied | ");
            } else {
                if (m.getRoles().contains(bot)) {
                    modifyNickname(event.getGuild(), m, "Bot | ");
                    return;
                }
                if (m.getRoles().contains(owner)) {
                    modifyNickname(event.getGuild(), m, "Owner | ");
                    return;
                }
                if (m.getRoles().contains(coOwner)) {
                    modifyNickname(event.getGuild(), m, "CoOwner | ");
                    return;
                }
                if (m.getRoles().contains(admin)) {
                    modifyNickname(event.getGuild(), m, "Admin | ");
                    return;
                }
                if (m.getRoles().contains(srCon)) {
                    modifyNickname(event.getGuild(), m, "SrContent | ");
                    return;
                }
                if (m.getRoles().contains(srMod)) {
                    modifyNickname(event.getGuild(), m, "SrMod | ");
                    return;
                }
                if (m.getRoles().contains(mod)) {
                    modifyNickname(event.getGuild(), m, "Mod | ");
                    return;
                }
                if (m.getRoles().contains(con)) {
                    modifyNickname(event.getGuild(), m, "Content | ");
                    return;
                }
                if (m.getRoles().contains(sup)) {
                    modifyNickname(event.getGuild(), m, "Sup | ");
                    return;
                }
                if (m.getRoles().contains(tsup)) {
                    modifyNickname(event.getGuild(), m, "T-Sup | ");
                    return;
                }
                if (m.getRoles().contains(designer)) {
                    modifyNickname(event.getGuild(), m, "Designer | ");
                    return;
                }
                if (m.getRoles().contains(tcteam)) {
                    modifyNickname(event.getGuild(), m, "Tipico-Team | ");
                    return;
                }
                if (m.getRoles().contains(partner)) {
                    modifyNickname(event.getGuild(), m, "Booster | ");
                    return;
                }
                if (m.getRoles().contains(partner)) {
                    modifyNickname(event.getGuild(), m, "Partner | ");
                    return;
                }
                if (m.getRoles().contains(yt)) {
                    modifyNickname(event.getGuild(), m, "YouTube | ");
                    return;
                }
                if (m.getRoles().contains(twitch)) {
                    modifyNickname(event.getGuild(), m, "Twitch | ");
                    return;
                }
                if (m.getRoles().contains(miniyt)) {
                    modifyNickname(event.getGuild(), m, "Mini-YT | ");
                    return;
                }
                if (m.getRoles().contains(friend)){
                    modifyNickname(event.getGuild(), m, "Freund | ");
                    return;
                }
                if (m.getRoles().contains(tipico)) {
                    modifyNickname(event.getGuild(), m, "Tipico | ");
                    return;
                }
                if (m.getRoles().contains(hero)) {
                    modifyNickname(event.getGuild(), m, "Hero | ");
                    return;
                }
                if (m.getRoles().contains(ultra)) {
                    modifyNickname(event.getGuild(), m, "Ultra | ");
                    return;
                }
                if (m.getRoles().contains(muted)) {
                    modifyNickname(event.getGuild(), m, "Muted | ");
                    return;
                }
                modifyNickname(event.getGuild(), m, "Mitglied | ");
            }

        }
    }

    private void modifyNickname(Guild g, Member m, String prefix) {
        String oldNick;
        if(m.getNickname() != null) {
            oldNick = getCleanNick(m);
        } else {
            oldNick = m.getEffectiveName();
        }

        String newNick = prefix + oldNick;
        g.modifyNickname(m, newNick).queue();
    }

    private String getCleanNick(Member m) {
        String nick = m.getNickname();
        return nick.replace("Owner | ", "").replace("CoOwner | ", "").replace("Admin | ", "").replace("Techniker | ", "").replace("SrMod | ", "").replace("SrContent | ", "")
                .replace("Mod | ", "").replace("Content | ", "").replace("Sup | ", "").replace("T-Sup | ", "").replace("Designer | ", "").replace("Tipico Team | ", "").replace("Partner | ", "").replace("YouTube | ", "")
                .replace("Twitch | ", "").replace("Mini-YT | ", "").replace("Freund | ", "").replace("Tipico | ", "").replace("Booster | ", "").replace("Hero | ", "")
                .replace("Ultra | ", "").replace("Muted | ", "").replace("Mitglied | ", "").replace("Bot | ", "");
    }
}
