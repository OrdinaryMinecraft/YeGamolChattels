/***************************************************************************************************
 * Copyright (c) 2014, Lukas Tenbrink.
 * http://lukas.axxim.net
 **************************************************************************************************/

// Date: 2-1-2014 18:12:28
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package ivorius.yegamolchattels.client.rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJamiensShelf extends ModelBase
{
    //fields
    ModelRenderer base;
    ModelRenderer right;
    ModelRenderer left;
    ModelRenderer back;
    ModelRenderer top;
    ModelRenderer middleplank;
    ModelRenderer rightfront1;
    ModelRenderer rightback1;
    ModelRenderer leftfront1;
    ModelRenderer leftback1;
    ModelRenderer rightfront2;
    ModelRenderer rightback2;
    ModelRenderer leftfront2;
    ModelRenderer leftback2;

    public ModelJamiensShelf()
    {
        textureWidth = 128;
        textureHeight = 64;

        base = new ModelRenderer(this, 0, 0);
        base.addBox(-12F, 0F, -5F, 24, 2, 10);
        base.setRotationPoint(8F, 18F, 0F);
        base.setTextureSize(128, 64);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        right = new ModelRenderer(this, 0, 13);
        right.addBox(-1F, 0F, -5F, 2, 10, 12);
        right.setRotationPoint(-5F, 10F, 0F);
        right.setTextureSize(128, 64);
        right.mirror = true;
        setRotation(right, 0F, 0F, 0F);
        left = new ModelRenderer(this, 29, 13);
        left.addBox(-1F, 0F, -5F, 2, 10, 12);
        left.setRotationPoint(21F, 10F, 0F);
        left.setTextureSize(128, 64);
        left.mirror = true;
        setRotation(left, 0F, 0F, 0F);
        back = new ModelRenderer(this, 0, 36);
        back.addBox(-12F, 0F, -1F, 24, 10, 2);
        back.setRotationPoint(8F, 10F, 6F);
        back.setTextureSize(128, 64);
        back.mirror = true;
        setRotation(back, 0F, 0F, 0F);
        top = new ModelRenderer(this, 0, 49);
        top.addBox(-16F, 0F, -5F, 32, 2, 12);
        top.setRotationPoint(8F, 8F, 0F);
        top.setTextureSize(128, 64);
        top.mirror = true;
        setRotation(top, 0F, 0F, 0F);
        middleplank = new ModelRenderer(this, 58, 13);
        middleplank.addBox(-12F, 0F, -4F, 24, 2, 9);
        middleplank.setRotationPoint(8F, 13F, 0F);
        middleplank.setTextureSize(128, 64);
        middleplank.mirror = true;
        setRotation(middleplank, 0F, 0F, 0F);
        rightfront1 = new ModelRenderer(this, 58, 25);
        rightfront1.addBox(-1F, 0F, -1F, 2, 15, 2);
        rightfront1.setRotationPoint(-6F, 10F, -3F);
        rightfront1.setTextureSize(128, 64);
        rightfront1.mirror = true;
        setRotation(rightfront1, 0F, 0F, 0.0743572F);
        rightback1 = new ModelRenderer(this, 67, 25);
        rightback1.addBox(-1F, 0F, -1F, 2, 15, 2);
        rightback1.setRotationPoint(-6F, 10F, 5F);
        rightback1.setTextureSize(128, 64);
        rightback1.mirror = true;
        setRotation(rightback1, 0F, 0F, 0.0743572F);
        leftfront1 = new ModelRenderer(this, 76, 25);
        leftfront1.addBox(-1F, 0F, -1F, 2, 15, 2);
        leftfront1.setRotationPoint(22F, 10F, -3F);
        leftfront1.setTextureSize(128, 64);
        leftfront1.mirror = true;
        setRotation(leftfront1, 0F, 0F, -0.074351F);
        leftback1 = new ModelRenderer(this, 85, 25);
        leftback1.addBox(-1F, 0F, -1F, 2, 15, 2);
        leftback1.setRotationPoint(22F, 10F, 5F);
        leftback1.setTextureSize(128, 64);
        leftback1.mirror = true;
        setRotation(leftback1, 0F, 0F, -0.074351F);
        rightfront2 = new ModelRenderer(this, 58, 41);
        rightfront2.addBox(1F, 9F, -1F, 2, 6, 2);
        rightfront2.setRotationPoint(-6F, 10F, -3F);
        rightfront2.setTextureSize(128, 64);
        rightfront2.mirror = true;
        setRotation(rightfront2, 0F, 0F, 0.074351F);
        rightback2 = new ModelRenderer(this, 67, 41);
        rightback2.addBox(1F, 9F, -1F, 2, 6, 2);
        rightback2.setRotationPoint(-6F, 10F, 5F);
        rightback2.setTextureSize(128, 64);
        rightback2.mirror = true;
        setRotation(rightback2, 0F, 0F, 0.074351F);
        leftfront2 = new ModelRenderer(this, 76, 41);
        leftfront2.addBox(-3F, 9F, -1F, 2, 6, 2);
        leftfront2.setRotationPoint(22F, 10F, -3F);
        leftfront2.setTextureSize(128, 64);
        leftfront2.mirror = true;
        setRotation(leftfront2, 0F, 0F, -0.074351F);
        leftback2 = new ModelRenderer(this, 85, 41);
        leftback2.addBox(-3F, 9F, -1F, 2, 6, 2);
        leftback2.setRotationPoint(22F, 10F, 5F);
        leftback2.setTextureSize(128, 64);
        leftback2.mirror = true;
        setRotation(leftback2, 0F, 0F, -0.074351F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        base.render(f5);
        right.render(f5);
        left.render(f5);
        back.render(f5);
        top.render(f5);
        middleplank.render(f5);
        rightfront1.render(f5);
        rightback1.render(f5);
        leftfront1.render(f5);
        leftback1.render(f5);
        rightfront2.render(f5);
        rightback2.render(f5);
        leftfront2.render(f5);
        leftback2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}