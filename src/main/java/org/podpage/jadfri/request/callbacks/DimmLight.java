package org.podpage.jadfri.request.callbacks;

import org.eclipse.californium.core.coap.CoAP;
import org.podpage.jadfri.device.UnknownDevice;
import org.podpage.jadfri.request.CoAPRequest;
import org.podpage.jadfri.request.Request;

/**
 * Created by podpage on 28.10.2017.
 */
public class DimmLight extends CoAPRequest<String> {

    public DimmLight(UnknownDevice unknownDevice, byte b) {
        super(unknownDevice.getGateway(), new Request(CoAP.Code.PUT, "15001/" + unknownDevice.getId(), "{\"3311\":[{\"5851\":" + (b & 0xFF) + "}]}"));
    }

    @Override
    protected String getObjectFromJson(String json) {
        return json;
    }
}


