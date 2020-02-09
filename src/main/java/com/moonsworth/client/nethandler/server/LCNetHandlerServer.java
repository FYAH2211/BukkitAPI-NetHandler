package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.shared.LCNetHandler;

public interface LCNetHandlerServer extends LCNetHandler {

    void handleStaffModStatus(LCPacketStaffModStatus packet);

}