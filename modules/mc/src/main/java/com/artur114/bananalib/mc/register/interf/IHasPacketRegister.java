package com.artur114.bananalib.mc.register.interf;

import com.artur114.bananalib.mc.register.data.PacketRegData;

import java.util.List;

public interface IHasPacketRegister {
    void registerPackets(List<PacketRegData> regData);
}
