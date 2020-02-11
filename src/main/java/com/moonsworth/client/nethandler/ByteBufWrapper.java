package com.moonsworth.client.nethandler;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class ByteBufWrapper {

    private final ByteBuf buf;

    public ByteBufWrapper(ByteBuf buf) {
        this.buf = buf;
    }

    public void writeVarInt(int b) {
        while ((b & -128) != 0) {
            this.buf.writeByte(b & 127 | 128);
            b >>>= 7;
        }

        this.buf.writeByte(b);
    }

    public int readVarInt() {
        int i = 0;
        int chunk = 0;
        byte b;

        do {
            b = this.buf.readByte();
            i |= (b & 127) << chunk++ * 7;

            if (chunk > 5) {
                throw new RuntimeException("VarInt too big");
            }
        } while ((b & 128) == 128);

        return i;
    }

    public <T> T readOptional(Supplier<T> supplier) {
        boolean isPresent = buf.readBoolean();

        if (isPresent) {
            return supplier.get();
        } else {
            return null;
        }
    }

    public void writeString(String s) {
        byte[] arr = s.getBytes(Charsets.UTF_8);

        this.writeVarInt(arr.length);
        this.buf.writeBytes(arr);
    }

    public String readString() {
        int len = readVarInt();

        byte[] buffer = new byte[len];
        buf.readBytes(buffer);

        return new String(buffer, Charsets.UTF_8);
    }

    public void writeUUID(UUID uuid) {
        this.buf.writeLong(uuid.getMostSignificantBits());
        this.buf.writeLong(uuid.getLeastSignificantBits());
    }

    public UUID readUUID() {
        long mostSigBits = this.buf.readLong();
        long leastSigBits = this.buf.readLong();

        return new UUID(mostSigBits, leastSigBits);
    }

    public ByteBuf buf() {
        return buf;
    }

}