package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.client.ILCNetHandlerClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class LCPacketVoiceChannelUpdate extends LCPacket {

    /**
     * 0 for adding a player
     * 1 for removing a player
     * 2 for marking a player as listening
     * 3 for marking a player as deafened
     */
    @Getter public int status;

    @Getter private UUID channelUuid;

    @Getter private UUID uuid;

    @Getter private String name;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeVarInt(status);
        b.writeUUID(channelUuid);
        b.writeUUID(uuid);
        b.writeString(name);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.status = b.readVarInt();
        this.channelUuid = b.readUUID();
        this.uuid = b.readUUID();
        this.name = b.readString();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleVoiceChannelUpdate(this);
    }
}
