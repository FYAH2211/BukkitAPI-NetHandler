package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.client.ILCNetHandlerClient;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class LCPacketNametagsOverride extends LCPacket {

    private UUID player;

    private List<String> tags;

    public LCPacketNametagsOverride() {
    }

    public LCPacketNametagsOverride(UUID player, List<String> tags) {
        this.player = player;
        this.tags = tags;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(this.player);

        b.writeOptional(this.tags, (t) -> {
            b.writeVarInt(t.size());
            for (String s : t) {
                b.writeString(s);
            }
        });
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.player = b.readUUID();

        this.tags = b.readOptional(() -> {
            int tagsSize = b.readVarInt();

            List<String> tags = new ArrayList<>();
            for (int i = 0; i < tagsSize; i++) {
                tags.add(b.readString());
            }

            return tags;
        });
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleOverrideNametags(this);
    }
}
