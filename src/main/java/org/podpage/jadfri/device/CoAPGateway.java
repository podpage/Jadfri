package org.podpage.jadfri.device;

import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.scandium.DTLSConnector;
import org.podpage.jadfri.GatewayStore;
import org.podpage.jadfri.MACUtils;
import org.podpage.jadfri.group.UnknownGroup;
import org.podpage.jadfri.request.callbacks.*;
import org.podpage.jadfri.request.GateWayCallback;
import org.podpage.jadfri.schedules.UnknownSchedule;
import org.xbill.mDNS.Lookup;
import org.xbill.mDNS.ServiceInstance;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by podpage on 09.07.2017.
 */
public class CoAPGateway {

    private String name;
    private InetAddress[] inetAddresses;
    private int port;
    private String version;
    private byte[] mac;
    private GatewayStore gatewayStore;

    public CoAPGateway() {
        removeLogger();
    }

    public CoAPGateway(String address, int port) throws UnknownHostException {
        this.name = "CoAPGateway";
        this.inetAddresses = new InetAddress[]{InetAddress.getByName(address)};
        this.port = port;
        removeLogger();
    }

    public static CoAPGateway[] lookUp() throws IOException {
        Lookup lookup = new Lookup("_coap._udp.local.");
        ServiceInstance[] services = lookup.lookupServices();
        CoAPGateway[] gateways = new CoAPGateway[services.length];
        int i = 0;
        for (ServiceInstance service : services) {
            CoAPGateway gateway = new CoAPGateway();
            gateway.setPort(service.getPort());
            gateway.setInetAddresses(service.getAddresses());
            gateway.setName(service.getName().getInstance());
            gateway.setVersion(service.getNiceText());
            gateway.setMac(MACUtils.fromString(gateway.getName().substring(3)));
            gateways[i] = gateway;
            i++;
        }
        return gateways;
    }

    public static CoAPGateway lookUp(String macAddress) throws IOException {
        Lookup lookup = new Lookup("_coap._udp.local.");
        ServiceInstance[] services = lookup.lookupServices();
        for (ServiceInstance service : services) {
            CoAPGateway gateway = new CoAPGateway();
            gateway.setPort(service.getPort());
            gateway.setInetAddresses(service.getAddresses());
            gateway.setName(service.getName().getInstance());
            gateway.setVersion(service.getNiceText());
            gateway.setMac(MACUtils.fromString(gateway.getName().substring(3)));

            if (MACUtils.toString(gateway.getMac()).equals(macAddress.toUpperCase())) {
                return gateway;
            }
        }
        return null;
    }

    public UnknownDevice[] collectDevices() {
        return new CollectDevices(this).requestSync();
    }

    public void collectDevices(GateWayCallback gateWayCallback) {
        new CollectDevices(this).request(gateWayCallback);
    }

    public void lookupDevice(UnknownDevice unknownDevice, GateWayCallback gateWayCallback) {
        new LookupDevice(unknownDevice).request(gateWayCallback);
    }

    public UnknownGroup[] collectGroups() {
        return new CollectGroups(this).requestSync();
    }

    public void collectGroups(GateWayCallback gateWayCallback) {
        new CollectDevices(this).request(gateWayCallback);
    }

    public void lookupGroup(UnknownGroup group, GateWayCallback gateWayCallback) {
        new LookupGroup(group).request(gateWayCallback);
    }

    public UnknownSchedule[] collectSchedules() {
        return new CollectSchedules(this).requestSync();
    }

    public void collectSchedules(GateWayCallback gateWayCallback) {
        new CollectDevices(this).request(gateWayCallback);
    }

    public void lookupSchedule(UnknownSchedule schedule, GateWayCallback gateWayCallback) {
        new LookupSchedule(schedule).request(gateWayCallback);
    }

    public void login(String key) {
        this.gatewayStore = new GatewayStore(key);
    }

    public GatewayStore getGatewayStore() {
        return gatewayStore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InetAddress[] getInetAddresses() {
        return inetAddresses;
    }

    public void setInetAddresses(InetAddress[] inetAddresses) {
        this.inetAddresses = inetAddresses;
    }

    public byte[] getMac() {
        return mac;
    }

    public void setMac(byte[] mac) {
        this.mac = mac;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    private void removeLogger() {
        removeLogger(NetworkConfig.class);
        removeLogger(CoapEndpoint.class);
        removeLogger(DTLSConnector.class);
    }

    private void removeLogger(Class<?> c) {
        try {
            Field f = c.getDeclaredField("LOGGER");
            f.setAccessible(true);
            Logger logger = (Logger) f.get(c);
            logger.setLevel(Level.OFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CoAPGateway{" +
                "name='" + name + '\'' +
                ", inetAddresses=" + Arrays.toString(inetAddresses) +
                ", port=" + port +
                ", version='" + version + '\'' +
                ", mac=" + Arrays.toString(mac) +
                '}';
    }
}
