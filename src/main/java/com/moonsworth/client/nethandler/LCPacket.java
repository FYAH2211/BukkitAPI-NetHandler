package com.moonsworth.client.nethandler;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.moonsworth.client.nethandler.client.LCPacketClientVoice;
import com.moonsworth.client.nethandler.client.LCPacketVoiceChannelSwitch;
import com.moonsworth.client.nethandler.client.LCPacketVoiceMute;
import com.moonsworth.client.nethandler.server.*;
import com.moonsworth.client.nethandler.shared.LCPacketWaypointAdd;
import com.moonsworth.client.nethandler.shared.LCPacketWaypointRemove;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.IOException;

public abstract class LCPacket {

    private static final BiMap<Class, Integer> REGISTRY = HashBiMap.create();

    static {
        // Client
        addPacket(0, LCPacketClientVoice.class);
        addPacket(1, LCPacketVoiceChannelSwitch.class);
        addPacket(2, LCPacketVoiceMute.class);

        // Server
        addPacket(3, LCPacketCooldown.class);
        addPacket(4, LCPacketHologram.class);
        addPacket(6, LCPacketHologramRemove.class);
        addPacket(5, LCPacketHologramUpdate.class);
        addPacket(7, LCPacketNametagsOverride.class);
        addPacket(8, LCPacketNametagsUpdate.class);
        addPacket(9, LCPacketNotification.class);
        addPacket(10, LCPacketServerRule.class);
        addPacket(11, LCPacketServerUpdate.class);
        addPacket(12, LCPacketStaffModState.class);
        addPacket(13, LCPacketTeammates.class);
        addPacket(14, LCPacketTitle.class);
        addPacket(15, LCPacketUpdateWorld.class);
        addPacket(16, LCPacketVoice.class);
        addPacket(17, LCPacketVoiceChannel.class);
        addPacket(18, LCPacketVoiceChannelRemove.class);
        addPacket(19, LCPacketVoiceChannelUpdate.class);
        addPacket(20, LCPacketWorldBorder.class);
        addPacket(21, LCPacketWorldBorderRemove.class);
        addPacket(22, LCPacketWorldBorderUpdate.class);
        addPacket(25, LCPacketGhost.class);

        // Shared
        addPacket(23, LCPacketWaypointAdd.class);
        addPacket(24, LCPacketWaypointRemove.class);
    }

    private Object attachment;

    public static LCPacket handle(byte[] data) {
        return handle(data, null);
    }

    public static LCPacket handle(byte[] data, Object attachment) {
        ByteBufWrapper wrappedBuffer = new ByteBufWrapper(Unpooled.wrappedBuffer(data));

        int packetId = wrappedBuffer.readVarInt();
        Class packetClass = REGISTRY.inverse().get(packetId);

        if (packetClass != null) {
            try {
                LCPacket packet = (LCPacket) packetClass.newInstance();

                packet.attach(attachment);
                packet.read(wrappedBuffer);

                return packet;
            } catch (IOException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static byte[] getPacketData(LCPacket packet) {
        ByteBufWrapper wrappedBuffer = new ByteBufWrapper(Unpooled.buffer());
        wrappedBuffer.writeVarInt(REGISTRY.get(packet.getClass()));

        try {
            packet.write(wrappedBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wrappedBuffer.buf().array();
    }

    public static ByteBuf getPacketBuf(LCPacket packet) {
        ByteBufWrapper wrappedBuffer = new ByteBufWrapper(Unpooled.buffer());
        wrappedBuffer.writeVarInt(REGISTRY.get(packet.getClass()));

        try {
            packet.write(wrappedBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wrappedBuffer.buf();
    }

    private static void addPacket(int id, Class clazz) {
        if (REGISTRY.containsKey(clazz)) {
            throw new IllegalArgumentException("Duplicate packet class (" + clazz.getSimpleName() + "), already used by " + REGISTRY.get(clazz));
        } else if (REGISTRY.containsValue(id)) {
            throw new IllegalArgumentException("Duplicate packet ID (" + id + "), already used by " + REGISTRY.inverse().get(id).getSimpleName());
        }

        REGISTRY.put(clazz, id);
    }

    public abstract void write(ByteBufWrapper b) throws IOException;

    public abstract void read(ByteBufWrapper b) throws IOException;

    public abstract void process(ILCNetHandler handler);

    protected void writeBlob(ByteBufWrapper b, byte[] bytes) {
        b.buf().writeShort(bytes.length);
        b.buf().writeBytes(bytes);
    }

    protected byte[] readBlob(ByteBufWrapper b) {
        short key = b.buf().readShort();

        if (key < 0) {
            System.out.println("Key was smaller than nothing!  Weird key!");
        } else {
            byte[] blob = new byte[key];
            b.buf().readBytes(blob);
            return blob;
        }

        return null;
    }

    public <T> void attach(T obj) {
        this.attachment = obj;
    }

    @SuppressWarnings("unchecked")
    public <T> T getAttachment() {
        return (T) attachment;
    }
}
