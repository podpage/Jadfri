package org.podpage.jadfri.request.callbacks;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.eclipse.californium.core.coap.CoAP;
import org.podpage.jadfri.device.*;
import org.podpage.jadfri.request.CoAPRequest;
import org.podpage.jadfri.request.Request;

/**
 * Created by podpage on 09.07.2017.
 */
public class LookupDevice extends CoAPRequest<Device> {

    public LookupDevice(UnknownDevice unknownDevice) {
        super(unknownDevice.getGateway(), new Request(CoAP.Code.GET, "15001/" + unknownDevice.getId()));
    }

    @Override
    protected Device getObjectFromJson(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        int typeId = jsonObject.get("5750").getAsInt();

        Device device;
        if (typeId == 0) {
            device = new Gson().fromJson(json, Remote.class);
        } else if (typeId == 2) {
            device = new Gson().fromJson(json, ColorableLight.class);
        } else {
            device = new Gson().fromJson(json, Device.class);
        }
        device.setGateway(getGateway());
        return device;
    }
}