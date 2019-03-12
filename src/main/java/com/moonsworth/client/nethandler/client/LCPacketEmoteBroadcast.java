package com.moonsworth.client.nethandler.client;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.server.ILCNetHandlerServer;
import lombok.Getter;

import java.io.IOException;
import java.util.UUID;

public class LCPacketEmoteBroadcast extends LCPacket {

    @Getter private UUID uuid; // User doing the emote
    @Getter private int emoteId;

    public LCPacketEmoteBroadcast() {
    }

    public LCPacketEmoteBroadcast(UUID broadcastTo, int emoteId) {
        this.uuid = broadcastTo;
        this.emoteId = emoteId;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(uuid);
        b.buf().writeInt(emoteId);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.uuid = b.readUUID();
        this.emoteId = b.buf().readInt();
    }

    @Override
    public void process(ILCNetHandler handler) {
        handler.handleEmote(this);
    }
}
