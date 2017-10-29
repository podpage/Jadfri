package org.podpage.jadfri.request.callbacks;

import com.google.gson.Gson;
import org.eclipse.californium.core.coap.CoAP;
import org.podpage.jadfri.device.CoAPGateway;
import org.podpage.jadfri.request.CoAPRequest;
import org.podpage.jadfri.request.Request;
import org.podpage.jadfri.schedules.UnknownSchedule;

/**
 * Created by podpage on 28.10.2017.
 */
public class CollectSchedules extends CoAPRequest<UnknownSchedule[]> {
    public CollectSchedules(CoAPGateway gateway) {
        super(gateway, new Request(CoAP.Code.GET, "15010"));
    }

    @Override
    protected UnknownSchedule[] getObjectFromJson(String json) {
        int[] ids = new Gson().fromJson(json, int[].class);
        UnknownSchedule[] schedules = new UnknownSchedule[ids.length];
        int i = 0;
        for (int id : ids) {
            schedules[i] = new UnknownSchedule(id, getGateway());
            i++;
        }
        return schedules;
    }
}
