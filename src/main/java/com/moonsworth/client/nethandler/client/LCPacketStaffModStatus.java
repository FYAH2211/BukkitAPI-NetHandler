package com.moonsworth.client.nethandler.client;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.server.ILCNetHandlerServer;
import lombok.Getter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LCPacketStaffModStatus extends LCPacket {

    @Getter private Set<String> enabled;

    public LCPacketStaffModStatus(Set<String> enabled) {
        this.enabled = enabled;
    }
    public LCPacketStaffModStatus() {
        this.enabled = new HashSet<>();
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeVarInt(enabled.size());
        for (String s : enabled) {
            b.writeString(s);
        }
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        for (int i = 0; i < b.readVarInt(); i++) {
            enabled.add(b.readString());
        }
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerServer) handler).handleStaffModStatus(this);
    }

}
