package org.podpage.jadfri.group;

import com.google.gson.annotations.SerializedName;

/**
 * Created by podpage on 09.07.2017.
 */
public class Group extends UnknownGroup {

    @SerializedName("9001")
    private String name;

    @SerializedName("9002")
    private long createdAt;

    @SerializedName("5850")
    private int power;

    @SerializedName("9039")
    private int sceneId;

    public String getName() {
        return name;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public int getPower() {
        return power;
    }

    public int getSceneId() {
        return sceneId;
    }
}
