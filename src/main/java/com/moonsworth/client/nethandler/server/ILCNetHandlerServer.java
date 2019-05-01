package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.client.*;

public interface ILCNetHandlerServer extends ILCNetHandler {

    void handleVoice(LCPacketClientVoice packet);

    void handleVoiceChannelSwitch(LCPacketVoiceChannelSwitch packet);

    void handleVoiceMute(LCPacketVoiceMute packet);

    void handlePacketVersionNumber(LCPacketVersionNumber packet);
}
