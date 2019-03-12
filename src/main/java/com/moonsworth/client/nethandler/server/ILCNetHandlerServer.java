package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.client.LCPacketClientVoice;
import com.moonsworth.client.nethandler.client.LCPacketEmoteBroadcast;
import com.moonsworth.client.nethandler.client.LCPacketVoiceChannelSwitch;
import com.moonsworth.client.nethandler.client.LCPacketVoiceMute;

public interface ILCNetHandlerServer extends ILCNetHandler {

    void handleVoice(LCPacketClientVoice packet);

    void handleVoiceChannelSwitch(LCPacketVoiceChannelSwitch packet);

    void handleVoiceMute(LCPacketVoiceMute packet);

    void handleEmote(LCPacketEmoteBroadcast packet);
}
