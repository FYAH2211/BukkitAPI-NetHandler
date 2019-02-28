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
public class LCPacketServerUpdate extends LCPacket {

    @Getter private String server;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(server);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.server = b.readString();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleServerUpdate(this);
    }
}
