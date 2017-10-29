package org.podpage.jadfri.device;

import com.google.gson.annotations.SerializedName;

/**
 * Created by podpage on 13.07.2017.
 */
public class Light extends Device {

    @SerializedName("3311")
    private LightData[] lightData;

    public LightData[] getLightData() {
        return lightData;
    }
}
