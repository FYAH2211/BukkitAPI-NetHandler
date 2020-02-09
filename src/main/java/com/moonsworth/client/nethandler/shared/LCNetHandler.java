package com.moonsworth.client.nethandler.shared;

public interface LCNetHandler {

    void handleAddWaypoint(LCPacketWaypointAdd packet);
    void handleRemoveWaypoint(LCPacketWaypointRemove packet);
    void handleEmote(LCPacketEmoteBroadcast packet);

}