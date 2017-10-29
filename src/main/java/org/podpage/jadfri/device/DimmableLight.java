package org.podpage.jadfri.device;

import org.podpage.jadfri.request.callbacks.DimmLight;

/**
 * Created by podpage on 09.07.2017.
 */
public class DimmableLight extends Light {

    public void dimm(byte b) {
        new DimmLight(this, b).requestSync();
    }
}
