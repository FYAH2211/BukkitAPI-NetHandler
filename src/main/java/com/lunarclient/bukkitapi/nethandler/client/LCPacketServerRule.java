package com.lunarclient.bukkitapi.nethandler.client;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.client.obj.ServerRule;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.Getter;

import java.io.IOException;

public final class LCPacketServerRule extends LCPacket {

    @Getter
    private ServerRule rule;

    public LCPacketServerRule() {
    }

    public LCPacketServerRule(ServerRule rule, float value) {
        this.rule = rule;
    }

    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeString(JsonStream.serialize(rule));
    }

    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.rule = JsonIterator.parse(buf.readString()).read(ServerRule.class);
    }

    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient) handler).handleServerRule(this);
    }

}