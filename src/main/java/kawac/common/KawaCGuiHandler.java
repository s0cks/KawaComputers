package kawac.common;

import cpw.mods.fml.common.network.IGuiHandler;
import kawac.client.gui.GuiComputer;
import kawac.common.tile.TileEntityComputer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public final class KawaCGuiHandler
implements IGuiHandler{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        switch(ID)
        {
            default:{
                return null;
            }
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        switch(ID)
        {
            case 0:{
                return new GuiComputer(((TileEntityComputer) world.getTileEntity(x, y, z)));
            }
            default:{
                return null;
            }
        }
    }
}