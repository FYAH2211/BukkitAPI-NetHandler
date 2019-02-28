package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.ByteBufWrapper;
import com.moonsworth.client.nethandler.ILCNetHandler;
import com.moonsworth.client.nethandler.LCPacket;
import com.moonsworth.client.nethandler.client.ILCNetHandlerClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LCPacketTeammates extends LCPacket {

    private UUID leader;

    private long lastMs;

    private Map<UUID, Map<String, Double>> players;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.buf().writeBoolean(leader != null);
        if (leader != null) {
            b.writeUUID(leader);
        }

        b.buf().writeLong(lastMs);

        b.writeVarInt(this.players.values().size());

        for (Map.Entry<UUID, Map<String, Double>> entry : this.players.entrySet()) {
            b.writeUUID(entry.getKey());
            b.writeVarInt(entry.getValue().values().size());

            for (Map.Entry<String, Double> entry1 : entry.getValue().entrySet()) {
                b.writeString(entry1.getKey());
                b.buf().writeDouble(entry1.getValue());
            }
        }
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        boolean hasLeader = b.buf().readBoolean();
        if (hasLeader) this.leader = b.readUUID();
        this.lastMs = b.buf().readLong();

        int playersSize = b.readVarInt();
        this.players = new HashMap<>();

        for (int i = 0; i < playersSize; ++i) {
            UUID uuid = b.readUUID();

            int posMapSize = b.readVarInt();
            Map<String, Double> posMap = new HashMap<>();

            for (int j = 0; j < posMapSize; ++j) {
                String key = b.readString();
                double val = b.buf().readDouble();

                posMap.put(key, val);
            }

            this.players.put(uuid, posMap);
        }
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient) handler).handleTeammates(this);
    }
}
