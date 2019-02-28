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
public class LCPacketHologram extends LCPacket {

    private UUID uuid;

    private double x, y, z;

    private List<String> lines;

    public LCPacketHologram() {
    }

    public LCPacketHologram(UUID uuid, double x, double y, double z, List<String> lines) {
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.z = z;
        this.lines = lines;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(uuid);

        b.buf().writeDouble(x);
        b.buf().writeDouble(y);
        b.buf().writeDouble(z);

        b.writeVarInt(lines.size());

        for (String s : lines) {
            b.writeString(s);
        }
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.uuid = b.readUUID();

        this.x = b.buf().readDouble();
        this.y = b.buf().readDouble();
        this.z = b.buf().readDouble();

        int linesSize = b.readVarInt();
        this.lines = new ArrayList<>();

        for (int i = 0; i < linesSize; i++) {
            this.lines.add(b.readString());
        }
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleAddHologram(this);
    }
}
