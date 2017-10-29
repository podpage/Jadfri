package org.podpage.jadfri.device;

import com.google.gson.annotations.SerializedName;

/**
 * Created by podpage on 13.07.2017.
 */
public class DeviceData {

    @SerializedName("9")
    private int Battery;
    @SerializedName("3")
    private String FirmwareVersion;
    @SerializedName("0")
    private String Manufacture;
    @SerializedName("1")
    private String ModelNumber = "";
    @SerializedName("6")
    private int Power;
    @SerializedName("2")
    private String SerialNumber;

    public int getBattery() {
        return Battery;
    }

    public String getFirmwareVersion() {
        return FirmwareVersion;
    }

    public String getManufacture() {
        return Manufacture;
    }

    public String getModelNumber() {
        return ModelNumber;
    }

    public int getPower() {
        return Power;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }
}
