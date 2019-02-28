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
public class LCPacketWaypointRemove extends LCPacket {

    private String name;

    private String world;

    public LCPacketWaypointRemove(String name, String world) {
        this.name = Preconditions.checkNotNull(name, "name");
        this.world = Preconditions.checkNotNull(world, "world");
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(name);
        b.writeString(world);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.name = b.readString();
        this.world = b.readString();
    }

    @Override
    public void process(ILCNetHandler handler) {
        handler.handleRemoveWaypoint(this);
    }
}
