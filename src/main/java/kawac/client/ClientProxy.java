package kawac.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import kawac.client.render.entity.RenderEntityRobit;
import kawac.common.ServerProxy;
import kawac.common.entity.EntityRobit;

public final class ClientProxy
extends ServerProxy{
    @Override
    public void initRenders(){
        RenderingRegistry.registerEntityRenderingHandler(EntityRobit.class, new RenderEntityRobit());
    }

    @Override
    public void initHandlers(){

    }
}