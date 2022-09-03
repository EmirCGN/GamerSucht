package main.java.de.emir.manager;

import main.java.de.emir.main.Main;

public class WarnManager {

    Main main;

    public WarnManager(Main main) {
        this.main = main;
    }

    public void addWarn(long id, String reason, long from) {
        main.getMySQL().update("INSERT INTO Warns(ID, Reason, WFrom, Timestamp, Confirmed) VALUES ('" + id + "', '" + reason + "', '" + from + "', '" + System.currentTimeMillis() + "', '0')");
    }

    public void confirmWarn(long id) {
        main.getMySQL().update("UPDATE Warns SET Confirmed='1' WHERE ID='" + id + "'");
    }
}
