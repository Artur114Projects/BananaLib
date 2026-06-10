package com.artur114.bananalib.mc.registry.interf;

import com.artur114.bananalib.mc.registry.data.PacketRegData;

import java.util.List;

public interface IHasNetworkPacket {
    List<PacketRegData> registerPacketsData();
}
