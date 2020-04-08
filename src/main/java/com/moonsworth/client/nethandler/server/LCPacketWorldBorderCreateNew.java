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
public class LCPacketWorldBorderCreateNew extends LCPacket {

    private String id;

    private String world;

    private boolean cancelsEntry, cancelsExit;

    private boolean canShrinkExpand;

    private int color = 0xFF3333FF;

    private double minX, minZ, maxX, maxZ;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeOptional(id, b::writeString);
        b.writeString(world);
        b.buf().writeBoolean(cancelsEntry);
        b.buf().writeBoolean(cancelsExit);
        b.buf().writeBoolean(canShrinkExpand);
        b.buf().writeInt(color);
        b.buf().writeDouble(minX);
        b.buf().writeDouble(minZ);
        b.buf().writeDouble(maxX);
        b.buf().writeDouble(maxZ);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.id = b.readOptional(b::readString);
        this.world = b.readString();
        this.cancelsEntry = b.buf().readBoolean();
        this.cancelsExit = b.buf().readBoolean();
        this.canShrinkExpand = b.buf().readBoolean();
        this.color = b.buf().readInt();
        this.minX = b.buf().readDouble();
        this.minZ = b.buf().readDouble();
        this.maxX = b.buf().readDouble();
        this.maxZ = b.buf().readDouble();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleWorldBorderCreateNew(this);
    }
}
