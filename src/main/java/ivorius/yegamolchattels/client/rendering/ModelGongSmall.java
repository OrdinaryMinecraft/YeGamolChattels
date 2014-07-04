/***************************************************************************************************
 * Copyright (c) 2014, Lukas Tenbrink.
 * http://lukas.axxim.net
 **************************************************************************************************/

// Date: 15-9-2013 17:44:27
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package ivorius.yegamolchattels.client.rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGongSmall extends ModelBase
{
    //fields
    ModelRenderer gong1;
    ModelRenderer gong2;
    ModelRenderer gong3;
    ModelRenderer nipple;
    ModelRenderer ropeleft;
    ModelRenderer roperight;

    public ModelGongSmall()
    {
        textureWidth = 64;
        textureHeight = 32;

        gong1 = new ModelRenderer(this, 0, 0);
        gong1.addBox(-7F, 0F, -1F, 14, 10, 2);
        gong1.setRotationPoint(0F, 11.5F, 0F);
        gong1.setTextureSize(64, 32);
        gong1.mirror = true;
        setRotation(gong1, 0F, 0F, 0F);
        gong2 = new ModelRenderer(this, 0, 13);
        gong2.addBox(-5F, -2F, -1F, 10, 2, 2);
        gong2.setRotationPoint(0F, 11.5F, 0F);
        gong2.setTextureSize(64, 32);
        gong2.mirror = true;
        setRotation(gong2, 0F, 0F, 0F);
        gong3 = new ModelRenderer(this, 0, 18);
        gong3.addBox(-5F, 10F, -1F, 10, 2, 2);
        gong3.setRotationPoint(0F, 11.5F, 0F);
        gong3.setTextureSize(64, 32);
        gong3.mirror = true;
        setRotation(gong3, 0F, 0F, 0F);
        nipple = new ModelRenderer(this, 0, 23);
        nipple.addBox(-1.5F, 3.5F, -1.5F, 3, 3, 3);
        nipple.setRotationPoint(0F, 11.5F, 0F);
        nipple.setTextureSize(64, 32);
        nipple.mirror = true;
        setRotation(nipple, 0F, 0F, 0F);
        ropeleft = new ModelRenderer(this, 40, 0);
        ropeleft.addBox(-0.5F, 0F, 0F, 1, 5, 0);
        ropeleft.setRotationPoint(7F, 7F, 0F);
        ropeleft.setTextureSize(64, 32);
        ropeleft.mirror = true;
        setRotation(ropeleft, 0F, 0F, 0.3717861F);
        roperight = new ModelRenderer(this, 43, 0);
        roperight.addBox(-0.5F, 0F, 0F, 1, 5, 0);
        roperight.setRotationPoint(-7F, 7F, 0F);
        roperight.setTextureSize(64, 32);
        roperight.mirror = true;
        setRotation(roperight, 0F, 0F, -0.37179F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        gong1.render(f5);
        gong2.render(f5);
        gong3.render(f5);
        nipple.render(f5);
        ropeleft.render(f5);
        roperight.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        gong1.rotateAngleY = par7Entity.rotationYaw;
        gong2.rotateAngleY = par7Entity.rotationYaw;
        gong3.rotateAngleY = par7Entity.rotationYaw;
        nipple.rotateAngleY = par7Entity.rotationYaw;

        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}