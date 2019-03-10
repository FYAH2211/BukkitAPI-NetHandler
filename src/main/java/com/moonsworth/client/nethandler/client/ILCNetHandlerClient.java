package com.moonsworth.client.nethandler.client;

import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.server.*;

public interface ILCNetHandlerClient extends ILCNetHandler {

    void handleGhost(LCPacketGhost packet);

    void handleCooldown(LCPacketCooldown packet);

    void handleNotification(LCPacketNotification packet);

    void handleStaffModState(LCPacketStaffModState packet);

    void handleNametagsUpdate(LCPacketNametagsUpdate packet);

    void handleTeammates(LCPacketTeammates packet);

    void handleOverrideNametags(LCPacketNametagsOverride packet);

    void handleAddHologram(LCPacketHologram packet);

    void handleUpdateHologram(LCPacketHologramUpdate packet);

    void handleRemoveHologram(LCPacketHologramRemove packet);

    void handleTitle(LCPacketTitle packet);

    void handleServerRule(LCPacketServerRule packet);

    void handleVoice(LCPacketVoice packet);

    void handleVoiceChannels(LCPacketVoiceChannel packet);

    void handleVoiceChannelUpdate(LCPacketVoiceChannelUpdate packet);

    void handleVoiceChannelDelete(LCPacketVoiceChannelRemove packet);

    void handleUpdateWorld(LCPacketUpdateWorld packet);

    void handleServerUpdate(LCPacketServerUpdate packet);

    void handleWorldBorder(LCPacketWorldBorder packet);

    void handleWorldBorderUpdate(LCPacketWorldBorderUpdate packet);

    void handleWorldBorderRemove(LCPacketWorldBorderRemove packet);
}
