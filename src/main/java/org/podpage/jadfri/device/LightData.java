package org.podpage.jadfri.device;

import com.google.gson.annotations.SerializedName;

/**
 * Created by podpage on 13.07.2017.
 */
public class LightData {

    @SerializedName("5706")
    private String color;
    @SerializedName("5709")
    private int colorX;
    @SerializedName("5710")
    private int colorY;
    @SerializedName("5805")
    private float cumulativeActivePower;
    @SerializedName("5851")
    private int dimmer;
    @SerializedName("5850")
    private int onOff;
    @SerializedName("5852")
    private int onTime;
    @SerializedName("5820")
    private float powerFactor;
    @SerializedName("5712")
    private int transition_time = 5;
    @SerializedName("5701")
    private String unit;

    public String getColor() {
        return color;
    }

    public int getColorX() {
        return colorX;
    }

    public int getColorY() {
        return colorY;
    }

    public float getCumulativeActivePower() {
        return cumulativeActivePower;
    }

    public int getDimmer() {
        return dimmer;
    }

    public int getOnOff() {
        return onOff;
    }

    public int getOnTime() {
        return onTime;
    }

    public float getPowerFactor() {
        return powerFactor;
    }

    public int getTransition_time() {
        return transition_time;
    }

    public String getUnit() {
        return unit;
    }
}
