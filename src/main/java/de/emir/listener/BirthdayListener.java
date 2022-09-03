package main.java.de.emir.listener;

import main.java.de.emir.main.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class BirthdayListener extends ListenerAdapter {

    Main main;

    public BirthdayListener(Main main) {
        this.main = main;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(!event.isFromType(ChannelType.TEXT)) return;
        String[] args = event.getMessage().getContentDisplay().split(" ");
        if(args[0].equalsIgnoreCase("-geburtstag")) {
            if(main.getBirthdayManager().hasToldBirthday(event.getAuthor().getIdLong())) {
                event.getChannel().sendMessage(main.getMessage("Fehler", Color.RED, "Du hast deinen Geburtstag bereits angegeben!\nAktuell angegebener Geburtstag: " + main.getBirthdayManager().getBirthday(event.getAuthor().getIdLong()) +  main.getBirthdayManager().getBirthdayYear(event.getAuthor().getIdLong()) + "\n\nIst dein angegebener Geburtstag falsch? Dann teile dies bitte <@421044883114164227> mit!").build()).queue();
                return;
            }
            if(args.length != 2) {
                event.getChannel().sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-geburtstag <dd.mm.yyyy>`, um deinen Geburtstag abzuspeichern!").build()).queue();
                return;
            }
            String birthday = args[1];
            if(birthday.length() != 10) {
                event.getChannel().sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-geburtstag <dd.mm.yyyy>`, um deinen Geburtstag abzuspeichern!").build()).queue();
                return;
            }
            try {
                int i = Integer.parseInt(birthday.substring(0, 2));
                int i1 = Integer.parseInt(birthday.substring(3, 5));
                int i2 = Integer.parseInt(birthday.substring(6, 10));
                if(i < 1 || i > 31 || i1 < 1 || i1 > 12 || i2 < 1960 || i2 > 2020) {
                    event.getChannel().sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-geburtstag <dd.mm.yyyy>`, um deinen Geburtstag abzuspeichern!").build()).queue();
                    return;
                }
            } catch (NumberFormatException e) {
                event.getChannel().sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-geburtstag <dd.mm.yyyy>`, um deinen Geburtstag abzuspeichern!").build()).queue();
                return;
            }
            if(!args[1].substring(2, 3).equalsIgnoreCase(".") || !args[1].substring(5, 6).equalsIgnoreCase(".")) {
                event.getChannel().sendMessage(main.getMessage("Fehler", Color.RED, "Bitte verwende `-geburtstag <dd.mm.yyyy>`, um deinen Geburtstag abzuspeichern!").build()).queue();
                return;
            }
            main.getBirthdayManager().addBirthday(event.getAuthor().getIdLong(), birthday);
            event.getChannel().sendMessage(main.getMessage("Erfolg", Color.CYAN, "Du hast erfolgreich den " + birthday + " als deinen Geburtstag angegeben!\nAn diesem Tag wirst du eine besondere Rolle auf unserem Discord-Server erhalten.").build()).queue();
            int i = Integer.parseInt(birthday.substring(3, 5));
            if(i == 1) {
                main.guild.getTextChannelById("705900451010183269").editMessageById("960946472545845298", "**Januar**").queue();
                final int[] i1 = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i1[0] != 0) {
                            i1[0]--;
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
                                String birthday1 = rs.getString("Birthday");
                                if(Integer.parseInt(birthday1.substring(3, 5)) != i) return;
                                String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1)).complete().getContentRaw();
                                String newMessage = oldMessage + "\n<@" + m.getId() + "> *" + birthday1.substring(0, 6) + "*";
                                main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1), newMessage).complete();
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
            } else if(i == 2) {
                main.guild.getTextChannelById("705900451010183269").editMessageById("960946563037921311", "**Februar**").queue();
                final int[] i1 = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i1[0] != 0) {
                            i1[0]--;
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
                                String birthday1 = rs.getString("Birthday");
                                if(Integer.parseInt(birthday1.substring(3, 5)) != i) return;
                                String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1)).complete().getContentRaw();
                                String newMessage = oldMessage + "\n<@" + m.getId() + "> *" + birthday1.substring(0, 6) + "*";
                                main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1), newMessage).complete();
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
            } else if(i == 3) {
                main.guild.getTextChannelById("705900451010183269").editMessageById("960947609705512990", "**März**").queue();
                final int[] i1 = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i1[0] != 0) {
                            i1[0]--;
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
                                String birthday1 = rs.getString("Birthday");
                                if(Integer.parseInt(birthday1.substring(3, 5)) != i) return;
                                String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1)).complete().getContentRaw();
                                String newMessage = oldMessage + "\n<@" + m.getId() + "> *" + birthday1.substring(0, 6) + "*";
                                main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1), newMessage).complete();
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
            } else if(i == 4) {
                main.guild.getTextChannelById("705900451010183269").editMessageById("960947666584481862", "**April**").queue();
                final int[] i1 = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i1[0] != 0) {
                            i1[0]--;
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
                                String birthday1 = rs.getString("Birthday");
                                if(Integer.parseInt(birthday1.substring(3, 5)) != i) return;
                                String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1)).complete().getContentRaw();
                                String newMessage = oldMessage + "\n<@" + m.getId() + "> *" + birthday1.substring(0, 6) + "*";
                                main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1), newMessage).complete();
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
            } else if(i == 5) {
                main.guild.getTextChannelById("705900451010183269").editMessageById("960947689875439616", "**Mai**").queue();
                final int[] i1 = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i1[0] != 0) {
                            i1[0]--;
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
                                String birthday1 = rs.getString("Birthday");
                                if(Integer.parseInt(birthday1.substring(3, 5)) != i) return;
                                String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1)).complete().getContentRaw();
                                String newMessage = oldMessage + "\n<@" + m.getId() + "> *" + birthday1.substring(0, 6) + "*";
                                main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1), newMessage).complete();
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
            } else if(i == 6) {
                main.guild.getTextChannelById("705900451010183269").editMessageById("960947729842962554", "**Juni**").queue();
                final int[] i1 = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i1[0] != 0) {
                            i1[0]--;
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
                                String birthday1 = rs.getString("Birthday");
                                if(Integer.parseInt(birthday1.substring(3, 5)) != i) return;
                                String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1)).complete().getContentRaw();
                                String newMessage = oldMessage + "\n<@" + m.getId() + "> *" + birthday1.substring(0, 6) + "*";
                                main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1), newMessage).complete();
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
            } else if(i == 7) {
                main.guild.getTextChannelById("705900451010183269").editMessageById("960947761375768646", "**Juli**").queue();
                final int[] i1 = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i1[0] != 0) {
                            i1[0]--;
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
                                String birthday1 = rs.getString("Birthday");
                                if(Integer.parseInt(birthday1.substring(3, 5)) != i) return;
                                String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1)).complete().getContentRaw();
                                String newMessage = oldMessage + "\n<@" + m.getId() + "> *" + birthday1.substring(0, 6) + "*";
                                main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1), newMessage).complete();
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
            } else if(i == 8) {
                main.guild.getTextChannelById("705900451010183269").editMessageById("960947787636310157", "**August**").queue();
                final int[] i1 = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i1[0] != 0) {
                            i1[0]--;
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
                                String birthday1 = rs.getString("Birthday");
                                if(Integer.parseInt(birthday1.substring(3, 5)) != i) return;
                                String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1)).complete().getContentRaw();
                                String newMessage = oldMessage + "\n<@" + m.getId() + "> *" + birthday1.substring(0, 6) + "*";
                                main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1), newMessage).complete();
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
            } else if(i == 9) {
                main.guild.getTextChannelById("705900451010183269").editMessageById("960947831139618846", "**September**").queue();
                final int[] i1 = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i1[0] != 0) {
                            i1[0]--;
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
                                String birthday1 = rs.getString("Birthday");
                                if(Integer.parseInt(birthday1.substring(3, 5)) != i) return;
                                String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1)).complete().getContentRaw();
                                String newMessage = oldMessage + "\n<@" + m.getId() + "> *" + birthday1.substring(0, 6) + "*";
                                main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1), newMessage).complete();
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
            } else if(i == 10) {
                main.guild.getTextChannelById("705900451010183269").editMessageById("960947866216566865", "**Oktober**").queue();
                final int[] i1 = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i1[0] != 0) {
                            i1[0]--;
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
                                String birthday1 = rs.getString("Birthday");
                                if(Integer.parseInt(birthday1.substring(3, 5)) != i) return;
                                String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1)).complete().getContentRaw();
                                String newMessage = oldMessage + "\n<@" + m.getId() + "> *" + birthday1.substring(0, 6) + "*";
                                main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1), newMessage).complete();
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
            } else if(i == 11) {
                main.guild.getTextChannelById("705900451010183269").editMessageById("960947884252086313", "**November**").queue();
                final int[] i1 = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i1[0] != 0) {
                            i1[0]--;
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
                                String birthday1 = rs.getString("Birthday");
                                if(Integer.parseInt(birthday1.substring(3, 5)) != i) return;
                                String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1)).complete().getContentRaw();
                                String newMessage = oldMessage + "\n<@" + m.getId() + "> *" + birthday1.substring(0, 6) + "*";
                                main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1), newMessage).complete();
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
            } else if(i == 12) {
                main.guild.getTextChannelById("705900451010183269").editMessageById("960947913465397258", "**Dezember**").queue();
                final int[] i1 = {1};
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i1[0] != 0) {
                            i1[0]--;
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
                                String birthday1 = rs.getString("Birthday");
                                if(Integer.parseInt(birthday1.substring(3, 5)) != i) return;
                                String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1)).complete().getContentRaw();
                                String newMessage = oldMessage + "\n<@" + m.getId() + "> *" + birthday1.substring(0, 6) + "*";
                                main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday1), newMessage).complete();
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
            }
            String oldMessage = main.guild.getTextChannelById("705900451010183269").retrieveMessageById(main.getBirthdayManager().getMessageIDByDate(birthday)).complete().getContentRaw();
            String newMessage = oldMessage + "\n<@" + event.getAuthor().getId() + "> *" + birthday.substring(0, 6) + "*";
            main.guild.getTextChannelById("705900451010183269").editMessageById(main.getBirthdayManager().getMessageIDByDate(birthday), newMessage).queue();

        }
    }
}
