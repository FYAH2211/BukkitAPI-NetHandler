package com.moonsworth.client.nethandler.client;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.server.ILCNetHandlerServer;
import lombok.Getter;

import java.io.IOException;

public class LCPacketClientVoice extends LCPacket {

    @Getter private byte[] data;

    public LCPacketClientVoice() {
    }

    public LCPacketClientVoice(byte[] data) {
        this.data = data;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        writeBlob(b, this.data);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.data = readBlob(b);
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerServer) handler).handleVoice(this);
    }
}
