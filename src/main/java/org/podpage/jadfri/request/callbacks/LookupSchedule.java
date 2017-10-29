package org.podpage.jadfri.request.callbacks;

import com.google.gson.Gson;
import org.eclipse.californium.core.coap.CoAP;
import org.podpage.jadfri.request.CoAPRequest;
import org.podpage.jadfri.request.Request;
import org.podpage.jadfri.schedules.Schedule;
import org.podpage.jadfri.schedules.UnknownSchedule;

/**
 * Created by podpage on 28.10.2017.
 */
public class LookupSchedule extends CoAPRequest<Schedule> {
    public LookupSchedule(UnknownSchedule schedule) {
        super(schedule.getGateway(), new Request(CoAP.Code.GET, "15010/" + schedule.getId()));
    }

    @Override
    protected Schedule getObjectFromJson(String json) {
        Schedule schedule = new Gson().fromJson(json, Schedule.class);
        schedule.setGateway(getGateway());
        return schedule;
    }
}
