package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChatFilter extends ListenerAdapter {

    Main main;

    public ChatFilter(Main main){
        this.main = main;
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        String [] BannedWords = {"Hurensohn","Bitch","Cunt","Serbe", "Cetnik", "KFZ-Mechatroniker", "Qsine", "Nigga", "Niga", "Niger", "Nigger"};
        String [] message = event.getMessage().getContentRaw().split(" ");
        for(int i=0; i<message.length;i++) {
            for(int l=0; l<BannedWords.length; l++) {
                if(message[i].equalsIgnoreCase(BannedWords[l]))
                {
                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage("Unerlaubte Wörter sind auf diesem Server nicht erlaubt " + event.getAuthor().getName()).queue();
                }

            }
        }
    }
}
