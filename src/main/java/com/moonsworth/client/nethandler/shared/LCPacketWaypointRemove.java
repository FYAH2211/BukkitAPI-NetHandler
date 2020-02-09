package com.moonsworth.client.nethandler.shared;

import com.google.common.base.Preconditions;
import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.LCPacket;
import lombok.Getter;

import java.io.IOException;

public final class LCPacketWaypointRemove extends LCPacket {

    @Getter private String name;
    @Getter private String world;

    public LCPacketWaypointRemove() {}

    public LCPacketWaypointRemove(String name, String world) {
        this.name = Preconditions.checkNotNull(name, "name");
        this.world = Preconditions.checkNotNull(world, "world");
    }

    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeString(name);
        buf.writeString(world);
    }

    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.name = buf.readString();
        this.world = buf.readString();
    }

    @Override
    public void process(LCNetHandler handler) {
        handler.handleRemoveWaypoint(this);
    }

}