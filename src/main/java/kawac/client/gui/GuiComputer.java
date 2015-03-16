package kawac.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kawac.api.utils.RenderUtils;
import kawac.common.tile.TileEntityComputer;
import net.minecraft.client.gui.GuiScreen;

@SideOnly(Side.CLIENT)
public final class GuiComputer
extends GuiScreen{
    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;

    private int guiLeft;
    private int guiTop;

    private final TileEntityComputer computer;

    public GuiComputer(TileEntityComputer computer){
        this.computer = computer;
    }

    @Override
    public void initGui(){
        this.guiLeft = (this.width - WIDTH) / 2;
        this.guiTop = (this.height - HEIGHT) / 2;
    }

    @Override
    public void drawScreen(int x, int y, float f){
        RenderUtils.drawColoredQuad(0x000000, this.guiLeft, this.guiTop, this.zLevel, WIDTH, HEIGHT);
    }
}