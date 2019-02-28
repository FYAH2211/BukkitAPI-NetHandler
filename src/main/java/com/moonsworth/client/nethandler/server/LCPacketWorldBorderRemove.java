package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.client.ILCNetHandlerClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
public class LCPacketWorldBorderRemove extends LCPacket {

    @Getter private String id;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(id);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.id = b.readString();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleWorldBorderRemove(this);
    }
}
