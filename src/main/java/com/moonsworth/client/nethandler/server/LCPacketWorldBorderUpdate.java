package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.client.ILCNetHandlerClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LCPacketWorldBorderUpdate extends LCPacket {

    private String id;

    private double minX, minZ, maxX, maxZ;

    private int durationTicks;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(id);
        b.buf().writeDouble(minX);
        b.buf().writeDouble(minZ);
        b.buf().writeDouble(maxX);
        b.buf().writeDouble(maxZ);
        b.buf().writeInt(durationTicks);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.id = b.readString();
        this.minX = b.buf().readDouble();
        this.minZ = b.buf().readDouble();
        this.maxX = b.buf().readDouble();
        this.maxZ = b.buf().readDouble();
        this.durationTicks = b.buf().readInt();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleWorldBorderUpdate(this);
    }
}
