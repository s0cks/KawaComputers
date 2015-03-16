package kawac.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kawac.common.KawaComputers;
import kawac.common.tile.TileEntityComputer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public final class BlockComputer
extends BlockContainer{
    @SideOnly(Side.CLIENT)
    private final IIcon[] icons = new IIcon[6];

    public BlockComputer(){
        super(Material.iron);
        this.setCreativeTab(KawaComputers.tab);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz) {
        player.openGui(KawaComputers.instance, 0, world, x, y, z);
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return new TileEntityComputer();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register){
        icons[ForgeDirection.NORTH.ordinal()] = register.registerIcon("kawac:computer/front");
        icons[ForgeDirection.SOUTH.ordinal()] = register.registerIcon("kawac:computer/back");
        icons[ForgeDirection.WEST.ordinal()] = register.registerIcon("kawac:computer/right");
        icons[ForgeDirection.EAST.ordinal()] = register.registerIcon("kawac:computer/left");
        icons[ForgeDirection.DOWN.ordinal()] = register.registerIcon("kawac:computer/bottom");
        icons[ForgeDirection.UP.ordinal()] = register.registerIcon("kawac:computer/top");
    }

    @Override
    public IIcon getIcon(int side, int meta){
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        return icons[dir.ordinal()];
    }
}