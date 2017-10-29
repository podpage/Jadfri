package org.podpage.jadfri.schedules;

import com.google.gson.annotations.SerializedName;
import org.podpage.jadfri.device.CoAPGateway;

/**
 * Created by podpage on 28.10.2017.
 */
public class UnknownSchedule {

    @SerializedName("9003")
    private int id;

    private CoAPGateway gateway;

    public UnknownSchedule() {
    }

    public UnknownSchedule(int id, CoAPGateway gateway) {
        this.id = id;
        this.gateway = gateway;
    }

    public int getId() {
        return id;
    }

    public void setGateway(CoAPGateway gateway) {
        this.gateway = gateway;
    }

    public Schedule toGroup() {
        //return new LookupGroup(this, getGateway()).requestSync();
        return null;
    }

    public CoAPGateway getGateway() {
        return gateway;
    }
}
