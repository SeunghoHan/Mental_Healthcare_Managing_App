package application.project.mhm.monitoring;

import java.io.Serializable;

/**
 * Created by Seungho Han on 2017-05-20.
 */

public class LifestyleObj implements Serializable {
    private final String TAB_NAME = "생활";

    public static String[] LifestyleTypes = {"SLEEP", "APPETITE", "WORK"};

    public enum Types {
        SLEEP, APPETITE, WORK
    }

    private String str = null;

    public LifestyleObj () {
        this.str = "Test2";
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return TAB_NAME;
    }
}

