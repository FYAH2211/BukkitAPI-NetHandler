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

    private List<UUID> addGhostList;
    private List<UUID> removeGhostList;

    public LCPacketGhost() {
    }

    public LCPacketGhost(List<UUID> uuidList, List<UUID> removeGhostList) {
        this.addGhostList = uuidList;
        this.removeGhostList = removeGhostList;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeVarInt(addGhostList.size());

        for (UUID uuid : addGhostList) {
            b.writeUUID(uuid);
        }
        b.writeVarInt(removeGhostList.size());

        for (UUID uuid : removeGhostList) {
            b.writeUUID(uuid);
        }
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        int addListSize = b.readVarInt();
        this.addGhostList = new ArrayList<>();

        for (int i = 0; i < addListSize; i++) {
            this.addGhostList.add(b.readUUID());
        }
        int removeListSize = b.readVarInt();
        this.removeGhostList = new ArrayList<>();

        for (int i = 0; i < removeListSize; i++) {
            this.removeGhostList.add(b.readUUID());
        }
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleGhost(this);
    }
}
