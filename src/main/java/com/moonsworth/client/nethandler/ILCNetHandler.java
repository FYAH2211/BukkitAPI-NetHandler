package com.moonsworth.client.nethandler;

import com.moonsworth.client.nethandler.client.LCPacketEmoteBroadcast;
import com.moonsworth.client.nethandler.shared.LCPacketWaypointAdd;
import com.moonsworth.client.nethandler.shared.LCPacketWaypointRemove;

public interface ILCNetHandler {

    void handleAddWaypoint(LCPacketWaypointAdd packet);

    void handleRemoveWaypoint(LCPacketWaypointRemove packet);

    void handleEmote(LCPacketEmoteBroadcast packet);
}
