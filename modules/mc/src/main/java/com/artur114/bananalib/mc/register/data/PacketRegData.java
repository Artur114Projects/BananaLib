package com.artur114.bananalib.mc.register.data;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.relauncher.Side;

public class PacketRegData {
    private final Class<? extends IMessageHandler<?, ?>> messageHandler;
    private final Class<?> requestMessageType;
    private final int discriminator;
    private final Side side;

    public <REQ extends IMessage, REPLY extends IMessage> PacketRegData(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, int discriminator, Side side) {
        this.requestMessageType = requestMessageType;
        this.messageHandler = messageHandler;
        this.discriminator = discriminator;
        this.side = side;
    }

    public Class<? extends IMessageHandler<?, ?>> messageHandler() {
        return this.messageHandler;
    }

    public Class<?> requestMessageType() {
        return this.requestMessageType;
    }

    public int discriminator() {
        return this.discriminator;
    }

    public Side side() {
        return this.side;
    }

    public static <REQ extends IMessage, REPLY extends IMessage> PacketRegData of(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, int discriminator, Side side) {
        return new PacketRegData(messageHandler, requestMessageType, discriminator, side);
    }
}
