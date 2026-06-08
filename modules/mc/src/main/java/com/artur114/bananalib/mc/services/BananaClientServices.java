package com.artur114.bananalib.mc.services;

import com.artur114.bananalib.mc.services.client.BlockHighlightService;
import com.artur114.bananalib.mc.services.client.TileSoundsService;

public class BananaClientServices {
    @Service("block-highlight-draw")
    private static final BlockHighlightService BLOCK_HIGHLIGHT = new BlockHighlightService();
    @Service("tile-sounds")
    private static final TileSoundsService TILE_SOUNDS = new TileSoundsService();
}
