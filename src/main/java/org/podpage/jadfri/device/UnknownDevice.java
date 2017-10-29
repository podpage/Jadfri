package org.podpage.jadfri.device;

import com.google.gson.annotations.SerializedName;
import org.podpage.jadfri.request.callbacks.LookupDevice;

/**
 * Created by podpage on 09.07.2017.
 */
public class UnknownDevice {

    @SerializedName("9003")
    private int id;

    private CoAPGateway gateway;

    public UnknownDevice() {
    }

    public UnknownDevice(int id, CoAPGateway gateway) {
        this.id = id;
        this.gateway = gateway;
    }

    public int getId() {
        return id;
    }

    public void setGateway(CoAPGateway gateway) {
        this.gateway = gateway;
    }

    public Device toDevice() {
        return new LookupDevice(this).requestSync();
    }

    public CoAPGateway getGateway() {
        return gateway;
    }
}
