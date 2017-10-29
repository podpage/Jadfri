package org.podpage.jadfri.request;


import org.eclipse.californium.core.coap.CoAP;

/**
 * Created by podpage on 11.07.2017.
 */
public class Request {

    private CoAP.Code code;
    private String path;
    private String payload;

    public Request(CoAP.Code code, String path) {
        this.code = code;
        this.path = path;
    }

    public Request(CoAP.Code code, String path, String payload) {
        this.code = code;
        this.path = path;
        this.payload = payload;
    }

    public CoAP.Code getCode() {
        return code;
    }

    public void setCode(CoAP.Code code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
