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
public class LCPacketBossBar extends LCPacket {

    private int action;
    private String text;
    private float health;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeVarInt(action);
        if (action == 0) {
            b.writeString(text);
            b.buf().writeFloat(health);
        }
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.action = b.readVarInt();

        if (action == 0) {
            text = b.readString();
            health = b.buf().readFloat();
        }
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleBossBar(this);
    }

}
