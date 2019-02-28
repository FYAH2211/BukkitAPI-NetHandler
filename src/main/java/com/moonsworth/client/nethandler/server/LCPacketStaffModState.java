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
public class LCPacketStaffModState extends LCPacket {

    private String mod;

    private boolean state;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(mod);
        b.buf().writeBoolean(state);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.mod = b.readString();
        this.state = b.buf().readBoolean();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleStaffModState(this);
    }
}
