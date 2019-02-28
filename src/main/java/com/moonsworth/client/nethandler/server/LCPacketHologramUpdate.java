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
public class LCPacketHologramUpdate extends LCPacket {

    private UUID uuid;

    private List<String> lines;

    public LCPacketHologramUpdate() {
    }

    public LCPacketHologramUpdate(UUID uuid, List<String> lines) {
        this.uuid = uuid;
        this.lines = lines;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(uuid);

        b.writeVarInt(lines.size());

        for (String s : lines) {
            b.writeString(s);
        }
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.uuid = b.readUUID();

        int linesSize = b.readVarInt();
        this.lines = new ArrayList<>();

        for (int i = 0; i < linesSize; i++) {
            this.lines.add(b.readString());
        }
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleUpdateHologram(this);
    }
}
