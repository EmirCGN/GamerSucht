package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

public class UpdateallCommand implements Command {

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
    Role partner;
    Role yt;
    Role twitch;
    Role miniyt;
    Role friend;
    Role tipico;
    Role hero;
    Role ultra;
    Role muted;

    public UpdateallCommand(Main main) {
        this.main = main;
    }

    @Override
    public void performCommand(Member m1, TextChannel channel, Message msg) {
        if(!m1.hasPermission(Permission.ADMINISTRATOR)) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Du hast keine Rechte für diesen Befehl " + m1.getAsMention() + "!").build()).queue();
            return;
        }
        bot = channel.getGuild().getRoleById("477893098014375936");
        owner = channel.getGuild().getRoleById("477893096756215829");
        coOwner = channel.getGuild().getRoleById("477893097465053185");
        admin = channel.getGuild().getRoleById("477893104335323146");
        technic = channel.getGuild().getRoleById("679744099355459626");
        srMod = channel.getGuild().getRoleById("581481942726344705");
        srCon = channel.getGuild().getRoleById("679077582817198157");
        mod = channel.getGuild().getRoleById("477893113113870346");
        con = channel.getGuild().getRoleById("477893114950844467");
        sup = channel.getGuild().getRoleById("574972907378311168");
        tsup = channel.getGuild().getRoleById("758348227048243200");
        designer = channel.getGuild().getRoleById("477893116204941312");
        partner = channel.getGuild().getRoleById("477893124644012034");
        yt = channel.getGuild().getRoleById("477893122634940431");
        twitch = channel.getGuild().getRoleById("477893126485180427");
        miniyt = channel.getGuild().getRoleById("477893123343908873");
        friend = channel.getGuild().getRoleById("776589854703878144");
        tipico = channel.getGuild().getRoleById("749221161870557255");
        hero = channel.getGuild().getRoleById("477893128401977366");
        ultra = channel.getGuild().getRoleById("477893129777840148");
        muted = channel.getGuild().getRoleById("640149433325060116");
        int i = 0;
        channel.sendMessage(main.getMessage("Updaten...", Color.CYAN, "Jeder User wird geupdatet...\nDies wird einen Moment dauern!").build()).queue();
        for(Member m : channel.getGuild().getMembers()) {
            if (m == null) continue;
            if (m.getNickname() != null) {
                if (m.getRoles().contains(bot)) {
                    if (!m.getNickname().startsWith("Bot | ")) {
                        modifyNickname(channel.getGuild(), m, "Bot | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(owner)) {
                    if (!m.getNickname().startsWith("Owner | ")) {
                        modifyNickname(channel.getGuild(), m, "Owner | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(coOwner)) {
                    if (!m.getNickname().startsWith("CoOwner | ")) {
                        modifyNickname(channel.getGuild(), m, "CoOwner | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(admin)) {
                    if (!m.getNickname().startsWith("Admin | ")) {
                        modifyNickname(channel.getGuild(), m, "Admin | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(technic)) {
                    if (!m.getNickname().startsWith("Techniker | ")) {
                        modifyNickname(channel.getGuild(), m, "Techniker | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(srMod)) {
                    if (!m.getNickname().startsWith("SrMod | ")) {
                        modifyNickname(channel.getGuild(), m, "SrMod | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(srCon)) {
                    if (!m.getNickname().startsWith("SrContent | ")) {
                        modifyNickname(channel.getGuild(), m, "SrContent | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(mod)) {
                    if (!m.getNickname().startsWith("Mod | ")) {
                        modifyNickname(channel.getGuild(), m, "Mod | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(con)) {
                    if (!m.getNickname().startsWith("Content | ")) {
                        modifyNickname(channel.getGuild(), m, "Content | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(sup)) {
                    if (!m.getNickname().startsWith("Sup | ")) {
                        modifyNickname(channel.getGuild(), m, "Sup | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(tsup)) {
                    if (!m.getNickname().startsWith("T-Sup | ")) {
                        modifyNickname(channel.getGuild(), m, "T-Sup | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(designer)) {
                    if (!m.getNickname().startsWith("Designer | ")) {
                        modifyNickname(channel.getGuild(), m, "Designer | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(partner)) {
                    if (!m.getNickname().startsWith("Partner | ")) {
                        modifyNickname(channel.getGuild(), m, "Partner | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(yt)) {
                    if (!m.getNickname().startsWith("YouTube | ")) {
                        modifyNickname(channel.getGuild(), m, "YouTube | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(twitch)) {
                    if (!m.getNickname().startsWith("Twitch | ")) {
                        modifyNickname(channel.getGuild(), m, "Twitch | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(miniyt)) {
                    if (!m.getNickname().startsWith("Mini-YT | ")) {
                        modifyNickname(channel.getGuild(), m, "Mini-YT | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(friend)) {
                    if (!m.getNickname().startsWith("Freund | ")) {
                        modifyNickname(channel.getGuild(), m, "Freund | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(tipico)) {
                    if (!m.getNickname().startsWith("Tipico | ")) {
                        modifyNickname(channel.getGuild(), m, "Tipico | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(hero)) {
                    if (!m.getNickname().startsWith("Hero | ")) {
                        modifyNickname(channel.getGuild(), m, "Hero | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(ultra)) {
                    if (!m.getNickname().startsWith("Ultra | ")) {
                        modifyNickname(channel.getGuild(), m, "Ultra | ");
                    }
                    continue;
                }
                if (m.getRoles().contains(muted)) {
                    if (!m.getNickname().startsWith("Muted | ")) {
                        modifyNickname(channel.getGuild(), m, "Muted | ");
                    }
                    continue;
                }
                modifyNickname(channel.getGuild(), m, "Mitglied | ");
            } else {
                if (m.getRoles().contains(bot)) {
                    modifyNickname(channel.getGuild(), m, "Bot | ");
                    continue;
                }
                if (m.getRoles().contains(owner)) {
                    modifyNickname(channel.getGuild(), m, "Owner | ");
                    continue;
                }
                if (m.getRoles().contains(coOwner)) {
                    modifyNickname(channel.getGuild(), m, "CoOwner | ");
                    continue;
                }
                if (m.getRoles().contains(admin)) {
                    modifyNickname(channel.getGuild(), m, "Admin | ");
                    continue;
                }
                if (m.getRoles().contains(technic)) {
                    modifyNickname(channel.getGuild(), m, "Techniker | ");
                    continue;
                }
                if (m.getRoles().contains(srMod)) {
                    modifyNickname(channel.getGuild(), m, "SrMod | ");
                    continue;
                }
                if (m.getRoles().contains(srCon)) {
                    modifyNickname(channel.getGuild(), m, "SrContent | ");
                    continue;
                }
                if (m.getRoles().contains(mod)) {
                    modifyNickname(channel.getGuild(), m, "Mod | ");
                    continue;
                }
                if (m.getRoles().contains(con)) {
                    modifyNickname(channel.getGuild(), m, "Content | ");
                    continue;
                }
                if (m.getRoles().contains(sup)) {
                    modifyNickname(channel.getGuild(), m, "Sup | ");
                    continue;
                }
                if (m.getRoles().contains(tsup)) {
                    modifyNickname(channel.getGuild(), m, "T-Sup | ");
                    continue;
                }
                if (m.getRoles().contains(designer)) {
                    modifyNickname(channel.getGuild(), m, "Designer | ");
                    continue;
                }
                if (m.getRoles().contains(partner)) {
                    modifyNickname(channel.getGuild(), m, "Partner | ");
                    continue;
                }
                if (m.getRoles().contains(yt)) {
                    modifyNickname(channel.getGuild(), m, "YouTube | ");
                    continue;
                }
                if (m.getRoles().contains(twitch)) {
                    modifyNickname(channel.getGuild(), m, "Twitch | ");
                    continue;
                }
                if (m.getRoles().contains(miniyt)) {
                    modifyNickname(channel.getGuild(), m, "Mini-YT | ");
                    continue;
                }
                if (m.getRoles().contains(friend)) {
                    modifyNickname(channel.getGuild(), m, "Freund | ");
                    continue;
                }
                if (m.getRoles().contains(tipico)) {
                    modifyNickname(channel.getGuild(), m, "Rubin | ");
                    continue;
                }
                if (m.getRoles().contains(hero)) {
                    modifyNickname(channel.getGuild(), m, "Hero | ");
                    continue;
                }
                if (m.getRoles().contains(ultra)) {
                    modifyNickname(channel.getGuild(), m, "Ultra | ");
                    continue;
                }
                if (m.getRoles().contains(muted)) {
                    modifyNickname(channel.getGuild(), m, "Muted | ");
                    continue;
                }
                modifyNickname(channel.getGuild(), m, "Mitglied | ");
            }
        }
        final int[] i1 = {1};
        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                if(i1[0] == 0) {
                    channel.sendMessage(main.getMessage("Erfolg", Color.CYAN, "Jeder User wurde erfolgreich geupdatet!\n").build()).queue();
                    i1[0] = 1;
                    this.cancel();
                    return;
                }
                i1[0]--;

            }
        }, 0, 20000);
    }



    private void modifyNickname(Guild g, Member m, String prefix) {
        String oldNick;
        if(m.getNickname() != null) {
            oldNick = getCleanNick(m);
        } else {
            oldNick = m.getEffectiveName();
        }

        String newNick = prefix + oldNick;
        if(newNick.length() < 32) {
            g.modifyNickname(m, newNick).queue();
        }


    }

    private String getCleanNick(Member m) {
        String nick = m.getNickname();
        return nick.replace("Owner | ", "").replace("CoOwner | ", "").replace("Admin | ", "").replace("Techniker | ", "").replace("SrMod | ", "").replace("SrContent | ", "")
                .replace("Mod | ", "").replace("Content | ", "").replace("Sup | ", "").replace("T-Sup | ", "").replace("Designer | ", "").replace("Partner | ", "").replace("YouTube | ", "")
                .replace("Twitch | ", "").replace("Mini-YT | ", "").replace("Freund | ", "").replace("Tipico | ", "").replace("Smaragd | ", "").replace("Ultimate | ", "").replace("Hero | ", "")
                .replace("Ultra | ", "").replace("VIP+ | ", "").replace("VIP | ", "").replace("Legende | ", "")
                .replace("Muted | ", "").replace("Mitglied | ", "").replace("Bot | ", "");
    }
}
