package kawac.common;

import cpw.mods.fml.common.registry.GameRegistry;
import kawac.common.block.BlockComputer;

public final class KawaCBlocks{
    public static final BlockComputer blockComputer = new BlockComputer();

    public static void register(){
        GameRegistry.registerBlock(blockComputer, "blockComputer");
    }
}