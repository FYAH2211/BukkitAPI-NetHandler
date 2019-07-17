package com.moonsworth.client.nethandler.client;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.server.ILCNetHandlerServer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class LCPacketStaffModStatus extends LCPacket {

    private final Set<String> enabled = new HashSet<>();

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
