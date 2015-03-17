package kawac.api.utils;

import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public final class RenderUtils{
    private RenderUtils(){}

    public static void drawColoredQuad(int color, int alpha, double x, double y, double z, double width, double height){
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();

        int r = (color >> 16 & 0xFF);
        int g = (color >> 8 & 0xFF);
        int b = (color & 0xFF);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        tess.setColorRGBA(r, g, b, alpha);
        tess.addVertex(x, y + height, z);
        tess.addVertex(x + width, y + height, z);
        tess.addVertex(x + width, y, z);
        tess.addVertex(x, y, z);
        tess.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public static void drawColoredQuad(int color, double x, double y, double z, double w, double h){
        drawColoredQuad(color, 255, x, y, z, w, h);
    }

    public static int blend(int r, int g, int b){
        int rgb = r;
        rgb = (rgb << 8) + g;
        rgb = (rgb << 8) + b;
        return rgb;
    }
}