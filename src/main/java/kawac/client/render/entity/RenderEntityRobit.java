package kawac.client.render.entity;

import kawac.client.model.ModelRobit;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public final class RenderEntityRobit
extends Render{
    private final ModelRobit model = new ModelRobit();
    private final ResourceLocation texture = new ResourceLocation("kawac", "textures/entity/robit.png");

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float dt){
        System.out.println("Hello World");
        this.bindEntityTexture(entity);
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glTranslated(x, y + 2 / 16F, z);
        this.model.render(entity, 0, 0, 0, 0, 0, dt);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity e){
        return this.texture;
    }
}