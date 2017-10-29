package org.podpage.jadfri;

import org.eclipse.californium.scandium.dtls.pskstore.PskStore;

import java.net.InetSocketAddress;

/**
 * Created by podpage on 09.07.2017.
 */
public class GatewayStore implements PskStore {

private byte[] key;


    public GatewayStore(String key) {
        this.key = key.getBytes();
    }

    public byte[] getKey(String key) {
        return this.key;
    }

    public String getIdentity(InetSocketAddress var1) {
        return "Client_identity";
    }
}
