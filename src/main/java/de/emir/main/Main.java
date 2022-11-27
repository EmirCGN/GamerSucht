package main.java.de.emir.main;

import main.java.de.emir.listener.*;
import main.java.de.emir.manager.*;
import main.java.de.emir.mysql.MySQL;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public JDA jda;
    public JDA manager;
    CommandManager commandManager;
    MySQL mySQL;
    ProfileManager profileManager;
    ApplicationManager applicationManager;
    BirthdayManager birthdayManager;
    BirthdayTimer birthdayTimer;
    MuteManager muteManager;
    LevelManager levelManager;
    WarnManager warnManager;

    public List<User> needToTellFunction = new ArrayList<>();
    public List<User> needToSendApplication = new ArrayList<>();
    public List<User> confirming = new ArrayList<>();
    public Guild guild;
    public TextChannel applicationsChannel;
    public static Main INSTANCE;


    public HashMap<User, String> functions = new HashMap<>();

    public Main() {
        System.out.println("Lädt... (0%)");
        fetchClasses();
        System.out.println("Lädt... (20%)");
        getMySQL().connect();
        getMySQL().update("CREATE TABLE IF NOT EXISTS Applications(ID BIGINT, AFunction VARCHAR(10), Application TEXT, MessageID BIGINT, Timestamp BIGINT, PrivateChannelID BIGINT)");
        getMySQL().update("CREATE TABLE IF NOT EXISTS Birthdays(ID BIGINT, Birthday VARCHAR(6), Year BIGINT)");
        getMySQL().update("CREATE TABLE IF NOT EXISTS reactroles(ID BIGINT, guildid INT, channelid INT, messageid INT, emote VARCHAR(500), rollenid INT)");
        getMySQL().update("CREATE TABLE IF NOT EXISTS BirthdayMessages(Month VARCHAR(10), MessageID BIGINT)");
        getMySQL().update("CREATE TABLE IF NOT EXISTS User(ID BIGINT, isTeamMember INT, isTeamMemberSince BIGINT, TeamStrikes VARCHAR(500), Strikes VARCHAR(500), Notes VARCHAR(500), isTempbanned INT, isTempbannedFrom BIGINT, isTempbannedReason VARCHAR(100), getsUnbannedWhen BIGINT, isBanned INT, isBannedFrom BIGINT, isBannedReason VARCHAR(100), isTempmuted INT, isTempmutedFrom BIGINT, isTempmutedReason VARCHAR(100), getsUnmutedWhen BIGINT, isMuted INT, isMutedFrom BIGINT, isMutedReason VARCHAR(100), Roles TEXT)");
        getMySQL().update("CREATE TABLE IF NOT EXISTS Warns(ID BIGINT, Reason VARCHAR(100), WFrom BIGINT, Timestamp BIGINT, Confirmed INT)");
        getMySQL().update("CREATE TABLE IF NOT EXISTS Levels(ID BIGINT, TotalXP BIGINT, XP INT, Level INT)");
        System.out.println("Lädt... (50%)");
        JDABuilder jdaBuilder = JDABuilder.createDefault("NjE5ODg3NDYwMzMxMTU5NTYw.Gs5W9M._Iq7jPPVP1EAb7_QwsHbD6F6ZJ_KMU4n_6T3hI").enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS)).setActivity(Activity.watching("im Chat das keine Verbotene Sachen geschrieben werden :)")).setStatus(OnlineStatus.ONLINE).addEventListeners(new Object[]{new CommandListener(this)}).setMemberCachePolicy(MemberCachePolicy.ALL)
                .addEventListeners(new Object[]{new CreatePlayerListener(this)})
                .addEventListeners(new Object[]{new TeamListener(this)})
                .addEventListeners(new Object[]{new JoinListener(this)})
                .addEventListeners(new Object[]{new RoleAddListener(this)})
                .addEventListeners(new Object[]{new RoleRemoveListener(this)})
                .addEventListeners(new Object[]{new ApplyListener(this)})
                .addEventListeners(new Object[]{new ReactionListener(this)})
                .addEventListeners(new Object[]{new NicknameListener(this)})
                .addEventListeners(new Object[]{new ChangeNickListener(this)})
                .addEventListeners(new Object[]{new BirthdayListener(this)})
                .addEventListeners(new Object[]{new LevelListener(this)})
                .addEventListeners(new Object[]{new HeyListener(this)})
                .addEventListeners(new Object[]{new voiceListener(this)})
                .addEventListeners(new Object[]{new ChatFilter(this)})
                .addEventListeners(new Object[]{new AutoChannelListener(this)})
                .addEventListeners(new Object[]{new ExceptionListener(this)});
        System.out.println("Lädt... (70%)");
        try {
            jda = jdaBuilder.build();
            jda.awaitReady();
            System.out.println("Lädt... (100%)");
            Thread.sleep(2000);
            System.out.println("Erfolgreich geladen!");
            System.out.println("   _____                           _____            _     _   \n" +
                    "  / ____|                         / ____|          | |   | |  \n" +
                    " | |  __  __ _ _ __ ___   ___ _ _| (___  _   _  ___| |__ | |_ \n" +
                    " | | |_ |/ _` | '_ ` _ \\ / _ \\ '__\\___ \\| | | |/ __| '_ \\| __|\n" +
                    " | |__| | (_| | | | | | |  __/ |  ____) | |_| | (__| | | | |_ \n" +
                    "  \\_____|\\__,_|_| |_| |_|\\___|_| |_____/ \\__,_|\\___|_| |_|\\__|\n" +
                    "                                                              \n" +
                    "                                                              ");
        } catch (LoginException | IllegalArgumentException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Konnte nicht geladen werden!");
            manager = jda;
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    private void fetchClasses() {
        commandManager = new CommandManager(this);
        mySQL = new MySQL();
        profileManager = new ProfileManager(this);
        applicationManager = new ApplicationManager(this);
        birthdayManager = new BirthdayManager(this);
        birthdayTimer = new BirthdayTimer(this);
        muteManager = new MuteManager(this);
        levelManager = new LevelManager(this);
        warnManager = new WarnManager(this);
    }

    public static Logger getLogger() {
        return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    public EmbedBuilder getMessage(String title, Color color, String description) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(title);
        embedBuilder.setColor(color);
        embedBuilder.setDescription(description);
        return embedBuilder;
    }

    public EmbedBuilder getMessage(String title, Color color, String description, String url) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(title);
        embedBuilder.setColor(color);
        embedBuilder.setDescription(description);
        embedBuilder.setImage(url);
        return embedBuilder;
    }



    public CommandManager getCommandManager() {
        return commandManager;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }

    public ApplicationManager getApplicationManager() {
        return applicationManager;
    }

    public BirthdayManager getBirthdayManager() {
        return birthdayManager;
    }

    public BirthdayTimer getBirthdayTimer() {
        return birthdayTimer;
    }

    public MuteManager getMuteManager() {
        return muteManager;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public WarnManager getWarnManager() {
        return warnManager;
    }
}