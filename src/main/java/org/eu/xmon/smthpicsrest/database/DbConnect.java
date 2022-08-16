package org.eu.xmon.smthpicsrest.database;

import com.dieselpoint.norm.Database;
import lombok.Getter;

/**
 * @Author Xmon
 */
public class DbConnect {

    @Getter
    private static final Database database = new Database();

    /**
     * @apiNote connect to database
     */
    public void connect(){
        if (System.getenv("jdbc").isEmpty() || System.getenv("db-user").isEmpty() || System.getenv("db-password").isEmpty()) {
            System.exit(-1);
        }

        database.setJdbcUrl(System.getenv("jdbc"));
        database.setUser(System.getenv("db-user"));
        database.setPassword(System.getenv("db-password"));
    }
}
