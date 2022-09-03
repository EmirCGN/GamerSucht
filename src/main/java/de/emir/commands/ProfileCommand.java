package main.java.de.emir.commands;

import main.java.de.emir.main.Main;
import main.java.de.emir.manager.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class ProfileCommand implements Command {

    Main main;

    public ProfileCommand(Main main) {
        this.main = main;
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message msg) {
        if(!m.getRoles().contains(channel.getGuild().getRoleById("477893117908090881"))) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Du hast keine Rechte für diesen Befehl " + m.getAsMention() + "!").build()).queue();
            return;
        }
        String[] args = msg.getContentDisplay().split(" ");
        if(args.length < 2) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-profile <User>`, um das Profil eines Users zu sehen " + m.getAsMention() + "!").build()).queue();
            return;
        }
        long id = 0;
        if(msg.getMentionedMembers().size() == 1) {
            id = msg.getMentionedMembers().get(0).getIdLong();
        } else {
            try {
                id = Long.parseLong(args[1]);
            } catch (NumberFormatException e) {
                channel.sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-profile <User>`, um das Profil eines Users zu sehen " + m.getAsMention() + "!").build()).queue();
                return;
            }
        }
        if(!main.getProfileManager().existsPlayer(id)) {
            channel.sendMessage(main.getMessage("Fehler", Color.RED, "Es gibt keinen User mit dieser ID in der Datenbank " + m.getAsMention() + "!").build()).queue();
            return;
        }
        Member m1 = channel.getGuild().getMemberById(id);
        String name = m1.getAsMention() + "\n";
        String isBanned;
        if(main.getProfileManager().isBanned(id)) {
            isBanned = "Ja";
        } else {
            isBanned = "Nein";
        }
        String isMuted = "Nein";
        for(Role r : m1.getRoles()) {
            if(r.getId().equalsIgnoreCase("640149433325060116")) isMuted = "Ja";
        }
        String teamMember = "Nein\n";
        for(Role r : m1.getRoles()) {
            if(r.getId().equalsIgnoreCase("477893117908090881")) {
                String teamStrikes = "";
                if(main.getProfileManager().getTeamStrikes(m1.getIdLong()).size() > 1) {
                    for (String strike : main.getProfileManager().getTeamStrikes(m1.getIdLong())) {
                        String[] strikeSplit = strike.split("/");
                        teamStrikes = teamStrikes + "- Grund:" + strikeSplit[0] + " / Erstellt von: " + strikeSplit[1] + " / Datum: " + strikeSplit[2] + "\n";
                    }
                } else {
                    teamStrikes = "";
                }
                teamMember = "Ja\n   Rang: " + main.getProfileManager().getTeamRank(m1) + "\n   Im Team seit: " + main.getProfileManager().inTeamSince(m1.getIdLong()) + "\n" +
                        "   Team-Strikes: " + main.getProfileManager().getTeamStrikesNumber(m1.getIdLong()) + "\n" + teamStrikes;
            }
        }
        String strikes = "0";
        if(main.getProfileManager().getStrikes(m1.getIdLong()).size() > 1) {
            for (String strike : main.getProfileManager().getStrikes(m1.getIdLong())) {
                String[] strikeSplit = strike.split("/");
                strikes = strikes + "- Grund:" + strikeSplit[0] + " / Erstellt von: " + strikeSplit[1] + " / Datum: " + strikeSplit[2] + "\n";
            }
        } else {
            strikes = "0";
        }
        String notes = "";
        if(main.getProfileManager().getNotes(m1.getIdLong()).isEmpty()) {
            notes = "Keine\n";
        } else {
            for(String note : main.getProfileManager().getNotes(m1.getIdLong())) {
                notes = notes + "- " + note + "\n";
            }
        }
        channel.sendMessage(main.getMessage("Userprofil", Color.GREEN, "Name: " + name + "Gebannt: " + isBanned + "\nGemuted: " + isMuted + "\nTeammitglied: " + teamMember + "Strikes: " + strikes + "\nNotizen: " + notes, m1.getUser().getAvatarUrl()).build()).queue();

    }

}
