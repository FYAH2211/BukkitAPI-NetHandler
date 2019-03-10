package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.client.ILCNetHandlerClient;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class LCPacketGhost extends LCPacket {

    private List<UUID> uuidList;

    public LCPacketGhost() {
    }

    public LCPacketGhost(List<UUID> uuidList) {
        this.uuidList = uuidList;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeVarInt(uuidList.size());

        for (UUID uuid : uuidList) {
            b.writeUUID(uuid);
        }
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        int uuidListSize = b.readVarInt();
        this.uuidList = new ArrayList<>();

        for (int i = 0; i < uuidListSize; i++) {
            this.uuidList.add(b.readUUID());
        }
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleGhost(this);
    }
}
