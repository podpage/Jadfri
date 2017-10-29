package org.podpage.jadfri.request;

public interface GateWayCallback<T> {

    public void success(T result);
    public void fail(int code);
}
