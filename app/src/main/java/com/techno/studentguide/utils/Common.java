package com.techno.studentguide.utils;

/**
 * Created by tech on 5/24/2016.
 */
public class Common {
    private static Common ourInstance = new Common();

    public static Common getInstance() {
        return ourInstance;
    }

    private Common() {
    }


}
