package org.podpage.jadfri.request;

import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.EndpointManager;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.config.DtlsConnectorConfig;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.podpage.jadfri.device.CoAPGateway;

import java.net.InetSocketAddress;
import java.net.URI;

abstract public class CoAPRequest<T> implements GatewayAPI<T> {

    private CoAPGateway gateway;
    private org.podpage.jadfri.request.Request request;

    protected CoAPRequest(CoAPGateway gateway, org.podpage.jadfri.request.Request request) {
        this.gateway = gateway;
        this.request = request;
    }

    public Response doRequest() {
        try {
            Request request = new Request(this.request.getCode());

            request.setURI(new URI("coaps://" + gateway.getInetAddresses()[0].getHostAddress() + ":" + gateway.getPort() + "/" + this.request.getPath()));

            if (this.request.getPayload() != null && (this.request.getCode() == CoAP.Code.POST || this.request.getCode() == CoAP.Code.PUT)) {
                request.setPayload(this.request.getPayload());
            }

            request.getOptions().setContentFormat(MediaTypeRegistry.TEXT_PLAIN);

            //if (request.getScheme().equals(CoAP.COAP_SECURE_URI_SCHEME)) {
            DtlsConnectorConfig.Builder builder = new DtlsConnectorConfig.Builder(new InetSocketAddress(0));

            builder.setPskStore(gateway.getGatewayStore());
            builder.setSupportedCipherSuites(new CipherSuite[]{CipherSuite.TLS_PSK_WITH_AES_128_CCM_8});

            DTLSConnector dtlsconnector = new DTLSConnector(builder.build(), null);

            CoapEndpoint dtlsEndpoint = new CoapEndpoint(dtlsconnector, NetworkConfig.getStandard());

            dtlsEndpoint.start();
            EndpointManager.getEndpointManager().setDefaultSecureEndpoint(dtlsEndpoint);
            //}

            request.send();
            org.eclipse.californium.core.coap.Response response = null;
            try {
                response = request.waitForResponse();
            } catch (InterruptedException e) {
                System.err.println("Failed to receive response: " + e.getMessage());
            }

            dtlsconnector.stop();
            dtlsEndpoint.stop();

            if (response != null) {
                return new Response(response.getPayloadString(), response.getCode().value, response.getRTT());
            } else {
                return new Response(null, -1, -1);
            }
        } catch (Exception e) {
            return new Response(null, -1, -1);
        }
    }

    private Response process(String... params) {
        return doRequest();
    }

    public CoAPGateway getGateway() {
        return gateway;
    }

    protected abstract T getObjectFromJson(String json);

    public void request(GateWayCallback<T> callback, String... params) {
        Response response = process(params);
        if (response.getData() != null) {
            T object = getObjectFromJson(response.getData());
            callback.success(object);
        } else {
            callback.fail(response.getCode());
        }
    }

    public T requestSync(String... params) {
        Response response = process(params);
        if (response.getData() != null) {
            return getObjectFromJson(response.getData());
        } else {
            return null;
        }
    }
}
