package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.client.ILCNetHandlerClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LCPacketTitle extends LCPacket {

    private String type, message;
    private float scale;

    private long displayTimeMs, fadeInTimeMs, fadeOutTimeMs;

    public LCPacketTitle(String type, String message, long displayTimeMs, long fadeInTimeMs, long fadeOutTimeMs) {
        this(type, message, 1F, displayTimeMs, fadeInTimeMs, fadeOutTimeMs);
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(type);
        b.writeString(message);
        b.buf().writeFloat(scale);
        b.buf().writeLong(displayTimeMs);
        b.buf().writeLong(fadeInTimeMs);
        b.buf().writeLong(fadeOutTimeMs);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.type = b.readString();
        this.message = b.readString();
        this.scale = b.buf().readFloat();
        this.displayTimeMs = b.buf().readLong();
        this.fadeInTimeMs = b.buf().readLong();
        this.fadeOutTimeMs = b.buf().readLong();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleTitle(this);
    }
}
