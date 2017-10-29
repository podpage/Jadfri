package org.podpage.jadfri.request.callbacks;

import org.eclipse.californium.core.coap.CoAP;
import org.podpage.jadfri.device.ColorableLight;
import org.podpage.jadfri.device.UnknownDevice;
import org.podpage.jadfri.request.CoAPRequest;
import org.podpage.jadfri.request.Request;

/**
 * Created by podpage on 28.10.2017.
 */
public class ColorLight extends CoAPRequest<String> {

    public ColorLight(UnknownDevice unknownDevice, ColorableLight.ColorTemperature temperature) {
        super(unknownDevice.getGateway(), new Request(CoAP.Code.PUT, "15001/" + unknownDevice.getId(), "{\"3311\":[{\"5706\":\"" + temperature.getColor() + "\"}]}"));
    }

    public ColorLight(UnknownDevice unknownDevice, String color) {
        super(unknownDevice.getGateway(), new Request(CoAP.Code.PUT, "15001/" + unknownDevice.getId(), "{\"3311\":[{\"5706\":\"" + color + "\"}]}"));
    }

    @Override
    protected String getObjectFromJson(String json) {
        return json;
    }
}


