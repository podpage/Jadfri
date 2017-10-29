package org.podpage.jadfri.device;

import org.podpage.jadfri.request.callbacks.ColorLight;

/**
 * Created by podpage on 13.07.2017.
 */
public class ColorableLight extends DimmableLight {

    public void color(ColorTemperature temperature) {
        new ColorLight(this, temperature).requestSync();
    }

    public enum ColorTemperature {
        COLD("f5faf6"),
        MEDIUM("f1e0b5"),
        WARM("efd275");

        private String temp;

        ColorTemperature(String temp) {
            this.temp = temp;
        }

        public String getColor() {
            return temp;
        }
    }
}
