package org.podpage.jadfri.device;

/**
 * Created by podpage on 28.10.2017.
 */
public enum DeviceType {
    REMOTE(0, Remote.class),
    DIMMER(-1, Dimmer.class),
    MOTION_SENSOR(-1, MotionSensor.class),
    LIGHT(2, Light.class),
    LIGHT_DIMMABLE(2, DimmableLight.class),
    LIGHT_COLOR(2, ColorableLight.class),
    UNKNOWN(-1, Device.class);

    private int typeId;
    private Class c;

    DeviceType(int typeId, Class<? extends Device> c) {
        this.typeId = typeId;
        this.c = c;
    }

    public static DeviceType fromDevice(Device device) {
        for (DeviceType deviceType : values()) {
            if (deviceType.getC().equals(device.getClass())) {
                return deviceType;
            }
        }
        return UNKNOWN;
    }

    public int getId() {
        return typeId;
    }

    public Class getC() {
        return c;
    }
}
