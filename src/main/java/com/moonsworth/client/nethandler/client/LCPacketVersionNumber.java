package com.moonsworth.client.nethandler.client;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.server.ILCNetHandlerServer;
import lombok.Getter;

import java.io.IOException;
import java.util.UUID;

public class LCPacketVersionNumber extends LCPacket {

    @Getter private String currentVersionNumber;

    public LCPacketVersionNumber() {
    }

    public LCPacketVersionNumber(String currentVersionNumber) {
        this.currentVersionNumber = currentVersionNumber;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(currentVersionNumber);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.currentVersionNumber = b.readString();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerServer) handler).handlePacketVersionNumber(this);
    }
}
