package org.podpage.jadfri.request.callbacks;

import com.google.gson.Gson;
import org.eclipse.californium.core.coap.CoAP;
import org.podpage.jadfri.device.CoAPGateway;
import org.podpage.jadfri.device.UnknownDevice;
import org.podpage.jadfri.request.CoAPRequest;
import org.podpage.jadfri.request.Request;

/**
 * Created by podpage on 09.07.2017.
 */
public class CollectDevices extends CoAPRequest<UnknownDevice[]> {

    public CollectDevices(CoAPGateway gateway) {
        super(gateway, new Request(CoAP.Code.GET, "15001"));
    }

    @Override
    protected UnknownDevice[] getObjectFromJson(String json) {
        int[] ids = new Gson().fromJson(json, int[].class);
        UnknownDevice[] unknownDevices = new UnknownDevice[ids.length];
        int i = 0;
        for (int id : ids) {
            unknownDevices[i] = new UnknownDevice(id, getGateway());
            i++;
        }
        return unknownDevices;
    }
}