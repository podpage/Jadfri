package org.podpage.jadfri.device;

import com.google.gson.annotations.SerializedName;

/**
 * Created by podpage on 13.07.2017.
 */
public class Device extends UnknownDevice {

    @SerializedName("5750")
    private int type;
    @SerializedName("9001")
    private String name = "";
    @SerializedName("9002")
    private long createdAt;
    @SerializedName("9019")
    private int reachable;
    @SerializedName("9020")
    private long lastSeen;

    @SerializedName("3")
    private DeviceData deviceData;

    public long getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public DeviceType getDeviceType() {
        return DeviceType.fromDevice(this);
    }

    public DeviceData getDeviceData() {
        return deviceData;
    }
}
