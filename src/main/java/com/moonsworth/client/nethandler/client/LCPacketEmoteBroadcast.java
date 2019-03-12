package com.moonsworth.client.nethandler.client;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.server.ILCNetHandlerServer;
import lombok.Getter;

import java.io.IOException;
import java.util.UUID;

public class LCPacketEmoteBroadcast extends LCPacket {

    @Getter private UUID broadcastTo;
    @Getter private int emoteId;

    public LCPacketEmoteBroadcast() {
    }

    public LCPacketEmoteBroadcast(UUID broadcastTo, int emoteId) {
        this.broadcastTo = broadcastTo;
        this.emoteId = emoteId;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.buf().writeInt(emoteId);
        b.writeUUID(broadcastTo);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.broadcastTo = b.readUUID();
        this.emoteId = b.buf().readInt();
    }

    @Override
    public void process(ILCNetHandler handler) {
        handler.handleEmote(this);
    }
}
