package org.podpage.jadfri.request;

public interface GatewayAPI<T> {

	void request(GateWayCallback<T> callback, String... args);
}
