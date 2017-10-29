package org.podpage.jadfri.request.callbacks;

import com.google.gson.Gson;
import org.eclipse.californium.core.coap.CoAP;
import org.podpage.jadfri.device.CoAPGateway;
import org.podpage.jadfri.group.UnknownGroup;
import org.podpage.jadfri.request.CoAPRequest;
import org.podpage.jadfri.request.Request;

/**
 * Created by podpage on 28.10.2017.
 */
public class CollectGroups extends CoAPRequest<UnknownGroup[]> {
    public CollectGroups(CoAPGateway gateway) {
        super(gateway, new Request(CoAP.Code.GET, "15004"));
    }

    @Override
    protected UnknownGroup[] getObjectFromJson(String json) {
        int[] ids = new Gson().fromJson(json, int[].class);
        UnknownGroup[] groups = new UnknownGroup[ids.length];
        int i = 0;
        for (int id : ids) {
            groups[i] = new UnknownGroup(id, getGateway());
            i++;
        }
        return groups;
    }
}
