package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.client.ILCNetHandlerClient;
import lombok.Getter;

import java.io.IOException;

@Getter
public class LCPacketCooldown extends LCPacket {

    private String message;

    private long durationMs;

    private int iconId;

    public LCPacketCooldown() {
    }

    public LCPacketCooldown(String message, long durationMs, int iconId) {
        this.message = message;
        this.durationMs = durationMs;
        this.iconId = iconId;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(message);
        b.buf().writeLong(durationMs);
        b.buf().writeInt(iconId);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        message = b.readString();
        durationMs = b.buf().readLong();
        iconId = b.buf().readInt();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleCooldown(this);
    }
}
