package com.artur114.bananalib.mc.util;

import com.artur114.bananalib.mc.register.data.PacketRegData;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.List;

public class PacketRegDataList {
    private List<PacketRegData> list = new ArrayList<>();
    private int discriminator;

    public <REQ extends IMessage, REPLY extends IMessage> PacketRegDataList apply(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
        this.list.add(PacketRegData.of(messageHandler, requestMessageType, this.discriminator++, side));
        return this;
    }

    public List<PacketRegData> list() {
        List<PacketRegData> ret = this.list;
        this.list = new ArrayList<>();
        this.discriminator = 0;
        return ret;
    }
}
