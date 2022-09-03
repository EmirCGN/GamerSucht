package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ChangeNickListener extends ListenerAdapter {

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
    Role tipico;
    Role hero;
    Role ultra;
    Role muted;

    boolean done = false;

    public ChangeNickListener(Main main) {
        this.main = main;
    }

    @Override
    public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {
        if (main.guild != null && !done) {
            bot = event.getGuild().getRoleById("477893098014375936");
            owner = event.getGuild().getRoleById("477893096756215829");
            coOwner = event.getGuild().getRoleById("477893097465053185");
            admin = event.getGuild().getRoleById("477893104335323146");
            technic = event.getGuild().getRoleById("679744099355459626");
            srCon = event.getGuild().getRoleById("870045319612735550");
            srMod = event.getGuild().getRoleById("581481942726344705");
            con = event.getGuild().getRoleById("870046872264388669");
            mod = event.getGuild().getRoleById("477893113113870346");
            sup = event.getGuild().getRoleById("574972907378311168");
            tsup = event.getGuild().getRoleById("758348227048243200");
            designer = event.getGuild().getRoleById("477893116204941312");
            tcteam = event.getGuild().getRoleById("868249341054304287");
            partner = event.getGuild().getRoleById("477893124644012034");
            yt = event.getGuild().getRoleById("477893122634940431");
            twitch = event.getGuild().getRoleById("477893126485180427");
            miniyt = event.getGuild().getRoleById("477893123343908873");
            friend = event.getGuild().getRoleById("776589854703878144");
            booster = event.getGuild().getRoleById("622386116800151573");
            tipico = event.getGuild().getRoleById("749221161870557255");
            hero = event.getGuild().getRoleById("477893128401977366");
            ultra = event.getGuild().getRoleById("477893129777840148");
            muted = event.getGuild().getRoleById("640149433325060116");
            done = true;
        }
        Member m = event.getMember();
        if(m == null) return;
        if(event.getNewNickname() != null) {
            if (m.getRoles().contains(bot)) {
                if(!event.getNewNickname().startsWith("Bot | ")) {
                    modifyNickname(event.getGuild(), m, "Bot | ");
                }
                return;
            }
            if (m.getRoles().contains(owner)) {
                if(!event.getNewNickname().startsWith("Owner | ")) {
                    modifyNickname(event.getGuild(), m, "Owner | ");
                }
                return;
            }
            if (m.getRoles().contains(coOwner)) {
                if(!event.getNewNickname().startsWith("CoOwner | ")) {
                    modifyNickname(event.getGuild(), m, "CoOwner | ");
                }
                return;
            }
            if (m.getRoles().contains(admin)){
                if(!event.getNewNickname().startsWith("Admin | ")) {
                    modifyNickname(event.getGuild(), m, "Admin | ");
                }
                return;
            }
            if (m.getRoles().contains(srCon)) {
                if(!event.getNewNickname().startsWith("SrContent | ")) {
                    modifyNickname(event.getGuild(), m, "SrContent | ");
                }
                return;
            }
            if (m.getRoles().contains(srMod)){
                if(!event.getNewNickname().startsWith("SrMod | ")) {
                    modifyNickname(event.getGuild(), m, "SrMod | ");
                }
                return;
            }
            if (m.getRoles().contains(con)){
                if(!event.getNewNickname().startsWith("Content | ")) {
                    modifyNickname(event.getGuild(), m, "Content | ");
                }
                return;
            }
            if (m.getRoles().contains(mod)){
                if(!event.getNewNickname().startsWith("Mod | ")) {
                    modifyNickname(event.getGuild(), m, "Mod | ");
                }
                return;
            }
            if (m.getRoles().contains(sup)){
                if(!event.getNewNickname().startsWith("Sup | ")) {
                    modifyNickname(event.getGuild(), m, "Sup | ");
                }
                return;
            }
            if (m.getRoles().contains(tsup)){
                if(!event.getNewNickname().startsWith("T-Sup | ")) {
                    modifyNickname(event.getGuild(), m, "T-Sup | ");
                }
                return;
            }
            if (m.getRoles().contains(designer)){
                if(!event.getNewNickname().startsWith("Designer | ")) {
                    modifyNickname(event.getGuild(), m, "Designer | ");
                }
                return;
            }
            if (m.getRoles().contains(tcteam)){
                if(!event.getNewNickname().startsWith("Tipico-Team | ")) {
                    modifyNickname(event.getGuild(), m, "Tipico-Team | ");
                }
                return;
            }
            if (m.getRoles().contains(booster)){
                if(!event.getNewNickname().startsWith("Booster | ")) {
                    modifyNickname(event.getGuild(), m, "Booster | ");
                }
                return;
            }
            if (m.getRoles().contains(partner)){
                if(!event.getNewNickname().startsWith("Partner | ")) {
                    modifyNickname(event.getGuild(), m, "Partner | ");
                }
                return;
            }
            if (m.getRoles().contains(yt)){
                if(!event.getNewNickname().startsWith("YouTube | ")) {
                    modifyNickname(event.getGuild(), m, "YouTube | ");
                }
                return;
            }
            if (m.getRoles().contains(twitch)){
                if(!event.getNewNickname().startsWith("Twitch | ")) {
                    modifyNickname(event.getGuild(), m, "Twitch | ");
                }
                return;
            }
            if (m.getRoles().contains(miniyt)){
                if(!event.getNewNickname().startsWith("Mini-YT | ")) {
                    modifyNickname(event.getGuild(), m, "Mini-YT | ");
                }
                return;
            }
            if (m.getRoles().contains(friend)){
                if(!event.getNewNickname().startsWith("Freund | ")) {
                    modifyNickname(event.getGuild(), m, "Freund | ");
                }
                return;
            }
            if (m.getRoles().contains(tipico)){
                if(!event.getNewNickname().startsWith("Tipico | ")) {
                    modifyNickname(event.getGuild(), m, "Tipico | ");
                }
                return;
            }
            if (m.getRoles().contains(hero)) {
                if(!event.getNewNickname().startsWith("Hero | ")) {
                    modifyNickname(event.getGuild(), m, "Hero | ");
                }
                return;
            }
            if (m.getRoles().contains(ultra)) {
                if(!event.getNewNickname().startsWith("Ultra | ")) {
                    modifyNickname(event.getGuild(), m, "Ultra | ");
                }
                return;
            }
            if (m.getRoles().contains(muted)){
                if(!event.getNewNickname().startsWith("Muted | ")) {
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
            if (m.getRoles().contains(con)) {
                modifyNickname(event.getGuild(), m, "Content | ");
                return;
            }
            if (m.getRoles().contains(mod)) {
                modifyNickname(event.getGuild(), m, "Mod | ");
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
            if (m.getRoles().contains(miniyt)) {
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
            if (m.getRoles().contains(friend)) {
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
        return nick.replace("Owner | ", "").replace("CoOwner | ", "").replace("Admin | ", "").replace("SrContent | ", "").replace("SrMod | ", "")
                .replace("Content | ", "").replace("Mod | ", "").replace("Sup | ", "").replace("T-Sup | ", "").replace("Designer | ", "").replace("Tipico-Team | ", "").replace("Partner | ", "").replace("YouTube | ", "")
                .replace("Twitch | ", "").replace("Mini-YT | ", "").replace("Freund | ", "").replace("Tipico | ", "").replace("Booster | ", "").replace("Smaragd | ", "").replace("Ultimate | ", "").replace("Hero | ", "")
                .replace("Ultra | ", "").replace("VIP+ | ", "").replace("VIP | ", "").replace("Legende | ", "")
                .replace("Muted | ", "").replace("Mitglied | ", "").replace("Bot | ", "");
    }
}
