package kawac.common.block;

import kawac.common.tile.TileEntityComputer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public final class BlockComputer
extends BlockContainer{
    public BlockComputer(){
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return new TileEntityComputer();
    }
}