package org.podpage.jadfri.group;

import com.google.gson.annotations.SerializedName;
import org.podpage.jadfri.device.CoAPGateway;
import org.podpage.jadfri.request.callbacks.LookupGroup;

/**
 * Created by podpage on 28.10.2017.
 */
public class UnknownGroup {

    @SerializedName("9003")
    private int id;

    private CoAPGateway gateway;

    public UnknownGroup() {
    }

    public UnknownGroup(int id) {
        this.id = id;
    }

    public UnknownGroup(int id, CoAPGateway gateway) {
        this.id = id;
        this.gateway = gateway;
    }

    public int getId() {
        return id;
    }

    public void setGateway(CoAPGateway gateway) {
        this.gateway = gateway;
    }

    public Group toGroup() {
        return new LookupGroup(this).requestSync();
    }

    public CoAPGateway getGateway() {
        return gateway;
    }
}
