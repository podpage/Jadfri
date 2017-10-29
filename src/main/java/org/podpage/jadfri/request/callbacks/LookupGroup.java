package org.podpage.jadfri.request.callbacks;

import com.google.gson.Gson;
import org.eclipse.californium.core.coap.CoAP;
import org.podpage.jadfri.group.Group;
import org.podpage.jadfri.group.UnknownGroup;
import org.podpage.jadfri.request.CoAPRequest;
import org.podpage.jadfri.request.Request;

/**
 * Created by podpage on 28.10.2017.
 */
public class LookupGroup extends CoAPRequest<Group> {
    public LookupGroup(UnknownGroup group) {
        super(group.getGateway(), new Request(CoAP.Code.GET, "15004/" + group.getId()));
    }

    @Override
    protected Group getObjectFromJson(String json) {
        Group group = new Gson().fromJson(json, Group.class);
        group.setGateway(getGateway());
        return group;
    }
}
