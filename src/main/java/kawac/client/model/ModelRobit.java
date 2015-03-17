package kawac.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public final class ModelRobit
extends ModelBiped{
    ModelRenderer lega1;
    ModelRenderer lega2;
    ModelRenderer uperTorso;
    ModelRenderer lowerTorso;
    ModelRenderer arm2;
    ModelRenderer arm1;
    ModelRenderer head;

    public ModelRobit(){
        textureWidth = 32;
        textureHeight = 32;

        lega1 = new ModelRenderer(this, 6, 16);
        lega1.addBox(-1F, 0F, -1F, 2, 4, 2);
        lega1.setRotationPoint(-2F, 20F, 0F);
        lega1.setTextureSize(32, 32);
        lega1.mirror = true;
        setRotation(lega1, 0F, 0F, 0F);

        lega2 = new ModelRenderer(this, 16, 16);
        lega2.addBox(-1F, 0F, -1F, 2, 4, 2);
        lega2.setRotationPoint(1F, 20F, 0F);
        lega2.setTextureSize(32, 32);
        lega2.mirror = true;
        setRotation(lega2, 0F, 0F, 0F);

        uperTorso = new ModelRenderer(this, 6, 7);
        uperTorso.addBox(0F, 0F, 0F, 5, 3, 3);
        uperTorso.setRotationPoint(-3F, 16F, -1.5F);
        uperTorso.setTextureSize(32, 32);
        uperTorso.mirror = true;
        setRotation(uperTorso, 0F, 0F, 0F);

        lowerTorso = new ModelRenderer(this, 10, 13);
        lowerTorso.addBox(0F, 0F, 0F, 3, 1, 2);
        lowerTorso.setRotationPoint(-2F, 19F, -1F);
        lowerTorso.setTextureSize(32, 32);
        lowerTorso.mirror = true;
        setRotation(lowerTorso, 0F, 0F, 0F);

        arm2 = new ModelRenderer(this, 24, 5);
        arm2.addBox(0F, 0F, -1F, 1, 5, 2);
        arm2.setRotationPoint(2F, 16F, 0F);
        arm2.setTextureSize(32, 32);
        arm2.mirror = true;
        setRotation(arm2, 0F, 0F, 0F);

        arm1 = new ModelRenderer(this, 0, 5);
        arm1.addBox(-1F, 0F, -1F, 1, 5, 2);
        arm1.setRotationPoint(-3F, 16F, 0F);
        arm1.setTextureSize(32, 32);
        arm1.mirror = true;
        setRotation(arm1, 0F, 0F, 0F);

        head = new ModelRenderer(this, 11, 1);
        head.addBox(-1.466667F, -3F, -2F, 3, 3, 3);
        head.setRotationPoint(-0.5F, 16F, 0F);
        head.setTextureSize(32, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
        super.render(entity, f, f1, f2, f3, f4, f5);
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        lega1.render(f5);
        lega2.render(f5);
        uperTorso.render(f5);
        lowerTorso.render(f5);
        arm2.render(f5);
        arm1.render(f5);
        head.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z){
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}