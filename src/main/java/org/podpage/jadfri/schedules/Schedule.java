package org.podpage.jadfri.schedules;

import com.google.gson.annotations.SerializedName;

/**
 * Created by podpage on 28.10.2017.
 */
public class Schedule extends UnknownSchedule {
    @SerializedName("9002")
    private long createdAt;

    @SerializedName("5850")
    private int power;

    @SerializedName("9041")
    private int days;

    public long getCreatedAt() {
        return createdAt;
    }

    public int getPower() {
        return power;
    }

    public Days[] getDays() {
        return Days.getDays(days);
    }
}
