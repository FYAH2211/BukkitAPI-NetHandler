package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.client.ILCNetHandlerClient;
import lombok.Getter;

import java.io.IOException;
import java.util.UUID;

@Getter
public class LCPacketHologramRemove extends LCPacket {

    private UUID uuid;

    public LCPacketHologramRemove() {
    }

    public LCPacketHologramRemove(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(uuid);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.uuid = b.readUUID();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleRemoveHologram(this);
    }
}
