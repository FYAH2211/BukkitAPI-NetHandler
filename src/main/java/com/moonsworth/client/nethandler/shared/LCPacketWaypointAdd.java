package com.moonsworth.client.nethandler.shared;

import com.google.common.base.Preconditions;
import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@Getter
@NoArgsConstructor
public class LCPacketWaypointAdd extends LCPacket {

    private String name;

    private String world;

    private int color;

    private int x, y, z;

    private boolean forced, visible;

    public LCPacketWaypointAdd(String name, String world, int color, int x, int y, int z, boolean forced, boolean visible) {
        this.name = Preconditions.checkNotNull(name, "name");
        this.world = Preconditions.checkNotNull(world, "world");
        this.color = color;
        this.x = x;
        this.y = y;
        this.z = z;
        this.forced = forced;
        this.visible = visible;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(name);
        b.writeString(world);
        b.buf().writeInt(color);
        b.buf().writeInt(x);
        b.buf().writeInt(y);
        b.buf().writeInt(z);
        b.buf().writeBoolean(forced);
        b.buf().writeBoolean(visible);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.name = b.readString();
        this.world = b.readString();
        this.color = b.buf().readInt();
        this.x = b.buf().readInt();
        this.y = b.buf().readInt();
        this.z = b.buf().readInt();
        this.forced = b.buf().readBoolean();
        this.visible = b.buf().readBoolean();
    }

    @Override
    public void process(ILCNetHandler handler) {
        handler.handleAddWaypoint(this);
    }
}
