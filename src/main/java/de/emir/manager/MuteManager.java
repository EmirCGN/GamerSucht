package main.java.de.emir.manager;

import main.java.de.emir.listener.RoleAddListener;
import main.java.de.emir.listener.RoleRemoveListener;
import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.Timer;
import java.util.TimerTask;

public class MuteManager {

    Main main;

    public MuteManager(Main main) {
        this.main = main;
    }

    public void mute(Member member, long fromID, String reason) {
        RoleRemoveListener.isMuting = true;
        RoleAddListener.isMuting = true;
        for(Role r : main.guild.getMember(member.getUser()).getRoles()) {
            if(!r.getId().equalsIgnoreCase("622386116800151573")) {
                main.guild.removeRoleFromMember(member, r).complete();
            }
        }
        main.guild.addRoleToMember(member, main.guild.getRoleById("640149433325060116")).complete();
        final int[] i = {1};
        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                if(i[0] != 0) {
                    i[0]--;
                    return;
                }
                main.getMySQL().update("UPDATE User SET isMuted='1' WHERE ID='" + member.getIdLong() + "'");
                main.getMySQL().update("UPDATE User SET isMutedFrom='" + fromID + "' WHERE ID='" + member.getIdLong() + "'");
                main.getMySQL().update("UPDATE User SET isMutedReason='" + reason + "' WHERE ID='" + member.getIdLong() + "'");
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
                RoleRemoveListener.isMuting = false;
                RoleAddListener.isMuting = false;
                this.cancel();
            }
        }, 0, 20000);
    }

    public void unmute(Member member) {
        RoleRemoveListener.isMuting = true;
        RoleAddListener.isMuting = true;
        main.guild.removeRoleFromMember(member.getIdLong(), main.guild.getRoleById("640149433325060116")).complete();
        for(long l : main.getProfileManager().getRoles(member.getIdLong())) {
            main.guild.addRoleToMember(member.getIdLong(), main.guild.getRoleById(l)).complete();
        }
        final int[] i = {1};
        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                if(i[0] != 0) {
                    i[0]--;
                    return;
                }
                main.getMySQL().update("UPDATE User SET isMuted='0' WHERE ID='" + member.getIdLong() + "'");
                main.getMySQL().update("UPDATE User SET isMutedFrom='0' WHERE ID='" + member.getIdLong() + "'");
                main.getMySQL().update("UPDATE User SET isMutedReason='' WHERE ID='" + member.getIdLong() + "'");
                RoleRemoveListener.isMuting = false;
                RoleAddListener.isMuting = false;
                this.cancel();
            }
        }, 0, 10000);


    }

    public void tempmute(long userID, long fromID, String reason, long timestamp, long howlong) {

    }

    public boolean isMuted(long userID) {
        return true;
    }
}